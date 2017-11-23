package services;

import org.dozer.Mapper;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/***
 * @author Jakub Palenik, 422453@mail.muni.cz
 */

@Service
public class MappingServiceImpl implements MappingService {

    @Inject
    private Mapper dozer;

    public <T> List<T> mapTo(Collection<?> objects, Class<T> mapToClass) {
        List<T> mappedCollection = new ArrayList<>();
        for (Object object : objects) {
            mappedCollection.add(dozer.map(object, mapToClass));
        }
        return mappedCollection;
    }

    public <T> T mapTo(Object u, Class<T> mapToClass) {
        if (u == null) {
            return null;
        }
        return dozer.map(u, mapToClass);
    }

    public Mapper getMapper() {
        return dozer;
    }
}
