/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal;

import scala.reflect.internal.ClassfileConstants$FlagTranslation$;

/*
 * Illegal identifiers - consider using --renameillegalidents true
 */
public final class ClassfileConstants$ {
    public static final ClassfileConstants$ MODULE$;
    private final int JAVA_MAGIC;
    private final int JAVA_MAJOR_VERSION;
    private final int JAVA_MINOR_VERSION;
    private final int JAVA_ACC_PUBLIC;
    private final int JAVA_ACC_PRIVATE;
    private final int JAVA_ACC_PROTECTED;
    private final int JAVA_ACC_STATIC;
    private final int JAVA_ACC_FINAL;
    private final int JAVA_ACC_SUPER;
    private final int JAVA_ACC_SYNCHRONIZED;
    private final int JAVA_ACC_VOLATILE;
    private final int JAVA_ACC_BRIDGE;
    private final int JAVA_ACC_TRANSIENT;
    private final int JAVA_ACC_VARARGS;
    private final int JAVA_ACC_NATIVE;
    private final int JAVA_ACC_INTERFACE;
    private final int JAVA_ACC_ABSTRACT;
    private final int JAVA_ACC_STRICT;
    private final int JAVA_ACC_SYNTHETIC;
    private final int JAVA_ACC_ANNOTATION;
    private final int JAVA_ACC_ENUM;
    private final int CONSTANT_UTF8;
    private final int CONSTANT_UNICODE;
    private final int CONSTANT_INTEGER;
    private final int CONSTANT_FLOAT;
    private final int CONSTANT_LONG;
    private final int CONSTANT_DOUBLE;
    private final int CONSTANT_CLASS;
    private final int CONSTANT_STRING;
    private final int CONSTANT_FIELDREF;
    private final int CONSTANT_METHODREF;
    private final int CONSTANT_INTFMETHODREF;
    private final int CONSTANT_NAMEANDTYPE;
    private final int CONSTANT_METHODHANDLE;
    private final int CONSTANT_METHODTYPE;
    private final int CONSTANT_INVOKEDYNAMIC;
    private final char BYTE_TAG;
    private final char CHAR_TAG;
    private final char DOUBLE_TAG;
    private final char FLOAT_TAG;
    private final char INT_TAG;
    private final char LONG_TAG;
    private final char SHORT_TAG;
    private final char BOOL_TAG;
    private final char STRING_TAG;
    private final char ENUM_TAG;
    private final char CLASS_TAG;
    private final char ARRAY_TAG;
    private final char VOID_TAG;
    private final char TVAR_TAG;
    private final char OBJECT_TAG;
    private final char ANNOTATION_TAG;
    private final String SCALA_NOTHING;
    private final String SCALA_NULL;
    private final int T_BOOLEAN;
    private final int T_CHAR;
    private final int T_FLOAT;
    private final int T_DOUBLE;
    private final int T_BYTE;
    private final int T_SHORT;
    private final int T_INT;
    private final int T_LONG;
    private final int nop;
    private final int aconst_null;
    private final int iconst_m1;
    private final int iconst_0;
    private final int iconst_1;
    private final int iconst_2;
    private final int iconst_3;
    private final int iconst_4;
    private final int iconst_5;
    private final int lconst_0;
    private final int lconst_1;
    private final int fconst_0;
    private final int fconst_1;
    private final int fconst_2;
    private final int dconst_0;
    private final int dconst_1;
    private final int bipush;
    private final int sipush;
    private final int ldc;
    private final int ldc_w;
    private final int ldc2_w;
    private final int iload;
    private final int lload;
    private final int fload;
    private final int dload;
    private final int aload;
    private final int iload_0;
    private final int iload_1;
    private final int iload_2;
    private final int iload_3;
    private final int lload_0;
    private final int lload_1;
    private final int lload_2;
    private final int lload_3;
    private final int fload_0;
    private final int fload_1;
    private final int fload_2;
    private final int fload_3;
    private final int dload_0;
    private final int dload_1;
    private final int dload_2;
    private final int dload_3;
    private final int aload_0;
    private final int aload_1;
    private final int aload_2;
    private final int aload_3;
    private final int iaload;
    private final int laload;
    private final int faload;
    private final int daload;
    private final int aaload;
    private final int baload;
    private final int caload;
    private final int saload;
    private final int istore;
    private final int lstore;
    private final int fstore;
    private final int dstore;
    private final int astore;
    private final int istore_0;
    private final int istore_1;
    private final int istore_2;
    private final int istore_3;
    private final int lstore_0;
    private final int lstore_1;
    private final int lstore_2;
    private final int lstore_3;
    private final int fstore_0;
    private final int fstore_1;
    private final int fstore_2;
    private final int fstore_3;
    private final int dstore_0;
    private final int dstore_1;
    private final int dstore_2;
    private final int dstore_3;
    private final int astore_0;
    private final int astore_1;
    private final int astore_2;
    private final int astore_3;
    private final int iastore;
    private final int lastore;
    private final int fastore;
    private final int dastore;
    private final int aastore;
    private final int bastore;
    private final int castore;
    private final int sastore;
    private final int pop;
    private final int pop2;
    private final int dup;
    private final int dup_x1;
    private final int dup_x2;
    private final int dup2;
    private final int dup2_x1;
    private final int dup2_x2;
    private final int swap;
    private final int iadd;
    private final int ladd;
    private final int fadd;
    private final int dadd;
    private final int isub;
    private final int lsub;
    private final int fsub;
    private final int dsub;
    private final int imul;
    private final int lmul;
    private final int fmul;
    private final int dmul;
    private final int idiv;
    private final int ldiv;
    private final int fdiv;
    private final int ddiv;
    private final int irem;
    private final int lrem;
    private final int frem;
    private final int drem;
    private final int ineg;
    private final int lneg;
    private final int fneg;
    private final int dneg;
    private final int ishl;
    private final int lshl;
    private final int ishr;
    private final int lshr;
    private final int iushr;
    private final int lushr;
    private final int iand;
    private final int land;
    private final int ior;
    private final int lor;
    private final int ixor;
    private final int lxor;
    private final int iinc;
    private final int i2l;
    private final int i2f;
    private final int i2d;
    private final int l2i;
    private final int l2f;
    private final int l2d;
    private final int f2i;
    private final int f2l;
    private final int f2d;
    private final int d2i;
    private final int d2l;
    private final int d2f;
    private final int i2b;
    private final int i2c;
    private final int i2s;
    private final int lcmp;
    private final int fcmpl;
    private final int fcmpg;
    private final int dcmpl;
    private final int dcmpg;
    private final int ifeq;
    private final int ifne;
    private final int iflt;
    private final int ifge;
    private final int ifgt;
    private final int ifle;
    private final int if_icmpeq;
    private final int if_icmpne;
    private final int if_icmplt;
    private final int if_icmpge;
    private final int if_icmpgt;
    private final int if_icmple;
    private final int if_acmpeq;
    private final int if_acmpne;
    private final int goto;
    private final int jsr;
    private final int ret;
    private final int tableswitch;
    private final int lookupswitch;
    private final int ireturn;
    private final int lreturn;
    private final int freturn;
    private final int dreturn;
    private final int areturn;
    private final int return_;
    private final int getstatic;
    private final int putstatic;
    private final int getfield;
    private final int putfield;
    private final int invokevirtual;
    private final int invokespecial;
    private final int invokestatic;
    private final int invokeinterface;
    private final int invokedynamic;
    private final int new_;
    private final int newarray;
    private final int anewarray;
    private final int arraylength;
    private final int athrow;
    private final int checkcast;
    private final int instanceof;
    private final int monitorenter;
    private final int monitorexit;
    private final int wide;
    private final int multianewarray;
    private final int ifnull;
    private final int ifnonnull;
    private final int goto_w;
    private final int jsr_w;
    private final int breakpoint;
    private final int impdep1;
    private final int impdep2;

