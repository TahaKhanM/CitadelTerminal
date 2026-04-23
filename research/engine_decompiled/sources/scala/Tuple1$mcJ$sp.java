/*
 * Decompiled with CFR 0.152.
 */
package scala;

import scala.Product1$mcJ$sp;
import scala.Product1$mcJ$sp$class;
import scala.Tuple1;

public class Tuple1$mcJ$sp
extends Tuple1<Object>
implements Product1$mcJ$sp {
    public final long _1$mcJ$sp;

    @Override
    public long _1$mcJ$sp() {
        return this._1$mcJ$sp;
    }

    @Override
    public long _1() {
        return this._1$mcJ$sp();
    }

    @Override
    public <T1> long copy$default$1() {
        return this.copy$default$1$mcJ$sp();
    }

    @Override
    public <T1> long copy$default$1$mcJ$sp() {
        return this._1();
    }

    @Override
    public boolean specInstance$() {
        return true;
    }

    public Tuple1$mcJ$sp(long _1$mcJ$sp) {
        this._1$mcJ$sp = _1$mcJ$sp;
        super(null);
        Product1$mcJ$sp$class.$init$(this);
    }
}

