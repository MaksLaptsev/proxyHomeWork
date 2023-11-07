package proxyhomework.psevdoSpring.configurator;

import proxyhomework.psevdoSpring.AppContext;

/**
 * Конфигуратор для созданных объектов
 */
public interface ObjectConfig {
    void config(Object object, AppContext context);
}