    static {
        new ClassfileConstants$();
    }

    public final int JAVA_MAGIC() {
        return -889275714;
    }

    public final int JAVA_MAJOR_VERSION() {
        return 45;
    }

    public final int JAVA_MINOR_VERSION() {
        return 3;
    }

    public final int JAVA_ACC_PUBLIC() {
        return 1;
    }

    public final int JAVA_ACC_PRIVATE() {
        return 2;
    }

    public final int JAVA_ACC_PROTECTED() {
        return 4;
    }

    public final int JAVA_ACC_STATIC() {
        return 8;
    }

    public final int JAVA_ACC_FINAL() {
        return 16;
    }

    public final int JAVA_ACC_SUPER() {
        return 32;
    }

    public final int JAVA_ACC_SYNCHRONIZED() {
        return 32;
    }

    public final int JAVA_ACC_VOLATILE() {
        return 64;
    }

    public final int JAVA_ACC_BRIDGE() {
        return 64;
    }

    public final int JAVA_ACC_TRANSIENT() {
        return 128;
    }

    public final int JAVA_ACC_VARARGS() {
        return 128;
    }

    public final int JAVA_ACC_NATIVE() {
        return 256;
    }

    public final int JAVA_ACC_INTERFACE() {
        return 512;
    }

    public final int JAVA_ACC_ABSTRACT() {
        return 1024;
    }

