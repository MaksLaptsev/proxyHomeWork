package proxyhomework.proxy.proxyEntity;

import lombok.SneakyThrows;
import proxyhomework.anotation.Log;
import proxyhomework.service.Service;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

public class MyProxy<T> implements Service<T> {
    private Object object;

    public MyProxy(Object object) {
        this.object = object;
    }

    @Override
    @SneakyThrows
    public T getById(int id) {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        Method m = object.getClass().getMethod(methodName,int.class);
        m.setAccessible(true);

        if(m.isAnnotationPresent(Log.class)){
            System.out.print("Method with name \""+methodName+"\" will be start, ");
            System.out.println("With params: "+ Arrays.toString(new int[]{id})+" from Class: "+object.getClass().getName());
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
            System.out.print("Method with name \""+methodName+"\" will be start, ");
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
            System.out.print("Method with name \""+methodName+"\" will be start, ");
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
            System.out.print("Method with name \""+methodName+"\" will be start, ");
            System.out.println("With params: "+Arrays.toString(new Object[]{o})+" from Class: "+object.getClass().getName());
            m.invoke(object,o);
        }
    }
}
