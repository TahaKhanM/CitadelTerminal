/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.api;

import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.reflect.api.Annotations;
import scala.reflect.api.Constants;
import scala.reflect.api.Mirror;
import scala.reflect.api.Names;
import scala.reflect.api.Position;
import scala.reflect.api.Scopes;
import scala.reflect.api.Symbols;
import scala.reflect.api.Trees;
import scala.reflect.api.Types;

@ScalaSignature(bytes="\u0006\u0001\u0015uc!C\u0001\u0003!\u0003\r\n!CC+\u00051IU\u000e\u001d7jG&$H+Y4t\u0015\t\u0019A!A\u0002ba&T!!\u0002\u0004\u0002\u000fI,g\r\\3di*\tq!A\u0003tG\u0006d\u0017m\u0001\u0001\u0014\u0005\u0001Q\u0001CA\u0006\r\u001b\u00051\u0011BA\u0007\u0007\u0005\u0019\te.\u001f*fM\"9q\u0002\u0001b\u0001\u000e\u0007\u0001\u0012\u0001E!o]>$\u0018\r^3e)f\u0004X\rV1h+\u0005\t\u0002c\u0001\n\u0014+5\tA!\u0003\u0002\u0015\t\tA1\t\\1tgR\u000bw\r\u0005\u0002\u0017/5\t\u0001!\u0003\u0002\u00193\ti\u0011I\u001c8pi\u0006$X\r\u001a+za\u0016L!A\u0007\u0002\u0003\u000bQK\b/Z:\t\u000fq\u0001!\u0019!D\u0002;\u00051\"i\\;oI\u0016$w+\u001b7eG\u0006\u0014H\rV=qKR\u000bw-F\u0001\u001f!\r\u00112c\b\t\u0003-\u0001J!!I\r\u0003'\t{WO\u001c3fI^KG\u000eZ2be\u0012$\u0016\u0010]3\t\u000f\r\u0002!\u0019!D\u0002I\u0005\u00012\t\\1tg&sgm\u001c+za\u0016$\u0016mZ\u000b\u0002KA\u0019!c\u0005\u0014\u0011\u0005Y9\u0013B\u0001\u0015\u001a\u00055\u0019E.Y:t\u0013:4w\u000eV=qK\"9!\u0006\u0001b\u0001\u000e\u0007Y\u0013aD\"p[B|WO\u001c3UsB,G+Y4\u0016\u00031\u00022AE\n.!\t1b&\u0003\u000203\ta1i\\7q_VtG\rV=qK\"9\u0011\u0007\u0001b\u0001\u000e\u0007\u0011\u0014aD\"p]N$\u0018M\u001c;UsB,G+Y4\u0016\u0003M\u00022AE\n5!\t1R'\u0003\u000273\ta1i\u001c8ti\u0006tG\u000fV=qK\"9\u0001\b\u0001b\u0001\u000e\u0007I\u0014AE#ySN$XM\u001c;jC2$\u0016\u0010]3UC\u001e,\u0012A\u000f\t\u0004%MY\u0004C\u0001\f=\u0013\ti\u0014DA\bFq&\u001cH/\u001a8uS\u0006dG+\u001f9f\u0011\u001dy\u0004A1A\u0007\u0004\u0001\u000bQ\"T3uQ>$G+\u001f9f)\u0006<W#A!\u0011\u0007I\u0019\"\t\u0005\u0002\u0017\u0007&\u0011A)\u0007\u0002\u000b\u001b\u0016$\bn\u001c3UsB,\u0007b\u0002$\u0001\u0005\u00045\u0019aR\u0001\u0015\u001dVdG.\u0019:z\u001b\u0016$\bn\u001c3UsB,G+Y4\u0016\u0003!\u00032AE\nJ!\t1\"*\u0003\u0002L3\t\tb*\u001e7mCJLX*\u001a;i_\u0012$\u0016\u0010]3\t\u000f5\u0003!\u0019!D\u0002\u001d\u0006Y\u0001k\u001c7z)f\u0004X\rV1h+\u0005y\u0005c\u0001\n\u0014!B\u0011a#U\u0005\u0003%f\u0011\u0001\u0002U8msRK\b/\u001a\u0005\b)\u0002\u0011\rQb\u0001V\u00039\u0011VMZ5oK\u0012$\u0016\u0010]3UC\u001e,\u0012A\u0016\t\u0004%M9\u0006C\u0001\fY\u0013\tI\u0016DA\u0006SK\u001aLg.\u001a3UsB,\u0007bB.\u0001\u0005\u00045\u0019\u0001X\u0001\u000e'&tw\r\\3UsB,G+Y4\u0016\u0003u\u00032AE\n_!\t1r,\u0003\u0002a3\tQ1+\u001b8hY\u0016$\u0016\u0010]3\t\u000f\t\u0004!\u0019!D\u0002G\u0006\u00012+\u001b8hY\u0016$xN\u001c+za\u0016$\u0016mZ\u000b\u0002IB\u0019!cE3\u0011\u0005Y1\u0017BA4\u001a\u00055\u0019\u0016N\\4mKR|g\u000eV=qK\"9\u0011\u000e\u0001b\u0001\u000e\u0007Q\u0017\u0001D*va\u0016\u0014H+\u001f9f)\u0006<W#A6\u0011\u0007I\u0019B\u000e\u0005\u0002\u0017[&\u0011a.\u0007\u0002\n'V\u0004XM\u001d+za\u0016Dq\u0001\u001d\u0001C\u0002\u001b\r\u0011/A\u0006UQ&\u001cH+\u001f9f)\u0006<W#\u0001:\u0011\u0007I\u00192\u000f\u0005\u0002\u0017i&\u0011Q/\u0007\u0002\t)\"L7\u000fV=qK\"9q\u000f\u0001b\u0001\u000e\u0007A\u0018!\u0004+za\u0016\u0014u.\u001e8egR\u000bw-F\u0001z!\r\u00112C\u001f\t\u0003-mL!\u0001`\r\u0003\u0015QK\b/\u001a\"pk:$7\u000fC\u0004\u007f\u0001\t\u0007i1A@\u0002\u0015QK\b/\u001a*fMR\u000bw-\u0006\u0002\u0002\u0002A!!cEA\u0002!\r1\u0012QA\u0005\u0004\u0003\u000fI\"a\u0002+za\u0016\u0014VM\u001a\u0005\n\u0003\u0017\u0001!\u0019!D\u0002\u0003\u001b\t\u0001\u0002V=qKR\u000bwmZ\u000b\u0003\u0003\u001f\u0001BAE\n\u0002\u0012A\u0019a#a\u0005\n\u0007\u0005U\u0011D\u0001\u0003UsB,\u0007\"CA\r\u0001\t\u0007i1AA\u000e\u0003\u001dq\u0015-\\3UC\u001e,\"!!\b\u0011\tI\u0019\u0012q\u0004\t\u0004-\u0005\u0005\u0012\u0002BA\u0012\u0003K\u0011AAT1nK&\u0019\u0011q\u0005\u0002\u0003\u000b9\u000bW.Z:\t\u0013\u0005-\u0002A1A\u0007\u0004\u00055\u0012a\u0003+fe6t\u0015-\\3UC\u001e,\"!a\f\u0011\tI\u0019\u0012\u0011\u0007\t\u0004-\u0005M\u0012\u0002BA\u001b\u0003K\u0011\u0001\u0002V3s[:\u000bW.\u001a\u0005\n\u0003s\u0001!\u0019!D\u0002\u0003w\t1\u0002V=qK:\u000bW.\u001a+bOV\u0011\u0011Q\b\t\u0005%M\ty\u0004E\u0002\u0017\u0003\u0003JA!a\u0011\u0002&\tAA+\u001f9f\u001d\u0006lW\rC\u0005\u0002H\u0001\u0011\rQb\u0001\u0002J\u0005A1kY8qKR\u000bw-\u0006\u0002\u0002LA!!cEA'!\r1\u0012qJ\u0005\u0005\u0003#\n\u0019FA\u0003TG>\u0004X-C\u0002\u0002V\t\u0011aaU2pa\u0016\u001c\b\"CA-\u0001\t\u0007i1AA.\u00039iU-\u001c2feN\u001bw\u000e]3UC\u001e,\"!!\u0018\u0011\tI\u0019\u0012q\f\t\u0004-\u0005\u0005\u0014\u0002BA2\u0003'\u00121\"T3nE\u0016\u00148kY8qK\"I\u0011q\r\u0001C\u0002\u001b\r\u0011\u0011N\u0001\u000e\u0003:tw\u000e^1uS>tG+Y4\u0016\u0005\u0005-\u0004\u0003\u0002\n\u0014\u0003[\u00022AFA8\u0013\u0011\t\t(a\u001d\u0003\u0015\u0005sgn\u001c;bi&|g.C\u0002\u0002v\t\u00111\"\u00118o_R\fG/[8og\"I\u0011\u0011\u0010\u0001C\u0002\u001b\r\u00111P\u0001\u0010\u0015\u00064\u0018-\u0011:hk6,g\u000e\u001e+bOV\u0011\u0011Q\u0010\t\u0005%M\ty\bE\u0002\u0017\u0003\u0003KA!a!\u0002t\ta!*\u0019<b\u0003J<W/\\3oi\"I\u0011q\u0011\u0001C\u0002\u001b\r\u0011\u0011R\u0001\u0013\u0019&$XM]1m\u0003J<W/\\3oiR\u000bw-\u0006\u0002\u0002\fB!!cEAG!\r1\u0012qR\u0005\u0005\u0003#\u000b\u0019HA\bMSR,'/\u00197Be\u001e,X.\u001a8u\u0011%\t)\n\u0001b\u0001\u000e\u0007\t9*\u0001\tBeJ\f\u00170\u0011:hk6,g\u000e\u001e+bOV\u0011\u0011\u0011\u0014\t\u0005%M\tY\nE\u0002\u0017\u0003;KA!a(\u0002t\ti\u0011I\u001d:bs\u0006\u0013x-^7f]RD\u0011\"a)\u0001\u0005\u00045\u0019!!*\u0002#9+7\u000f^3e\u0003J<W/\\3oiR\u000bw-\u0006\u0002\u0002(B!!cEAU!\r1\u00121V\u0005\u0005\u0003[\u000b\u0019H\u0001\bOKN$X\rZ!sOVlWM\u001c;\t\u0013\u0005E\u0006A1A\u0007\u0004\u0005M\u0016!\u0004+fe6\u001c\u00160\u001c2pYR\u000bw-\u0006\u0002\u00026B!!cEA\\!\r1\u0012\u0011X\u0005\u0005\u0003w\u000biL\u0001\u0006UKJl7+_7c_2L1!a0\u0003\u0005\u001d\u0019\u00160\u001c2pYND\u0011\"a1\u0001\u0005\u00045\u0019!!2\u0002\u001f5+G\u000f[8e'fl'm\u001c7UC\u001e,\"!a2\u0011\tI\u0019\u0012\u0011\u001a\t\u0004-\u0005-\u0017\u0002BAg\u0003{\u0013A\"T3uQ>$7+_7c_2D\u0011\"!5\u0001\u0005\u00045\u0019!a5\u0002\u0013MKXNY8m)\u0006<WCAAk!\u0011\u00112#a6\u0011\u0007Y\tI.\u0003\u0003\u0002\\\u0006u&AB*z[\n|G\u000eC\u0005\u0002`\u0002\u0011\rQb\u0001\u0002b\u0006iA+\u001f9f'fl'm\u001c7UC\u001e,\"!a9\u0011\tI\u0019\u0012Q\u001d\t\u0004-\u0005\u001d\u0018\u0002BAu\u0003{\u0013!\u0002V=qKNKXNY8m\u0011%\ti\u000f\u0001b\u0001\u000e\u0007\ty/A\bN_\u0012,H.Z*z[\n|G\u000eV1h+\t\t\t\u0010\u0005\u0003\u0013'\u0005M\bc\u0001\f\u0002v&!\u0011q_A_\u00051iu\u000eZ;mKNKXNY8m\u0011%\tY\u0010\u0001b\u0001\u000e\u0007\ti0\u0001\bDY\u0006\u001c8oU=nE>dG+Y4\u0016\u0005\u0005}\b\u0003\u0002\n\u0014\u0005\u0003\u00012A\u0006B\u0002\u0013\u0011\u0011)!!0\u0003\u0017\rc\u0017m]:Ts6\u0014w\u000e\u001c\u0005\n\u0005\u0013\u0001!\u0019!D\u0002\u0005\u0017\t1\u0002U8tSRLwN\u001c+bOV\u0011!Q\u0002\t\u0005%M\u0011y\u0001E\u0002\u0017\u0005#IAAa\u0005\u0003\u0016\tA\u0001k\\:ji&|g.C\u0002\u0003\u0018\t\u0011\u0011\u0002U8tSRLwN\\:\t\u0013\tm\u0001A1A\u0007\u0004\tu\u0011aC\"p]N$\u0018M\u001c;UC\u001e,\"Aa\b\u0011\tI\u0019\"\u0011\u0005\t\u0004-\t\r\u0012\u0002\u0002B\u0013\u0005O\u0011\u0001bQ8ogR\fg\u000e^\u0005\u0004\u0005S\u0011!!C\"p]N$\u0018M\u001c;t\u0011%\u0011i\u0003\u0001b\u0001\u000e\u0007\u0011y#\u0001\u0006GY\u0006<7+\u001a;UC\u001e,\"A!\r\u0011\tI\u0019\"1\u0007\t\u0004-\tU\u0012\u0002\u0002B\u001c\u0005s\u0011qA\u00127bON+G/C\u0002\u0003<\t\u0011\u0001B\u00127bON+Go\u001d\u0005\n\u0005\u007f\u0001!\u0019!D\u0002\u0005\u0003\nA\"T8eS\u001aLWM]:UC\u001e,\"Aa\u0011\u0011\tI\u0019\"Q\t\t\u0004-\t\u001d\u0013\u0002\u0002B%\u0005\u0017\u0012\u0011\"T8eS\u001aLWM]:\n\u0007\t5#AA\u0003Ue\u0016,7\u000fC\u0005\u0003R\u0001\u0011\rQb\u0001\u0003T\u0005q\u0011\t\u001c;fe:\fG/\u001b<f)\u0006<WC\u0001B+!\u0011\u00112Ca\u0016\u0011\u0007Y\u0011I&\u0003\u0003\u0003\\\t-#aC!mi\u0016\u0014h.\u0019;jm\u0016D\u0011Ba\u0018\u0001\u0005\u00045\u0019A!\u0019\u0002\u0019\u0005sgn\u001c;bi\u0016$G+Y4\u0016\u0005\t\r\u0004\u0003\u0002\n\u0014\u0005K\u00022A\u0006B4\u0013\u0011\u0011IGa\u0013\u0003\u0013\u0005sgn\u001c;bi\u0016$\u0007\"\u0003B7\u0001\t\u0007i1\u0001B8\u0003I\t\u0005\u000f\u001d7jK\u0012$\u0016\u0010]3Ue\u0016,G+Y4\u0016\u0005\tE\u0004\u0003\u0002\n\u0014\u0005g\u00022A\u0006B;\u0013\u0011\u00119Ha\u0013\u0003\u001f\u0005\u0003\b\u000f\\5fIRK\b/\u001a+sK\u0016D\u0011Ba\u001f\u0001\u0005\u00045\u0019A! \u0002\u0011\u0005\u0003\b\u000f\\=UC\u001e,\"Aa \u0011\tI\u0019\"\u0011\u0011\t\u0004-\t\r\u0015\u0002\u0002BC\u0005\u0017\u0012Q!\u00119qYfD\u0011B!#\u0001\u0005\u00045\u0019Aa#\u0002'\u0005\u001b8/[4o\u001fJt\u0015-\\3e\u0003J<G+Y4\u0016\u0005\t5\u0005\u0003\u0002\n\u0014\u0005\u001f\u00032A\u0006BI\u0013\u0011\u0011\u0019Ja\u0013\u0003!\u0005\u001b8/[4o\u001fJt\u0015-\\3e\u0003J<\u0007\"\u0003BL\u0001\t\u0007i1\u0001BM\u0003%\t5o]5h]R\u000bw-\u0006\u0002\u0003\u001cB!!c\u0005BO!\r1\"qT\u0005\u0005\u0005C\u0013YE\u0001\u0004BgNLwM\u001c\u0005\n\u0005K\u0003!\u0019!D\u0002\u0005O\u000bqAQ5oIR\u000bw-\u0006\u0002\u0003*B!!c\u0005BV!\r1\"QV\u0005\u0005\u0005_\u0013YE\u0001\u0003CS:$\u0007\"\u0003BZ\u0001\t\u0007i1\u0001B[\u0003!\u0011En\\2l)\u0006<WC\u0001B\\!\u0011\u00112C!/\u0011\u0007Y\u0011Y,\u0003\u0003\u0003>\n-#!\u0002\"m_\u000e\\\u0007\"\u0003Ba\u0001\t\u0007i1\u0001Bb\u0003)\u0019\u0015m]3EK\u001a$\u0016mZ\u000b\u0003\u0005\u000b\u0004BAE\n\u0003HB\u0019aC!3\n\t\t-'1\n\u0002\b\u0007\u0006\u001cX\rR3g\u0011%\u0011y\r\u0001b\u0001\u000e\u0007\u0011\t.A\u0006DY\u0006\u001c8\u000fR3g)\u0006<WC\u0001Bj!\u0011\u00112C!6\u0011\u0007Y\u00119.\u0003\u0003\u0003Z\n-#\u0001C\"mCN\u001cH)\u001a4\t\u0013\tu\u0007A1A\u0007\u0004\t}\u0017aE\"p[B|WO\u001c3UsB,GK]3f)\u0006<WC\u0001Bq!\u0011\u00112Ca9\u0011\u0007Y\u0011)/\u0003\u0003\u0003h\n-#\u0001E\"p[B|WO\u001c3UsB,GK]3f\u0011%\u0011Y\u000f\u0001b\u0001\u000e\u0007\u0011i/A\u0005EK\u001a$UM\u001a+bOV\u0011!q\u001e\t\u0005%M\u0011\t\u0010E\u0002\u0017\u0005gLAA!>\u0003L\t1A)\u001a4EK\u001aD\u0011B!?\u0001\u0005\u00045\u0019Aa?\u0002\u0015\u0011+g\r\u0016:fKR\u000bw-\u0006\u0002\u0003~B!!c\u0005B\u0000!\r12\u0011A\u0005\u0005\u0007\u0007\u0011YEA\u0004EK\u001a$&/Z3\t\u0013\r\u001d\u0001A1A\u0007\u0004\r%\u0011AF#ySN$XM\u001c;jC2$\u0016\u0010]3Ue\u0016,G+Y4\u0016\u0005\r-\u0001\u0003\u0002\n\u0014\u0007\u001b\u00012AFB\b\u0013\u0011\u0019\tBa\u0013\u0003'\u0015C\u0018n\u001d;f]RL\u0017\r\u001c+za\u0016$&/Z3\t\u0013\rU\u0001A1A\u0007\u0004\r]\u0011a\u0003$v]\u000e$\u0018n\u001c8UC\u001e,\"a!\u0007\u0011\tI\u001921\u0004\t\u0004-\ru\u0011\u0002BB\u0010\u0005\u0017\u0012\u0001BR;oGRLwN\u001c\u0005\n\u0007G\u0001!\u0019!D\u0002\u0007K\tqbR3oKJL7-\u00119qYf$\u0016mZ\u000b\u0003\u0007O\u0001BAE\n\u0004*A\u0019aca\u000b\n\t\r5\"1\n\u0002\r\u000f\u0016tWM]5d\u0003B\u0004H.\u001f\u0005\n\u0007c\u0001!\u0019!D\u0002\u0007g\t\u0001\"\u00133f]R$\u0016mZ\u000b\u0003\u0007k\u0001BAE\n\u00048A\u0019ac!\u000f\n\t\rm\"1\n\u0002\u0006\u0013\u0012,g\u000e\u001e\u0005\n\u0007\u007f\u0001!\u0019!D\u0002\u0007\u0003\nQ!\u00134UC\u001e,\"aa\u0011\u0011\tI\u00192Q\t\t\u0004-\r\u001d\u0013\u0002BB%\u0005\u0017\u0012!!\u00134\t\u0013\r5\u0003A1A\u0007\u0004\r=\u0013AC%na2$UM\u001a+bOV\u00111\u0011\u000b\t\u0005%M\u0019\u0019\u0006E\u0002\u0017\u0007+JAaa\u0016\u0003L\t9\u0011*\u001c9m\t\u00164\u0007\"CB.\u0001\t\u0007i1AB/\u0003EIU\u000e]8siN+G.Z2u_J$\u0016mZ\u000b\u0003\u0007?\u0002BAE\n\u0004bA\u0019aca\u0019\n\t\r\u0015$1\n\u0002\u000f\u00136\u0004xN\u001d;TK2,7\r^8s\u0011%\u0019I\u0007\u0001b\u0001\u000e\u0007\u0019Y'A\u0005J[B|'\u000f\u001e+bOV\u00111Q\u000e\t\u0005%M\u0019y\u0007E\u0002\u0017\u0007cJAaa\u001d\u0003L\t1\u0011*\u001c9peRD\u0011ba\u001e\u0001\u0005\u00045\u0019a!\u001f\u0002\u00171\u000b'-\u001a7EK\u001a$\u0016mZ\u000b\u0003\u0007w\u0002BAE\n\u0004~A\u0019aca \n\t\r\u0005%1\n\u0002\t\u0019\u0006\u0014W\r\u001c#fM\"I1Q\u0011\u0001C\u0002\u001b\r1qQ\u0001\u000b\u0019&$XM]1m)\u0006<WCABE!\u0011\u00112ca#\u0011\u0007Y\u0019i)\u0003\u0003\u0004\u0010\n-#a\u0002'ji\u0016\u0014\u0018\r\u001c\u0005\n\u0007'\u0003!\u0019!D\u0002\u0007+\u000b\u0001\"T1uG\"$\u0016mZ\u000b\u0003\u0007/\u0003BAE\n\u0004\u001aB\u0019aca'\n\t\ru%1\n\u0002\u0006\u001b\u0006$8\r\u001b\u0005\n\u0007C\u0003!\u0019!D\u0002\u0007G\u000bA\"T3nE\u0016\u0014H)\u001a4UC\u001e,\"a!*\u0011\tI\u00192q\u0015\t\u0004-\r%\u0016\u0002BBV\u0005\u0017\u0012\u0011\"T3nE\u0016\u0014H)\u001a4\t\u0013\r=\u0006A1A\u0007\u0004\rE\u0016\u0001D'pIVdW\rR3g)\u0006<WCABZ!\u0011\u00112c!.\u0011\u0007Y\u00199,\u0003\u0003\u0004:\n-#!C'pIVdW\rR3g\u0011%\u0019i\f\u0001b\u0001\u000e\u0007\u0019y,A\u0006OC6,GK]3f)\u0006<WCABa!\u0011\u00112ca1\u0011\u0007Y\u0019)-\u0003\u0003\u0004H\n-#\u0001\u0003(b[\u0016$&/Z3\t\u0013\r-\u0007A1A\u0007\u0004\r5\u0017A\u0002(foR\u000bw-\u0006\u0002\u0004PB!!cEBi!\r121[\u0005\u0005\u0007+\u0014YEA\u0002OK^D\u0011b!7\u0001\u0005\u00045\u0019aa7\u0002\u001bA\u000b7m[1hK\u0012+g\rV1h+\t\u0019i\u000e\u0005\u0003\u0013'\r}\u0007c\u0001\f\u0004b&!11\u001dB&\u0005)\u0001\u0016mY6bO\u0016$UM\u001a\u0005\n\u0007O\u0004!\u0019!D\u0002\u0007S\f!BU3g)J,W\rV1h+\t\u0019Y\u000f\u0005\u0003\u0013'\r5\bc\u0001\f\u0004p&!1\u0011\u001fB&\u0005\u001d\u0011VM\u001a+sK\u0016D\u0011b!>\u0001\u0005\u00045\u0019aa>\u0002\u0013I+G/\u001e:o)\u0006<WCAB}!\u0011\u00112ca?\u0011\u0007Y\u0019i0\u0003\u0003\u0004\u0000\n-#A\u0002*fiV\u0014h\u000eC\u0005\u0005\u0004\u0001\u0011\rQb\u0001\u0005\u0006\u0005)2+\u001a7fGR4%o\\7UsB,GK]3f)\u0006<WC\u0001C\u0004!\u0011\u00112\u0003\"\u0003\u0011\u0007Y!Y!\u0003\u0003\u0005\u000e\t-#AE*fY\u0016\u001cGO\u0012:p[RK\b/\u001a+sK\u0016D\u0011\u0002\"\u0005\u0001\u0005\u00045\u0019\u0001b\u0005\u0002\u0013M+G.Z2u)\u0006<WC\u0001C\u000b!\u0011\u00112\u0003b\u0006\u0011\u0007Y!I\"\u0003\u0003\u0005\u001c\t-#AB*fY\u0016\u001cG\u000fC\u0005\u0005 \u0001\u0011\rQb\u0001\u0005\"\u0005!2+\u001b8hY\u0016$xN\u001c+za\u0016$&/Z3UC\u001e,\"\u0001b\t\u0011\tI\u0019BQ\u0005\t\u0004-\u0011\u001d\u0012\u0002\u0002C\u0015\u0005\u0017\u0012\u0011cU5oO2,Go\u001c8UsB,GK]3f\u0011%!i\u0003\u0001b\u0001\u000e\u0007!y#A\u0004Ti\u0006\u0014H+Y4\u0016\u0005\u0011E\u0002\u0003\u0002\n\u0014\tg\u00012A\u0006C\u001b\u0013\u0011!9Da\u0013\u0003\tM#\u0018M\u001d\u0005\n\tw\u0001!\u0019!D\u0002\t{\t\u0001bU;qKJ$\u0016mZ\u000b\u0003\t\u007f\u0001BAE\n\u0005BA\u0019a\u0003b\u0011\n\t\u0011\u0015#1\n\u0002\u0006'V\u0004XM\u001d\u0005\n\t\u0013\u0002!\u0019!D\u0002\t\u0017\n!bU=n)J,W\rV1h+\t!i\u0005\u0005\u0003\u0013'\u0011=\u0003c\u0001\f\u0005R%!A1\u000bB&\u0005\u001d\u0019\u00160\u001c+sK\u0016D\u0011\u0002b\u0016\u0001\u0005\u00045\u0019\u0001\"\u0017\u0002\u0017Q+W\u000e\u001d7bi\u0016$\u0016mZ\u000b\u0003\t7\u0002BAE\n\u0005^A\u0019a\u0003b\u0018\n\t\u0011\u0005$1\n\u0002\t)\u0016l\u0007\u000f\\1uK\"IAQ\r\u0001C\u0002\u001b\rAqM\u0001\f)\u0016\u0014X\u000e\u0016:fKR\u000bw-\u0006\u0002\u0005jA!!c\u0005C6!\r1BQN\u0005\u0005\t_\u0012YE\u0001\u0005UKJlGK]3f\u0011%!\u0019\b\u0001b\u0001\u000e\u0007!)(A\u0004UQ&\u001cH+Y4\u0016\u0005\u0011]\u0004\u0003\u0002\n\u0014\ts\u00022A\u0006C>\u0013\u0011!iHa\u0013\u0003\tQC\u0017n\u001d\u0005\n\t\u0003\u0003!\u0019!D\u0002\t\u0007\u000b\u0001\u0002\u00165s_^$\u0016mZ\u000b\u0003\t\u000b\u0003BAE\n\u0005\bB\u0019a\u0003\"#\n\t\u0011-%1\n\u0002\u0006)\"\u0014xn\u001e\u0005\n\t\u001f\u0003!\u0019!D\u0002\t#\u000bq\u0001\u0016:fKR\u000bw-\u0006\u0002\u0005\u0014B!!c\u0005CK!\r1BqS\u0005\u0005\t3\u0013YE\u0001\u0003Ue\u0016,\u0007\"\u0003CO\u0001\t\u0007i1\u0001CP\u0003\u0019!&/\u001f+bOV\u0011A\u0011\u0015\t\u0005%M!\u0019\u000bE\u0002\u0017\tKKA\u0001b*\u0003L\t\u0019AK]=\t\u0013\u0011-\u0006A1A\u0007\u0004\u00115\u0016A\u0003+zaR\u0013X-\u001a+bOV\u0011Aq\u0016\t\u0005%M!\t\fE\u0002\u0017\tgKA\u0001\".\u0003L\t9A+\u001f9Ue\u0016,\u0007\"\u0003C]\u0001\t\u0007i1\u0001C^\u00031!\u0016\u0010]3BaBd\u0017\u0010V1h+\t!i\f\u0005\u0003\u0013'\u0011}\u0006c\u0001\f\u0005B&!A1\u0019B&\u0005%!\u0016\u0010]3BaBd\u0017\u0010C\u0005\u0005H\u0002\u0011\rQb\u0001\u0005J\u0006\tB+\u001f9f\u0005>,h\u000eZ:Ue\u0016,G+Y4\u0016\u0005\u0011-\u0007\u0003\u0002\n\u0014\t\u001b\u00042A\u0006Ch\u0013\u0011!\tNa\u0013\u0003\u001dQK\b/\u001a\"pk:$7\u000f\u0016:fK\"IAQ\u001b\u0001C\u0002\u001b\rAq[\u0001\u000b)f\u0004X\rR3g)\u0006<WC\u0001Cm!\u0011\u00112\u0003b7\u0011\u0007Y!i.\u0003\u0003\u0005`\n-#a\u0002+za\u0016$UM\u001a\u0005\n\tG\u0004!\u0019!D\u0002\tK\f1\u0002V=qKR\u0013X-\u001a+bOV\u0011Aq\u001d\t\u0005%M!I\u000fE\u0002\u0017\tWLA\u0001\"<\u0003L\tAA+\u001f9f)J,W\rC\u0005\u0005r\u0002\u0011\rQb\u0001\u0005t\u0006AA+\u001f9fIR\u000bw-\u0006\u0002\u0005vB!!c\u0005C|!\r1B\u0011`\u0005\u0005\tw\u0014YEA\u0003UsB,G\rC\u0005\u0005\u0000\u0002\u0011\rQb\u0001\u0006\u0002\u0005QQK\\!qa2LH+Y4\u0016\u0005\u0015\r\u0001\u0003\u0002\n\u0014\u000b\u000b\u00012AFC\u0004\u0013\u0011)IAa\u0013\u0003\u000fUs\u0017\t\u001d9ms\"IQQ\u0002\u0001C\u0002\u001b\rQqB\u0001\n-\u0006dG)\u001a4UC\u001e,\"!\"\u0005\u0011\tI\u0019R1\u0003\t\u0004-\u0015U\u0011\u0002BC\f\u0005\u0017\u0012aAV1m\t\u00164\u0007\"CC\u000e\u0001\t\u0007i1AC\u000f\u000391\u0016\r\\(s\t\u00164G)\u001a4UC\u001e,\"!b\b\u0011\tI\u0019R\u0011\u0005\t\u0004-\u0015\r\u0012\u0002BC\u0013\u0005\u0017\u00121BV1m\u001fJ$UM\u001a#fM\"IQ\u0011\u0006\u0001C\u0002\u001b\rQ1F\u0001\u000e)J,WmQ8qS\u0016\u0014H+Y4\u0016\u0005\u00155\u0002\u0003\u0002\n\u0014\u000b_\u00012AFC\u0019\u0013\u0011)\u0019Da\u0013\u0003\u0015Q\u0013X-Z\"pa&,'\u000fC\u0005\u00068\u0001\u0011\rQb\u0001\u0006:\u0005y!+\u001e8uS6,7\t\\1tgR\u000bw-\u0006\u0002\u0006<A!!cEC\u001f!\r1RqH\u0005\u0005\u000b\u0003*\u0019E\u0001\u0007Sk:$\u0018.\\3DY\u0006\u001c8/C\u0002\u0006F\t\u0011q!T5se>\u00148\u000fC\u0005\u0006J\u0001\u0011\rQb\u0001\u0006L\u0005IQ*\u001b:s_J$\u0016mZ\u000b\u0003\u000b\u001b\u0002BAE\n\u0006PA\u0019a#\"\u0015\n\t\u0015MS1\t\u0002\u0007\u001b&\u0014(o\u001c:\u0011\t\u0015]S\u0011L\u0007\u0002\u0005%\u0019Q1\f\u0002\u0003\u0011Us\u0017N^3sg\u0016\u0004")
public interface ImplicitTags {
    public ClassTag<Types.AnnotatedTypeApi> AnnotatedTypeTag();

