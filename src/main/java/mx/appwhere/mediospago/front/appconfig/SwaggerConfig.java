package mx.appwhere.mediospago.front.appconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * http://host:port/context-path/swagger-ui.html
 * @author VBARRIOS
 * @version 1.0 - 29/03/2019
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
   
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("mx.appwhere.mediospago.front.application.controllers"))
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public ApiInfo apiInfo() {
       
    	Contact contact = new Contact("Appwhere","https://www.appwhere.mx/","");
        
        return new ApiInfoBuilder().title("Servicios de Solicitud de Medios de Pago")
                .description("Api de consulta para desarrolladores")
                .termsOfServiceUrl("Terminos de servicio N/A").contact(contact)
                .license("Licencia N/A")
                .licenseUrl("").version("1.0").build();
    }
}