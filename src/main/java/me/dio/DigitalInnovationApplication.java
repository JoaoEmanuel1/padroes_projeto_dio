package me.dio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class DigitalInnovationApplication {

	public static void main(String[] args) {
		SpringApplication.run(DigitalInnovationApplication.class, args);
	}

}
