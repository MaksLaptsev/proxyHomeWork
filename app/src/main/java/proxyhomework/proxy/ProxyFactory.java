package proxyhomework.proxy;

import lombok.SneakyThrows;
import org.reflections.Reflections;
import proxyhomework.anotation.MakeProxy;
import proxyhomework.anotation.ProxyClass;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;


@SuppressWarnings("unchecked")
public class ProxyFactory {

    private Map<Class<?>,Object> beans;
    private Set<Class<?>> proxyList;

    public ProxyFactory(Package packageScan) {
        Reflections reflections = new Reflections(packageScan.getName());
        Set<Class<?>> proxyClasses = reflections.getTypesAnnotatedWith(MakeProxy.class);
        proxyList = reflections.getTypesAnnotatedWith(ProxyClass.class);
        beans = initializeBeans(proxyClasses);
    }

    @SneakyThrows
    public <T> T getProxy(Class<T> implClass){
        Class<?> proxyClass = proxyList.stream()
                .filter(x-> Arrays.asList(x.getAnnotation(ProxyClass.class).value()).contains(implClass.getSimpleName()))
                .findFirst()
                .orElseThrow();
        Object proxy = proxyClass.getDeclaredConstructor(Object.class).newInstance(beans.get(implClass));
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
