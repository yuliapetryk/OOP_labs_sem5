package task10;

import org.junit.Test;
import java.util.concurrent.CountDownLatch;
import static org.junit.Assert.fail;

public class MyThreadPoolTest {

    @Test
    public void testThreadPoolExecution() {
        final int numOfThreads = 3;
        MyThreadPool threadPool = new MyThreadPool(numOfThreads);
        final CountDownLatch latch = new CountDownLatch(1);
        final CountDownLatch executionLatch = new CountDownLatch(numOfThreads);

        Runnable runnableTask = () -> {
            try {
                System.out.println("Running...");
                Thread.sleep(2000);
                System.out.println("Stop Running...");
                latch.await();


            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        try {
            for (int i = 0; i < numOfThreads; i++) {
                threadPool.execute(runnableTask);

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            fail("Exception occurred while executing tasks");
        }

        latch.countDown();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadPool.shutdown();
    }

    @Test(expected = InterruptedException.class)
    public void testExecuteAfterShutdown() throws InterruptedException {
        MyThreadPool threadPool = new MyThreadPool(1);
        threadPool.shutdown();
        threadPool.execute(() -> System.out.println("This should not be executed"));
    }
}
