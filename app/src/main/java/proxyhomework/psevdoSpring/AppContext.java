package proxyhomework.psevdoSpring;

import lombok.Getter;
import lombok.Setter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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
    public void removeCache(Class aClass){
        cacheMap.remove(aClass);
    }
    public void addToCache(Class aClass,Object object){
        cacheMap.put(aClass,object);
    }

    public boolean isContain(Class aClass){
        return cacheMap.containsKey(aClass);
    }

    public <T> T getObject(Class<T> tClass){
        if (cacheMap.containsKey(tClass)){
            return (T) cacheMap.get(tClass);
        }

        Class<? extends T> implClass = tClass;
        if(tClass.isInterface()){
            implClass = config.getImplClass(tClass);
        }

        T t = objectFactory.createObject(implClass);


        if (!cacheMap.containsKey(tClass)){
            cacheMap.put(tClass,t);
        } else {
            t = (T) cacheMap.get(tClass);
        }
        return t;
    }
}
