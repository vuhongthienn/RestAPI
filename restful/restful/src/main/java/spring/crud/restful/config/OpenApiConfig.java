package spring.crud.restful.config;

import com.google.common.collect.Lists;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .servers(Lists.newArrayList(
                        new Server().url("http://localhost:8080"),
                        new Server().url("https://cloundtea-production.up.railway.app")

                ))
                // info
                .info(new Info().title("RESTful Api CRUD project about member\n" +
                                "management.")
                        .description("API backend")
                        .contact(new Contact()
                                .email("vuhongthien18@gmail.com")
                                .name("vuhongthien")
                                .url("***")));
    }
}