    public final int JAVA_ACC_STRICT() {
        return 2048;
    }

    public final int JAVA_ACC_SYNTHETIC() {
        return 4096;
    }

    public final int JAVA_ACC_ANNOTATION() {
        return 8192;
    }

    public final int JAVA_ACC_ENUM() {
        return 16384;
    }

    public final int CONSTANT_UTF8() {
        return 1;
    }

    public final int CONSTANT_UNICODE() {
        return 2;
    }

    public final int CONSTANT_INTEGER() {
        return 3;
    }

    public final int CONSTANT_FLOAT() {
        return 4;
    }

    public final int CONSTANT_LONG() {
        return 5;
    }

    public final int CONSTANT_DOUBLE() {
        return 6;
    }

    public final int CONSTANT_CLASS() {
        return 7;
    }

    public final int CONSTANT_STRING() {
        return 8;
    }

    public final int CONSTANT_FIELDREF() {
        return 9;
    }

    public final int CONSTANT_METHODREF() {
        return 10;
    }

    public final int CONSTANT_INTFMETHODREF() {
        return 11;
    }

    public final int CONSTANT_NAMEANDTYPE() {
        return 12;
    }

    public final int CONSTANT_METHODHANDLE() {
        return 15;
    }

    public final int CONSTANT_METHODTYPE() {
        return 16;
    }

    public final int CONSTANT_INVOKEDYNAMIC() {
        return 18;
    }

    public final char BYTE_TAG() {
        return 'B';
    }

    public final char CHAR_TAG() {
        return 'C';
    }

    public final char DOUBLE_TAG() {
        return 'D';
    }

    public final char FLOAT_TAG() {
        return 'F';
    }

    public final char INT_TAG() {
        return 'I';
    }

    public final char LONG_TAG() {
        return 'J';
    }

    public final char SHORT_TAG() {
        return 'S';
    }

    public final char BOOL_TAG() {
        return 'Z';
    }

    public final char STRING_TAG() {
        return 's';
    }

    public final char ENUM_TAG() {
        return 'e';
    }

    public final char CLASS_TAG() {
        return 'c';
    }

    public final char ARRAY_TAG() {
        return '[';
    }

    public final char VOID_TAG() {
        return 'V';
    }

    public final char TVAR_TAG() {
        return 'T';
    }

    public final char OBJECT_TAG() {
        return 'L';
    }

    public final char ANNOTATION_TAG() {
        return '@';
    }

    public final String SCALA_NOTHING() {
        return "scala.runtime.Nothing$";
    }

    public final String SCALA_NULL() {
        return "scala.runtime.Null$";
    }

    public final int T_BOOLEAN() {
        return 4;
    }

    public final int T_CHAR() {
        return 5;
    }

    public final int T_FLOAT() {
        return 6;
    }

    public final int T_DOUBLE() {
        return 7;
    }

    public final int T_BYTE() {
        return 8;
    }

    public final int T_SHORT() {
        return 9;
    }

    public final int T_INT() {
        return 10;
    }

    public final int T_LONG() {
        return 11;
    }

    public final int nop() {
        return 0;
    }

    public final int aconst_null() {
        return 1;
    }

    public final int iconst_m1() {
        return 2;
    }

    public final int iconst_0() {
        return 3;
    }

    public final int iconst_1() {
        return 4;
    }

    public final int iconst_2() {
        return 5;
    }

    public final int iconst_3() {
        return 6;
    }

    public final int iconst_4() {
        return 7;
    }

    public final int iconst_5() {
        return 8;
    }

    public final int lconst_0() {
        return 9;
    }

    public final int lconst_1() {
        return 10;
    }

    public final int fconst_0() {
        return 11;
    }

    public final int fconst_1() {
        return 12;
    }

    public final int fconst_2() {
        return 13;
    }

    public final int dconst_0() {
        return 14;
    }

    public final int dconst_1() {
        return 15;
    }

    public final int bipush() {
        return 16;
    }

    public final int sipush() {
        return 17;
    }

    public final int ldc() {
        return 18;
    }

    public final int ldc_w() {
        return 19;
    }

    public final int ldc2_w() {
        return 20;
    }

    public final int iload() {
        return 21;
    }

    public final int lload() {
        return 22;
    }

    public final int fload() {
        return 23;
    }

    public final int dload() {
        return 24;
    }

    public final int aload() {
        return 25;
    }

    public final int iload_0() {
        return 26;
    }

    public final int iload_1() {
        return 27;
    }

    public final int iload_2() {
        return 28;
    }

