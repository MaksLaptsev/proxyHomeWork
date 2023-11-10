package proxyhomework.psevdoSpring.configurator.impl;

import lombok.SneakyThrows;
import proxyhomework.anotation.MakeProxy;
import proxyhomework.anotation.OriginalObject;
import proxyhomework.psevdoSpring.AppContext;
import proxyhomework.psevdoSpring.configurator.ObjectConfig;
import java.lang.reflect.Field;

/**
 * Конфигуратор для аннотации MakeProxy, классы промаркированные данной аннотацией будут обернуты в класс {@link proxyhomework.proxy.proxyEntity.MyProxy}
 */
@SuppressWarnings("unchecked")
public class MakeProxyAnnotationConfig implements ObjectConfig {
    @Override
    @SneakyThrows
    public Object config(Object object, AppContext context) {
        MakeProxy makeProxy = object.getClass().getAnnotation(MakeProxy.class);
        if(makeProxy != null){

            Class key = object.getClass().getInterfaces()[0];
            Class proxyClass = context.getConfig().getClassProxySet().stream().findFirst().orElseThrow();

            Object proxy = context.getObject(proxyClass);
            context.removeCache(proxy.getClass());

            Field[] fields = proxy.getClass().getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(OriginalObject.class)){
                    field.setAccessible(true);
                    field.set(proxy,object);
                }
            }
            return proxy;
        }
        return object;
    }
}
