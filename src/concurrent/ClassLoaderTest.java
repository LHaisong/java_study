package concurrent;

import java.io.IOException;
import java.io.InputStream;

public class ClassLoaderTest {
    public static void main(String[]args){
        ClassLoader myLoader=new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                String filename=name;
                InputStream inputStream=getClass().getResourceAsStream(filename);
                if(inputStream==null){
                    return super.loadClass(name);
                }
                byte[]b;
                try {
                    b = new byte[inputStream.available()];
                    inputStream.read(b);
                } catch (IOException e) {
                    throw new ClassNotFoundException();
                }
                return defineClass(name,b,0,b.length);
            }
        };

        try {
            Object object1 = ClassLoaderTest.class.getClassLoader().loadClass("concurrent.ClassLoaderTest").newInstance();
            System.out.println(object1.getClass());
            System.out.println(object1 instanceof concurrent.ClassLoaderTest);
            Object object2 = myLoader.loadClass("concurrent.ClassLoaderTest").newInstance();
            System.out.println(object2.getClass());
            System.out.println(object2 instanceof concurrent.ClassLoaderTest);
        }catch (ClassNotFoundException e0) {
            e0.printStackTrace();
        }catch (InstantiationException e1){
            e1.printStackTrace();
        }catch (IllegalAccessException e2){
            e2.printStackTrace();
        }
    }
}
