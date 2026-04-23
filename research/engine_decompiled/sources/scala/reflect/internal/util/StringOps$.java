/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal.util;

import scala.Function1;
import scala.Option;
import scala.Tuple2;
import scala.collection.Seq;
import scala.collection.immutable.List;
import scala.reflect.internal.util.StringOps;
import scala.reflect.internal.util.StringOps$class;

public final class StringOps$
implements StringOps {
    public static final StringOps$ MODULE$;

    static {
        new StringOps$();
    }

    @Override
    public Seq<String> oempty(Seq<String> xs) {
        return StringOps$class.oempty(this, xs);
    }

    @Override
    public String ojoin(Seq<String> xs) {
        return StringOps$class.ojoin(this, xs);
    }

    @Override
    public String longestCommonPrefix(List<String> xs) {
        return StringOps$class.longestCommonPrefix(this, xs);
    }

    @Override
    public String trimTrailingSpace(String s2) {
        return StringOps$class.trimTrailingSpace(this, s2);
    }

    @Override
    public String trimAllTrailingSpace(String s2) {
        return StringOps$class.trimAllTrailingSpace(this, s2);
    }

    @Override
    public List<String> decompose(String str, char sep) {
        return StringOps$class.decompose(this, str, sep);
    }

    @Override
    public List<String> words(String str) {
        return StringOps$class.words(this, str);
    }

    @Override
    public Option<Tuple2<String, String>> splitWhere(String str, Function1<Object, Object> f, boolean doDropIndex) {
        return StringOps$class.splitWhere(this, str, f, doDropIndex);
    }

    @Override
    public Option<Tuple2<String, String>> splitAt(String str, int idx, boolean doDropIndex) {
        return StringOps$class.splitAt(this, str, idx, doDropIndex);
    }

    @Override
    public String countElementsAsString(int n, String elements) {
        return StringOps$class.countElementsAsString(this, n, elements);
    }

    @Override
    public String countAsString(int n) {
        return StringOps$class.countAsString(this, n);
    }

    @Override
    public boolean splitWhere$default$3() {
        return StringOps$class.splitWhere$default$3(this);
    }

    @Override
    public boolean splitAt$default$3() {
        return StringOps$class.splitAt$default$3(this);
    }

    private StringOps$() {
        MODULE$ = this;
        StringOps$class.$init$(this);
    }
}

