package proxyhomework.psevdoSpring.configurator.impl;

import lombok.SneakyThrows;
import proxyhomework.anotation.MakeProxy;
import proxyhomework.anotation.OriginalObject;
import proxyhomework.psevdoSpring.AppContext;
import proxyhomework.psevdoSpring.configurator.ObjectConfig;
import java.lang.reflect.Field;

@SuppressWarnings("unchecked")
public class MakeProxyAnnotationConfig implements ObjectConfig {
    @Override
    @SneakyThrows
    public void config(Object object, AppContext context) {
        MakeProxy makeProxy = object.getClass().getAnnotation(MakeProxy.class);
        if(makeProxy != null){

            Class key = object.getClass().getInterfaces()[0];
            Class proxyClass = context.getConfig().getClassProxySet().stream().findFirst().orElseThrow();

            Object proxy = context.getObject(proxyClass);
            Field[] fields = proxy.getClass().getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(OriginalObject.class)){
                    field.setAccessible(true);
                    field.set(proxy,object);

                    if(context.isContain(key)){
                        context.removeCache(object.getClass());
                        context.removeCache(proxy.getClass());
                        context.addToCache(object.getClass(),proxy);
                    }else {
                        context.removeCache(key);
                        context.removeCache(proxy.getClass());
                        context.addToCache(key,proxy);
                    }
                }
            }
        }
    }
}
