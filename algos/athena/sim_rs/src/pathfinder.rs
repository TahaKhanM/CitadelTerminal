//! Bit-exact Rust port of engine.jar `PathFinder` + `CoordQueue`.
//!
//! Engine sources:
//!   research/engine_decompiled/sources/com/c1games/terminal/game/systems/
//!     map/path/PathFinder.java (491 LOC, CFR 0.152 output)
//!   research/engine_decompiled/sources/com/c1games/terminal/game/systems/
//!     map/path/CoordQueue.java (76 LOC)
//!
//! See `research/engine_decompiled/PATHFINDER_SPEC.md` for the full spec with
//! line citations; this file mirrors `algos/athena/sim/pathfinder.py`
//! line-for-line semantically (the Python port is the byte-identical
//! reference; cross_validate.py enforces Python ↔ Rust equality).
//!
//! Ownership: 4 `PathFinder` instances per game — one per target edge
//! (TR=0, TL=1, BL=2, BR=3). Each maintains persistent state across frames:
//! walls added via `put(x,y)`, removed via `remove(x,y)`, validation runs
//! lazily inside `get_step`.

use indexmap::IndexMap;

// ---------------------------------------------------------------- constants
// Status byte values (PathFinder.java:10-12).
pub const STATUS_INVALID: u8 = 0;
pub const STATUS_OPEN: u8 = 1;
pub const STATUS_WALL: u8 = 2;

// Direction byte values (PathFinder.java:13-17).
pub const NONE: u8 = 0;
pub const LEFT: u8 = 1;
pub const RIGHT: u8 = 2;
pub const DOWN: u8 = 3;
pub const UP: u8 = 4;

// getStep() last-move constants (PathFinder.java:351-353).
pub const SPAWNED: u8 = 0;
pub const HORIZONTAL: u8 = 1;
pub const VERTICAL: u8 = 2;

// Neighbor offsets (PathFinder.java:19-21). ORDER MATTERS for filter #1 tiebreak.
pub const NEIGHBOR_X: [i32; 4] = [1, -1, 0, 0];
pub const NEIGHBOR_Y: [i32; 4] = [0, 0, 1, -1];
const PARENT_DIRECTION: [u8; 4] = [LEFT, RIGHT, DOWN, UP];

const I32_MAX: i32 = i32::MAX;
const I32_MIN: i32 = i32::MIN;

// ------------------------------------------------------------- CoordQueue
/// Ring-buffer port of engine's `CoordQueue` (CoordQueue.java:1-76).
/// `push` appends, `next` pops FIFO while populating `curr_x`/`curr_y`.
#[derive(Debug, Clone)]
pub struct CoordQueue {
    data: Vec<i32>,
    start: usize,
    end: usize,
    size: usize,
    pub curr_x: i32,
    pub curr_y: i32,
}

impl CoordQueue {
    /// CoordQueue.java:6-12 — engine uses cap=200; we use 256 (power of two)
    /// so index arithmetic compiles to AND-with-mask instead of modulo. The
    /// capacity threshold `(size+1)*2 >= cap` matches the engine, just with
    /// a different numeric cap. Growth is still doubling, so all later caps
    /// stay powers of two as well.
    pub fn new() -> Self {
        Self {
            data: vec![0i32; 256],
            start: 0,
            end: 0,
            size: 0,
            curr_x: -559_038_737,
            curr_y: -558_907_665,
        }
    }

    /// CoordQueue.java:14-28 — doubling ring buffer; grow when (size+1)*2 >= cap.
    #[inline]
    pub fn push(&mut self, x: i32, y: i32) {
        let cap = self.data.len();
        if (self.size + 1) * 2 >= cap {
            let new_cap = cap * 2;
            let mut new_data = vec![0i32; new_cap];
            for i in 0..(self.size * 2) {
                new_data[i] = self.data[(i + self.start) & (cap - 1)];
            }
            self.data = new_data;
            self.start = 0;
            self.end = self.size * 2;
            let cap = self.data.len();
            // Same as below — power-of-two mask.
            self.data[self.end] = x;
            self.data[self.end + 1] = y;
            self.end = (self.end + 2) & (cap - 1);
            self.size += 1;
            return;
        }
        let mask = cap - 1;
        self.data[self.end] = x;
        self.data[self.end + 1] = y;
        self.end = (self.end + 2) & mask;
        self.size += 1;
    }

