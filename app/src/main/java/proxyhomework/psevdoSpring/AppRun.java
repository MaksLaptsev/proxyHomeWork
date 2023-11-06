package proxyhomework.psevdoSpring;

import java.util.Map;

public class AppRun {
    public static AppContext run(String packageName, Map<Class,Class> classMap){
        JavaConfig config = new JavaConfig(packageName, classMap);
        AppContext context = new AppContext(config);
        ObjectFactory objectFactory = new ObjectFactory(context);
        context.setObjectFactory(objectFactory);
        return context;
    }
    public static AppContext run(String packageName){
        JavaConfig config = new JavaConfig(packageName);
        AppContext context = new AppContext(config);
        ObjectFactory objectFactory = new ObjectFactory(context);
        context.setObjectFactory(objectFactory);
        return context;
    }
}
