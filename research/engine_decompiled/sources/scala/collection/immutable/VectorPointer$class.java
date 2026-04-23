/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.immutable;

import scala.MatchError;
import scala.collection.immutable.VectorPointer;
import scala.compat.Platform$;
import scala.math.package$;
import scala.runtime.BoxesRunTime;

public abstract class VectorPointer$class {
    public static final void initFrom(VectorPointer $this, VectorPointer that) {
        $this.initFrom(that, that.depth());
    }

    public static final void initFrom(VectorPointer $this, VectorPointer that, int depth) {
        $this.depth_$eq(depth);
        int n = depth - 1;
        switch (n) {
            default: {
                throw new MatchError(BoxesRunTime.boxToInteger(n));
            }
            case 5: {
                $this.display5_$eq(that.display5());
                $this.display4_$eq(that.display4());
                $this.display3_$eq(that.display3());
                $this.display2_$eq(that.display2());
                $this.display1_$eq(that.display1());
                $this.display0_$eq(that.display0());
                break;
            }
            case 4: {
                $this.display4_$eq(that.display4());
                $this.display3_$eq(that.display3());
                $this.display2_$eq(that.display2());
                $this.display1_$eq(that.display1());
                $this.display0_$eq(that.display0());
                break;
            }
            case 3: {
                $this.display3_$eq(that.display3());
                $this.display2_$eq(that.display2());
                $this.display1_$eq(that.display1());
                $this.display0_$eq(that.display0());
                break;
            }
            case 2: {
                $this.display2_$eq(that.display2());
                $this.display1_$eq(that.display1());
                $this.display0_$eq(that.display0());
                break;
            }
            case 1: {
                $this.display1_$eq(that.display1());
                $this.display0_$eq(that.display0());
                break;
            }
            case 0: {
                $this.display0_$eq(that.display0());
            }
            case -1: 
        }
    }

    public static final Object getElem(VectorPointer $this, int index, int xor) {
        block8: {
            Object object;
            block3: {
                block7: {
                    block6: {
                        block5: {
                            block4: {
                                block2: {
                                    if (xor >= 32) break block2;
                                    object = $this.display0()[index & 0x1F];
                                    break block3;
                                }
                                if (xor >= 1024) break block4;
                                object = ((Object[])$this.display1()[index >> 5 & 0x1F])[index & 0x1F];
                                break block3;
                            }
                            if (xor >= 32768) break block5;
                            object = ((Object[])((Object[])$this.display2()[index >> 10 & 0x1F])[index >> 5 & 0x1F])[index & 0x1F];
                            break block3;
                        }
                        if (xor >= 0x100000) break block6;
                        object = ((Object[])((Object[])((Object[])$this.display3()[index >> 15 & 0x1F])[index >> 10 & 0x1F])[index >> 5 & 0x1F])[index & 0x1F];
                        break block3;
                    }
                    if (xor >= 0x2000000) break block7;
                    object = ((Object[])((Object[])((Object[])((Object[])$this.display4()[index >> 20 & 0x1F])[index >> 15 & 0x1F])[index >> 10 & 0x1F])[index >> 5 & 0x1F])[index & 0x1F];
                    break block3;
                }
                if (xor >= 0x40000000) break block8;
                object = ((Object[])((Object[])((Object[])((Object[])((Object[])$this.display5()[index >> 25 & 0x1F])[index >> 20 & 0x1F])[index >> 15 & 0x1F])[index >> 10 & 0x1F])[index >> 5 & 0x1F])[index & 0x1F];
            }
            return object;
        }
        throw new IllegalArgumentException();
    }

