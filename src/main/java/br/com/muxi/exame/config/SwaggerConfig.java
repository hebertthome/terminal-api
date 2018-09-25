package br.com.muxi.exame.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2).useDefaultResponseMessages(false).select()
                .apis(RequestHandlerSelectors.basePackage("br.com.muxi.exame.resources.documenteded")).build().apiInfo(metaData());
    }

    private ApiInfo metaData() {
        return new ApiInfo("Api Documentation", "Api Documentation", "1.0", "Terms of service",
                new Contact("Hebert Thomé", "https://github.com/hebertthome/terminal-api", "hebert.t.santos@gmail.com"), "Apache 2.0",
                "https://www.apache.org/licenses/LICENSE-2.0");
    }
}