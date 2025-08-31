package br.dev.ferreiras.threads;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ThreadWorker implements Runnable {
    public static int counter = 10;

    private static final Logger logger = Logger.getLogger(ThreadWorker.class.getName());

    Thread thread = new Thread();

    public ThreadWorker() {
    }

    public ThreadWorker(Thread thread) {
        this.thread = thread;
    }

    @Override
    public void run() {
        try {
            thread.join();
        } catch (InterruptedException interruptedException) {
            logger.info(String.format("Interruption caused by %s", interruptedException));
            Thread.currentThread().interrupt();
        }
        synchronized (ThreadWorker.class) {
            for (int count = 0; count < counter; count += 2) {
                if (logger.isLoggable(Level.INFO)) {
                    logger.info(String.format("Executing threadEven #%d", count));
                }
            }
        }
    }
}
