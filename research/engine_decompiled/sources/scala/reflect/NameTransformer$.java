/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect;

import scala.Predef$;
import scala.Serializable;
import scala.collection.immutable.StringOps;
import scala.collection.mutable.StringBuilder;
import scala.reflect.NameTransformer;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.sys.package$;

public final class NameTransformer$ {
    public static final NameTransformer$ MODULE$;
    private final String MODULE_SUFFIX_STRING;
    private final String NAME_JOIN_STRING;
    private final String MODULE_INSTANCE_NAME;
    private final String LOCAL_SUFFIX_STRING;
    private final String SETTER_SUFFIX_STRING;
    private final String TRAIT_SETTER_SEPARATOR_STRING;
    private final int nops;
    private final int ncodes;
    private final String[] op2code;
    private final NameTransformer.OpCodes[] code2op;

    static {
        new NameTransformer$();
    }

    public String MODULE_SUFFIX_STRING() {
        return this.MODULE_SUFFIX_STRING;
    }

    public String NAME_JOIN_STRING() {
        return this.NAME_JOIN_STRING;
    }

    public String MODULE_INSTANCE_NAME() {
        return this.MODULE_INSTANCE_NAME;
    }

    public String LOCAL_SUFFIX_STRING() {
        return this.LOCAL_SUFFIX_STRING;
    }

    public String SETTER_SUFFIX_STRING() {
        return this.SETTER_SUFFIX_STRING;
    }

    public String TRAIT_SETTER_SEPARATOR_STRING() {
        return this.TRAIT_SETTER_SEPARATOR_STRING;
    }

    private int nops() {
        return this.nops;
    }

    private int ncodes() {
        return this.ncodes;
    }

    private String[] op2code() {
        return this.op2code;
    }

    private NameTransformer.OpCodes[] code2op() {
        return this.code2op;
    }

    private void enterOp(char op, String code) {
        this.op2code()[op] = code;
        int c = (code.charAt(1) - 97) * 26 + code.charAt(2) - 97;
        this.code2op()[c] = new NameTransformer.OpCodes(op, code, this.code2op()[c]);
    }

    public String encode(String name) {
        StringBuilder buf = null;
        int len = name.length();
        for (int i = 0; i < len; ++i) {
            java.io.Serializable serializable;
            java.io.Serializable serializable2;
            char c = name.charAt(i);
            if (c < this.nops() && this.op2code()[c] != null) {
                java.io.Serializable serializable3;
                if (buf == null) {
                    buf = new StringBuilder();
                    serializable3 = buf.append(name.substring(0, i));
                } else {
                    serializable3 = BoxedUnit.UNIT;
                }
                serializable2 = buf.append(this.op2code()[c]);
                continue;
            }
            if (Character.isJavaIdentifierPart(c)) {
                if (buf != null) {
                    serializable2 = buf.append(c);
                    continue;
                }
                serializable2 = BoxedUnit.UNIT;
                continue;
            }
            if (buf == null) {
                buf = new StringBuilder();
                serializable = buf.append(name.substring(0, i));
            } else {
                serializable = BoxedUnit.UNIT;
            }
            Predef$ predef$ = Predef$.MODULE$;
            serializable2 = buf.append(new StringOps("$u%04X").format(Predef$.MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(c)})));
        }
        return buf == null ? name : buf.toString();
    }

    /*
     * Exception decompiling
     */
    public String decode(String name0) {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: Back jump on a try block [egrp 0[TRYBLOCK] [0 : 358->422)] java.lang.NumberFormatException
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op02WithProcessedDataAndRefs.insertExceptionBlocks(Op02WithProcessedDataAndRefs.java:2283)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:415)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:278)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:201)
         *     at org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:94)
         *     at org.benf.cfr.reader.entities.Method.analyse(Method.java:531)
         *     at org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:1055)
         *     at org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:942)
         *     at org.benf.cfr.reader.Driver.doJarVersionTypes(Driver.java:257)
         *     at org.benf.cfr.reader.Driver.doJar(Driver.java:139)
         *     at org.benf.cfr.reader.CfrDriverImpl.analyse(CfrDriverImpl.java:76)
         *     at org.benf.cfr.reader.Main.main(Main.java:54)
         */
        throw new IllegalStateException("Decompilation failed");
    }

    private NameTransformer$() {
        MODULE$ = this;
        this.MODULE_SUFFIX_STRING = package$.MODULE$.props().getOrElse("SCALA_MODULE_SUFFIX_STRING", new Serializable(){
            public static final long serialVersionUID = 0L;

            public final String apply() {
                return "$";
            }
        });
        this.NAME_JOIN_STRING = package$.MODULE$.props().getOrElse("SCALA_NAME_JOIN_STRING", new Serializable(){
            public static final long serialVersionUID = 0L;

            public final String apply() {
                return "$";
            }
        });
        this.MODULE_INSTANCE_NAME = "MODULE$";
        this.LOCAL_SUFFIX_STRING = " ";
        this.SETTER_SUFFIX_STRING = "_$eq";
        this.TRAIT_SETTER_SEPARATOR_STRING = "$_setter_$";
        this.nops = 128;
        this.ncodes = 676;
        this.op2code = new String[this.nops()];
        this.code2op = new NameTransformer.OpCodes[this.ncodes()];
        this.enterOp('~', "$tilde");
        this.enterOp('=', "$eq");
        this.enterOp('<', "$less");
        this.enterOp('>', "$greater");
        this.enterOp('!', "$bang");
        this.enterOp('#', "$hash");
        this.enterOp('%', "$percent");
        this.enterOp('^', "$up");
        this.enterOp('&', "$amp");
        this.enterOp('|', "$bar");
        this.enterOp('*', "$times");
        this.enterOp('/', "$div");
        this.enterOp('+', "$plus");
        this.enterOp('-', "$minus");
        this.enterOp(':', "$colon");
        this.enterOp('\\', "$bslash");
        this.enterOp('?', "$qmark");
        this.enterOp('@', "$at");
    }
}

