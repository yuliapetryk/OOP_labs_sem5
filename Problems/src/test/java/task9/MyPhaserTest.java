package task9;


import org.junit.Test;
import task7.CyclicBarrierTest;


import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.concurrent.Phaser;

import static junit.framework.TestCase.assertEquals;

public class MyPhaserTest {

    private record TestTask(MyPhaser phaser) implements Runnable {
        @Override
        public void run() {
            try {
                for (int i = 0; i < 3; i++) {
                    int arrive = phaser.arrive();
                    phaser.awaitAdvance(arrive);
                    System.out.println(Thread.currentThread().getName() + " has reached a phase " + arrive);
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Test
    public void myPhaserTest() {
        MyPhaser phaser = new MyPhaser(3);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        int numberOfThreads = 3;
        Thread[] threads = new Thread[numberOfThreads];
        TestTask testTask = new TestTask(phaser);
        for (int i = 0; i < numberOfThreads; i++) {
            threads[i] = new Thread(testTask);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            threads[i].start();
        }

        for (int i = 0; i < numberOfThreads; i++) {
            try{
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}