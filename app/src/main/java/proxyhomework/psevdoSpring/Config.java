package proxyhomework.psevdoSpring;

import org.reflections.Reflections;
import java.util.Set;

public interface Config {
    <T> Class<? extends T> getImplClass(Class<T> clazz);
    Reflections getReflections();

    Set<Class<?>> getClassProxySet();

}
