package proxyhomework.anotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * ProxyClass - аннотация указывает на то, что данный класс выступает в роли прокси объекта
 * данный клаас обязан имплементить все интерфейсы классов(которые реализуют данные интерфейсы),которые он будет проксировать.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface ProxyClass {
}
