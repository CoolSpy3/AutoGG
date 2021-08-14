/* Decompiler 87ms, total 1100ms, lines 41 */
package com.coolspy3.autogg;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Multithreading {
   private static final AtomicInteger counter = new AtomicInteger(0);
   private static final ScheduledExecutorService RUNNABLE_POOL = Executors.newScheduledThreadPool(10, (r) -> {
      return new Thread(r, "ModCore Thread " + counter.incrementAndGet());
   });
   public static ThreadPoolExecutor POOL;

   public static ScheduledFuture<?> schedule(Runnable r, long initialDelay, long delay, TimeUnit unit) {
      return RUNNABLE_POOL.scheduleAtFixedRate(r, initialDelay, delay, unit);
   }

   public static ScheduledFuture<?> schedule(Runnable r, long delay, TimeUnit unit) {
      return RUNNABLE_POOL.schedule(r, delay, unit);
   }

   public static void runAsync(Runnable runnable) {
      POOL.execute(runnable);
   }

   public static Future<?> submit(Runnable runnable) {
      return POOL.submit(runnable);
   }

   static {
      POOL = new ThreadPoolExecutor(50, 50, 0L, TimeUnit.SECONDS, new LinkedBlockingQueue<>(), (r) -> {
         return new Thread(r, String.format("Thread %s", counter.incrementAndGet()));
      });
   }
}