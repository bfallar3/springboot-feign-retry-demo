package com.benjsoft.feignretryableexample;

import com.benjsoft.feignretryableexample.thread.Processor;
import feign.Retryer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

@EnableFeignClients
@SpringBootApplication
public class FeignRetryableExampleApplication implements CommandLineRunner {

   private final ScheduledExecutorService executorService;

   public FeignRetryableExampleApplication() {
      this.executorService = Executors.newScheduledThreadPool(10);
   }

   public static void main(String[] args) {
      SpringApplication.run(FeignRetryableExampleApplication.class, args);
   }

   @Bean
   public Retryer retryer() {
      // return new Retryer.Default(1000L, 5000L, 5);
      return new Retryer.Default();
   }

   @Override
   public void run(String... args) throws Exception {
      List<Integer> items = Arrays.asList(10, 20, 30, 40, 50);

      for (Integer item : items) {
         try {
            Processor command = new Processor(item);
            executorService.scheduleAtFixedRate(command, 0, 30, TimeUnit.SECONDS);
         } catch (RuntimeException e) {
            System.out.println(e.getMessage());
         }
      }
   }
}
