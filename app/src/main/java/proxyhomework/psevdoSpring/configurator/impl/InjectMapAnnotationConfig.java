package proxyhomework.psevdoSpring.configurator.impl;

import lombok.SneakyThrows;
import proxyhomework.psevdoSpring.AppContext;
import proxyhomework.psevdoSpring.annotations.InjectMap;
import proxyhomework.psevdoSpring.configurator.ObjectConfig;
import proxyhomework.utils.LoadEntity;
import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Конфигуратор для полей с аннотацией InjectMap, инициализирует соответствующие поля
 */
@SuppressWarnings("unchecked")
public class InjectMapAnnotationConfig implements ObjectConfig {
    private Map<String,String> propertiesMap;

    @SneakyThrows
    public InjectMapAnnotationConfig() {
        String path = ClassLoader.getSystemClassLoader().getResource("app.properties").getPath();
        Stream<String> lines = new BufferedReader(new FileReader(path)).lines();
        propertiesMap = lines.map(line->line.split("="))
                .collect(Collectors.toMap(x->x[0], x->x[1]));
    }

    @Override
    @SneakyThrows
    public Object config(Object object, AppContext context) {
        for (Field field : object.getClass().getDeclaredFields()){
            field.setAccessible(true);
            InjectMap injectMap = field.getAnnotation(InjectMap.class);
            String valueName;
            if (injectMap != null){
                valueName = injectMap.value().isEmpty() ? propertiesMap.get(field.getName()) :
                        propertiesMap.get(injectMap.value());

                Class<?> keyClass = (Class) ((ParameterizedType)field.getGenericType()).getActualTypeArguments()[0];
                Class<?> entityClass = (Class) ((ParameterizedType)field.getGenericType()).getActualTypeArguments()[1];

                field.set(object,new LoadEntity<>().getListOfObjects(valueName,(Class)field.getType(),keyClass,entityClass));
            }
        }
        return object;
    }
}
