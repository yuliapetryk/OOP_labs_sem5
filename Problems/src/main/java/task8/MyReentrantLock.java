package task8;

public class MyReentrantLock {

        private final Object object = new Object();
        private Thread current = null;
        public void lock() throws InterruptedException {
            synchronized (object) {
                if (current == Thread.currentThread())
                    return;

                while (current != null) {
                    object.wait();
                }
            }
        }

        public void unlock() {
            synchronized (object) {
                if (current == Thread.currentThread()) {
                    current = null;
                    object.notify();
                }
            }
        }
    }
