package org.amd.task04.schedule;

import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Task with settings of duration and repetition.
 */
public class ScheduledTask implements Runnable {
    private final Runnable task;
    private final int period;
    private final TimeUnit timeUnit;
    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

    private final AtomicInteger times;
    private final AtomicBoolean started;

    /**
     * Constructor for a task
     * @param task Task for run
     * @param times Number of repetition
     * @param period Repetition interval
     * @param timeUnit Unit of repetition interval
     */
    public ScheduledTask(Runnable task, int times, int period, TimeUnit timeUnit) {
        this.task = task;
        this.period = period;
        this.timeUnit = timeUnit;
        this.times = new AtomicInteger(times);
        this.started = new AtomicBoolean(false);
    }

    /**
     * Run a configured task
     */
    public void launch() throws RejectedExecutionException {
        if (scheduler.isShutdown() || scheduler.isTerminated()) {
            throw new RejectedExecutionException("Task is inactive");
        }

        if (started.get()) {
            throw new RejectedExecutionException("Task has already started");
        }

        started.set(true);
        scheduler.scheduleAtFixedRate(this, 0, period, timeUnit);
    }

    /**
     * Run one iteration of task
     */
    @Override
    public void run() {
        if (times.get() == 0) {
            stop();
            return;
        }

        new Thread(task).start();

        times.decrementAndGet();
    }

    /**
     * Returns working state
     * @return Working state
     */
    public boolean getStarted() {
        return started.get();
    }

    private void stop() {
        started.set(false);
        scheduler.shutdown();
    }
}