    public static final void gotoPos(VectorPointer $this, int index, int xor) {
        block7: {
            block2: {
                block6: {
                    block5: {
                        block4: {
                            block3: {
                                if (xor < 32) break block2;
                                if (xor >= 1024) break block3;
                                $this.display0_$eq((Object[])$this.display1()[index >> 5 & 0x1F]);
                                break block2;
                            }
                            if (xor >= 32768) break block4;
                            $this.display1_$eq((Object[])$this.display2()[index >> 10 & 0x1F]);
                            $this.display0_$eq((Object[])$this.display1()[index >> 5 & 0x1F]);
                            break block2;
                        }
                        if (xor >= 0x100000) break block5;
                        $this.display2_$eq((Object[])$this.display3()[index >> 15 & 0x1F]);
                        $this.display1_$eq((Object[])$this.display2()[index >> 10 & 0x1F]);
                        $this.display0_$eq((Object[])$this.display1()[index >> 5 & 0x1F]);
                        break block2;
                    }
                    if (xor >= 0x2000000) break block6;
                    $this.display3_$eq((Object[])$this.display4()[index >> 20 & 0x1F]);
                    $this.display2_$eq((Object[])$this.display3()[index >> 15 & 0x1F]);
                    $this.display1_$eq((Object[])$this.display2()[index >> 10 & 0x1F]);
                    $this.display0_$eq((Object[])$this.display1()[index >> 5 & 0x1F]);
                    break block2;
                }
                if (xor >= 0x40000000) break block7;
                $this.display4_$eq((Object[])$this.display5()[index >> 25 & 0x1F]);
                $this.display3_$eq((Object[])$this.display4()[index >> 20 & 0x1F]);
                $this.display2_$eq((Object[])$this.display3()[index >> 15 & 0x1F]);
                $this.display1_$eq((Object[])$this.display2()[index >> 10 & 0x1F]);
                $this.display0_$eq((Object[])$this.display1()[index >> 5 & 0x1F]);
            }
            return;
        }
        throw new IllegalArgumentException();
    }

    public static final void gotoNextBlockStart(VectorPointer $this, int index, int xor) {
        block7: {
            block3: {
                block6: {
                    block5: {
                        block4: {
                            block2: {
                                if (xor >= 1024) break block2;
                                $this.display0_$eq((Object[])$this.display1()[index >> 5 & 0x1F]);
                                break block3;
                            }
                            if (xor >= 32768) break block4;
                            $this.display1_$eq((Object[])$this.display2()[index >> 10 & 0x1F]);
                            $this.display0_$eq((Object[])$this.display1()[0]);
                            break block3;
                        }
                        if (xor >= 0x100000) break block5;
                        $this.display2_$eq((Object[])$this.display3()[index >> 15 & 0x1F]);
                        $this.display1_$eq((Object[])$this.display2()[0]);
                        $this.display0_$eq((Object[])$this.display1()[0]);
                        break block3;
                    }
                    if (xor >= 0x2000000) break block6;
                    $this.display3_$eq((Object[])$this.display4()[index >> 20 & 0x1F]);
                    $this.display2_$eq((Object[])$this.display3()[0]);
                    $this.display1_$eq((Object[])$this.display2()[0]);
                    $this.display0_$eq((Object[])$this.display1()[0]);
                    break block3;
                }
                if (xor >= 0x40000000) break block7;
                $this.display4_$eq((Object[])$this.display5()[index >> 25 & 0x1F]);
                $this.display3_$eq((Object[])$this.display4()[0]);
                $this.display2_$eq((Object[])$this.display3()[0]);
                $this.display1_$eq((Object[])$this.display2()[0]);
                $this.display0_$eq((Object[])$this.display1()[0]);
            }
            return;
        }
        throw new IllegalArgumentException();
    }

