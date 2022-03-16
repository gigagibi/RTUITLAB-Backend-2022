package rtuitlab.supplies.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class SwaggerConfiguration {
    @Bean
    public Docket api() {
        Contact contact = new Contact(
                "It's me bro",
                "",
                "chel@gmail.com");

        List<VendorExtension> vext = new ArrayList<>();
        ApiInfo apiInfo = new ApiInfo(
                "Supplies documentation",
                "Supplies service of pizzeria automation system",
                "1.0",
                "...",
                contact,
                "...",
                "...",
                vext);
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo)
                .select()
                .paths(PathSelectors.regex("/.*"))
                .apis(RequestHandlerSelectors.basePackage(("rtuitlab.supplies.controllers")))
                .build();
    }
}
