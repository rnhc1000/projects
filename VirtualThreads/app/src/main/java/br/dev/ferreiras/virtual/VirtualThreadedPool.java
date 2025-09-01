package br.dev.ferreiras.virtual;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class VirtualThreadedPool {

    private static final Logger LOGGER = LoggerFactory.getLogger(VirtualThreadedPool.class);

    public void executeTasks(int NUMBER_OF_TASKS) {
        int BLOCKING_CALL = 1;
        LOGGER.info("Number of tasks executed using 'VirtualThreadPool()");

        long startTime = System.currentTimeMillis();
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            IntStream.range(0, NUMBER_OF_TASKS).forEach(i -> {
                executor.submit(() -> {
                    Thread.sleep(BLOCKING_CALL);
//                    LOGGER.warn(Thread.currentThread().getName());
                    return i;
                });
            });
        } catch (Exception ex) {
            LOGGER.error("Something bad had just happened! -> {}", ex.getMessage());
            throw new RuntimeException();
        }
        long endTime = System.currentTimeMillis();
        LOGGER.info("For executing -> {} tasks, duration is: -> {} ms", NUMBER_OF_TASKS, endTime - startTime);
    }
}
