package task9;

public class MyPhaser {
    private final int threadExpect;
    private int currentPhase;
    private int threadAwait;
    private final Object object = new Object();

    public MyPhaser(int threadExpect) {
        this.threadExpect = threadExpect;
    }

    public int arrive() {
        synchronized (object) {
            threadAwait++;
            if (threadAwait == threadExpect) {
                threadAwait = 0;
                object.notifyAll();
                return currentPhase++;
            }
        }
        return currentPhase;
    }

    public void awaitAdvance(int phase) throws InterruptedException {
        synchronized (object) {
                while (currentPhase  <= phase ) {
                object.wait();
            }
        }
    }
}
