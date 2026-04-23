/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal.pickling;

public final class PickleFormat$ {
    public static final PickleFormat$ MODULE$;
    private final int MajorVersion;
    private final int MinorVersion;
    private final int TERMname;
    private final int TYPEname;
    private final int NONEsym;
    private final int TYPEsym;
    private final int ALIASsym;
    private final int CLASSsym;
    private final int MODULEsym;
    private final int VALsym;
    private final int EXTref;
    private final int EXTMODCLASSref;
    private final int NOtpe;
    private final int NOPREFIXtpe;
    private final int THIStpe;
    private final int SINGLEtpe;
    private final int CONSTANTtpe;
    private final int TYPEREFtpe;
    private final int TYPEBOUNDStpe;
    private final int REFINEDtpe;
    private final int CLASSINFOtpe;
    private final int METHODtpe;
    private final int POLYtpe;
    private final int IMPLICITMETHODtpe;
    private final int LITERAL;
    private final int LITERALunit;
    private final int LITERALboolean;
    private final int LITERALbyte;
    private final int LITERALshort;
    private final int LITERALchar;
    private final int LITERALint;
    private final int LITERALlong;
    private final int LITERALfloat;
    private final int LITERALdouble;
    private final int LITERALstring;
    private final int LITERALnull;
    private final int LITERALclass;
    private final int LITERALenum;
    private final int SYMANNOT;
    private final int CHILDREN;
    private final int ANNOTATEDtpe;
    private final int ANNOTINFO;
    private final int ANNOTARGARRAY;
    private final int SUPERtpe;
    private final int DEBRUIJNINDEXtpe;
    private final int EXISTENTIALtpe;
    private final int TREE;
    private final int EMPTYtree;
    private final int PACKAGEtree;
    private final int CLASStree;
    private final int MODULEtree;
    private final int VALDEFtree;
    private final int DEFDEFtree;
    private final int TYPEDEFtree;
    private final int LABELtree;
    private final int IMPORTtree;
    private final int DOCDEFtree;
    private final int TEMPLATEtree;
    private final int BLOCKtree;
    private final int CASEtree;
    private final int ALTERNATIVEtree;
    private final int STARtree;
    private final int BINDtree;
    private final int UNAPPLYtree;
    private final int ARRAYVALUEtree;
    private final int FUNCTIONtree;
    private final int ASSIGNtree;
    private final int IFtree;
    private final int MATCHtree;
    private final int RETURNtree;
    private final int TREtree;
    private final int THROWtree;
    private final int NEWtree;
    private final int TYPEDtree;
    private final int TYPEAPPLYtree;
    private final int APPLYtree;
    private final int APPLYDYNAMICtree;
    private final int SUPERtree;
    private final int THIStree;
    private final int SELECTtree;
    private final int IDENTtree;
    private final int LITERALtree;
    private final int TYPEtree;
    private final int ANNOTATEDtree;
    private final int SINGLETONTYPEtree;
    private final int SELECTFROMTYPEtree;
    private final int COMPOUNDTYPEtree;
    private final int APPLIEDTYPEtree;
    private final int TYPEBOUNDStree;
    private final int EXISTENTIALTYPEtree;
    private final int MODIFIERS;
    private final int firstSymTag;
    private final int lastSymTag;
    private final int lastExtSymTag;

    static {
        new PickleFormat$();
    }

    public int MajorVersion() {
        return this.MajorVersion;
    }

    public int MinorVersion() {
        return this.MinorVersion;
    }

    public final int TERMname() {
        return 1;
    }

    public final int TYPEname() {
        return 2;
    }

    public final int NONEsym() {
        return 3;
    }

    public final int TYPEsym() {
        return 4;
    }

    public final int ALIASsym() {
        return 5;
    }

    public final int CLASSsym() {
        return 6;
    }

    public final int MODULEsym() {
        return 7;
    }

    public final int VALsym() {
        return 8;
    }

    public final int EXTref() {
        return 9;
    }

    public final int EXTMODCLASSref() {
        return 10;
    }

    public final int NOtpe() {
        return 11;
    }

    public final int NOPREFIXtpe() {
        return 12;
    }

    public final int THIStpe() {
        return 13;
    }

    public final int SINGLEtpe() {
        return 14;
    }

    public final int CONSTANTtpe() {
        return 15;
    }

    public final int TYPEREFtpe() {
        return 16;
    }

    public final int TYPEBOUNDStpe() {
        return 17;
    }

    public final int REFINEDtpe() {
        return 18;
    }