    /// CoordQueue.java:30-39 — FIFO pop; `true` iff something was popped.
    #[inline]
    pub fn next(&mut self) -> bool {
        if self.start != self.end {
            self.curr_x = self.data[self.start];
            self.curr_y = self.data[self.start + 1];
            let mask = self.data.len() - 1;
            self.start = (self.start + 2) & mask;
            self.size -= 1;
            true
        } else {
            false
        }
    }

    /// CoordQueue.java:41-45 — reset pointers; retains backing storage.
    pub fn drain(&mut self) {
        self.start = 0;
        self.end = 0;
        self.size = 0;
    }

    /// CoordQueue.java:47-49.
    #[inline]
    pub fn size(&self) -> usize {
        self.size
    }
}

impl Default for CoordQueue {
    fn default() -> Self {
        Self::new()
    }
}

// ------------------------------------------------------------ PathFinder
/// Exact port of `PathFinder.java`. One instance per target edge.
#[derive(Debug, Clone)]
pub struct PathFinder {
    // Geometry.
    pub dimension: i32,
    #[allow(dead_code)]
    size: usize,
    pub direction: (i32, i32),
    perfect_list: Vec<(i32, i32)>,

    // State arrays of length `size` (PathFinder.java:49-55).
    pub status: Vec<u8>,
    pub parent_direction: Vec<i16>,
    pub pathlength: Vec<i16>,
    pub idealness_arr: Vec<i32>, // `idealness` name reserved by the method.
    visited: Vec<i32>,
    explored: Vec<bool>,
    perfect_lookup: Vec<bool>,

    // Scratch queues (PathFinder.java:56-61).
    current_queue: CoordQueue,
    frontier_queue: CoordQueue,
    start_search_at: CoordQueue,
    requires_validation: CoordQueue,
    possible_steps: CoordQueue,

    // Idealness-search scalars (PathFinder.java:37-38, 248-249).
    searched_best_idealness: i32,
    searched_curr_pathlength: i32,

    // Global invalidation flag (PathFinder.java:39).
    pub board_invalid: bool,
}

impl PathFinder {
    /// Constructor — PathFinder.java:43-80.
    ///
    /// * Pre-marks the 4 triangular corners of the diamond as WALL so
    ///   off-diamond tiles behave identically to real walls (lines 62-70).
    /// * Flags every `perfect` (edge) tile OPEN with pathlength=0,
    ///   idealness=MAX_VALUE, and sets `perfectLookup[]` (lines 71-76).
    /// * Marks each entry in `walls` as WALL (lines 77-79).
    pub fn new(
        dimension: i32,
        direction: (i32, i32),
        walls: &[(i32, i32)],
        perfect: &[(i32, i32)],
    ) -> Self {
        let size = (dimension * dimension) as usize;
        let mut pf = Self {
            dimension,
            size,
            direction,
            perfect_list: perfect.to_vec(),
            status: vec![STATUS_INVALID; size],
            parent_direction: vec![0i16; size],
            pathlength: vec![0i16; size],
            // PathFinder.java:330-334 — zero(int[]) fills MAX_VALUE.
            idealness_arr: vec![I32_MAX; size],
            visited: vec![I32_MAX; size],
            explored: vec![false; size],
            perfect_lookup: vec![false; size],
            current_queue: CoordQueue::new(),
            frontier_queue: CoordQueue::new(),
            start_search_at: CoordQueue::new(),
            requires_validation: CoordQueue::new(),
            possible_steps: CoordQueue::new(),
            searched_best_idealness: 0,
            searched_curr_pathlength: 0,
            board_invalid: true,
        };

        // Triangular corners (PathFinder.java:62-70).
        let half = dimension / 2;
        for i in 0..half {
            for j in 0..half {
                if i + j >= half - 1 {
                    continue;
                }
                let idx1 = pf.index(i, j);
                let idx2 = pf.index(dimension - i - 1, j);
                let idx3 = pf.index(dimension - i - 1, dimension - j - 1);
                let idx4 = pf.index(i, dimension - j - 1);
                pf.status[idx1] = STATUS_WALL;
                pf.status[idx2] = STATUS_WALL;
                pf.status[idx3] = STATUS_WALL;
                pf.status[idx4] = STATUS_WALL;
            }
        }

        // Perfect (edge) tiles (PathFinder.java:71-76).
        for &(cx, cy) in &pf.perfect_list.clone() {
            let idx = pf.index(cx, cy);
            pf.perfect_lookup[idx] = true;
            pf.status[idx] = STATUS_OPEN;
            pf.pathlength[idx] = 0;
            pf.idealness_arr[idx] = I32_MAX;
        }

        // Initial walls (PathFinder.java:77-79).
        for &(wx, wy) in walls {
            let idx = pf.index(wx, wy);
            pf.status[idx] = STATUS_WALL;
        }

        pf
    }

