package task1;

import org.junit.Test;
import static junit.framework.TestCase.*;

public class ClientTest {
    @Test
    public void testSendMyObjectToServer() {
        MyObject expectedObject = getMyObject();
        assertEquals(expectedObject.toString(), Client.getMyObject().toString());
    }

    private MyObject getMyObject() {
        MyObject dataObject = new MyObject();
        dataObject.setName("Yulia");
        dataObject.setMessage("Hi");
        dataObject.setNumber(7);
        return dataObject;
    }
    
}
