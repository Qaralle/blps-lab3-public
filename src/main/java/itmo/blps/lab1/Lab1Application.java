package itmo.blps.lab1;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "TED.COM API",
                version = "1.4.88",
                description = "API для создания конференций на сайте TED.COM",
                contact = @Contact(url = "https://pornhub.com/", name = "Antonov Maxim")
        )
)
public class Lab1Application extends SpringBootServletInitializer {

        @Override
        protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
            return application.sources(Lab1Application.class);
        }

        public static void main(String[] args) throws Exception {
            SpringApplication.run(Lab1Application.class, args);
        }

}