    public final int iload_3() {
        return 29;
    }

    public final int lload_0() {
        return 30;
    }

    public final int lload_1() {
        return 31;
    }

    public final int lload_2() {
        return 32;
    }

    public final int lload_3() {
        return 33;
    }

    public final int fload_0() {
        return 34;
    }

    public final int fload_1() {
        return 35;
    }

    public final int fload_2() {
        return 36;
    }

    public final int fload_3() {
        return 37;
    }

    public final int dload_0() {
        return 38;
    }

    public final int dload_1() {
        return 39;
    }

    public final int dload_2() {
        return 40;
    }

    public final int dload_3() {
        return 41;
    }

    public final int aload_0() {
        return 42;
    }

    public final int aload_1() {
        return 43;
    }

    public final int aload_2() {
        return 44;
    }

    public final int aload_3() {
        return 45;
    }

    public final int iaload() {
        return 46;
    }

    public final int laload() {
        return 47;
    }

    public final int faload() {
        return 48;
    }

    public final int daload() {
        return 49;
    }

    public final int aaload() {
        return 50;
    }

    public final int baload() {
        return 51;
    }

    public final int caload() {
        return 52;
    }

    public final int saload() {
        return 53;
    }

    public final int istore() {
        return 54;
    }

    public final int lstore() {
        return 55;
    }

    public final int fstore() {
        return 56;
    }

    public final int dstore() {
        return 57;
    }

    public final int astore() {
        return 58;
    }

    public final int istore_0() {
        return 59;
    }

    public final int istore_1() {
        return 60;
    }

    public final int istore_2() {
        return 61;
    }

    public final int istore_3() {
        return 62;
    }

    public final int lstore_0() {
        return 63;
    }

    public final int lstore_1() {
        return 64;
    }

    public final int lstore_2() {
        return 65;
    }

    public final int lstore_3() {
        return 66;
    }

    public final int fstore_0() {
        return 67;
    }

    public final int fstore_1() {
        return 68;
    }

    public final int fstore_2() {
        return 69;
    }

    public final int fstore_3() {
        return 70;
    }

    public final int dstore_0() {
        return 71;
    }

    public final int dstore_1() {
        return 72;
    }

    public final int dstore_2() {
        return 73;
    }

    public final int dstore_3() {
        return 74;
    }

    public final int astore_0() {
        return 75;
    }

    public final int astore_1() {
        return 76;
    }

    public final int astore_2() {
        return 77;
    }

    public final int astore_3() {
        return 78;
    }

    public final int iastore() {
        return 79;
    }

    public final int lastore() {
        return 80;
    }

    public final int fastore() {
        return 81;
    }

    public final int dastore() {
        return 82;
    }

    public final int aastore() {
        return 83;
    }

    public final int bastore() {
        return 84;
    }

    public final int castore() {
        return 85;
    }

    public final int sastore() {
        return 86;
    }

    public final int pop() {
        return 87;
    }

    public final int pop2() {
        return 88;
    }

    public final int dup() {
        return 89;
    }

    public final int dup_x1() {
        return 90;
    }

    public final int dup_x2() {
        return 91;
    }

    public final int dup2() {
        return 92;
    }

    public final int dup2_x1() {
        return 93;
    }

    public final int dup2_x2() {
        return 94;
    }

    public final int swap() {
        return 95;
    }

    public final int iadd() {
        return 96;
    }

    public final int ladd() {
        return 97;
    }

    public final int fadd() {
        return 98;
    }

    public final int dadd() {
        return 99;
    }

    public final int isub() {
        return 100;
    }

    public final int lsub() {
        return 101;
    }

    public final int fsub() {
        return 102;
    }

    public final int dsub() {
        return 103;
    }

    public final int imul() {
        return 104;
    }

    public final int lmul() {
        return 105;
    }

    public final int fmul() {
        return 106;
    }

    public final int dmul() {
        return 107;
    }

    public final int idiv() {
        return 108;
    }

    public final int ldiv() {
        return 109;
    }

    public final int fdiv() {
        return 110;
    }

    public final int ddiv() {
        return 111;
    }

    public final int irem() {
        return 112;
    }

    public final int lrem() {
        return 113;
    }

    public final int frem() {
        return 114;
    }

    public final int drem() {
        return 115;
    }

    public final int ineg() {
        return 116;
    }

    public final int lneg() {
        return 117;
    }

    public final int fneg() {
        return 118;
    }

    public final int dneg() {
        return 119;
    }

    public final int ishl() {
        return 120;
    }

    public final int lshl() {
        return 121;
    }

    public final int ishr() {
        return 122;
    }