    public ClassTag<Types.BoundedWildcardTypeApi> BoundedWildcardTypeTag();

    public ClassTag<Types.ClassInfoTypeApi> ClassInfoTypeTag();

    public ClassTag<Types.CompoundTypeApi> CompoundTypeTag();

    public ClassTag<Types.ConstantTypeApi> ConstantTypeTag();

    public ClassTag<Types.ExistentialTypeApi> ExistentialTypeTag();

    public ClassTag<Types.MethodTypeApi> MethodTypeTag();

    public ClassTag<Types.NullaryMethodTypeApi> NullaryMethodTypeTag();

    public ClassTag<Types.PolyTypeApi> PolyTypeTag();

    public ClassTag<Types.RefinedTypeApi> RefinedTypeTag();

    public ClassTag<Types.SingleTypeApi> SingleTypeTag();

    public ClassTag<Types.SingletonTypeApi> SingletonTypeTag();

    public ClassTag<Types.SuperTypeApi> SuperTypeTag();

    public ClassTag<Types.ThisTypeApi> ThisTypeTag();

    public ClassTag<Types.TypeBoundsApi> TypeBoundsTag();

    public ClassTag<Types.TypeRefApi> TypeRefTag();

    public ClassTag<Types.TypeApi> TypeTagg();

    public ClassTag<Names.NameApi> NameTag();

