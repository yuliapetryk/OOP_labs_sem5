package task4;

import org.junit.Test;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class ClassInfoTest {
    @Test
    public void test() throws ClassNotFoundException {
        String className = "data.MathStudent";
        ClassData testClass = new ClassData();
        String result = testClass.info(className);

        assertFalse(result.contains("data.MathStudentNotHere"));
        assertFalse(result.contains("private class java.lang.String NotMyName"));

        assertTrue(result.contains("Constructors:"));
        assertTrue(result.contains("data.MathStudent"));
        assertTrue(result.contains("Parameters: [java.lang.String arg0, java.lang.String arg1, int arg2, java.lang.String arg3, double arg4, int arg5]"));
        assertTrue(result.contains("Interfaces: data.Student"));
        assertTrue(result.contains("Superclass: java.lang.Object"));
        assertTrue(result.contains("Class: MathStudent"));
        assertTrue(result.contains("Fields:"));
        assertTrue(result.contains("private class java.lang.String name"));
        assertTrue(result.contains("private class java.lang.String surname"));
        assertTrue(result.contains("private int age"));
        assertTrue(result.contains("private class java.lang.String nationality"));
        assertTrue(result.contains("private double gpa"));
        assertTrue(result.contains("private int mathLevel"));
        assertTrue(result.contains("Methods:"));
        assertTrue(result.contains("getName java.lang.String"));
        assertTrue(result.contains("setName void"));
        assertTrue(result.contains("displayMathStudentInfo void"));
        assertTrue(result.contains("equals boolean"));
        assertTrue(result.contains("toString java.lang.String"));
        assertTrue(result.contains("hashCode int"));
        assertTrue(result.contains("getClass java.lang.Class"));
        assertTrue(result.contains("notify void"));
        assertTrue(result.contains("notifyAll void"));
        assertTrue(result.contains("wait void"));
    }
}
