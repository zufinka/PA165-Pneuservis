package services;

import org.dozer.Mapper;

import java.util.Collection;
import java.util.List;

/***
 * Dozer mapping class
 *
 * @author Jakub Palenik, 422453@mail.muni.cz
 */

public interface MappingService {

    /**
     * Self explanatory
     * @param objects
     * @param mapToClass
     * @param <T>
     * @return
     */
    <T> List<T> mapTo(Collection<?> objects, Class<T> mapToClass);

    /**
     * Self explanatory
     * @param u
     * @param mapToClass
     * @param <T>
     * @return
     */
    <T> T mapTo(Object u, Class<T> mapToClass);

    Mapper getMapper();

}