    public ClassTag<Names.TermNameApi> TermNameTag();

    public ClassTag<Names.TypeNameApi> TypeNameTag();

    public ClassTag<Scopes.ScopeApi> ScopeTag();

    public ClassTag<Scopes.MemberScopeApi> MemberScopeTag();

    public ClassTag<Annotations.AnnotationApi> AnnotationTag();

    public ClassTag<Annotations.JavaArgumentApi> JavaArgumentTag();

    public ClassTag<Annotations.LiteralArgumentApi> LiteralArgumentTag();

    public ClassTag<Annotations.ArrayArgumentApi> ArrayArgumentTag();

    public ClassTag<Annotations.NestedArgumentApi> NestedArgumentTag();

    public ClassTag<Symbols.TermSymbolApi> TermSymbolTag();

    public ClassTag<Symbols.MethodSymbolApi> MethodSymbolTag();

    public ClassTag<Symbols.SymbolApi> SymbolTag();

    public ClassTag<Symbols.TypeSymbolApi> TypeSymbolTag();

    public ClassTag<Symbols.ModuleSymbolApi> ModuleSymbolTag();

    public ClassTag<Symbols.ClassSymbolApi> ClassSymbolTag();

    public ClassTag<Position> PositionTag();

    public ClassTag<Constants.ConstantApi> ConstantTag();

