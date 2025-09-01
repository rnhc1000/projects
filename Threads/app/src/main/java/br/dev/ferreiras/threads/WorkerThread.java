package br.dev.ferreiras.threads;

import java.util.logging.Level;
import java.util.logging.Logger;

public class WorkerThread extends Thread {
    public static int counter = 10;

    private static final Logger logger = Logger.getLogger(WorkerThread.class.getName());
    @Override
    public void run() {
        synchronized (WorkerThread.class) {
            for (int count = 1; count < counter; count += 2) {
                if (logger.isLoggable(Level.INFO)) {
                    logger.info(String.format("Executing threadOdd #%d", count));
                }
                Thread.yield();
            }
        }
    }
}
