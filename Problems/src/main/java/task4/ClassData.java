package task4;

import java.lang.reflect.*;
import java.util.Arrays;

public class ClassData {
    public String info( String className) throws ClassNotFoundException {

        StringBuilder builder = new StringBuilder();

        MyClassLoader classLoader = new MyClassLoader();
        Class<?> myClass = classLoader.findClass(className);

        builder.append("Class: ").append(myClass.getSimpleName()).append("\n");
        for(AnnotatedType type: myClass.getAnnotatedInterfaces()) {
            if (type.getType().getTypeName()!= null) {
                builder.append("Interfaces: ").append(type.getType().getTypeName()).append(" ").append("\n");
            }
        }

        builder.append("Superclass: ").append(myClass.getAnnotatedSuperclass().getType().getTypeName()).append("\n");
        int i = 1;
        if (myClass.getDeclaredFields().length != 0) {
           builder.append("Fields: ").append("\n");
           for (Field field : myClass.getDeclaredFields()) {
               builder.append(i).append(") ");
               i++;
               builder.append(Modifier.toString(field.getModifiers())).append(" ").append(field.getType()).append(" ").append(field.getName()).append("\n");
           }
        }

        i = 1;
        builder.append("Constructors: ").append("\n");
        for(Constructor<?> constructor: myClass.getConstructors()) {
            builder.append(i).append(") ");
            i++;
            builder.append(constructor.getName()).append("\n");
            builder.append("Parameters: ");
            builder.append(Arrays.toString(constructor.getParameters())).append("\n");
        }

        builder.append("Methods: ").append("\n");
         i = 1;
        for(var method: myClass.getMethods()) {
            builder.append(i).append(") ");
            i++;
            builder.append(method.getName()).append(" ").append(method.getReturnType().getName()).append("\n");
            builder.append("Parameters:");
            builder.append(Arrays.toString(method.getParameters())).append("\n");
        }

        if( myClass.getClasses().length != 0) {
            builder.append("Members: ").append("\n");
            for (var member : myClass.getClasses()) {
                builder.append(member.getName()).append("\n");
            }
        }

        return builder.toString();
    }
}