    public final int lshr() {
        return 123;
    }

    public final int iushr() {
        return 124;
    }

    public final int lushr() {
        return 125;
    }

    public final int iand() {
        return 126;
    }

    public final int land() {
        return 127;
    }

    public final int ior() {
        return 128;
    }

    public final int lor() {
        return 129;
    }

    public final int ixor() {
        return 130;
    }

    public final int lxor() {
        return 131;
    }

    public final int iinc() {
        return 132;
    }

    public final int i2l() {
        return 133;
    }

    public final int i2f() {
        return 134;
    }

    public final int i2d() {
        return 135;
    }

    public final int l2i() {
        return 136;
    }

    public final int l2f() {
        return 137;
    }

    public final int l2d() {
        return 138;
    }

    public final int f2i() {
        return 139;
    }

    public final int f2l() {
        return 140;
    }

    public final int f2d() {
        return 141;
    }

    public final int d2i() {
        return 142;
    }

    public final int d2l() {
        return 143;
    }

    public final int d2f() {
        return 144;
    }

    public final int i2b() {
        return 145;
    }

    public final int i2c() {
        return 146;
    }

    public final int i2s() {
        return 147;
    }

    public final int lcmp() {
        return 148;
    }

    public final int fcmpl() {
        return 149;
    }

    public final int fcmpg() {
        return 150;
    }

    public final int dcmpl() {
        return 151;
    }

    public final int dcmpg() {
        return 152;
    }

    public final int ifeq() {
        return 153;
    }

    public final int ifne() {
        return 154;
    }

    public final int iflt() {
        return 155;
    }

    public final int ifge() {
        return 156;
    }

    public final int ifgt() {
        return 157;
    }

    public final int ifle() {
        return 158;
    }

    public final int if_icmpeq() {
        return 159;
    }

    public final int if_icmpne() {
        return 160;
    }

    public final int if_icmplt() {
        return 161;
    }

    public final int if_icmpge() {
        return 162;
    }

    public final int if_icmpgt() {
        return 163;
    }

    public final int if_icmple() {
        return 164;
    }

    public final int if_acmpeq() {
        return 165;
    }

    public final int if_acmpne() {
        return 166;
    }

    public final int goto() {
        return 167;
    }

    public final int jsr() {
        return 168;
    }

    public final int ret() {
        return 169;
    }

    public final int tableswitch() {
        return 170;
    }

    public final int lookupswitch() {
        return 171;
    }

    public final int ireturn() {
        return 172;
    }

    public final int lreturn() {
        return 173;
    }

    public final int freturn() {
        return 174;
    }

    public final int dreturn() {
        return 175;
    }

    public final int areturn() {
        return 176;
    }

    public final int return_() {
        return 177;
    }

    public final int getstatic() {
        return 178;
    }

    public final int putstatic() {
        return 179;
    }

    public final int getfield() {
        return 180;
    }

    public final int putfield() {
        return 181;
    }

    public final int invokevirtual() {
        return 182;
    }

    public final int invokespecial() {
        return 183;
    }

    public final int invokestatic() {
        return 184;
    }

    public final int invokeinterface() {
        return 185;
    }

    public final int invokedynamic() {
        return 186;
    }

    public final int new_() {
        return 187;
    }

    public final int newarray() {
        return 188;
    }

    public final int anewarray() {
        return 189;
    }

    public final int arraylength() {
        return 190;
    }

    public final int athrow() {
        return 191;
    }

    public final int checkcast() {
        return 192;
    }

    public final int instanceof() {
        return 193;
    }

    public final int monitorenter() {
        return 194;
    }

    public final int monitorexit() {
        return 195;
    }

    public final int wide() {
        return 196;
    }

    public final int multianewarray() {
        return 197;
    }

    public final int ifnull() {
        return 198;
    }

    public final int ifnonnull() {
        return 199;
    }

    public final int goto_w() {
        return 200;
    }

    public final int jsr_w() {
        return 201;
    }

    public final int breakpoint() {
        return 202;
    }

    public final int impdep1() {
        return 254;
    }

    public final int impdep2() {
        return 255;
    }

    public long toScalaMethodFlags(int flags) {
        return ClassfileConstants$FlagTranslation$.MODULE$.methodFlags(flags);
    }

    public long toScalaClassFlags(int flags) {
        return ClassfileConstants$FlagTranslation$.MODULE$.classFlags(flags);
    }

    public long toScalaFieldFlags(int flags) {
        return ClassfileConstants$FlagTranslation$.MODULE$.fieldFlags(flags);
    }

    private ClassfileConstants$() {
        MODULE$ = this;
    }
}

