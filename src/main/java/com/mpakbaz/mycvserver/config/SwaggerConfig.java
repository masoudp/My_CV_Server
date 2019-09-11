package com.mpakbaz.mycvserver.config;

import com.mpakbaz.mycvserver.domain.enums.LOCALE;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

import static com.google.common.collect.Lists.newArrayList;


@Configuration
@EnableSwagger2
public class SwaggerConfig {
    ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("API Reference")
                .version("0.10")
                .build();
    }

    @Bean
    public Docket customImplementation() {
        Parameter header_locale = new ParameterBuilder()
                .name("Accept-Language")
                .description("Language")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false)
                .defaultValue(LOCALE.FA.name().toLowerCase())
                .build();

//        Parameter header_token = new ParameterBuilder()
//                .name("Authorization")
//                .text("token key")
//                .modelRef(new ModelRef("string"))
//                .parameterType("header")
//                .required(false)
//                .build();

        return new Docket(DocumentationType.SWAGGER_2)
                //todo: remove this before create client codes
                .forCodeGeneration(true)
                .globalOperationParameters(Collections.singletonList(header_locale))

                .apiInfo(apiInfo())
                .securitySchemes(newArrayList(apiKey()))
                .select().paths(PathSelectors.any())
//                .apis(RequestHandlerSelectors.any())  // If you want to list all the apis including springboots own
                .apis(RequestHandlerSelectors.basePackage("com.mpakbaz.mycvserver.controller"))
                .build()
                .pathMapping("/")
                .useDefaultResponseMessages(false)
//                .directModelSubstitute(LocalDate.class, String.class)
//                .genericModelSubstitutes(ResponseEntity.class)
                ;
    }



    private ApiKey apiKey() {
        return new ApiKey("Authorization", "Authorization", "header");
    }

}