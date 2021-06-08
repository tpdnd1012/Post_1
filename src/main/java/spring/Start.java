package spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing // JPA 감시된 시간값을 넣어줌
public class Start {

    public static void main(String[] args) {

        SpringApplication.run(Start.class, args);

    }

}
