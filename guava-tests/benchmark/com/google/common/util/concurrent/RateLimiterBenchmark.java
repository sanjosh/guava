package com.google.common.util.concurrent;

import com.google.common.base.Stopwatch;
import com.google.common.util.concurrent.RateLimiter.SleepingStopwatch;

import static java.util.concurrent.TimeUnit.MICROSECONDS;

/**
 * Created by sandeep on 18/9/16.
 */
public final class RateLimiterBenchmark {

    public static void main(String[] args) {

        RateLimiter.SleepingStopwatch stopwatch = RateLimiter.SleepingStopwatch.createFromSystemTimer();

        RateLimiter limiter = RateLimiter.create(stopwatch, 100.0);
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            limiter.acquire();
        }
        long endTime = System.currentTimeMillis();

        System.out.println(endTime - startTime);
    }
}
