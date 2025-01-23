import entities.Admin;
import entities.Course;
import entities.Student;
import entities.Teacher;
import input_output.IpOp;
import org.json.*;
import org.json.JSONString;
import repositories.CourseRepo;
import repositories.StudentRepo;
import utility.NameValidator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main_test {
    public static void main(String[] args) {
//        Course c1 = new Course(12,"12");
//        Course c2 = new Course(13,"13");
//        Teacher t1 = new Teacher(2,"t",23);
//        Teacher t2 = new Teacher(5,"t2",23);
//        Student s1 = new Student(3,"s",12);
//        System.out.println(c1);
//        System.out.println(t1);
//        System.out.println(s1);
//        c1.addStudent(s1);
//        c1.addTeacher(t1);
//        c1.addTeacher(t2);
//        t1.addCourse(c1);
//        t2.addCourse(c1);
//        s1.addCourse(c1);
//        System.out.println(s1.getCourses());
//        CourseRepo.addElement(c1);
//        System.out.println(c1);
//        t1.deleteCourseFromSystem();
//        System.out.println(c1);
//        System.out.println(t1.getCourses());
//        System.out.println(t2.getCourses());
//        System.out.println(s1.getCourses());


//        s1.addCourse(c1);
//        s1.addCourse(c2);
//        s1.removeCourse(c1.getCode());
//        System.out.println(s1.getCourses());
//        s1.addCourse(c1);
//        System.out.println(s1.getCourses());

//        String name = " sagnik              kumar ghosh     ";
//        System.out.println("Name: " + name);
//        System.out.println("Format name: " + NameValidator.formatName(name));

//        StudentRepo.addElements(List.of(
//                new Student(1, "a", 34, 5.5, List.of(
//                        new Course(101, "LOL"),
//                        new Course(102, "LOL"))),
//                new Student(2, "a", 34),
//                new Student(3, "a", 34),
//                new Student(4, "a", 34)));
//        StudentRepo.displayElements();
//        IpOp.allReadIns();

        IpOp.allReadIns();

        try (BufferedReader br = new BufferedReader(new FileReader("src/input_output/files/utils.json"))) {
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line);
            }

            JSONObject jsonObject = content.isEmpty() ? new JSONObject() : new JSONObject(new JSONTokener(content.toString()));
            jsonObject.put("maxId", 12345);

            try (FileWriter fileWriter = new FileWriter("src/input_output/files/utils.json")) {
                fileWriter.write(jsonObject.toString(4));
            }
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        } catch (org.json.JSONException e) {
            System.out.println("Invalid JSON format: " + e.getMessage());
        }
    }
}