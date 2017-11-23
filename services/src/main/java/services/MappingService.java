package services;

import org.dozer.Mapper;

import java.util.Collection;
import java.util.List;

/***
 * @author Jakub Palenik, 422453@mail.muni.cz
 */

public interface MappingService {

    <T> List<T> mapTo(Collection<?> objects, Class<T> mapToClass);

    <T> T mapTo(Object u, Class<T> mapToClass);

    Mapper getMapper();

}
