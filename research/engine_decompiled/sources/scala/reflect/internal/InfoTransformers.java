/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal;

import scala.Function0;
import scala.Predef$;
import scala.Serializable;
import scala.collection.immutable.StringOps;
import scala.collection.mutable.StringBuilder;
import scala.reflect.ScalaSignature;
import scala.reflect.internal.NoPhase$;
import scala.reflect.internal.SymbolTable;
import scala.reflect.internal.Symbols;
import scala.reflect.internal.Types;
import scala.runtime.BoxesRunTime;

@ScalaSignature(bytes="\u0006\u0001e3\u0001\"\u0001\u0002\u0011\u0002\u0007\u0005\u0011\"\u0016\u0002\u0011\u0013:4w\u000e\u0016:b]N4wN]7feNT!a\u0001\u0003\u0002\u0011%tG/\u001a:oC2T!!\u0002\u0004\u0002\u000fI,g\r\\3di*\tq!A\u0003tG\u0006d\u0017m\u0001\u0001\u0014\u0005\u0001Q\u0001CA\u0006\r\u001b\u00051\u0011BA\u0007\u0007\u0005\u0019\te.\u001f*fM\")q\u0002\u0001C\u0001!\u00051A%\u001b8ji\u0012\"\u0012!\u0005\t\u0003\u0017II!a\u0005\u0004\u0003\tUs\u0017\u000e\u001e\u0004\u0006+\u0001\t\tA\u0006\u0002\u0010\u0013:4w\u000e\u0016:b]N4wN]7feN\u0011AC\u0003\u0005\u00061Q!\t!G\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003i\u0001\"a\u0007\u000b\u000e\u0003\u0001Aq!\b\u000bA\u0002\u0013\u0005a$\u0001\u0003qe\u00164X#\u0001\u000e\t\u000f\u0001\"\u0002\u0019!C\u0001C\u0005A\u0001O]3w?\u0012*\u0017\u000f\u0006\u0002\u0012E!91eHA\u0001\u0002\u0004Q\u0012a\u0001=%c!1Q\u0005\u0006Q!\ni\tQ\u0001\u001d:fm\u0002Bqa\n\u000bA\u0002\u0013\u0005a$\u0001\u0003oKb$\bbB\u0015\u0015\u0001\u0004%\tAK\u0001\t]\u0016DHo\u0018\u0013fcR\u0011\u0011c\u000b\u0005\bG!\n\t\u00111\u0001\u001b\u0011\u0019iC\u0003)Q\u00055\u0005)a.\u001a=uA!9q\u0006\u0006b\u0001\u000e\u0003\u0001\u0014a\u00019jIV\t\u0011\u0007\u0005\u0002\fe%\u00111G\u0002\u0002\u0004\u0013:$\bbB\u001b\u0015\u0005\u00045\tAN\u0001\u0013G\"\fgnZ3t\u0005\u0006\u001cXm\u00117bgN,7/F\u00018!\tY\u0001(\u0003\u0002:\r\t9!i\\8mK\u0006t\u0007\"B\u001e\u0015\r\u0003a\u0014!\u0003;sC:\u001chm\u001c:n)\ri$)\u0013\t\u00037yJ!a\u0010!\u0003\tQK\b/Z\u0005\u0003\u0003\n\u0011Q\u0001V=qKNDQa\u0011\u001eA\u0002\u0011\u000b1a]=n!\tYR)\u0003\u0002G\u000f\n11+_7c_2L!\u0001\u0013\u0002\u0003\u000fMKXNY8mg\")!J\u000fa\u0001{\u0005\u0019A\u000f]3\t\u000b1#B\u0011A'\u0002\r%t7/\u001a:u)\t\tb\nC\u0003P\u0017\u0002\u0007!$\u0001\u0003uQ\u0006$\b\"B)\u0015\t\u0003\u0011\u0016\u0001\u00038fqR4%o\\7\u0015\u0005i\u0019\u0006\"\u0002+Q\u0001\u0004\t\u0014\u0001\u00024s_6\u0004\"AV,\u000e\u0003\tI!\u0001\u0017\u0002\u0003\u0017MKXNY8m)\u0006\u0014G.\u001a")
public interface InfoTransformers {

    public static abstract class InfoTransformer {
        private InfoTransformer prev;
        private InfoTransformer next;
        public final /* synthetic */ SymbolTable $outer;

        public InfoTransformer prev() {
            return this.prev;
        }

        public void prev_$eq(InfoTransformer x$1) {
            this.prev = x$1;
        }

        public InfoTransformer next() {
            return this.next;
        }

        public void next_$eq(InfoTransformer x$1) {
            this.next = x$1;
        }

        public abstract int pid();

        public abstract boolean changesBaseClasses();

        public abstract Types.Type transform(Symbols.Symbol var1, Types.Type var2);

        public void insert(InfoTransformer that) {
            boolean bl = this.pid() != that.pid();
            Predef$ predef$ = Predef$.MODULE$;
            if (!bl) {
                throw new AssertionError((Object)new StringBuilder().append((Object)"assertion failed: ").append(BoxesRunTime.boxToInteger(this.pid())).toString());
            }
            if (that.pid() < this.pid()) {
                this.prev().insert(that);
            } else if (this.next().pid() <= that.pid() && this.next().pid() != NoPhase$.MODULE$.id()) {
                this.next().insert(that);
            } else {
                this.scala$reflect$internal$InfoTransformers$InfoTransformer$$$outer().log((Function0<Object>)((Object)new Serializable(this, that){
                    public static final long serialVersionUID = 0L;
                    private final /* synthetic */ InfoTransformer $outer;
                    private final InfoTransformer that$1;

                    public final String apply() {
                        Predef$ predef$ = Predef$.MODULE$;
                        return new StringOps("Inserting info transformer %s following %s").format(Predef$.MODULE$.genericWrapArray(new Object[]{this.$outer.scala$reflect$internal$InfoTransformers$InfoTransformer$$$outer().phaseOf(this.that$1.pid()), this.$outer.scala$reflect$internal$InfoTransformers$InfoTransformer$$$outer().phaseOf(this.$outer.pid())}));
                    }
                    {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                        this.that$1 = that$1;
                    }
                }));
                that.next_$eq(this.next());
                that.prev_$eq(this);
                this.next().prev_$eq(that);
                this.next_$eq(that);
            }
        }

        public InfoTransformer nextFrom(int from2) {
            return from2 == this.pid() ? this : (from2 < this.pid() ? (this.prev().pid() < from2 ? this : this.prev().nextFrom(from2)) : (this.next().pid() == NoPhase$.MODULE$.id() ? this.next() : this.next().nextFrom(from2)));
        }

        public /* synthetic */ SymbolTable scala$reflect$internal$InfoTransformers$InfoTransformer$$$outer() {
            return this.$outer;
        }

        public InfoTransformer(SymbolTable $outer) {
            if ($outer == null) {
                throw null;
            }
            this.$outer = $outer;
            this.prev = this;
            this.next = this;
        }
    }
}

