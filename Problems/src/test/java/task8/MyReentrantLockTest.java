package task8;

import org.junit.Test;

public class MyReentrantLockTest {
    private final MyReentrantLock lock = new MyReentrantLock();
    private int value = 0;

    @Test
    public void testSharedResource() throws InterruptedException {
        MyReentrantLockTest sharedResource = new MyReentrantLockTest();

        Thread thread1 = new Thread(() -> {
            try {
                sharedResource.performTask();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                sharedResource.performTask();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
    }
    public void performTask() throws InterruptedException {
        lock.lock();
        try {
            value++;
            System.out.println("Task performed by thread " + Thread.currentThread().getName() + ". New value: " + value);
            for (int i = 0; i < 3; i++){
                value++;
                System.out.println("Nested task performed by thread " + Thread.currentThread().getName() + ". New value: " + value);
            }
        } finally {
            lock.unlock();
        }
    }
}
