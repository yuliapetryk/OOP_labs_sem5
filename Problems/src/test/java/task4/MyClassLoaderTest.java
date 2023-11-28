package task4;

import org.junit.Test;
import static junit.framework.TestCase.*;

public class MyClassLoaderTest {

    @Test
    public void testFindClass() {
        MyClassLoader myClassLoader = new MyClassLoader();
        try {
            Class<?> loadedClass = myClassLoader.findClass("data.MathStudent");
            assertNotNull(loadedClass);
            assertEquals("data.MathStudent", loadedClass.getName());
        } catch (ClassNotFoundException e) {
            fail("Unexpected ClassNotFoundException");
        }
    }

    @Test
    public void testLoadClassData() {
        MyClassLoader myClassLoader = new MyClassLoader();
        String className = "data.MathStudent";
        byte[] classData = myClassLoader.loadClassData(className);
        assertNotNull(classData);
        assertTrue(classData.length > 0);
    }

    @Test
    public void testLoadNonExistentClassData() {
        MyClassLoader myClassLoader = new MyClassLoader();
        String className = "NonExistentClass";
        byte[] classData = myClassLoader.loadClassData(className);
        assertNull(classData);
    }
}
