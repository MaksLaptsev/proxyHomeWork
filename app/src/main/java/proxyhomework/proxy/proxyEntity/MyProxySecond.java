package proxyhomework.proxy.proxyEntity;

import lombok.SneakyThrows;
import proxyhomework.anotation.Log;
import proxyhomework.anotation.ProxyClass;
import proxyhomework.service.Service;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
/**
 * MyProxySecond - класс, который выступает в роли прокси класса. Для работы необходимо что бы он реализовывал все интерфейсы,
 * которые реализовывает проксируемый класс.
 * ProxyClass - аннотация , в значении которой указываем те классы, которые необходимо проксировать
 */
@ProxyClass("PersonServiceImpl")
public class MyProxySecond implements Service {
    private Object object;

    public MyProxySecond(Object object) {
        this.object = object;
    }

    @Override
    @SneakyThrows
    public Object getById(int id) {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        Method m = object.getClass().getMethod(methodName,int.class);
        m.setAccessible(true);

        if(m.isAnnotationPresent(Log.class)){
            System.out.print("Method with name \""+methodName+"\""+" owner: "+object.getClass().getSimpleName()+" will be start, ");
            System.out.println("With params: "+ Arrays.toString(new int[]{id})+" from Class: "+this.getClass().getName());
            id++;
            System.out.println("Params change to id = "+id);
            return  m.invoke(object,id);
        }
        return m.invoke(object,id);
    }

    @Override
    @SneakyThrows
    public List<?> getAll() {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        Method m = object.getClass().getMethod(methodName);
        m.setAccessible(true);

        if(m.isAnnotationPresent(Log.class)){
            System.out.print("Method with name \""+methodName+"\""+" owner: "+object.getClass().getSimpleName()+" will be start, ");
            System.out.println("With params: "+" from Class: "+object.getClass().getName());
            return (List<?>) m.invoke(object);
        }
        return (List<?>) m.invoke(object);
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
            id++;
            System.out.println("Params change to id = "+id);
            m.invoke(object,id);
        }
    }

    @Override
    @SneakyThrows
    public void save(Object o) {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        Method m = object.getClass().getMethod(methodName,o.getClass());
        m.setAccessible(true);
        if(m.isAnnotationPresent(Log.class)){
            System.out.print("Method with name \""+methodName+"\""+" owner: "+object.getClass().getSimpleName()+" will be start, ");
            System.out.println("With params: "+Arrays.toString(new Object[]{o})+" from Class: "+object.getClass().getName());
            System.out.println("Object not save :D");
        }
    }
}
