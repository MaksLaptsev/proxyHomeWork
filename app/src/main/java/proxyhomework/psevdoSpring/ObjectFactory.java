package proxyhomework.psevdoSpring;

import lombok.SneakyThrows;
import proxyhomework.psevdoSpring.configurator.ObjectConfig;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 * Фабрика по созданию объектов
 */
public class ObjectFactory {
    private List<ObjectConfig> configs = new ArrayList<>();
    private AppContext context;

    /**
     * При вызове конструктора сканирует пакет на наличие реализаций интерфейса ObjectConfig.class,
     * складывает их в коллекцию.
     * @param context - {@link AppContext}
     */
    @SneakyThrows
    public ObjectFactory(AppContext context) {
        this.context = context;
        for (Class<? extends ObjectConfig> aClass : context.getConfig().getReflections().getSubTypesOf(ObjectConfig.class)) {
            if (!(Modifier.isAbstract(aClass.getModifiers()) && !Modifier.isInterface(aClass.getModifiers()))){
                configs.add(aClass.getDeclaredConstructor().newInstance());
            }
        }
    }

    /**
     * Создание объекта по переданому Class<T> clazz
     * После создание - конфигурация(настройка) объекта
     * @param clazz - Интерфейс(как правило) -объект реализующего класса которого необходимо создать
     * @return - Созданный и сконфигурированный(настроенный) объект типа <T>
     */
    @SneakyThrows
    public <T> T createObject(Class<T> clazz){
        T t = clazz.getDeclaredConstructor().newInstance();

        configs.forEach(objectConfig -> objectConfig.config(t,context));

        return t;
    }
}
