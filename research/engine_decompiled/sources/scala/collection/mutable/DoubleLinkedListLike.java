/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.mutable;

import scala.Option;
import scala.collection.mutable.LinkedListLike;
import scala.collection.mutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.TraitSetter;

@ScalaSignature(bytes="\u0006\u0001\u0005\u0015b\u0001C\u0001\u0003!\u0003\r\t!\u0003\u0015\u0003)\u0011{WO\u00197f\u0019&t7.\u001a3MSN$H*[6f\u0015\t\u0019A!A\u0004nkR\f'\r\\3\u000b\u0005\u00151\u0011AC2pY2,7\r^5p]*\tq!A\u0003tG\u0006d\u0017m\u0001\u0001\u0016\u0007))rd\u0005\u0003\u0001\u0017=I\u0003C\u0001\u0007\u000e\u001b\u00051\u0011B\u0001\b\u0007\u0005\u0019\te.\u001f*fMB!\u0001#E\n\u001f\u001b\u0005\u0011\u0011B\u0001\n\u0003\u0005\u001d\u0019V-\u001d'jW\u0016\u0004\"\u0001F\u000b\r\u0001\u0011)a\u0003\u0001b\u0001/\t\t\u0011)\u0005\u0002\u00197A\u0011A\"G\u0005\u00035\u0019\u0011qAT8uQ&tw\r\u0005\u0002\r9%\u0011QD\u0002\u0002\u0004\u0003:L\bC\u0001\u000b \t\u0015\u0001\u0003A1\u0001\"\u0005\u0011!\u0006.[:\u0012\u0005a\u0011#cA\u0012&Q\u0019!A\u0005\u0001\u0001#\u00051a$/\u001a4j]\u0016lWM\u001c;?!\r\u0001beE\u0005\u0003O\t\u00111aU3r!\u0011\u0001\u0002a\u0005\u0010\u0011\tAQ3CH\u0005\u0003W\t\u0011a\u0002T5oW\u0016$G*[:u\u0019&\\W\rC\u0003.\u0001\u0011\u0005a&\u0001\u0004%S:LG\u000f\n\u000b\u0002_A\u0011A\u0002M\u0005\u0003c\u0019\u0011A!\u00168ji\"I1\u0007\u0001a\u0001\u0002\u0004%\t\u0001N\u0001\u0005aJ,g/F\u0001\u001f\u0011%1\u0004\u00011AA\u0002\u0013\u0005q'\u0001\u0005qe\u00164x\fJ3r)\ty\u0003\bC\u0004:k\u0005\u0005\t\u0019\u0001\u0010\u0002\u0007a$\u0013\u0007\u0003\u0004<\u0001\u0001\u0006KAH\u0001\u0006aJ,g\u000f\t\u0005\u0006{\u0001!\tEP\u0001\u0007CB\u0004XM\u001c3\u0015\u0005yy\u0004\"\u0002!=\u0001\u0004q\u0012\u0001\u0002;iCRDQA\u0011\u0001\u0005B\r\u000ba!\u001b8tKJ$HCA\u0018E\u0011\u0015\u0001\u0015\t1\u0001\u001f\u0011\u00151\u0005\u0001\"\u0001/\u0003\u0019\u0011X-\\8wK\"\"Q\t\u0013(Q!\tIE*D\u0001K\u0015\tYe!\u0001\u0006b]:|G/\u0019;j_:L!!\u0014&\u0003\u00135LwM]1uS>t\u0017%A(\u0002}\u0011{WO\u00197fA1Lgn[3eA1L7\u000f\u001e\u0011o_^\u0004#/Z7pm\u0016\u001c\b\u0005\u001e5fA\r,(O]3oi\u0002rw\u000eZ3!MJ|W\u000e\t;iK\u0002b\u0017n\u001d;/C\u0005\t\u0016!\u0002\u001a/s9\u0002\u0004\"B*\u0001\t\u0013!\u0016AC1u\u0019>\u001c\u0017\r^5p]V\u0011Q+\u0017\u000b\u0003-\u0016$\"a\u00161\u0015\u0005a[\u0006C\u0001\u000bZ\t\u0015Q&K1\u0001\u0018\u0005\u0005!\u0006B\u0002/S\t\u0003\u0007Q,A\u0007p]>+Ho\u00144C_VtGm\u001d\t\u0004\u0019yC\u0016BA0\u0007\u0005!a$-\u001f8b[\u0016t\u0004\"B1S\u0001\u0004\u0011\u0017!\u00014\u0011\t1\u0019g\u0004W\u0005\u0003I\u001a\u0011\u0011BR;oGRLwN\\\u0019\t\u000b\u0019\u0014\u0006\u0019A4\u0002\u00039\u0004\"\u0001\u00045\n\u0005%4!aA%oi\")1\u000e\u0001C\u0005Y\u0006Yq.\u001e;pM\n|WO\u001c3t)\tAR\u000eC\u0003gU\u0002\u0007q\rC\u0003p\u0001\u0011\u0005\u0003/\u0001\u0003ee>\u0004HC\u0001\u0010r\u0011\u00151g\u000e1\u0001h\u0011\u0015\u0019\b\u0001\"\u00115\u0003\u0011!\u0018-\u001b7\t\u000bU\u0004A\u0011\t<\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\u0005M9\b\"\u00024u\u0001\u00049\u0007\"B=\u0001\t\u0003R\u0018AB;qI\u0006$X\rF\u00020wrDQA\u001a=A\u0002\u001dDQ! =A\u0002M\t\u0011\u0001\u001f\u0005\u0007\u007f\u0002!\t%!\u0001\u0002\u0007\u001d,G\u000f\u0006\u0003\u0002\u0004\u0005%\u0001\u0003\u0002\u0007\u0002\u0006MI1!a\u0002\u0007\u0005\u0019y\u0005\u000f^5p]\")aM a\u0001O\"q\u0011Q\u0002\u0001\u0011\u0002\u0007\u0005\t\u0011\"\u0003\u0002\u0010\u0005M\u0011\u0001D:va\u0016\u0014H%\u001b8tKJ$HcA\u0018\u0002\u0012!1\u0001)a\u0003A\u0002yI!A\u0011\u0016)\u000f\u0001\t9\"!\b\u0002\"A\u0019A\"!\u0007\n\u0007\u0005maA\u0001\u0006eKB\u0014XmY1uK\u0012\f#!a\b\u0002C2{w/\f7fm\u0016d\u0007\u0005\\5oW\u0016$\u0007\u0005\\5tiN\u0004\u0013M]3!I\u0016\u0004(/Z2bi\u0016$\u0007\u0005Z;fAQ|\u0007%\u001b3j_NLhn\u0019:bg&,7\u000fI5oA%tG/\u001a:gC\u000e,\u0007%\u00198eA%t7m\\7qY\u0016$X\r\t4fCR,(/Z:/C\t\t\u0019#\u0001\u00043]E\nd\u0006\r")
public interface DoubleLinkedListLike<A, This extends Seq<A> & DoubleLinkedListLike<A, This>>
extends LinkedListLike<A, This> {
    public /* synthetic */ void scala$collection$mutable$DoubleLinkedListLike$$super$insert(Seq var1);

    public This prev();

    @TraitSetter
    public void prev_$eq(This var1);

    @Override
    public This append(This var1);

    @Override
    public void insert(This var1);

    public void remove();

    @Override
    public This drop(int var1);

    @Override
    public This tail();

    @Override
    public A apply(int var1);

    @Override
    public void update(int var1, A var2);

    @Override
    public Option<A> get(int var1);
}