    public ClassTag<Object> FlagSetTag();

    public ClassTag<Trees.ModifiersApi> ModifiersTag();

    public ClassTag<Trees.AlternativeApi> AlternativeTag();

    public ClassTag<Trees.AnnotatedApi> AnnotatedTag();

    public ClassTag<Trees.AppliedTypeTreeApi> AppliedTypeTreeTag();

    public ClassTag<Trees.ApplyApi> ApplyTag();

    public ClassTag<Trees.AssignOrNamedArgApi> AssignOrNamedArgTag();

    public ClassTag<Trees.AssignApi> AssignTag();

    public ClassTag<Trees.BindApi> BindTag();

    public ClassTag<Trees.BlockApi> BlockTag();

    public ClassTag<Trees.CaseDefApi> CaseDefTag();

    public ClassTag<Trees.ClassDefApi> ClassDefTag();

    public ClassTag<Trees.CompoundTypeTreeApi> CompoundTypeTreeTag();

    public ClassTag<Trees.DefDefApi> DefDefTag();

    public ClassTag<Trees.DefTreeApi> DefTreeTag();

    public ClassTag<Trees.ExistentialTypeTreeApi> ExistentialTypeTreeTag();

    public ClassTag<Trees.FunctionApi> FunctionTag();

    public ClassTag<Trees.GenericApplyApi> GenericApplyTag();