    // -------------------------------------------------------------- helpers

    /// PathFinder.java:311-316. OOB coordinates clamp to index 0 (origin).
    #[inline]
    pub fn index(&self, x: i32, y: i32) -> usize {
        if x < 0 || y < 0 || y >= self.dimension || x >= self.dimension {
            return 0;
        }
        (y * self.dimension + x) as usize
    }

    /// PathFinder.java:324-328 — fill bool[] with false.
    fn zero_bool(arr: &mut [bool]) {
        for b in arr.iter_mut() {
            *b = false;
        }
    }

    /// PathFinder.java:330-334 — fill int[] with Integer.MAX_VALUE.
    fn zero_int(arr: &mut [i32]) {
        for v in arr.iter_mut() {
            *v = I32_MAX;
        }
    }

    /// PathFinder.java:336-340 — fill short[] with 0.
    fn zero_short(arr: &mut [i16]) {
        for v in arr.iter_mut() {
            *v = 0;
        }
    }

    /// PathFinder.java:318-322.
    #[inline]
    fn swap_queues(&mut self) {
        std::mem::swap(&mut self.current_queue, &mut self.frontier_queue);
    }

    // --------------------------------------------------------- idealness maths

    /// PathFinder.java:198-207.
    fn idealness(&self, x: i32, y: i32) -> i32 {
        let idx = self.index(x, y);
        if self.perfect_lookup[idx] {
            return I32_MAX;
        }
        let x_id = if self.direction.0 == 1 { x } else { self.dimension - x - 1 };
        let y_id = if self.direction.1 == 1 { y } else { self.dimension - y - 1 };
        // assert((x_id & 0xFFFF0000) == 0)
        (y_id << 16) | x_id
    }

    /// PathFinder.java:209-221.
    fn extract_idealness_coords(&self, idealness: i32, into: &mut CoordQueue) {
        if idealness == I32_MAX {
            for &(cx, cy) in &self.perfect_list {
                into.push(cx, cy);
            }
        } else {
            let x_id = idealness & 0xFFFF;
            let y_id = ((idealness as u32 & 0xFFFF_0000) >> 16) as i32;
            let x = if self.direction.0 == 1 { x_id } else { self.dimension - x_id };
            let y = if self.direction.1 == 1 { y_id } else { self.dimension - y_id };
            into.push(x, y);
        }
    }

    /// PathFinder.java:223-241.
    fn get_min_distance_from_idealness(&self, idealness: i32, x: i32, y: i32) -> i32 {
        let mut targets = CoordQueue::new();
        self.extract_idealness_coords(idealness, &mut targets);
        let mut best_distance = I32_MAX;
        let num_targets = targets.size();
        for _ in 0..num_targets {
            targets.next();
            let distance = (targets.curr_x - x).abs() + (targets.curr_y - y).abs();
            if distance < best_distance {
                best_distance = distance;
            }
            targets.push(targets.curr_x, targets.curr_y);
        }
        best_distance
    }

