import entities.*;
import repositories.StudentRepo;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
//        Student s1 = new Student(1, "John", 25, 9.2);
////        System.out.println(s1);
//
//        List<String> list = new ArrayList<>();
//        list.add("Maths");
//        list.add("Java");
//        list.add("Python");
//
//        User t1 = new Teacher(1, "Alice", 54, null);
//
//        Admin admin = new Admin(1,"Bob",22,"1234");
//        admin.updatePassword("2222");
////        System.out.println(admin);
//
//        Teacher t2 = new Teacher(21,"lol",45);
//        Course c1 = new Course(001,"Java",List.of(t2),null);
////        System.out.println(c1);
//        c1.addStudent(new Student(1,"Josh",21,8.7,null));
////        System.out.println(c1);
//        c1.removeStudent(1);
////        System.out.println(c1);
//        t2.addCourse(c1);
//        System.out.println(t2);
//        System.out.println(t2.getCourses());
////        System.out.println(c1.getTeachers().get(0));

        StudentRepo srepo = StudentRepo.getInstance();
        srepo.addElement(new Student(1, "Alice", 21, 9.0));
        srepo.addElement(new Student(2, "Alice2", 21, 9.0));
        srepo.addElements(List.of(
                new Student(3, "Alice3", 21, 9.0),
                new Student(4, "Alice4", 21, 9.0),
                new Student(5, "Alice5", 21, 9.0)
        ));
//        srepo.displayElements();

        Student stu = new Student(1, "Alice", 21, 9.0);
        stu.printDetails();
    }
}
