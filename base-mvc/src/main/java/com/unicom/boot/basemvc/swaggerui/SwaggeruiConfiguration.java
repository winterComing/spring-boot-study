package com.unicom.boot.basemvc.swaggerui;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author dengh
 */
@Configuration
@EnableSwagger2
public class SwaggeruiConfiguration {

        @Bean
        public Docket createRestApi() {
            return new Docket(DocumentationType.SWAGGER_2)
                    .apiInfo(apiInfo())
                    .select()
                    .apis(RequestHandlerSelectors.basePackage("com.unicom.boot.basemvc"))
                    .paths(PathSelectors.any())
                    .build();
        }
        @Bean
        public ApiInfo apiInfo() {
            return new ApiInfoBuilder()
                    .title("Spring Boot中使用Swagger2构建RESTful APIs")
                    .description("更多Spring Boot相关文章请关注：http://blog.didispace.com/")
                    .termsOfServiceUrl("http://blog.didispace.com/")
                    .version("1.0")
                    .build();
        }

}
