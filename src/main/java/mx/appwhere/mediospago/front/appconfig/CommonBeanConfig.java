package mx.appwhere.mediospago.front.appconfig;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Common third classes spring beans are registered here.
 *
 * @author Alejandro Martin
 * @version 1.0 - 2017/10/04
 */
@Configuration
public class CommonBeanConfig {

    /**
     * Create a bean for {@link ModelMapper}
     *
     * @return an instance of {@link ModelMapper}
     */
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    /**
     * Create a bean for {@link ObjectMapper}
     *
     * @return an instance of {@link ObjectMapper}
     */
    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