    // ---------------------------------------------------- parent-direction mask

    /// PathFinder.java:451-457.
    fn add_direction_to_mask(current_mask: i16, new_direction: u8) -> i16 {
        let mut i = 0i32;
        let mut mask = current_mask as u32;
        while i < 4 && (mask & (7u32 << (i * 3))) != 0 {
            i += 1;
        }
        // assert i < 4
        mask |= (new_direction as u32) << (i * 3);
        mask as i16
    }

    /// PathFinder.java:459-461.
    #[inline]
    fn get_direction_from_mask(mask: i16, index: i32) -> u8 {
        let m = mask as u32;
        ((m & (7u32 << (index * 3))) >> (index * 3)) as u8
    }

    /// PathFinder.java:463-469.
    fn is_direction_in_map(mask: i16, direction: u8) -> bool {
        for i in 0..4 {
            if Self::get_direction_from_mask(mask, i) == direction {
                return true;
            }
        }
        false
    }

    // ------------------------------------------------------ public: mutate walls

    /// PathFinder.java:82-119 — wall added. BFS outward invalidation.
    pub fn put(&mut self, target_x: i32, target_y: i32) {
        let idx = self.index(target_x, target_y);
        if self.status[idx] == STATUS_WALL {
            return;
        }
        self.status[idx] = STATUS_WALL;
        for i in 0..4 {
            let nx = NEIGHBOR_X[i] + target_x;
            let ny = NEIGHBOR_Y[i] + target_y;
            if self.status[self.index(nx, ny)] != STATUS_OPEN {
                continue;
            }
            self.current_queue.push(nx, ny);
        }
        Self::zero_bool(&mut self.explored);
        while self.current_queue.next() {
            let cell_x = self.current_queue.curr_x;
            let cell_y = self.current_queue.curr_y;
            let cell_idx = self.index(cell_x, cell_y);
            // Edge tile — skip (pathlength == 0 AND OPEN). PathFinder.java:100.
            if self.status[cell_idx] == STATUS_OPEN && self.pathlength[cell_idx] == 0 {
                continue;
            }
            // Is the cell's pathlength still achievable via any neighbor?
            let cell_pl = self.pathlength[cell_idx];
            let mut found = false;
            for i in 0..4 {
                let nx = NEIGHBOR_X[i] + cell_x;
                let ny = NEIGHBOR_Y[i] + cell_y;
                let n_idx = self.index(nx, ny);
                if self.status[n_idx] != STATUS_OPEN || self.pathlength[n_idx] != cell_pl - 1 {
                    continue;
                }
                found = true;
                break;
            }
            if found {
                continue;
            }
            // Invalidate; push OPEN neighbors (with explored de-dupe).
            self.status[cell_idx] = STATUS_INVALID;
            for i in 0..4 {
                let nx = NEIGHBOR_X[i] + cell_x;
                let ny = NEIGHBOR_Y[i] + cell_y;
                let n_idx = self.index(nx, ny);
                if self.status[n_idx] != STATUS_OPEN || self.explored[n_idx] {
                    continue;
                }
                self.explored[n_idx] = true;
                self.current_queue.push(nx, ny);
            }
        }
    }

    /// PathFinder.java:121-141 — wall removed. Global invalidation sweep on
    /// first remove since last validate().
    pub fn remove(&mut self, target_x: i32, target_y: i32) {
        let idx = self.index(target_x, target_y);
        if self.status[idx] != STATUS_WALL {
            return;
        }
        if self.idealness(target_x, target_y) == I32_MAX {
            self.status[idx] = STATUS_OPEN;
            self.pathlength[idx] = 0;
            self.idealness_arr[idx] = I32_MAX;
        } else {
            self.status[idx] = STATUS_INVALID;
        }
        if !self.board_invalid {
            self.board_invalid = true;
            for i in 0..self.dimension {
                for j in 0..self.dimension {
                    let pos = self.index(i, j);
                    if self.status[pos] != STATUS_OPEN {
                        continue;
                    }
                    if self.idealness(i, j) == I32_MAX {
                        continue;
                    }
                    self.status[pos] = STATUS_INVALID;
                }
            }
        }
    }

