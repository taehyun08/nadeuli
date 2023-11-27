package kr.nadeuli;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class NadeuliApplication {

    public static void main(String[] args) {
        SpringApplication.run(NadeuliApplication.class, args);
    }

}
