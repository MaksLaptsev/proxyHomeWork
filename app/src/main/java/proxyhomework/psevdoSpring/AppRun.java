package proxyhomework.psevdoSpring;

import java.util.Map;

/**
 * Используется для запуска PsevdoSpring, инициализации AppContext, ObjectFactory, а так же связывает их между собой
 */
public class AppRun {
    public static AppContext run(String packageName, Map<Class,Class> classMap){
        JavaConfig config = new JavaConfig(packageName, classMap);
        AppContext context = new AppContext(config);
        ObjectFactory objectFactory = new ObjectFactory(context);
        context.setObjectFactory(objectFactory);
        return context;
    }

    /**
     * Создает JavaConfig, AppContext, ObjectFactory
     * @param packageName - имя пакета, который необходимо просканировать
     * @return - экземпляр настроеного AppContext
     */
    public static AppContext run(String packageName){
        JavaConfig config = new JavaConfig(packageName);
        AppContext context = new AppContext(config);
        ObjectFactory objectFactory = new ObjectFactory(context);
        context.setObjectFactory(objectFactory);
        return context;
    }
}
