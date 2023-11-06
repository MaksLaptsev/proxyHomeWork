package proxyhomework.proxy;

import org.reflections.Reflections;
import proxyhomework.anotation.MakeProxy;
import proxyhomework.proxy.proxyEntity.MyProxy;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@SuppressWarnings("unchecked")
public class ProxyFactory {

    private Map<Class<?>,Object> beans;

    public ProxyFactory(Package packageScan) {
        Reflections reflections = new Reflections(packageScan.getName());
        Set<Class<?>> proxyClasses = reflections.getTypesAnnotatedWith(MakeProxy.class);
        beans = initializeBeans(proxyClasses);
    }

    public <T> T getProxy(Class<T> implClass){
        MyProxy<T> proxy = new MyProxy<>(beans.get(implClass));

        return (T)proxy;
    }

    private Map<Class<?>,Object> initializeBeans(Set<Class<?>> proxyClasses){
        return proxyClasses.stream()
                .collect(Collectors.toMap(Function.identity(),x->{
                    try {
                        return x.getDeclaredConstructor().newInstance();
                    } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                             NoSuchMethodException e) {
                        throw new RuntimeException(e);
                    }
                }));
    }
}
