package com.benjsoft.feignretryableexample.thread;

import java.time.LocalTime;

public class Processor implements Runnable {

   private Integer retry;

   private Integer value;

   public Processor(int value) {
      this.value = value;
      this.retry = 0;
   }

   @Override
   public void run() {
      System.out.println("[" + LocalTime.now() + "] STARTED Processing value = " + this.value + " from thread " + Thread.currentThread().getName());

      try {
         System.out.println("Executing processor value: " + this.value + ", retry: " + this.retry);
         Thread.sleep(5000);

         if (this.value == 20) {
            throw new InterruptedException("This caused exception to cancel the schedule!");
         }

         retry++;
         if(retry >= 24) {
            throw new InterruptedException("Processor value = " + this.value + " cancelled!, retry reached 24 times!");
         }
      } catch (InterruptedException e) {
         System.out.println("Exception caught in Processor: " + this.value + " : " + e.getMessage());
         throw new RuntimeException(e);
      }

      System.out.println("[" + LocalTime.now() + "] COMPLETED Processing value = " + this.value + " from thread " + Thread.currentThread().getName());
   }
}
