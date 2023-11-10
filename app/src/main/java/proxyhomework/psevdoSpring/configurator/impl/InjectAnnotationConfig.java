package proxyhomework.psevdoSpring.configurator.impl;

import lombok.SneakyThrows;
import proxyhomework.psevdoSpring.AppContext;
import proxyhomework.psevdoSpring.annotations.Inject;
import proxyhomework.psevdoSpring.configurator.ObjectConfig;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InjectAnnotationConfig implements ObjectConfig {
    private Map<String,String> propertiesMap;

    @SneakyThrows
    public InjectAnnotationConfig() {
        String path = ClassLoader.getSystemClassLoader().getResource("app.properties").getPath();
        Stream<String> lines = new BufferedReader(new FileReader(path)).lines();
        propertiesMap = lines.map(line->line.split("="))
                .collect(Collectors.toMap(x->x[0], x->x[1]));
    }

    @Override
    @SneakyThrows
    public Object config(Object object, AppContext context) {
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Inject inject = field.getAnnotation(Inject.class);
            String valueToParse;
            if(inject!=null){
                valueToParse = inject.value().isEmpty() ? propertiesMap.get(field.getName()) :
                        propertiesMap.get(inject.value());
                parseToFieldValueAndSet(field,valueToParse, object);
            }
        }
        return object;
    }

    @SneakyThrows
    private void parseToFieldValueAndSet(Field field,String valueToSet,Object o){
        if(field.getType().equals(int.class)||field.getType().equals(Integer.class)){
            field.setInt(o,Integer.parseInt(valueToSet));
        } else if (field.getType().equals(double.class)||field.getType().equals(Double.class)) {
            valueToSet = valueToSet.replace(",",".");
            field.setDouble(o,Double.parseDouble(valueToSet));
        } else if (field.getType().equals(float.class)||field.getType().equals(Float.class)) {
            field.setFloat(o,Float.parseFloat(valueToSet));
        } else if (field.getType().equals(byte.class)||field.getType().equals(Byte.class)) {
            field.setByte(o,Byte.parseByte(valueToSet));
        } else if (field.getType().equals(short.class)||field.getType().equals(Short.class)) {
            field.setShort(o,Short.parseShort(valueToSet));
        }else if (field.getType().equals(String.class)){
            field.set(o,valueToSet);
        } else if (field.getType().equals(boolean.class)||field.getType().equals(Boolean.class)) {
            field.setBoolean(o,Boolean.parseBoolean(valueToSet));
        } else if (field.getType().equals(char.class)||field.getType().equals(Character.class)) {
            field.setChar(o,valueToSet.charAt(0));
        } else if (field.getType().equals(long.class)||field.getType().equals(Long.class)) {
            field.setLong(o,Long.parseLong(valueToSet));
        }
    }
}
