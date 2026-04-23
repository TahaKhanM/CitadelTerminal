/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal.tpe;

import scala.collection.AbstractIterable;
import scala.collection.immutable.List;
import scala.reflect.ScalaSignature;
import scala.reflect.internal.SymbolTable;
import scala.reflect.internal.Symbols;
import scala.reflect.internal.Types;
import scala.reflect.internal.tpe.TypeMaps;

@ScalaSignature(bytes="\u0006\u0001y3\u0011\"\u0001\u0002\u0011\u0002\u0007\u0005AA\u0003.\u0003\u0019\r{W.\\8o\u001f^tWM]:\u000b\u0005\r!\u0011a\u0001;qK*\u0011QAB\u0001\tS:$XM\u001d8bY*\u0011q\u0001C\u0001\be\u00164G.Z2u\u0015\u0005I\u0011!B:dC2\f7C\u0001\u0001\f!\taQ\"D\u0001\t\u0013\tq\u0001B\u0001\u0004B]f\u0014VM\u001a\u0005\u0006!\u0001!\tAE\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0004\u0001Q\t1\u0003\u0005\u0002\r)%\u0011Q\u0003\u0003\u0002\u0005+:LG\u000f\u0003\u0004\u0018\u0001\u0011EA\u0001G\u0001\fG>lWn\u001c8Po:,'\u000f\u0006\u0002\u001a?A\u0011!dG\u0007\u0002\u0001%\u0011A$\b\u0002\u0007'fl'm\u001c7\n\u0005y!!aB*z[\n|Gn\u001d\u0005\u0006AY\u0001\r!I\u0001\u0002iB\u0011!DI\u0005\u0003G\u0011\u0012A\u0001V=qK&\u0011Q\u0005\u0002\u0002\u0006)f\u0004Xm\u001d\u0005\u0007/\u0001!\t\u0002B\u0014\u0015\u0005eA\u0003\"B\u0015'\u0001\u0004Q\u0013a\u0001;qgB\u00191FL\u0011\u000f\u00051a\u0013BA\u0017\t\u0003\u001d\u0001\u0018mY6bO\u0016L!a\f\u0019\u0003\t1K7\u000f\u001e\u0006\u0003[!AQA\r\u0001\u0005\u0012M\nabY8n[>twj\u001e8fe6\u000b\u0007/F\u00015!\tQRG\u0002\u00037\u0001!9$AD\"p[6|gnT<oKJl\u0015\r]\n\u0003ka\u00022AG\u001d\u001a\u0013\tQ4HA\fUsB,GK]1wKJ\u001cXM],ji\"\u0014Vm];mi&\u0011AH\u0001\u0002\t)f\u0004X-T1qg\")a(\u000eC\u0001\u007f\u00051A(\u001b8jiz\"\u0012\u0001\u000e\u0005\n\u0003V\u0002\r\u00111A\u0005\u0002\t\u000baA]3tk2$X#A\r\t\u0013\u0011+\u0004\u0019!a\u0001\n\u0003)\u0015A\u0003:fgVdGo\u0018\u0013fcR\u00111C\u0012\u0005\b\u000f\u000e\u000b\t\u00111\u0001\u001a\u0003\rAH%\r\u0005\u0007\u0013V\u0002\u000b\u0015B\r\u0002\u000fI,7/\u001e7uA!)1*\u000eC\u0001%\u0005)1\r\\3be\")Q*\u000eC\u0005\u001d\u0006A!/Z4jgR,'\u000f\u0006\u0002\u0014\u001f\")\u0001\u000b\u0014a\u00013\u0005\u00191/_7\t\u000bI+D\u0011A*\u0002\u0011Q\u0014\u0018M^3sg\u0016$\"a\u0005+\t\u000bU\u000b\u0006\u0019A\u0011\u0002\u0005Q\u0004\b\u0002C,\u0001\u0011\u000b\u0007I\u0011B\u001a\u0002#\r|W.\\8o\u001f^tWM]'ba>\u0013'\u000e\u0003\u0005Z\u0001!\u0005\t\u0015)\u00035\u0003I\u0019w.\\7p]>;h.\u001a:NCB|%M\u001b\u0011\u0011\u0005mcV\"\u0001\u0003\n\u0005u#!aC*z[\n|G\u000eV1cY\u0016\u0004")
public interface CommonOwners {
    public Symbols.Symbol commonOwner(Types.Type var1);

    public Symbols.Symbol commonOwner(List<Types.Type> var1);

    public CommonOwnerMap commonOwnerMap();

    public CommonOwnerMap scala$reflect$internal$tpe$CommonOwners$$commonOwnerMapObj();

    public static class CommonOwnerMap
    extends TypeMaps.TypeTraverserWithResult<Symbols.Symbol> {
        private Symbols.Symbol result;
        public final /* synthetic */ SymbolTable $outer;

        @Override
        public Symbols.Symbol result() {
            return this.result;
        }

        public void result_$eq(Symbols.Symbol x$1) {
            this.result = x$1;
        }

        @Override
        public void clear() {
            this.result_$eq(null);
        }

        private void register(Symbols.Symbol sym) {
            if (this.result() == null || sym == this.scala$reflect$internal$tpe$CommonOwners$CommonOwnerMap$$$outer().NoSymbol()) {
                this.result_$eq(sym);
            } else {
                while (this.result() != this.scala$reflect$internal$tpe$CommonOwners$CommonOwnerMap$$$outer().NoSymbol() && this.result() != sym && !sym.isNestedIn(this.result())) {
                    this.result_$eq(this.result().owner());
                }
            }
        }

        /*
         * Enabled aggressive block sorting
         */
        @Override
        public void traverse(Types.Type tp) {
            Types.Type type = tp.normalize();
            if (type instanceof Types.ThisType) {
                Types.ThisType thisType = (Types.ThisType)type;
                this.register(thisType.sym());
                return;
            }
            if (type instanceof Types.TypeRef) {
                Types.TypeRef typeRef = (Types.TypeRef)type;
                if (this.scala$reflect$internal$tpe$CommonOwners$CommonOwnerMap$$$outer().NoPrefix().equals(typeRef.pre())) {
                    this.register(typeRef.sym().owner());
                    List list2 = typeRef.args();
                    while (!((AbstractIterable)list2).isEmpty()) {
                        Types.Type type2 = (Types.Type)((AbstractIterable)list2).head();
                        this.traverse(type2);
                        list2 = (List)list2.tail();
                    }
                    return;
                }
            }
            if (type instanceof Types.SingleType) {
                Types.SingleType singleType = (Types.SingleType)type;
                if (this.scala$reflect$internal$tpe$CommonOwners$CommonOwnerMap$$$outer().NoPrefix().equals(singleType.pre())) {
                    this.register(singleType.sym().owner());
                    return;
                }
            }
            this.mapOver(tp);
        }

        public /* synthetic */ SymbolTable scala$reflect$internal$tpe$CommonOwners$CommonOwnerMap$$$outer() {
            return this.$outer;
        }

        public CommonOwnerMap(SymbolTable $outer) {
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
            super($outer);
        }
    }
}

