---
name: skill-creator-terminal
description: Create a new Codex skill tailored to the Citadel Terminal project. Use when the user wants to formalize a repeated workflow into an invocable `/<name>` skill — e.g. "make a skill for profiling algo turn time", "add a skill that pulls my latest ranked replay", "create a skill that runs opponent-specific regression matches".
---

# skill-creator-terminal

Creates a new skill under `.Codex/skills/<name>/SKILL.md` with the right shape for this project. Keeps skill definitions consistent and well-integrated with the existing tooling (`tools/`, `docs/`, `algos/`).

## When to use
- User has done the same multi-step workflow 2+ times and wants a single invocable name for it.
- User wants to encode a team/project convention ("always run test-algo AND a match before uploading").
- User is making the repo more Codex-friendly ("add a skill for X").

## Skill anatomy

Every skill in this project is a directory under `.Codex/skills/<kebab-name>/` containing at minimum `SKILL.md` with YAML frontmatter:

```yaml
---
name: kebab-name
description: One sentence explaining what the skill does AND when Codex should invoke it. Good descriptions start with a verb and name common trigger phrases ("Use when the user says X, Y, or Z").
---
```

The body of `SKILL.md` is a **runbook**: concrete steps Codex should follow when the skill is invoked. Write it like an SOP for a colleague.

## Template

When creating a new skill, write `SKILL.md` in this shape:

```markdown
---
name: <kebab-name>
description: <one-sentence trigger-aware description. Start with a verb. Include 2-3 example trigger phrases the user might say.>
---

# <kebab-name>

<One-paragraph summary of what the skill produces.>

## When to use
- <Bullet: what user phrases trigger this.>
- <Bullet: what situations this applies to.>

## Steps
1. <First concrete action. Include the exact Bash command where relevant.>
2. <Second action. Reference relevant doc files with clickable paths like `docs/GAME_RULES.md`.>
3. <Continue...>

## Failure modes
- <Known things that go wrong and how to recover.>

## Don't
- <Bullet: anti-patterns to avoid.>
```

## Steps to create a new skill

1. **Understand the workflow** from the user:
   - What does the skill do end-to-end?
   - What are the trigger phrases?
   - What inputs does it need (file paths, algo names, replay files)?
   - What output / side effects?
   - What could go wrong?

2. **Pick a kebab-case name**. Good: `profile-turn-time`, `fetch-ranked-replay`. Bad: `myNewSkill`, `skill1`.

3. **Check for overlap** with existing skills under `.Codex/skills/`. If there's significant overlap, suggest extending the existing skill instead.

4. **Create the directory**:
   ```bash
   mkdir -p .Codex/skills/<name>
   ```

5. **Write `SKILL.md`** using the template above. Key quality bars:
   - The description must be specific enough for Codex to know *when* to invoke (listing trigger phrases is the single biggest lever).
   - Steps must be concrete. "Analyze the replay" is useless; "Use `tools/analyze_replay.py <path>` and highlight any turn with >3 breaches" is actionable.
   - Reference this project's tools (`tools/run.sh`, `tools/test.sh`, etc.) rather than reinventing.
   - Reference this project's docs (`docs/GAME_RULES.md`, etc.) for authoritative rule citations.

6. **If the skill requires a new helper script**, add it under `tools/` and wire the skill to call it. Keep skills thin — complex logic belongs in Python scripts that the skill invokes.

7. **Add a permission entry** in `.Codex/settings.json` under `permissions.allow` if the skill runs a new Bash command pattern. Example: `"Bash(python3 tools/new_helper.py:*)"`.

8. **Update `README.md`**'s skill table to include the new skill.

9. **Test it** by writing a trigger phrase and confirming Codex would pick this skill over others (mentally; no runtime test).

## Design principles

- **Skills are runbooks, not code.** Keep logic in `tools/` or `docs/`. A skill says what to do; it doesn't re-implement it.
- **Skills should be triggerable.** If you can't imagine what the user would type to invoke it, the description is weak.
- **Small & orthogonal is better than big & uber.** Prefer 3 skills that each do one thing over 1 skill that branches on arguments.
- **Reference, don't duplicate.** Link to `docs/<file>.md` instead of inlining rules. The docs are the source of truth; skills are how Codex finds and uses them.

## Quality checklist

Before completing, verify:

- [ ] `description` says both WHAT the skill does and WHEN to use it.
- [ ] At least one concrete trigger phrase is listed in "When to use".
- [ ] Every step is actionable (command, file path, or decision rule).
- [ ] Failure modes section exists with at least one entry.
- [ ] If new tools/ scripts were added, they're executable and documented in README.md.
- [ ] If new permissions are needed, `.Codex/settings.json` is updated.
- [ ] README.md's skill table is updated.
