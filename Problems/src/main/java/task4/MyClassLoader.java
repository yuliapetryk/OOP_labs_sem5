package task4;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MyClassLoader extends ClassLoader {

        @Override
        public Class<?> findClass(String name) throws ClassNotFoundException {
            byte[] bytes = loadClassData(name);
            return defineClass(name, bytes, 0, bytes.length);
        }

        byte[] loadClassData(String className) {
            ByteArrayOutputStream byteSt = new ByteArrayOutputStream();
            int len;
            String name = className.replace(".", "/") + ".class";
            try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(name)) {
               if(inputStream == null) {
                   return null;
               } else {
                   while (inputStream != null) {
                       if ((len = inputStream.read()) == -1) break;
                       byteSt.write(len);
                   }
               }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return byteSt.toByteArray();
        }
}