    public static final void gotoNextBlockStartWritable(VectorPointer $this, int index, int xor) {
        block13: {
            block9: {
                block12: {
                    block11: {
                        block10: {
                            block8: {
                                if (xor >= 1024) break block8;
                                if ($this.depth() == 1) {
                                    $this.display1_$eq(new Object[32]);
                                    $this.display1()[0] = $this.display0();
                                    $this.depth_$eq($this.depth() + 1);
                                }
                                $this.display0_$eq(new Object[32]);
                                $this.display1()[index >> 5 & 0x1F] = $this.display0();
                                break block9;
                            }
                            if (xor >= 32768) break block10;
                            if ($this.depth() == 2) {
                                $this.display2_$eq(new Object[32]);
                                $this.display2()[0] = $this.display1();
                                $this.depth_$eq($this.depth() + 1);
                            }
                            $this.display0_$eq(new Object[32]);
                            $this.display1_$eq(new Object[32]);
                            $this.display1()[index >> 5 & 0x1F] = $this.display0();
                            $this.display2()[index >> 10 & 0x1F] = $this.display1();
                            break block9;
                        }
                        if (xor >= 0x100000) break block11;
                        if ($this.depth() == 3) {
                            $this.display3_$eq(new Object[32]);
                            $this.display3()[0] = $this.display2();
                            $this.depth_$eq($this.depth() + 1);
                        }
                        $this.display0_$eq(new Object[32]);
                        $this.display1_$eq(new Object[32]);
                        $this.display2_$eq(new Object[32]);
                        $this.display1()[index >> 5 & 0x1F] = $this.display0();
                        $this.display2()[index >> 10 & 0x1F] = $this.display1();
                        $this.display3()[index >> 15 & 0x1F] = $this.display2();
                        break block9;
                    }
                    if (xor >= 0x2000000) break block12;
                    if ($this.depth() == 4) {
                        $this.display4_$eq(new Object[32]);
                        $this.display4()[0] = $this.display3();
                        $this.depth_$eq($this.depth() + 1);
                    }
                    $this.display0_$eq(new Object[32]);
                    $this.display1_$eq(new Object[32]);
                    $this.display2_$eq(new Object[32]);
                    $this.display3_$eq(new Object[32]);
                    $this.display1()[index >> 5 & 0x1F] = $this.display0();
                    $this.display2()[index >> 10 & 0x1F] = $this.display1();
                    $this.display3()[index >> 15 & 0x1F] = $this.display2();
                    $this.display4()[index >> 20 & 0x1F] = $this.display3();
                    break block9;
                }
                if (xor >= 0x40000000) break block13;
                if ($this.depth() == 5) {
                    $this.display5_$eq(new Object[32]);
                    $this.display5()[0] = $this.display4();
                    $this.depth_$eq($this.depth() + 1);
                }
                $this.display0_$eq(new Object[32]);
                $this.display1_$eq(new Object[32]);
                $this.display2_$eq(new Object[32]);
                $this.display3_$eq(new Object[32]);
                $this.display4_$eq(new Object[32]);
                $this.display1()[index >> 5 & 0x1F] = $this.display0();
                $this.display2()[index >> 10 & 0x1F] = $this.display1();
                $this.display3()[index >> 15 & 0x1F] = $this.display2();
                $this.display4()[index >> 20 & 0x1F] = $this.display3();
                $this.display5()[index >> 25 & 0x1F] = $this.display4();
            }
            return;
        }
        throw new IllegalArgumentException();
    }

    public static final Object[] copyOf(VectorPointer $this, Object[] a) {
        Object[] b = new Object[a.length];
        int n = a.length;
        Platform$ platform$ = Platform$.MODULE$;
        System.arraycopy(a, 0, b, 0, n);
        return b;
    }

    public static final Object[] nullSlotAndCopy(VectorPointer $this, Object[] array, int index) {
        Object x = array[index];
        array[index] = null;
        return $this.copyOf((Object[])x);
    }

    public static final void stabilize(VectorPointer $this, int index) {
        int n = $this.depth() - 1;
        switch (n) {
            default: {
                throw new MatchError(BoxesRunTime.boxToInteger(n));
            }
            case 1: {
                $this.display1_$eq($this.copyOf($this.display1()));
                $this.display1()[index >> 5 & 0x1F] = $this.display0();
                break;
            }
            case 2: {
                $this.display2_$eq($this.copyOf($this.display2()));
                $this.display1_$eq($this.copyOf($this.display1()));
                $this.display2()[index >> 10 & 0x1F] = $this.display1();
                $this.display1()[index >> 5 & 0x1F] = $this.display0();
                break;
            }
            case 3: {
                $this.display3_$eq($this.copyOf($this.display3()));
                $this.display2_$eq($this.copyOf($this.display2()));
                $this.display1_$eq($this.copyOf($this.display1()));
                $this.display3()[index >> 15 & 0x1F] = $this.display2();
                $this.display2()[index >> 10 & 0x1F] = $this.display1();
                $this.display1()[index >> 5 & 0x1F] = $this.display0();
                break;
            }
            case 4: {
                $this.display4_$eq($this.copyOf($this.display4()));
                $this.display3_$eq($this.copyOf($this.display3()));
                $this.display2_$eq($this.copyOf($this.display2()));
                $this.display1_$eq($this.copyOf($this.display1()));
                $this.display4()[index >> 20 & 0x1F] = $this.display3();
                $this.display3()[index >> 15 & 0x1F] = $this.display2();
                $this.display2()[index >> 10 & 0x1F] = $this.display1();
                $this.display1()[index >> 5 & 0x1F] = $this.display0();
                break;
            }
            case 5: {
                $this.display5_$eq($this.copyOf($this.display5()));
                $this.display4_$eq($this.copyOf($this.display4()));
                $this.display3_$eq($this.copyOf($this.display3()));
                $this.display2_$eq($this.copyOf($this.display2()));
                $this.display1_$eq($this.copyOf($this.display1()));
                $this.display5()[index >> 25 & 0x1F] = $this.display4();
                $this.display4()[index >> 20 & 0x1F] = $this.display3();
                $this.display3()[index >> 15 & 0x1F] = $this.display2();
                $this.display2()[index >> 10 & 0x1F] = $this.display1();
                $this.display1()[index >> 5 & 0x1F] = $this.display0();
            }
            case 0: 
        }
    }

