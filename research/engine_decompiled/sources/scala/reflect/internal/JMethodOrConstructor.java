/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import scala.MatchError;
import scala.reflect.ScalaSignature;
import scala.reflect.internal.JMethodOrConstructor$;
import scala.runtime.BoxedUnit;

@ScalaSignature(bytes="\u0006\u0001i4A!\u0001\u0002\u0001\u0013\t!\"*T3uQ>$wJ]\"p]N$(/^2u_JT!a\u0001\u0003\u0002\u0011%tG/\u001a:oC2T!!\u0002\u0004\u0002\u000fI,g\r\\3di*\tq!A\u0003tG\u0006d\u0017m\u0001\u0001\u0014\u0005\u0001Q\u0001CA\u0006\r\u001b\u00051\u0011BA\u0007\u0007\u0005\u0019\te.\u001f*fM\"Aq\u0002\u0001BC\u0002\u0013\u0005\u0001#\u0001\u0004nK6\u0014WM]\u000b\u0002#I\u0019!\u0003F\u000f\u0007\tM\u0001\u0001!\u0005\u0002\ryI,g-\u001b8f[\u0016tGO\u0010\t\u0003+mi\u0011A\u0006\u0006\u0003\u000b]Q!\u0001G\r\u0002\t1\fgn\u001a\u0006\u00025\u0005!!.\u0019<b\u0013\tabC\u0001\u0004NK6\u0014WM\u001d\t\u0003+yI!a\b\f\u0003!\u0005sgn\u001c;bi\u0016$W\t\\3nK:$\b\u0002C\u0011\u0001\u0005\u0003\u0005\u000b\u0011B\t\u0002\u000f5,WNY3sA!)1\u0005\u0001C\u0001I\u00051A(\u001b8jiz\"\"!J\u0014\u0011\u0005\u0019\u0002Q\"\u0001\u0002\t\u000b=\u0011\u0003\u0019\u0001\u0015\u0013\u0007%\"RD\u0002\u0003\u0014\u0001\u0001A\u0003\"B\u0016\u0001\t\u0003a\u0013!C5t-\u0006\u0014\u0018I]4t+\u0005i\u0003CA\u0006/\u0013\tycAA\u0004C_>dW-\u00198\t\u000bE\u0002A\u0011\u0001\u001a\u0002\u0015QL\b/\u001a)be\u0006l7/F\u00014a\t!\u0014\bE\u0002\fk]J!A\u000e\u0004\u0003\u000b\u0005\u0013(/Y=\u0011\u0005aJD\u0002\u0001\u0003\nuA\n\t\u0011!A\u0003\u0002m\u00121a\u0018\u00132#\tat\b\u0005\u0002\f{%\u0011aH\u0002\u0002\b\u001d>$\b.\u001b8ha\t\u0001E\tE\u0002\u0016\u0003\u000eK!A\u0011\f\u0003\u0019QK\b/\u001a,be&\f'\r\\3\u0011\u0005a\"E!C#G\u0003\u0003\u0005\tQ!\u0001H\u0005\ryFE\r\u0003\nuA\n\t1!A\u0003\u0002m\n\"\u0001\u0010%\u0011\u0005-I\u0015B\u0001&\u0007\u0005\r\te.\u001f\u0005\u0006\u0019\u0002!\t!T\u0001\u000ba\u0006\u0014\u0018-\u001c+za\u0016\u001cX#\u0001(\u0011\u0007-)t\n\u0005\u0002\u0016!&\u0011\u0011K\u0006\u0002\u0005)f\u0004X\rC\u0003T\u0001\u0011\u0005A+\u0001\tqCJ\fW.\u00118o_R\fG/[8ogV\tQ\u000bE\u0002\fkY\u00032aC\u001bX!\tA6,D\u0001Z\u0015\tQv#\u0001\u0006b]:|G/\u0019;j_:L!\u0001X-\u0003\u0015\u0005sgn\u001c;bi&|g\u000eC\u0003_\u0001\u0011\u0005q,\u0001\u0006sKN,H\u000e\u001e+za\u0016,\u0012aT\u0004\u0006C\nA\tAY\u0001\u0015\u00156+G\u000f[8e\u001fJ\u001cuN\\:ueV\u001cGo\u001c:\u0011\u0005\u0019\u001ag!B\u0001\u0003\u0011\u0003!7CA2\u000b\u0011\u0015\u00193\r\"\u0001g)\u0005\u0011\u0007\"\u00025d\t\u0007I\u0017\u0001\u00057jMRlU\r\u001e5pIR{'*\\8d)\t)#\u000eC\u0003lO\u0002\u0007A.A\u0001n!\t)R.\u0003\u0002o-\t1Q*\u001a;i_\u0012DQ\u0001]2\u0005\u0004E\fQ\u0003\\5gi\u000e{gn\u001d;sk\u000e$xN\u001d+p\u00156|7\r\u0006\u0002&e\")1n\u001ca\u0001gB\u0012A\u000f\u001f\t\u0004+U<\u0018B\u0001<\u0017\u0005-\u0019uN\\:ueV\u001cGo\u001c:\u0011\u0005aBH!C=s\u0003\u0003\u0005\tQ!\u0001H\u0005\ryFe\r")
public class JMethodOrConstructor {
    private final Member member;

