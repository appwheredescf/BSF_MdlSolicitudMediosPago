package mx.appwhere.mediospago.front.appconfig;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.concurrent.Executors;

import mx.appwhere.mediospago.front.application.util.InExcelFileProcessor;
import mx.appwhere.mediospago.front.application.util.InFileProcessor;
import mx.appwhere.mediospago.front.application.util.OutFileProcessor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;

/**
 * Common third classes spring beans are registered here.
 *
 * @author Alejandro Martin
 * @version 1.0 - 2017/10/04
 */
@Configuration
@EnableScheduling
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
    
    @Bean
    public TaskScheduler taskExecutor () {
        return new ConcurrentTaskScheduler(Executors.newScheduledThreadPool(5));
    }

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public InFileProcessor inFileProcessor() {
        return new InFileProcessor();
    }

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public InExcelFileProcessor inExcelFileProcessor() {
        return new InExcelFileProcessor();
    }

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public OutFileProcessor outFileProcessor() {
        return new OutFileProcessor();
    }
}
