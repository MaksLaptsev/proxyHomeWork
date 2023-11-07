package proxyhomework.psevdoSpring.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Inject - аннотация указывает на то, что данное поле необходимо проинициализировать простыми объектами(int, byte, Long, String ...)
 * если value = default, то в качестве ключа для проперти(из файла app.properties) будет выступать имя поля, где значением будет
 * необходимое значение для инициализации поля.
 * Если value != default - будет использовано оно в качестве ключа для поиска значения в app.properties
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Inject {
    String value() default "";
}
