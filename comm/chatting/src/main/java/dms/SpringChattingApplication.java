package dms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan(basePackages = {"common"})
@SpringBootApplication(scanBasePackages = {"chatting", "common"})
public class SpringChattingApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringChattingApplication.class);
    }
}
