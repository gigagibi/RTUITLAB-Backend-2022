package rtuitlab.boughts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class PurchasesApplication {
    public static void main(String[] args) {
        SpringApplication.run(PurchasesApplication.class, args);
    }
}
