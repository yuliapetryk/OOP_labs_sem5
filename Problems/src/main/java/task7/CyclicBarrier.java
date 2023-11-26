package task7;

public class CyclicBarrier {
    private final int parties;
    private int  count;

    public CyclicBarrier(int parties) {
        this.parties = parties;
        this.count = 0;
    }

    public synchronized void await() throws InterruptedException {
        count++;
        while (count != parties) {
            wait();
        }
        reset();
    }

    public synchronized void reset() {
        count = 0;
        this.notifyAll();
    }
}