    public static final void gotoPosWritable0(VectorPointer $this, int newIndex, int xor) {
        int n = $this.depth() - 1;
        switch (n) {
            default: {
                throw new MatchError(BoxesRunTime.boxToInteger(n));
            }
            case 0: {
                $this.display0_$eq($this.copyOf($this.display0()));
                break;
            }
            case 1: {
                $this.display1_$eq($this.copyOf($this.display1()));
                $this.display0_$eq($this.nullSlotAndCopy($this.display1(), newIndex >> 5 & 0x1F));
                break;
            }
            case 2: {
                $this.display2_$eq($this.copyOf($this.display2()));
                $this.display1_$eq($this.nullSlotAndCopy($this.display2(), newIndex >> 10 & 0x1F));
                $this.display0_$eq($this.nullSlotAndCopy($this.display1(), newIndex >> 5 & 0x1F));
                break;
            }
            case 3: {
                $this.display3_$eq($this.copyOf($this.display3()));
                $this.display2_$eq($this.nullSlotAndCopy($this.display3(), newIndex >> 15 & 0x1F));
                $this.display1_$eq($this.nullSlotAndCopy($this.display2(), newIndex >> 10 & 0x1F));
                $this.display0_$eq($this.nullSlotAndCopy($this.display1(), newIndex >> 5 & 0x1F));
                break;
            }
            case 4: {
                $this.display4_$eq($this.copyOf($this.display4()));
                $this.display3_$eq($this.nullSlotAndCopy($this.display4(), newIndex >> 20 & 0x1F));
                $this.display2_$eq($this.nullSlotAndCopy($this.display3(), newIndex >> 15 & 0x1F));
                $this.display1_$eq($this.nullSlotAndCopy($this.display2(), newIndex >> 10 & 0x1F));
                $this.display0_$eq($this.nullSlotAndCopy($this.display1(), newIndex >> 5 & 0x1F));
                break;
            }
            case 5: {
                $this.display5_$eq($this.copyOf($this.display5()));
                $this.display4_$eq($this.nullSlotAndCopy($this.display5(), newIndex >> 25 & 0x1F));
                $this.display3_$eq($this.nullSlotAndCopy($this.display4(), newIndex >> 20 & 0x1F));
                $this.display2_$eq($this.nullSlotAndCopy($this.display3(), newIndex >> 15 & 0x1F));
                $this.display1_$eq($this.nullSlotAndCopy($this.display2(), newIndex >> 10 & 0x1F));
                $this.display0_$eq($this.nullSlotAndCopy($this.display1(), newIndex >> 5 & 0x1F));
            }
        }
    }

