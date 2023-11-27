package task10;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class MyThreadPool {

    private final BlockingQueue<Runnable> runnableQueue;
    private final AtomicBoolean isShutDown;

    public MyThreadPool(final int numOfThreads) {
        this.runnableQueue = new LinkedBlockingQueue<>();
        this.isShutDown = new AtomicBoolean(false);
        for (int i = 0; i < numOfThreads; ++i) {
            WorkerThread thread = new WorkerThread();
            thread.setName("Thread(" + i +")");
            thread.start();
        }
    }

    public void execute(Runnable runnableTask) throws InterruptedException {
        if (!isShutDown.get()) {
            runnableQueue.put(runnableTask);
            synchronized (this) {
                notifyAll();
            }
        } else {
            throw new InterruptedException("Unable to execute task, because thread pool is shutdown");
        }
    }

    public void shutdown() {
        isShutDown.set(true);
    }

    private class WorkerThread extends Thread {
        @Override
        public void run() {
            while (!(isShutDown.get())) {
                Runnable runnableTask;
                while ((runnableTask = runnableQueue.poll()) != null) {
                    runnableTask.run();
                }
            }
        }
    }
}



