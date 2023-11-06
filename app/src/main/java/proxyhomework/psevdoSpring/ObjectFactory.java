package proxyhomework.psevdoSpring;

import lombok.SneakyThrows;
import proxyhomework.psevdoSpring.configurator.ObjectConfig;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class ObjectFactory {
    private List<ObjectConfig> configs = new ArrayList<>();
    private AppContext context;

    @SneakyThrows
    public ObjectFactory(AppContext context) {
        this.context = context;
        for (Class<? extends ObjectConfig> aClass : context.getConfig().getReflections().getSubTypesOf(ObjectConfig.class)) {
            if (!(Modifier.isAbstract(aClass.getModifiers()) && !Modifier.isInterface(aClass.getModifiers()))){
                configs.add(aClass.getDeclaredConstructor().newInstance());
            }
        }
    }

    @SneakyThrows
    public <T> T createObject(Class<T> clazz){
        T t = clazz.getDeclaredConstructor().newInstance();

        configs.forEach(objectConfig -> objectConfig.config(t,context));

        return t;
    }
}
