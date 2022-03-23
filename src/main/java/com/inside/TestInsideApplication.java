package com.inside;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com"})
@EntityScan("com")
@EnableJpaAuditing
@EnableJpaRepositories("com.inside.repository")
public class TestInsideApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestInsideApplication.class, args);
    }

}
