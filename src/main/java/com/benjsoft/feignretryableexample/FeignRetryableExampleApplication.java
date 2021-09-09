package com.benjsoft.feignretryableexample;

import feign.Retryer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@EnableFeignClients
@SpringBootApplication
public class FeignRetryableExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeignRetryableExampleApplication.class, args);
	}

	@Bean
	public Retryer retryer() {
		// return new Retryer.Default(1000L, 5000L, 5);
		return new Retryer.Default();
	}

}
