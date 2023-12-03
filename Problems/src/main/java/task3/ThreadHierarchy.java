package task3;

public class ThreadHierarchy {
    private final StringBuilder builder = new StringBuilder();

    public String  printThreadHierarchy(ThreadGroup group) {
        Thread[] threads = new Thread[group.activeCount()];
        group.enumerate(threads);

        this.builder.append("Thread Hierarchy for Group: ").append(group.getName()).append("\n");

        for (Thread thread : threads) {
            if (thread != null) {
                this.builder.append("Thread: ").append(thread.getName()).append("\n");
            }
        }

        ThreadGroup[] groups = new ThreadGroup[group.activeGroupCount()];
        group.enumerate(groups);

        for (ThreadGroup subGroup : groups) {
            if (subGroup != null) {
                printThreadHierarchy(subGroup);
            }
        }
        return this.builder.toString();
    }

   /* public static void main(String[] args) {

        ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();
        ThreadGroup customGroup1 = new ThreadGroup(mainGroup, "CustomGroup1");
        ThreadGroup customGroup2 = new ThreadGroup(mainGroup, "CustomGroup2");

        Thread thread1 = new Thread(customGroup1, () -> {
            try {

                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Thread1");

        Thread thread2 = new Thread(customGroup2, () -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }, "Thread2");

        thread1.start();
        thread2.start();

        printThreadHierarchy(mainGroup);
        try {
            Thread.sleep(3000);
            printThreadHierarchy(mainGroup);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }*/
}