    public static final void gotoPosWritable1(VectorPointer $this, int oldIndex, int newIndex, int xor) {
        block8: {
            block3: {
                block7: {
                    block6: {
                        block5: {
                            block4: {
                                block2: {
                                    if (xor >= 32) break block2;
                                    $this.display0_$eq($this.copyOf($this.display0()));
                                    break block3;
                                }
                                if (xor >= 1024) break block4;
                                $this.display1_$eq($this.copyOf($this.display1()));
                                $this.display1()[oldIndex >> 5 & 0x1F] = $this.display0();
                                $this.display0_$eq($this.nullSlotAndCopy($this.display1(), newIndex >> 5 & 0x1F));
                                break block3;
                            }
                            if (xor >= 32768) break block5;
                            $this.display1_$eq($this.copyOf($this.display1()));
                            $this.display2_$eq($this.copyOf($this.display2()));
                            $this.display1()[oldIndex >> 5 & 0x1F] = $this.display0();
                            $this.display2()[oldIndex >> 10 & 0x1F] = $this.display1();
                            $this.display1_$eq($this.nullSlotAndCopy($this.display2(), newIndex >> 10 & 0x1F));
                            $this.display0_$eq($this.nullSlotAndCopy($this.display1(), newIndex >> 5 & 0x1F));
                            break block3;
                        }
                        if (xor >= 0x100000) break block6;
                        $this.display1_$eq($this.copyOf($this.display1()));
                        $this.display2_$eq($this.copyOf($this.display2()));
                        $this.display3_$eq($this.copyOf($this.display3()));
                        $this.display1()[oldIndex >> 5 & 0x1F] = $this.display0();
                        $this.display2()[oldIndex >> 10 & 0x1F] = $this.display1();
                        $this.display3()[oldIndex >> 15 & 0x1F] = $this.display2();
                        $this.display2_$eq($this.nullSlotAndCopy($this.display3(), newIndex >> 15 & 0x1F));
                        $this.display1_$eq($this.nullSlotAndCopy($this.display2(), newIndex >> 10 & 0x1F));
                        $this.display0_$eq($this.nullSlotAndCopy($this.display1(), newIndex >> 5 & 0x1F));
                        break block3;
                    }
                    if (xor >= 0x2000000) break block7;
                    $this.display1_$eq($this.copyOf($this.display1()));
                    $this.display2_$eq($this.copyOf($this.display2()));
                    $this.display3_$eq($this.copyOf($this.display3()));
                    $this.display4_$eq($this.copyOf($this.display4()));
                    $this.display1()[oldIndex >> 5 & 0x1F] = $this.display0();
                    $this.display2()[oldIndex >> 10 & 0x1F] = $this.display1();
                    $this.display3()[oldIndex >> 15 & 0x1F] = $this.display2();
                    $this.display4()[oldIndex >> 20 & 0x1F] = $this.display3();
                    $this.display3_$eq($this.nullSlotAndCopy($this.display4(), newIndex >> 20 & 0x1F));
                    $this.display2_$eq($this.nullSlotAndCopy($this.display3(), newIndex >> 15 & 0x1F));
                    $this.display1_$eq($this.nullSlotAndCopy($this.display2(), newIndex >> 10 & 0x1F));
                    $this.display0_$eq($this.nullSlotAndCopy($this.display1(), newIndex >> 5 & 0x1F));
                    break block3;
                }
                if (xor >= 0x40000000) break block8;
                $this.display1_$eq($this.copyOf($this.display1()));
                $this.display2_$eq($this.copyOf($this.display2()));
                $this.display3_$eq($this.copyOf($this.display3()));
                $this.display4_$eq($this.copyOf($this.display4()));
                $this.display5_$eq($this.copyOf($this.display5()));
                $this.display1()[oldIndex >> 5 & 0x1F] = $this.display0();
                $this.display2()[oldIndex >> 10 & 0x1F] = $this.display1();
                $this.display3()[oldIndex >> 15 & 0x1F] = $this.display2();
                $this.display4()[oldIndex >> 20 & 0x1F] = $this.display3();
                $this.display5()[oldIndex >> 25 & 0x1F] = $this.display4();
                $this.display4_$eq($this.nullSlotAndCopy($this.display5(), newIndex >> 25 & 0x1F));
                $this.display3_$eq($this.nullSlotAndCopy($this.display4(), newIndex >> 20 & 0x1F));
                $this.display2_$eq($this.nullSlotAndCopy($this.display3(), newIndex >> 15 & 0x1F));
                $this.display1_$eq($this.nullSlotAndCopy($this.display2(), newIndex >> 10 & 0x1F));
                $this.display0_$eq($this.nullSlotAndCopy($this.display1(), newIndex >> 5 & 0x1F));
            }
            return;
        }
        throw new IllegalArgumentException();
    }

    public static final Object[] copyRange(VectorPointer $this, Object[] array, int oldLeft, int newLeft) {
        Object[] elems = new Object[32];
        int n = 32 - package$.MODULE$.max(newLeft, oldLeft);
        Platform$ platform$ = Platform$.MODULE$;
        System.arraycopy(array, oldLeft, elems, newLeft, n);
        return elems;
    }

