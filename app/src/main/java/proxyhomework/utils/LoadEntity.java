package proxyhomework.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.TypeFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/**
 * Класс для загрузки информации об entity из json файла и последующей конвертации их в коллекцию
 * @param <T> - тип entity
 */
public class LoadEntity<T> {
    private final ObjectMapper mapper;

    public LoadEntity() {
        this.mapper = new ObjectMapper();
    }

    public Collection<T> getListOfObjects (String nameFile, Class<T> tClass){
        URL path = LoadEntity.class.getClassLoader().getResource(nameFile);
        CollectionType collectionType = mapper.getTypeFactory().constructCollectionType(ArrayList.class,tClass);
        Collection<T> collection = null;
        try {
            collection = mapper.readValue(path, collectionType);
        } catch (IOException e) {
            System.out.println("Not found file with name: "+nameFile);
        }
        return collection;
    }

    public Map<?,?> getListOfObjects (String nameFile, Class<? extends Map<?,?>> collectionClass, Class<?> keyClass, Class<?> valueClass){
        TypeFactory factory = TypeFactory.defaultInstance();
        MapType type = factory.constructMapType(collectionClass, keyClass, valueClass);
        URL path = LoadEntity.class.getClassLoader().getResource(nameFile);
        try {
            return mapper.readValue(path, type);
        } catch (IOException e) {
            System.out.println("Not found file with name: "+nameFile);
        }
        return null;
    }
}
