package SerializeDemo;

import java.io.Serializable;

public class Student implements Serializable {
    public String name;
    public int number;
    public String sex;
    public int age;
    public void sexCheck()
    {
        System.out.println("Mailing a check to " + name
                + " " + sex);
    }
}