    public static final void gotoFreshPosWritable0(VectorPointer $this, int oldIndex, int newIndex, int xor) {
        block23: {
            block18: {
                block22: {
                    block21: {
                        block20: {
                            block19: {
                                if (xor < 32) break block18;
                                if (xor >= 1024) break block19;
                                if ($this.depth() == 1) {
                                    $this.display1_$eq(new Object[32]);
                                    $this.display1()[oldIndex >> 5 & 0x1F] = $this.display0();
                                    $this.depth_$eq($this.depth() + 1);
                                }
                                $this.display0_$eq(new Object[32]);
                                break block18;
                            }
                            if (xor >= 32768) break block20;
                            if ($this.depth() == 2) {
                                $this.display2_$eq(new Object[32]);
                                $this.display2()[oldIndex >> 10 & 0x1F] = $this.display1();
                                $this.depth_$eq($this.depth() + 1);
                            }
                            $this.display1_$eq((Object[])$this.display2()[newIndex >> 10 & 0x1F]);
                            if ($this.display1() == null) {
                                $this.display1_$eq(new Object[32]);
                            }
                            $this.display0_$eq(new Object[32]);
                            break block18;
                        }
                        if (xor >= 0x100000) break block21;
                        if ($this.depth() == 3) {
                            $this.display3_$eq(new Object[32]);
                            $this.display3()[oldIndex >> 15 & 0x1F] = $this.display2();
                            $this.depth_$eq($this.depth() + 1);
                        }
                        $this.display2_$eq((Object[])$this.display3()[newIndex >> 15 & 0x1F]);
                        if ($this.display2() == null) {
                            $this.display2_$eq(new Object[32]);
                        }
                        $this.display1_$eq((Object[])$this.display2()[newIndex >> 10 & 0x1F]);
                        if ($this.display1() == null) {
                            $this.display1_$eq(new Object[32]);
                        }
                        $this.display0_$eq(new Object[32]);
                        break block18;
                    }
                    if (xor >= 0x2000000) break block22;
                    if ($this.depth() == 4) {
                        $this.display4_$eq(new Object[32]);
                        $this.display4()[oldIndex >> 20 & 0x1F] = $this.display3();
                        $this.depth_$eq($this.depth() + 1);
                    }
                    $this.display3_$eq((Object[])$this.display4()[newIndex >> 20 & 0x1F]);
                    if ($this.display3() == null) {
                        $this.display3_$eq(new Object[32]);
                    }
                    $this.display2_$eq((Object[])$this.display3()[newIndex >> 15 & 0x1F]);
                    if ($this.display2() == null) {
                        $this.display2_$eq(new Object[32]);
                    }
                    $this.display1_$eq((Object[])$this.display2()[newIndex >> 10 & 0x1F]);
                    if ($this.display1() == null) {
                        $this.display1_$eq(new Object[32]);
                    }
                    $this.display0_$eq(new Object[32]);
                    break block18;
                }
                if (xor >= 0x40000000) break block23;
                if ($this.depth() == 5) {
                    $this.display5_$eq(new Object[32]);
                    $this.display5()[oldIndex >> 25 & 0x1F] = $this.display4();
                    $this.depth_$eq($this.depth() + 1);
                }
                $this.display4_$eq((Object[])$this.display5()[newIndex >> 25 & 0x1F]);
                if ($this.display4() == null) {
                    $this.display4_$eq(new Object[32]);
                }
                $this.display3_$eq((Object[])$this.display4()[newIndex >> 20 & 0x1F]);
                if ($this.display3() == null) {
                    $this.display3_$eq(new Object[32]);
                }
                $this.display2_$eq((Object[])$this.display3()[newIndex >> 15 & 0x1F]);
                if ($this.display2() == null) {
                    $this.display2_$eq(new Object[32]);
                }
                $this.display1_$eq((Object[])$this.display2()[newIndex >> 10 & 0x1F]);
                if ($this.display1() == null) {
                    $this.display1_$eq(new Object[32]);
                }
                $this.display0_$eq(new Object[32]);
            }
            return;
        }
        throw new IllegalArgumentException();
    }

    public static final void gotoFreshPosWritable1(VectorPointer $this, int oldIndex, int newIndex, int xor) {
        $this.stabilize(oldIndex);
        $this.gotoFreshPosWritable0(oldIndex, newIndex, xor);
    }

    public static void debug(VectorPointer $this) {
    }

    public static void $init$(VectorPointer $this) {
    }
}

