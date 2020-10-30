package com.mountainpeak.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	@Bean
    public Docket swaggerApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiDetail())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.mountainpeak.api"))
                .paths(regex(".*?"))
                .build();
    }

    private ApiInfo apiDetail() {
        return new ApiInfoBuilder()
                .title("Mountain Peakers")
                .description("Mountain Peakers")
                .build();
    }

}
