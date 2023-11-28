package task1;

import org.junit.Test;
import java.io.*;
import static junit.framework.TestCase.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ServerTest {

    @Test
    public void saveTest() {
        String file = "src/main/resources/resultTest.txt";

        MyObject myObject = new MyObject();
        myObject.setMessage("Message");
        myObject.setName("Name");
        myObject.setNumber(1);

        try (FileWriter writer = new FileWriter(file, false)) {
        } catch (IOException e) {
            e.printStackTrace();
        }

        Server.save(myObject, file);

        StringBuilder stringBuilder = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String resultExpected = "Object  Name sent you the message 'Message' 1 times\n";
        String resultNotExpected = "Object  Wrong sent you the message 'Wrong' 100 times\n";

        assertEquals(resultExpected, stringBuilder.toString());
        assertNotEquals(resultNotExpected, stringBuilder.toString());
    }
}
