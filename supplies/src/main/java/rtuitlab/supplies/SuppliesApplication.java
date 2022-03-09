package rtuitlab.supplies;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SuppliesApplication {
    public static void main(String[] args) {
        SpringApplication.run(SuppliesApplication.class, args);
    }
}
