package io.github.cafeteru.profile.common.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(getInfo());
    }

    private static Info getInfo() {
        Contact email = new Contact().email("cafeteru.dev@gmail.com");
        return new Info()
                .title("Profile Java Test")
                .description("Profile Exam Backend Skills")
                .version("1.0")
                .contact(email);
    }
}
