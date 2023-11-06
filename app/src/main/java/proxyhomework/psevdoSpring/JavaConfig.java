package proxyhomework.psevdoSpring;

import org.reflections.Reflections;
import proxyhomework.anotation.ProxyClass;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@SuppressWarnings("unchecked")
public class JavaConfig implements Config{

    private Reflections reflections;
    private Map<Class,Class> classMap;

    private Set<Class<?>> classProxySet;

    public JavaConfig(String packageName,Map<Class,Class> classMap ) {
        this.classMap = classMap;
        this.reflections = new Reflections(packageName);
    }
    public JavaConfig(String packageName) {
        this.classMap = new HashMap<>();
        this.reflections = new Reflections(packageName);
    }

    @Override
    public <T> Class<? extends T> getImplClass(Class<T> clazz) {
        return classMap.computeIfAbsent(clazz, aClass -> {
            Set<Class<? extends T>> classes = reflections.getSubTypesOf(clazz);
            if(classes.size() != 1){
                classProxySet = classes.stream()
                        .filter(x->x.isAnnotationPresent(ProxyClass.class))
                        .collect(Collectors.toSet());
                classes = classes.stream()
                        .filter(x->!x.isAnnotationPresent(ProxyClass.class))
                        .collect(Collectors.toSet());
                if(classes.size() != 1){
                    throw new RuntimeException("0 or more than 1 impl");
                }
            }
            return classes.iterator().next();
        });
    }

    @Override
    public Reflections getReflections() {
        return reflections;
    }

    @Override
    public Set<Class<?>> getClassProxySet() {
        return classProxySet;
    }

}
