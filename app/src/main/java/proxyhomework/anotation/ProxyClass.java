package proxyhomework.anotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * ProxyClass - аннотация используется для маркировки класса, который выступает в роли прокси.
 * Value - должно быть именем класса, который необходимо проксировать
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface ProxyClass {
    String[] value() default {""};
}
