package task6;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.*;

public class NonBlockingMichaelScottQueueTest {

    @Test
    public void testEnqueueDequeue() throws InterruptedException {
        NonBlockingMichaelScottQueue<Integer> queue = new NonBlockingMichaelScottQueue<>();
        int numThreads = 4;
        int numElements = 1000;
        CountDownLatch enqueueLatch = new CountDownLatch(numThreads);
        CountDownLatch dequeueLatch = new CountDownLatch(numThreads);

        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);

        for (int i = 0; i < numThreads; i++) {
            executorService.submit(() -> {
                try {
                    for (int j = 0; j < numElements; j++) {
                        queue.enqueue(j);
                    }
                } finally {
                    enqueueLatch.countDown();
                }
            });
        }

        for (int i = 0; i < numThreads; i++) {
            executorService.submit(() -> {
                try {
                    for (int j = 0; j < numElements; j++) {
                        assertNotNull(queue.dequeue());
                    }
                } finally {
                    dequeueLatch.countDown();
                }
            });
        }

        enqueueLatch.await();
        dequeueLatch.await();
        executorService.shutdown();

        assertNull(queue.dequeue());
    }
}

