package dms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = {"dms"})
@SpringBootApplication(scanBasePackages = {"dms"})
@EnableJpaRepositories(basePackages = {"dms"})
public class SpringBulletinApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBulletinApplication.class);
    }
}
