package proxyhomework.psevdoSpring.configurator.impl;

import lombok.SneakyThrows;
import proxyhomework.psevdoSpring.AppContext;
import proxyhomework.psevdoSpring.annotations.Autowired;
import proxyhomework.psevdoSpring.configurator.ObjectConfig;
import java.lang.reflect.Field;

/**
 * Конфигуратор для полей с аннотацией Autowired, инициализирует соответствующие поля
 */
public class AutowiredAnnotationConfig implements ObjectConfig {
    @Override
    @SneakyThrows
    public Object config(Object object, AppContext context) {
        for (Field field : object.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(Autowired.class)){
                field.setAccessible(true);
                Object o = context.getObject(field.getType());
                field.set(object,o);
            }
        }
        return object;
    }
}