    // ----------------------------------------------------------- validation BFS

    /// PathFinder.java:191-196.
    #[inline]
    fn handle_parent(&mut self, x: i32, y: i32) {
        let idx = self.index(x, y);
        if !self.explored[idx] {
            self.explored[idx] = true;
            self.frontier_queue.push(x, y);
        }
    }

    /// PathFinder.java:243-309.
    ///
    /// BFS outward from `(target_x, target_y)` across all non-WALL cells.
    /// Populates `parent_direction[]`, `start_search_at`,
    /// `searched_best_idealness`, and `searched_curr_pathlength` so that
    /// `validate()` can walk back from anchor(s) to the target.
    fn idealness_search(&mut self, target_x: i32, target_y: i32) {
        Self::zero_bool(&mut self.explored);
        Self::zero_int(&mut self.visited);
        Self::zero_short(&mut self.parent_direction);
        self.start_search_at.drain();
        self.searched_best_idealness = I32_MIN;
        self.searched_curr_pathlength = 0;
        self.current_queue.push(target_x, target_y);
        let t_idx = self.index(target_x, target_y);
        self.explored[t_idx] = true;
        let mut distance_explored: i32 = 0;
        let mut current_best: i32 = I32_MAX;
        let mut open_found = false;
        self.visited[t_idx] = 0;

        while self.current_queue.size() != 0 {
            while self.current_queue.next() {
                let cell_x = self.current_queue.curr_x;
                let cell_y = self.current_queue.curr_y;
                let cell_idx = self.index(cell_x, cell_y);
                let current_idealness = self.idealness_arr[cell_idx];

                // PathFinder.java:267 — the big prune condition.
                if open_found {
                    if self.status[cell_idx] != STATUS_INVALID {
                        // OPEN path prune: strictly > currentBest.
                        if (self.pathlength[cell_idx] as i32) + distance_explored > current_best {
                            continue;
                        }
                    } else {
                        // INVALID path prune: >= currentBest (non-strict).
                        let min_dist = self.get_min_distance_from_idealness(
                            self.searched_best_idealness,
                            cell_x,
                            cell_y,
                        );
                        if min_dist + distance_explored >= current_best {
                            continue;
                        }
                    }
                }

                if self.status[cell_idx] == STATUS_OPEN {
                    let current_pathlength = self.pathlength[cell_idx] as i32;
                    if open_found && distance_explored + current_pathlength >= current_best {
                        // block6: tie (==) adds to start_search_at; > does nothing.
                        if distance_explored + current_pathlength == current_best {
                            self.start_search_at.push(cell_x, cell_y);
                        }
                    } else {
                        // block7: strictly better (or first OPEN found).
                        open_found = true;
                        self.searched_best_idealness = current_idealness;
                        self.start_search_at.drain();
                        self.start_search_at.push(cell_x, cell_y);
                        self.searched_curr_pathlength = current_pathlength;
                        current_best = current_pathlength + distance_explored;
                    }
                    // OPEN cells never expand neighbors; skip idealness tiebreak.
                    continue;
                }

                // status == INVALID: neighbor expansion (block5).
                for i in 0..4 {
                    let nx = NEIGHBOR_X[i] + cell_x;
                    let ny = NEIGHBOR_Y[i] + cell_y;
                    let n_idx = self.index(nx, ny);
                    if distance_explored < self.visited[n_idx] && self.status[n_idx] != STATUS_WALL
                    {
                        self.parent_direction[n_idx] = Self::add_direction_to_mask(
                            self.parent_direction[n_idx],
                            PARENT_DIRECTION[i],
                        );
                        self.visited[n_idx] = distance_explored + 1;
                    }
                    if self.explored[n_idx] || self.status[n_idx] == STATUS_WALL {
                        continue;
                    }
                    self.frontier_queue.push(nx, ny);
                    self.explored[n_idx] = true;
                }

                if open_found {
                    continue;
                }

                // Idealness tiebreak for INVALID cell (no OPEN found yet).
                let cell_idealness = self.idealness(cell_x, cell_y);
                if cell_idealness > self.searched_best_idealness {
                    self.start_search_at.drain();
                    self.start_search_at.push(cell_x, cell_y);
                    self.searched_best_idealness = cell_idealness;
                    continue;
                }
                if cell_idealness != self.searched_best_idealness {
                    continue;
                }
                self.start_search_at.push(cell_x, cell_y);
            }
            distance_explored += 1;
            self.swap_queues();
        }
    }

