package proxyhomework.psevdoSpring.configurator;

import proxyhomework.psevdoSpring.AppContext;

/**
 * Конфигуратор для созданных объектов
 */
public interface ObjectConfig {
    Object config(Object object, AppContext context);
}
