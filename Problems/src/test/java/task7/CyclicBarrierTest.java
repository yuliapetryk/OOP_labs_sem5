package task7;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class CyclicBarrierTest {

    @Test
    public void testCyclicBarrier() throws InterruptedException {
        final int parties = 2;
        final int numberOfThreads = 4;

        CyclicBarrier barrier = new CyclicBarrier(parties);
        TestTask testTask = new TestTask(barrier);

        Thread[] threads = new Thread[numberOfThreads];
        for (int i = 0; i < numberOfThreads; i++) {
            threads[i] = new Thread(testTask);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            threads[i].start();
        }

        assertEquals(0, testTask.getFailedCount());
    }

    private static class TestTask implements Runnable {
        private final CyclicBarrier barrier;
        private int failedCount = 0;

        public TestTask(CyclicBarrier barrier) {
            this.barrier = barrier;
        }

        public int getFailedCount() {
            return failedCount;
        }

        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName() + " is waiting at the barrier.");
                barrier.await();
                System.out.println(Thread.currentThread().getName() + " has passed the barrier.");
            } catch (InterruptedException e) {
                System.err.println(Thread.currentThread().getName() + " was interrupted.");
                failedCount++;
            }
        }
    }
}

