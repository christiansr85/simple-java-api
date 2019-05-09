package employee.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SpringFoxConfig {
    @Bean
    public Docket apiDocket() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("employee.controller"))
                .paths(PathSelectors.any()).build()
                .apiInfo(getApiInfo());
    }

    List<VendorExtension> vendorExtensions = new ArrayList<>();

    private ApiInfo getApiInfo() {
        return new ApiInfo("Employees service", "API Rest which provides CRUD operations to manage employees", "", "",
                new Contact("Cristian Sánchez Ruiz", "", "christiansr85@gmail.com"), "", "", vendorExtensions);
    }
}