    /// PathFinder.java:143-189. Drains the `requires_validation` queue
    /// in-place, re-validating any INVALID tiles along with the seed cells
    /// produced by `idealness_search`. Takes the queue by swap (not move)
    /// so `get_step` can reuse the single scratch allocation.
    #[inline]
    fn validate_rv(&mut self) {
        // Pop one target at a time; `requires_validation` is the queue
        // populated by `get_step`. Invariant: caller has already populated
        // `requires_validation` with the tiles that might be STATUS_INVALID.
        while self.requires_validation.next() {
            let target_x = self.requires_validation.curr_x;
            let target_y = self.requires_validation.curr_y;
            let t_idx = self.index(target_x, target_y);
            if self.status[t_idx] == STATUS_OPEN {
                continue;
            }
            self.board_invalid = false;
            self.idealness_search(target_x, target_y);
            let mut current_path_length: i32 = self.searched_curr_pathlength;
            self.current_queue.drain();
            self.frontier_queue.drain();
            while self.start_search_at.next() {
                self.current_queue
                    .push(self.start_search_at.curr_x, self.start_search_at.curr_y);
            }
            Self::zero_bool(&mut self.explored);

            // Outer BFS across layers.
            while self.current_queue.size() != 0 {
                let mut break_outer = false;
                while self.current_queue.next() {
                    let cell_x = self.current_queue.curr_x;
                    let cell_y = self.current_queue.curr_y;
                    let cell_idx = self.index(cell_x, cell_y);
                    if self.status[cell_idx] == STATUS_OPEN
                        && (self.pathlength[cell_idx] as i32) < current_path_length
                    {
                        continue;
                    }
                    self.idealness_arr[cell_idx] = self.searched_best_idealness;
                    self.status[cell_idx] = STATUS_OPEN;
                    self.pathlength[cell_idx] = current_path_length as i16;
                    if cell_idx == t_idx {
                        self.current_queue.drain();
                        self.frontier_queue.drain();
                        break_outer = true;
                        break;
                    }
                    let mask = self.parent_direction[cell_idx];
                    if Self::is_direction_in_map(mask, LEFT) {
                        self.handle_parent(cell_x - 1, cell_y);
                    }
                    if Self::is_direction_in_map(mask, UP) {
                        self.handle_parent(cell_x, cell_y + 1);
                    }
                    if Self::is_direction_in_map(mask, RIGHT) {
                        self.handle_parent(cell_x + 1, cell_y);
                    }
                    if Self::is_direction_in_map(mask, DOWN) {
                        self.handle_parent(cell_x, cell_y - 1);
                    }
                }
                if break_outer {
                    break;
                }
                current_path_length += 1;
                self.swap_queues();
            }
        }
    }

    // ----------------------------------------------------- public: query + step

