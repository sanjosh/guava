package com.google.common.util.concurrent;

import com.google.common.base.Stopwatch;
import com.google.common.util.concurrent.RateLimiter.SleepingStopwatch;
import java.util.List;
import java.util.ArrayList;

import static java.util.concurrent.TimeUnit.MICROSECONDS;

/**
 * Created by sandeep on 18/9/16.
 */
public final class RateLimiterBenchmark {

    public static class LimitRunnable implements Runnable {

        private RateLimiter limiter;

        public LimitRunnable(RateLimiter inputLimiter) {
           limiter = inputLimiter;
        }
        public void run() {
            long startTime = System.currentTimeMillis();


            for (int i = 0; i < 100; i++) {
                limiter.acquire();
            }
            long endTime = System.currentTimeMillis();

            System.out.println(endTime - startTime);
        }
    }

    public static void main(String[] args) {

        RateLimiter.SleepingStopwatch stopwatch = RateLimiter.SleepingStopwatch.createFromSystemTimer();

        RateLimiter limiter = RateLimiter.create(stopwatch, 100.0);

        List<Thread> listThreads = new ArrayList<Thread>();
        for (int i = 0; i < 5; i++) {
            Runnable r = new LimitRunnable(limiter);
            Thread t = new Thread(r);
            t.start();
            listThreads.add(t);
        }

        for (Thread t : listThreads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
