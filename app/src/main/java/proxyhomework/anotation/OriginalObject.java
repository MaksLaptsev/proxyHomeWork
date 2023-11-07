package proxyhomework.anotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * OriginalObject - аннотация используется в прокси-классе, указывает на объект оригинального(проксируемого) класса,
 * который необходимо засетать
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface OriginalObject {
}