    /// PathFinder.java:345-412.
    ///
    /// 6-step filter pipeline per `PATHFINDER_SPEC.md` §6. Uses a stack-
    /// allocated 5-slot `InlineStepList` instead of the CoordQueue because
    /// possible_steps has at most 5 entries (self + 4 neighbors) — the ring
    /// buffer has modulo and bounds-check overhead we pay for no reason.
    #[inline]
    pub fn get_step(&mut self, unit_x: i32, unit_y: i32, prev_direction: u8) -> (i32, i32) {
        // Build possible_steps inline — at most 5 entries (self + 4 neighbors).
        let mut ps: [(i32, i32); 5] = [(unit_x, unit_y); 5];
        let mut ps_len: usize = 1;
        self.requires_validation.drain();
        self.requires_validation.push(unit_x, unit_y);

        // Enumerate the 4 neighbors in (+x, -x, +y, -y) order.
        for i in 0..4 {
            let nx = NEIGHBOR_X[i] + unit_x;
            let ny = NEIGHBOR_Y[i] + unit_y;
            let n_idx = self.index(nx, ny);
            if self.status[n_idx] == STATUS_INVALID {
                self.requires_validation.push(nx, ny);
            }
            if self.status[n_idx] != STATUS_WALL {
                ps[ps_len] = (nx, ny);
                ps_len += 1;
            }
        }
        // Drain `self.requires_validation` in-place via `validate_rv`.
        self.validate_rv();

        // Filter #1: compact by running min pathlength (front-to-back).
        // `s` MUST be initialized to i32::MAX (PATHFINDER_SPEC.md §6.7).
        let mut s: i32 = I32_MAX;
        let mut w: usize = 0;
        for i in 0..ps_len {
            let (sx, sy) = ps[i];
            let pl = self.pathlength[self.index(sx, sy)] as i32;
            if pl > s {
                continue;
            }
            s = pl;
            ps[w] = (sx, sy);
            w += 1;
        }
        ps_len = w;

        // Filter #2: strict equality to final `s`.
        let mut w: usize = 0;
        for i in 0..ps_len {
            let (sx, sy) = ps[i];
            if self.pathlength[self.index(sx, sy)] as i32 != s {
                continue;
            }
            ps[w] = (sx, sy);
            w += 1;
        }
        ps_len = w;

        // Filter #3: zigzag (only if size > 1).
        if ps_len > 1 {
            let mut w: usize = 0;
            for i in 0..ps_len {
                let (sx, sy) = ps[i];
                if (prev_direction == VERTICAL && sx == unit_x)
                    || (prev_direction == HORIZONTAL && sy == unit_y)
                    || (prev_direction == SPAWNED && sy == unit_y)
                {
                    continue;
                }
                ps[w] = (sx, sy);
                w += 1;
            }
            ps_len = w;
        }

        // Filter #4: target-direction preference (only if size > 1).
        if ps_len > 1 {
            let mut w: usize = 0;
            for i in 0..ps_len {
                let (sx, sy) = ps[i];
                if self.direction.0 + unit_x != sx && self.direction.1 + unit_y != sy {
                    continue;
                }
                ps[w] = (sx, sy);
                w += 1;
            }
            ps_len = w;
        }

        if ps_len == 0 {
            // Per engine behaviour if every candidate was filtered out, stay.
            return (unit_x, unit_y);
        }
        ps[0]
    }
}

// ---------------------------------------------------------- exports for sim use

/// Edge-id → target-direction vector, per MapBounds.java:24-43.
/// Mirrors `algos/athena/sim/pathfinder.py::EDGE_DIRECTION`.
pub const EDGE_TOP_RIGHT: i32 = 0;
pub const EDGE_TOP_LEFT: i32 = 1;
pub const EDGE_BOTTOM_LEFT: i32 = 2;
pub const EDGE_BOTTOM_RIGHT: i32 = 3;

#[inline]
pub fn edge_direction(edge: i32) -> (i32, i32) {
    match edge {
        EDGE_TOP_RIGHT => (1, 1),
        EDGE_TOP_LEFT => (-1, 1),
        EDGE_BOTTOM_LEFT => (-1, -1),
        EDGE_BOTTOM_RIGHT => (1, -1),
        _ => panic!("invalid edge id"),
    }
}

