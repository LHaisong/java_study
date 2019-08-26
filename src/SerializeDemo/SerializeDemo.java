package SerializeDemo;

import java.io.*;

public class SerializeDemo {
    public static void main(String[]args){
        Student s=new Student();
        s.name="LHaisong";
        s.sex="男";
        s.number=2016;
        s.age=21;
        serialize(s);
        System.out.println("序列化成功");
        deserialize();
    }
    public static void serialize(Student s){
        try {
            FileOutputStream fos=new FileOutputStream("J:\\student.ser");
            ObjectOutputStream out = new ObjectOutputStream(fos);
            out.writeObject(s);
            fos.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void deserialize(){
        Student s;
        try {
            FileInputStream fis=new FileInputStream("J:\\student.ser");
            ObjectInputStream in=new ObjectInputStream(fis);
            s=(Student) in.readObject();
            System.out.println("反序列化 Student...");
            System.out.println("name: " + s.name);
            System.out.println("sex: " + s.sex);
            System.out.println("number: " + s.number);
            System.out.println("age: " + s.age);
        }catch (IOException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e1){
            e1.printStackTrace();
        }
    }
}
