package proxyhomework.proxy.proxyEntity;

import lombok.SneakyThrows;
import proxyhomework.anotation.Log;
import proxyhomework.anotation.OriginalObject;
import proxyhomework.anotation.ProxyClass;
import proxyhomework.service.HospitalService;
import proxyhomework.service.PersonService;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("unchecked")
@ProxyClass
public class MyProxy<T> implements HospitalService<T>, PersonService<T> {
    @OriginalObject
    private T object;

    public MyProxy() {
    }
    public MyProxy(T object) {
        this.object = object;
    }

    @Override
    @SneakyThrows
    public T getById(int id) {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        Method m = object.getClass().getMethod(methodName,int.class);
        m.setAccessible(true);

        if(m.isAnnotationPresent(Log.class)){
            System.out.print("Method with name \""+methodName+"\""+" owner: "+object.getClass().getSimpleName()+" will be start, ");
            System.out.println("With params: "+ Arrays.toString(new int[]{id})+" from Class: "+this.getClass().getName());
            return (T) m.invoke(object,id);
        }
        return (T) m.invoke(object,id);
    }

    @Override
    @SneakyThrows
    public List<T> getAll() {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        Method m = object.getClass().getMethod(methodName);
        m.setAccessible(true);

        if(m.isAnnotationPresent(Log.class)){
            System.out.print("Method with name \""+methodName+"\""+" owner: "+object.getClass().getSimpleName()+" will be start, ");
            System.out.println("With params: "+" from Class: "+object.getClass().getName());
            return (List<T>) m.invoke(object);
        }
        return (List<T>) m.invoke(object);
    }

    @Override
    @SneakyThrows
    public void deleteById(int id) {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        Method m = object.getClass().getMethod(methodName,int.class);
        m.setAccessible(true);
        if(m.isAnnotationPresent(Log.class)){
            System.out.print("Method with name \""+methodName+"\""+" owner: "+object.getClass().getSimpleName()+" will be start, ");
            System.out.println("With params: "+Arrays.toString(new int[]{id})+" from Class: "+object.getClass().getName());
            m.invoke(object,id);
        }
    }

    @Override
    @SneakyThrows
    public void save(T o) {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        Method m = object.getClass().getMethod(methodName,o.getClass());
        m.setAccessible(true);
        if(m.isAnnotationPresent(Log.class)){
            System.out.print("Method with name \""+methodName+"\""+" owner: "+object.getClass().getSimpleName()+" will be start, ");
            System.out.println("With params: "+Arrays.toString(new Object[]{o})+" from Class: "+object.getClass().getName());
            m.invoke(object,o);
        }
    }
}
