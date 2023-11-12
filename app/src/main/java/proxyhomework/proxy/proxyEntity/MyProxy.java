package proxyhomework.proxy.proxyEntity;

import lombok.SneakyThrows;
import org.apache.log4j.Logger;
import proxyhomework.psevdoSpring.annotations.Log;
import proxyhomework.psevdoSpring.annotations.OriginalObject;
import proxyhomework.psevdoSpring.annotations.ProxyClass;
import proxyhomework.service.HospitalService;
import proxyhomework.service.PersonService;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

/**
 * MyProxy - класс, который выступает в роли прокси класса. Для работы необходимо что бы он реализовывал все интерфейсы,
 * которые реализовывает проксируемый класс.
 * @param <T>
 */
@SuppressWarnings("unchecked")
@ProxyClass
public class MyProxy<T> implements HospitalService<T>, PersonService<T> {
    private Logger log = Logger.getLogger(MyProxy.class);
    /**
     * @OriginalObject - маркер, указывающий на оригинальный(проксируемый) объект
     */
    @OriginalObject
    private T object;

    @Override
    @SneakyThrows
    public T getById(int id) {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        Method m = object.getClass().getMethod(methodName,int.class);
        m.setAccessible(true);

        if(m.isAnnotationPresent(Log.class)){
            log.info("Method with name \""+methodName+"\""+" owner: "+object.getClass().getSimpleName()+" will be start, \n"
                    +"With params: "+ Arrays.toString(new int[]{id})+" from Class: "+this.getClass().getName());
            Object o =  m.invoke(object,id);
            log.info("Method with name \""+methodName+"\""+" owner: "+object.getClass().getSimpleName()+"  has completed execution");
            return (T)o;
        } else return (T) m.invoke(object,id);
    }

    @Override
    @SneakyThrows
    public List<T> getAll() {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        Method m = object.getClass().getMethod(methodName);
        m.setAccessible(true);

        if(m.isAnnotationPresent(Log.class)){
            log.info("Method with name \""+methodName+"\""+" owner: "+object.getClass().getSimpleName()+" will be start, \n"
                    +"With params: "+" from Class: "+object.getClass().getName());
            List<T> list = (List<T>) m.invoke(object);
            log.info("Method with name \""+methodName+"\""+" owner: "+object.getClass().getSimpleName()+"  has completed execution");
            return list;
        }else return (List<T>) m.invoke(object);
    }

    @Override
    @SneakyThrows
    public void deleteById(int id) {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        Method m = object.getClass().getMethod(methodName,int.class);
        m.setAccessible(true);
        if(m.isAnnotationPresent(Log.class)){
            log.info("Method with name \""+methodName+"\""+" owner: "+object.getClass().getSimpleName()+" will be start, \n"+
                    "With params: "+Arrays.toString(new int[]{id})+" from Class: "+object.getClass().getName());
            m.invoke(object,id);
            log.info("Method with name \""+methodName+"\""+" owner: "+object.getClass().getSimpleName()+"  has completed execution");
        }else m.invoke(object,id);
    }

    @Override
    @SneakyThrows
    public void save(T o) {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        Method m = object.getClass().getMethod(methodName,o.getClass());
        m.setAccessible(true);
        if(m.isAnnotationPresent(Log.class)){
            log.info("Method with name \""+methodName+"\""+" owner: "+object.getClass().getSimpleName()+" will be start, \n"
                    +"With params: "+Arrays.toString(new Object[]{o})+" from Class: "+object.getClass().getName());
            m.invoke(object,o);
            log.info("Method with name \""+methodName+"\""+" owner: "+object.getClass().getSimpleName()+"  has completed execution");
        }else  m.invoke(object,o);
    }
}