    public static JMethodOrConstructor liftConstructorToJmoc(Constructor<?> constructor) {
        return JMethodOrConstructor$.MODULE$.liftConstructorToJmoc(constructor);
    }

    public static JMethodOrConstructor liftMethodToJmoc(Method method) {
        return JMethodOrConstructor$.MODULE$.liftMethodToJmoc(method);
    }

    public Member member() {
        return this.member;
    }

    public boolean isVarArgs() {
        Member member;
        block4: {
            boolean bl;
            block3: {
                block2: {
                    member = this.member();
                    if (!(member instanceof Method)) break block2;
                    Method method = (Method)member;
                    bl = method.isVarArgs();
                    break block3;
                }
                if (!(member instanceof Constructor)) break block4;
                Constructor constructor = (Constructor)member;
                bl = constructor.isVarArgs();
            }
            return bl;
        }
        throw new MatchError(member);
    }

    public TypeVariable[] typeParams() {
        Member member;
        block4: {
            TypeVariable[] typeVariableArray;
            block3: {
                block2: {
                    member = this.member();
                    if (!(member instanceof Method)) break block2;
                    Method method = (Method)member;
                    typeVariableArray = method.getTypeParameters();
                    break block3;
                }
                if (!(member instanceof Constructor)) break block4;
                Constructor constructor = (Constructor)member;
                typeVariableArray = constructor.getTypeParameters();
            }
            return typeVariableArray;
        }
        throw new MatchError(member);
    }

    public Type[] paramTypes() {
        Member member;
        block4: {
            Type[] typeArray;
            block3: {
                block2: {
                    member = this.member();
                    if (!(member instanceof Method)) break block2;
                    Method method = (Method)member;
                    typeArray = method.getGenericParameterTypes();
                    break block3;
                }
                if (!(member instanceof Constructor)) break block4;
                Constructor constructor = (Constructor)member;
                typeArray = constructor.getGenericParameterTypes();
            }
            return typeArray;
        }
        throw new MatchError(member);
    }

    public Annotation[][] paramAnnotations() {
        Member member;
        block4: {
            Annotation[][] annotationArray;
            block3: {
                block2: {
                    member = this.member();
                    if (!(member instanceof Method)) break block2;
                    Method method = (Method)member;
                    annotationArray = method.getParameterAnnotations();
                    break block3;
                }
                if (!(member instanceof Constructor)) break block4;
                Constructor constructor = (Constructor)member;
                annotationArray = constructor.getParameterAnnotations();
            }
            return annotationArray;
        }
        throw new MatchError(member);
    }

    public Type resultType() {
        Member member;
        block4: {
            Type type;
            block3: {
                block2: {
                    member = this.member();
                    if (!(member instanceof Method)) break block2;
                    Method method = (Method)member;
                    type = method.getGenericReturnType();
                    break block3;
                }
                if (!(member instanceof Constructor)) break block4;
                type = BoxedUnit.TYPE;
            }
            return type;
        }
        throw new MatchError(member);
    }

    public JMethodOrConstructor(Member member) {
        this.member = member;
    }
}

