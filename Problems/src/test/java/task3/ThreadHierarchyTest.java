package task3;

import org.junit.Test;
import static junit.framework.TestCase.*;

public class ThreadHierarchyTest {

    @Test
    public void testThreadHierarchy() {
        ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();
        ThreadGroup subGroup1 = new ThreadGroup(mainGroup, "SubGroup1");
        ThreadGroup subGroup2 = new ThreadGroup(mainGroup, "SubGroup2");

        Thread thread1 = new Thread(subGroup1, () -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Thread1");

        Thread thread2 = new Thread(subGroup2, () -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Thread2");

        thread1.start();
        thread2.start();

        StringBuilder builder = new StringBuilder();
        builder.append("Thread Hierarchy for Group: main\n" +
                "Thread: main\n" +
                "Thread: Monitor Ctrl-Break\n" +
                "Thread: Thread1\n" +
                "Thread: Thread2\n" +
                "Thread Hierarchy for Group: SubGroup1\n" +
                "Thread: Thread1\n" +
                "Thread Hierarchy for Group: SubGroup2\n" +
                "Thread: Thread2");

        ThreadHierarchy threadHierarchy = new ThreadHierarchy();
        assertTrue(threadHierarchy.printThreadHierarchy(mainGroup).contains(builder));

        StringBuilder builder2 = new StringBuilder();
        builder2.append("Thread Hierarchy for Group: main\n" +
                "Thread: main\n" +
                "Thread: Monitor Ctrl-Break\n" +
                "Thread: Thread1\n" +
                "Thread Hierarchy for Group: SubGroup1\n" +
                "Thread: Thread1\n" +
                "Thread Hierarchy for Group: SubGroup2\n");

        String result = null;
        try {
            Thread.sleep(3000);
            ThreadHierarchy threadHierarchy2 = new ThreadHierarchy();
            result = threadHierarchy2 .printThreadHierarchy(mainGroup);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assert result != null;
        assertTrue(result.contains(builder2));
        assertFalse(result.contains("Thread: Thread2"));
    }

}
