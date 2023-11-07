package proxyhomework.psevdoSpring.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * InjectMap - указывает на то, что поле необходимо проинициализировать Map, которая будет построена из файла json,папки resources
 * если value = default, то в качестве ключа для проперти(из файла app.properties) будет выступать имя поля,
 * где значением будет имя файла, из которого нужно выгрузить и построить Map с entity. Если value - задано,
 * то оно будет выступать в качестве ключа
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface InjectMap {
    String value() default "";
}
