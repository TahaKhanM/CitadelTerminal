/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal;

import scala.Predef$;
import scala.reflect.internal.BaseTypeSeqsStats$;
import scala.reflect.internal.util.Statistics;
import scala.reflect.internal.util.Statistics$;

public final class TypesStats$ {
    public static final TypesStats$ MODULE$;
    private final Statistics.Counter rawTypeCount;
    private final Statistics.Counter subtypeCount;
    private final Statistics.Counter sametypeCount;
    private final Statistics.Counter lubCount;
    private final Statistics.Counter nestedLubCount;
    private final Statistics.Counter findMemberCount;
    private final Statistics.Counter findMembersCount;
    private final Statistics.SubCounter noMemberCount;
    private final Statistics.SubCounter multMemberCount;
    private final Statistics.Timer typerNanos;
    private final Statistics.StackableTimer lubNanos;
    private final Statistics.StackableTimer subtypeNanos;
    private final Statistics.StackableTimer findMemberNanos;
    private final Statistics.StackableTimer findMembersNanos;
    private final Statistics.StackableTimer asSeenFromNanos;
    private final Statistics.StackableTimer baseTypeSeqNanos;
    private final Statistics.StackableTimer baseClassesNanos;
    private final Statistics.SubCounter compoundBaseTypeSeqCount;
    private final Statistics.SubCounter typerefBaseTypeSeqCount;
    private final Statistics.SubCounter singletonBaseTypeSeqCount;
    private final Statistics.TimerStack typeOpsStack;

    static {
        new TypesStats$();
    }

    public Statistics.Counter rawTypeCount() {
        return this.rawTypeCount;
    }

    public Statistics.Counter subtypeCount() {
        return this.subtypeCount;
    }

    public Statistics.Counter sametypeCount() {
        return this.sametypeCount;
    }

    public Statistics.Counter lubCount() {
        return this.lubCount;
    }

    public Statistics.Counter nestedLubCount() {
        return this.nestedLubCount;
    }

    public Statistics.Counter findMemberCount() {
        return this.findMemberCount;
    }

    public Statistics.Counter findMembersCount() {
        return this.findMembersCount;
    }

    public Statistics.SubCounter noMemberCount() {
        return this.noMemberCount;
    }

    public Statistics.SubCounter multMemberCount() {
        return this.multMemberCount;
    }

    public Statistics.Timer typerNanos() {
        return this.typerNanos;
    }

    public Statistics.StackableTimer lubNanos() {
        return this.lubNanos;
    }

    public Statistics.StackableTimer subtypeNanos() {
        return this.subtypeNanos;
    }

    public Statistics.StackableTimer findMemberNanos() {
        return this.findMemberNanos;
    }

    public Statistics.StackableTimer findMembersNanos() {
        return this.findMembersNanos;
    }

    public Statistics.StackableTimer asSeenFromNanos() {
        return this.asSeenFromNanos;
    }

    public Statistics.StackableTimer baseTypeSeqNanos() {
        return this.baseTypeSeqNanos;
    }

    public Statistics.StackableTimer baseClassesNanos() {
        return this.baseClassesNanos;
    }

    public Statistics.SubCounter compoundBaseTypeSeqCount() {
        return this.compoundBaseTypeSeqCount;
    }

    public Statistics.SubCounter typerefBaseTypeSeqCount() {
        return this.typerefBaseTypeSeqCount;
    }

    public Statistics.SubCounter singletonBaseTypeSeqCount() {
        return this.singletonBaseTypeSeqCount;
    }

    public Statistics.TimerStack typeOpsStack() {
        return this.typeOpsStack;
    }

    private TypesStats$() {
        MODULE$ = this;
        this.rawTypeCount = Statistics$.MODULE$.newCounter("#raw type creations", Predef$.MODULE$.wrapRefArray((Object[])new String[0]));
        this.subtypeCount = Statistics$.MODULE$.newCounter("#subtype ops", Predef$.MODULE$.wrapRefArray((Object[])new String[0]));
        this.sametypeCount = Statistics$.MODULE$.newCounter("#sametype ops", Predef$.MODULE$.wrapRefArray((Object[])new String[0]));
        this.lubCount = Statistics$.MODULE$.newCounter("#toplevel lubs/glbs", Predef$.MODULE$.wrapRefArray((Object[])new String[0]));
        this.nestedLubCount = Statistics$.MODULE$.newCounter("#all lubs/glbs", Predef$.MODULE$.wrapRefArray((Object[])new String[0]));
        this.findMemberCount = Statistics$.MODULE$.newCounter("#findMember ops", Predef$.MODULE$.wrapRefArray((Object[])new String[0]));
        this.findMembersCount = Statistics$.MODULE$.newCounter("#findMembers ops", Predef$.MODULE$.wrapRefArray((Object[])new String[0]));
        this.noMemberCount = Statistics$.MODULE$.newSubCounter("  of which not found", this.findMemberCount());
        this.multMemberCount = Statistics$.MODULE$.newSubCounter("  of which multiple overloaded", this.findMemberCount());
        this.typerNanos = Statistics$.MODULE$.newTimer("time spent typechecking", Predef$.MODULE$.wrapRefArray((Object[])new String[]{"typer"}));
        this.lubNanos = Statistics$.MODULE$.newStackableTimer("time spent in lubs", this.typerNanos());
        this.subtypeNanos = Statistics$.MODULE$.newStackableTimer("time spent in <:<", this.typerNanos());
        this.findMemberNanos = Statistics$.MODULE$.newStackableTimer("time spent in findmember", this.typerNanos());
        this.findMembersNanos = Statistics$.MODULE$.newStackableTimer("time spent in findmembers", this.typerNanos());
        this.asSeenFromNanos = Statistics$.MODULE$.newStackableTimer("time spent in asSeenFrom", this.typerNanos());
        this.baseTypeSeqNanos = Statistics$.MODULE$.newStackableTimer("time spent in baseTypeSeq", this.typerNanos());
        this.baseClassesNanos = Statistics$.MODULE$.newStackableTimer("time spent in baseClasses", this.typerNanos());
        this.compoundBaseTypeSeqCount = Statistics$.MODULE$.newSubCounter("  of which for compound types", BaseTypeSeqsStats$.MODULE$.baseTypeSeqCount());
        this.typerefBaseTypeSeqCount = Statistics$.MODULE$.newSubCounter("  of which for typerefs", BaseTypeSeqsStats$.MODULE$.baseTypeSeqCount());
        this.singletonBaseTypeSeqCount = Statistics$.MODULE$.newSubCounter("  of which for singletons", BaseTypeSeqsStats$.MODULE$.baseTypeSeqCount());
        this.typeOpsStack = Statistics$.MODULE$.newTimerStack();
    }
}