    public ClassTag<Trees.IdentApi> IdentTag();

    public ClassTag<Trees.IfApi> IfTag();

    public ClassTag<Trees.ImplDefApi> ImplDefTag();

    public ClassTag<Trees.ImportSelectorApi> ImportSelectorTag();

    public ClassTag<Trees.ImportApi> ImportTag();

    public ClassTag<Trees.LabelDefApi> LabelDefTag();

    public ClassTag<Trees.LiteralApi> LiteralTag();

    public ClassTag<Trees.MatchApi> MatchTag();

    public ClassTag<Trees.MemberDefApi> MemberDefTag();

    public ClassTag<Trees.ModuleDefApi> ModuleDefTag();

    public ClassTag<Trees.NameTreeApi> NameTreeTag();

    public ClassTag<Trees.NewApi> NewTag();

    public ClassTag<Trees.PackageDefApi> PackageDefTag();

    public ClassTag<Trees.RefTreeApi> RefTreeTag();

    public ClassTag<Trees.ReturnApi> ReturnTag();

    public ClassTag<Trees.SelectFromTypeTreeApi> SelectFromTypeTreeTag();

    public ClassTag<Trees.SelectApi> SelectTag();

    public ClassTag<Trees.SingletonTypeTreeApi> SingletonTypeTreeTag();

