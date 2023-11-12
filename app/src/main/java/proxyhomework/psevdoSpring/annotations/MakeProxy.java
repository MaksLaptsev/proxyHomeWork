package proxyhomework.psevdoSpring.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * MakeProxy - аннотация указывающая на то, что этот класс необходимо проксировать
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface MakeProxy {
}