/// Factory for the 4 per-edge PathFinders, keyed by edge id (0..=3).
/// Mirrors `algos/athena/sim/pathfinder.py::make_pathfinders`.
///
/// `edge_to_perfects[edge_id]` is the list of perfect (edge) tiles for that
/// edge — caller passes the result of `crate::map::edge_tiles(edge_id)`.
pub fn make_pathfinders(
    dimension: i32,
    walls: &[(i32, i32)],
    edge_to_perfects: &IndexMap<i32, Vec<(i32, i32)>>,
) -> IndexMap<i32, PathFinder> {
    let mut out = IndexMap::with_capacity(4);
    for &edge in &[
        EDGE_TOP_RIGHT,
        EDGE_TOP_LEFT,
        EDGE_BOTTOM_LEFT,
        EDGE_BOTTOM_RIGHT,
    ] {
        let perfects = edge_to_perfects
            .get(&edge)
            .expect("edge_to_perfects missing edge id");
        out.insert(
            edge,
            PathFinder::new(dimension, edge_direction(edge), walls, perfects),
        );
    }
    out
}

// ------------------------------------------------------------------- tests
#[cfg(test)]
mod tests {
    use super::*;

    fn edge_tiles(edge: i32) -> Vec<(i32, i32)> {
        // Mirror algos/athena/sim/map.py::edge_tiles.
        let mut out = Vec::with_capacity(14);
        match edge {
            EDGE_TOP_RIGHT => {
                for y in 14..28 {
                    out.push((41 - y, y));
                }
            }
            EDGE_TOP_LEFT => {
                for y in 14..28 {
                    out.push((y - 14, y));
                }
            }
            EDGE_BOTTOM_LEFT => {
                for y in 0..14 {
                    out.push((13 - y, y));
                }
            }
            EDGE_BOTTOM_RIGHT => {
                for y in 0..14 {
                    out.push((y + 14, y));
                }
            }
            _ => unreachable!(),
        }
        out
    }

    #[test]
    fn coord_queue_basic_fifo() {
        let mut q = CoordQueue::new();
        assert_eq!(q.size(), 0);
        q.push(1, 2);
        q.push(3, 4);
        assert_eq!(q.size(), 2);
        assert!(q.next());
        assert_eq!((q.curr_x, q.curr_y), (1, 2));
        assert!(q.next());
        assert_eq!((q.curr_x, q.curr_y), (3, 4));
        assert!(!q.next());
    }

    #[test]
    fn coord_queue_grows_on_push() {
        let mut q = CoordQueue::new();
        // Initial cap=200 ints = 100 coords; push 150 to force a doubling.
        for i in 0..150 {
            q.push(i, i + 1);
        }
        assert_eq!(q.size(), 150);
        for i in 0..150 {
            assert!(q.next());
            assert_eq!(q.curr_x, i);
            assert_eq!(q.curr_y, i + 1);
        }
    }

    #[test]
    fn empty_board_step_toward_target() {
        // Empty-board pathfinder for EDGE_TOP_RIGHT; unit at (13,12) must
        // make progress toward (+x, +y).
        let perf = edge_tiles(EDGE_TOP_RIGHT);
        let mut pf = PathFinder::new(28, edge_direction(EDGE_TOP_RIGHT), &[], &perf);
        let step = pf.get_step(13, 12, SPAWNED);
        // Must move to an on-diamond neighbor.
        let (sx, sy) = step;
        assert!((sx - 13).abs() + (sy - 12).abs() <= 1);
    }

    #[test]
    fn put_and_remove_roundtrip() {
        let perf = edge_tiles(EDGE_TOP_RIGHT);
        let mut pf = PathFinder::new(28, edge_direction(EDGE_TOP_RIGHT), &[], &perf);
        let before = pf.get_step(13, 12, SPAWNED);
        pf.put(14, 14);
        let _during = pf.get_step(13, 12, SPAWNED);
        pf.remove(14, 14);
        // After removal, re-query should still produce a valid step.
        let after = pf.get_step(13, 12, SPAWNED);
        assert!((after.0 - 13).abs() + (after.1 - 12).abs() <= 1);
        // Sanity: before remained a legal neighbor.
        assert!((before.0 - 13).abs() + (before.1 - 12).abs() <= 1);
    }
}