    public ClassTag<Trees.StarApi> StarTag();

    public ClassTag<Trees.SuperApi> SuperTag();

    public ClassTag<Trees.SymTreeApi> SymTreeTag();

    public ClassTag<Trees.TemplateApi> TemplateTag();

    public ClassTag<Trees.TermTreeApi> TermTreeTag();

    public ClassTag<Trees.ThisApi> ThisTag();

    public ClassTag<Trees.ThrowApi> ThrowTag();

    public ClassTag<Trees.TreeApi> TreeTag();

    public ClassTag<Trees.TryApi> TryTag();

    public ClassTag<Trees.TypTreeApi> TypTreeTag();

    public ClassTag<Trees.TypeApplyApi> TypeApplyTag();

    public ClassTag<Trees.TypeBoundsTreeApi> TypeBoundsTreeTag();

    public ClassTag<Trees.TypeDefApi> TypeDefTag();

    public ClassTag<Trees.TypeTreeApi> TypeTreeTag();

    public ClassTag<Trees.TypedApi> TypedTag();

    public ClassTag<Trees.UnApplyApi> UnApplyTag();

    public ClassTag<Trees.ValDefApi> ValDefTag();

    public ClassTag<Trees.ValOrDefDefApi> ValOrDefDefTag();

    public ClassTag<Trees.TreeCopierOps> TreeCopierTag();

    public ClassTag<Object> RuntimeClassTag();

    public ClassTag<Mirror> MirrorTag();
}

