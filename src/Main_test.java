import entities.Course;
import entities.Student;
import entities.Teacher;
import repositories.CourseRepo;
import utility.NameValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main_test {
    public static void main(String[] args) {
        Course c1 = new Course(12,"12");
        Course c2 = new Course(13,"13");
        Teacher t1 = new Teacher(2,"t",23);
        Teacher t2 = new Teacher(5,"t2",23);
        Student s1 = new Student(3,"s",12);
        System.out.println(c1);
        System.out.println(t1);
        System.out.println(s1);
        c1.addStudent(s1);
        c1.addTeacher(t1);
        c1.addTeacher(t2);
        t1.addCourse(c1);
        t2.addCourse(c1);
        s1.addCourse(c1);
        System.out.println(s1.getCourses());
        CourseRepo.addElement(c1);
        System.out.println(c1);
        t1.deleteCourseFromSystem();
        System.out.println(c1);
        System.out.println(t1.getCourses());
        System.out.println(t2.getCourses());
        System.out.println(s1.getCourses());


//        s1.addCourse(c1);
//        s1.addCourse(c2);
//        s1.removeCourse(c1.getCode());
//        System.out.println(s1.getCourses());
//        s1.addCourse(c1);
//        System.out.println(s1.getCourses());

//        String name = " sagnik              kumar ghosh     ";
//        System.out.println("Name: " + name);
//        System.out.println("Format name: " + NameValidator.formatName(name));
    }
}
