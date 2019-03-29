package mx.appwhere.mediospago.front.application.converters;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import mx.appwhere.mediospago.front.domain.services.ConverterService;

/**
 * @author Alejandro Martin
 * @version 1.0 - 2017/09/22
 */
public abstract class AbstractConverter<S, T> implements Converter<S, T> {
    /**
     * Used for doing automatic mapping
     */
    protected ModelMapper modelMapper;

    /**
     * Use for calling other converters
     */
    protected ConverterService converterService;

    // setters & getters

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Autowired
    public void setConverterService(ConverterService converterService) {
        this.converterService = converterService;
    }
}
