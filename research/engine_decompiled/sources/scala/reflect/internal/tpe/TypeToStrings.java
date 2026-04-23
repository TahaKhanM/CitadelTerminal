/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal.tpe;

import scala.collection.mutable.HashSet;
import scala.reflect.ScalaSignature;
import scala.reflect.internal.Types;
import scala.runtime.TraitSetter;

@ScalaSignature(bytes="\u0006\u0001e3\u0011\"\u0001\u0002\u0011\u0002\u0007\u0005AAC+\u0003\u001bQK\b/\u001a+p'R\u0014\u0018N\\4t\u0015\t\u0019A!A\u0002ua\u0016T!!\u0002\u0004\u0002\u0011%tG/\u001a:oC2T!a\u0002\u0005\u0002\u000fI,g\r\\3di*\t\u0011\"A\u0003tG\u0006d\u0017m\u0005\u0002\u0001\u0017A\u0011A\"D\u0007\u0002\u0011%\u0011a\u0002\u0003\u0002\u0007\u0003:L(+\u001a4\t\u000bA\u0001A\u0011\u0001\n\u0002\r\u0011Jg.\u001b;%\u0007\u0001!\u0012a\u0005\t\u0003\u0019QI!!\u0006\u0005\u0003\tUs\u0017\u000e\u001e\u0005\b/\u0001\u0011\r\u0011\"\u0002\u0019\u0003Ui\u0017\r\u001f+p'R\u0014\u0018N\\4SK\u000e,(o]5p]N,\u0012!G\b\u00025u\t!\u0007\u0003\u0004\u001d\u0001\u0001\u0006i!G\u0001\u0017[\u0006DHk\\*ue&twMU3dkJ\u001c\u0018n\u001c8tA!9a\u0004\u0001a\u0001\n\u0013y\u0012aE0u_N#(/\u001b8h%\u0016\u001cWO]:j_:\u001cX#\u0001\u0011\u0011\u00051\t\u0013B\u0001\u0012\t\u0005\rIe\u000e\u001e\u0005\bI\u0001\u0001\r\u0011\"\u0003&\u0003]yFo\\*ue&twMU3dkJ\u001c\u0018n\u001c8t?\u0012*\u0017\u000f\u0006\u0002\u0014M!9qeIA\u0001\u0002\u0004\u0001\u0013a\u0001=%c!1\u0011\u0006\u0001Q!\n\u0001\nAc\u0018;p'R\u0014\u0018N\\4SK\u000e,(o]5p]N\u0004\u0003\"B\u0016\u0001\t\u0003y\u0012A\u0005;p'R\u0014\u0018N\\4SK\u000e,(o]5p]NDQ!\f\u0001\u0005\u00029\na\u0003^8TiJLgn\u001a*fGV\u00148/[8og~#S-\u001d\u000b\u0003'=BQ\u0001\r\u0017A\u0002\u0001\nQA^1mk\u0016DqA\r\u0001A\u0002\u0013%1'A\t`i>\u001cFO]5oON+(M[3diN,\u0012\u0001\u000e\t\u0004kibT\"\u0001\u001c\u000b\u0005]B\u0014aB7vi\u0006\u0014G.\u001a\u0006\u0003s!\t!bY8mY\u0016\u001cG/[8o\u0013\tYdGA\u0004ICND7+\u001a;\u0011\u0005urT\"\u0001\u0001\n\u0005}\u0002%\u0001\u0002+za\u0016L!!\u0011\u0003\u0003\u000bQK\b/Z:\t\u000f\r\u0003\u0001\u0019!C\u0005\t\u0006)r\f^8TiJLgnZ*vE*,7\r^:`I\u0015\fHCA\nF\u0011\u001d9#)!AA\u0002QBaa\u0012\u0001!B\u0013!\u0014AE0u_N#(/\u001b8h'V\u0014'.Z2ug\u0002BQ!\u0013\u0001\u0005\u0002M\n\u0001\u0003^8TiJLgnZ*vE*,7\r^:\t\u000b-\u0003A\u0011\u0003'\u0002\u0019QL\b/\u001a+p'R\u0014\u0018N\\4\u0015\u00055#\u0006C\u0001(R\u001d\taq*\u0003\u0002Q\u0011\u00051\u0001K]3eK\u001aL!AU*\u0003\rM#(/\u001b8h\u0015\t\u0001\u0006\u0002C\u0003\u0004\u0015\u0002\u0007A\b\u0005\u0002W/6\tA!\u0003\u0002Y\t\tY1+_7c_2$\u0016M\u00197f\u0001")
public interface TypeToStrings {
    public int maxToStringRecursions();

    public int scala$reflect$internal$tpe$TypeToStrings$$_toStringRecursions();

    @TraitSetter
    public void scala$reflect$internal$tpe$TypeToStrings$$_toStringRecursions_$eq(int var1);

    public int toStringRecursions();

    public void toStringRecursions_$eq(int var1);

    public HashSet<Types.Type> scala$reflect$internal$tpe$TypeToStrings$$_toStringSubjects();

    @TraitSetter
    public void scala$reflect$internal$tpe$TypeToStrings$$_toStringSubjects_$eq(HashSet<Types.Type> var1);

    public HashSet<Types.Type> toStringSubjects();

    public String typeToString(Types.Type var1);
}

