package br.com.muxi.exame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import br.com.muxi.exame.config.SwaggerConfig;
import br.com.muxi.exame.config.WebMvcConfigSuport;

@SpringBootApplication
@EnableJpaRepositories
@Import(value = { WebMvcConfigSuport.class, SwaggerConfig.class })
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
