package proxyhomework.psevdoSpring;

import lombok.Getter;
import lombok.Setter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Класс используется для получения, и их кеширования, от ObjectFactory
 */
@SuppressWarnings("unchecked")
public class AppContext {
    @Setter
    private ObjectFactory objectFactory;
    private Map<Class<?>,Object> cacheMap;
    @Getter
    private Config config;

    public AppContext(Config config) {
        this.config = config;
        this.cacheMap = new ConcurrentHashMap<>();
    }

    /**
     *В случае, если запрашиваемый объект уже был создан и сконфигурирован и помещен в кеш, то достает объект из кеша и отдает его
     * если объект еще никто не запрашивал - делегирует задачу по созданию и настройке объекта {@link ObjectFactory}
     * после помещает объект в кеш(если его там еще нету)
     * @param tClass - класс, объект которого необходим
     * @return - экземпляр tClass,если это итерфейс, то экземпляр класса, реализующего этот интерфейс
     */
    public <T> T getObject(Class<T> tClass){
        if (cacheMap.containsKey(tClass)){
            return (T) cacheMap.get(tClass);
        }

        Class<? extends T> implClass = tClass;
        if(tClass.isInterface()){
            implClass = config.getImplClass(tClass);
        }

        T t = objectFactory.createObject(implClass);

        cacheMap.put(tClass,t);

        return t;
    }

    public void removeCache(Class aClass){
        cacheMap.remove(aClass);
    }
    public void addToCache(Class aClass,Object object){
        cacheMap.put(aClass,object);
    }

    public boolean isContain(Class aClass){
        return cacheMap.containsKey(aClass);
    }
}