    public final int CLASSINFOtpe() {
        return 19;
    }

    public final int METHODtpe() {
        return 20;
    }

    public final int POLYtpe() {
        return 21;
    }

    public final int IMPLICITMETHODtpe() {
        return 22;
    }

    public final int LITERAL() {
        return 23;
    }

    public final int LITERALunit() {
        return 24;
    }

    public final int LITERALboolean() {
        return 25;
    }

    public final int LITERALbyte() {
        return 26;
    }

    public final int LITERALshort() {
        return 27;
    }

    public final int LITERALchar() {
        return 28;
    }

    public final int LITERALint() {
        return 29;
    }

    public final int LITERALlong() {
        return 30;
    }

    public final int LITERALfloat() {
        return 31;
    }

    public final int LITERALdouble() {
        return 32;
    }

    public final int LITERALstring() {
        return 33;
    }

    public final int LITERALnull() {
        return 34;
    }

    public final int LITERALclass() {
        return 35;
    }

    public final int LITERALenum() {
        return 36;
    }

    public final int SYMANNOT() {
        return 40;
    }

    public final int CHILDREN() {
        return 41;
    }

    public final int ANNOTATEDtpe() {
        return 42;
    }

    public final int ANNOTINFO() {
        return 43;
    }

    public final int ANNOTARGARRAY() {
        return 44;
    }

    public final int SUPERtpe() {
        return 46;
    }

    public final int DEBRUIJNINDEXtpe() {
        return 47;
    }

    public final int EXISTENTIALtpe() {
        return 48;
    }

    public final int TREE() {
        return 49;
    }

    public final int EMPTYtree() {
        return 1;
    }

    public final int PACKAGEtree() {
        return 2;
    }

    public final int CLASStree() {
        return 3;
    }

    public final int MODULEtree() {
        return 4;
    }

    public final int VALDEFtree() {
        return 5;
    }

    public final int DEFDEFtree() {
        return 6;
    }

    public final int TYPEDEFtree() {
        return 7;
    }

    public final int LABELtree() {
        return 8;
    }

    public final int IMPORTtree() {
        return 9;
    }

    public final int DOCDEFtree() {
        return 11;
    }

    public final int TEMPLATEtree() {
        return 12;
    }

    public final int BLOCKtree() {
        return 13;
    }

    public final int CASEtree() {
        return 14;
    }

    public final int ALTERNATIVEtree() {
        return 16;
    }

    public final int STARtree() {
        return 17;
    }

    public final int BINDtree() {
        return 18;
    }

    public final int UNAPPLYtree() {
        return 19;
    }

    public final int ARRAYVALUEtree() {
        return 20;
    }

    public final int FUNCTIONtree() {
        return 21;
    }

    public final int ASSIGNtree() {
        return 22;
    }

    public final int IFtree() {
        return 23;
    }

    public final int MATCHtree() {
        return 24;
    }

    public final int RETURNtree() {
        return 25;
    }

    public final int TREtree() {
        return 26;
    }

    public final int THROWtree() {
        return 27;
    }

    public final int NEWtree() {
        return 28;
    }

    public final int TYPEDtree() {
        return 29;
    }

    public final int TYPEAPPLYtree() {
        return 30;
    }

    public final int APPLYtree() {
        return 31;
    }

    public final int APPLYDYNAMICtree() {
        return 32;
    }

    public final int SUPERtree() {
        return 33;
    }

    public final int THIStree() {
        return 34;
    }

    public final int SELECTtree() {
        return 35;
    }

    public final int IDENTtree() {
        return 36;
    }

    public final int LITERALtree() {
        return 37;
    }

    public final int TYPEtree() {
        return 38;
    }

    public final int ANNOTATEDtree() {
        return 39;
    }

    public final int SINGLETONTYPEtree() {
        return 40;
    }

    public final int SELECTFROMTYPEtree() {
        return 41;
    }

    public final int COMPOUNDTYPEtree() {
        return 42;
    }

    public final int APPLIEDTYPEtree() {
        return 43;
    }

    public final int TYPEBOUNDStree() {
        return 44;
    }

    public final int EXISTENTIALTYPEtree() {
        return 45;
    }

    public final int MODIFIERS() {
        return 50;
    }

    public final int firstSymTag() {
        return 3;
    }

    public final int lastSymTag() {
        return 8;
    }

    public final int lastExtSymTag() {
        return 10;
    }

    private PickleFormat$() {
        MODULE$ = this;
        this.MajorVersion = 5;
        this.MinorVersion = 0;
    }
}

