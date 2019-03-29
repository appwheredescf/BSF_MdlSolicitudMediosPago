package mx.appwhere.mediospago.front.domain.services;

import java.util.List;

/**
 * @author Alejandro Martin
 * @version 1.0 - 2017/10/18
 */
public interface ConverterService {
    <T> T convert(Object source, Class<T> targetType);

    <T1, T2> List<T2> getListOfConvertedObjects(List<T1> sourceList, Class<T1> sourceClass,
                                                Class<T2> targetClass);
}
