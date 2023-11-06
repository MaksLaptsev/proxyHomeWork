package proxyhomework.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class LoadEntity<T> {
    private final ObjectMapper mapper;

    public LoadEntity() {
        this.mapper = new ObjectMapper();
    }

    public List<T> getListOfObjects (String nameFile, Class<T> tClass){
        URL path = LoadEntity.class.getClassLoader().getResource(nameFile);
        CollectionType collectionType = mapper.getTypeFactory().constructCollectionType(ArrayList.class,tClass);
        List<T> list = null;
        try {
            list = mapper.readValue(path, collectionType);
        } catch (IOException e) {
            System.out.println("Not found file with name: "+nameFile);
        }
        return list;
    }
}
