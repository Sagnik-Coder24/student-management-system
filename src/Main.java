import entities.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        User s1 = new Student(1, "John", 25, 9.2);
//        System.out.println(s1);

        List<String> list = new ArrayList<>();
        list.add("Maths");
        list.add("Java");
        list.add("Python");

        User t1 = new Teacher(1, "Alice", 54, null);

        Admin admin = new Admin(1,"Bob",22,"1234");
        admin.updatePassword("2222");
//        System.out.println(admin);

        Course c1 = new Course(001,"Java",List.of(new Teacher(21,"lol",45)),null);
        System.out.println(c1);
        c1.addStudent(new Student(1,"Josh",21,8.7,null));
        System.out.println(c1);
        c1.removeStudent(0);
        System.out.println(c1);
    }
}
