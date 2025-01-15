package repositories;

import entities.Student;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentRepo {
    private StudentRepo() {
    }

    private static final Map<Long, Student> allStudents = new HashMap<>();

    public static void addElement(Student obj) {
        allStudents.put(obj.getId(), obj);
        System.out.println("\nThe provided student is added to our system.");
        obj.printDetails();
    }

    public static void addElements(List<Student> objs) {
        objs.forEach(StudentRepo::addElement);
    }

    public static void removeElement(long id) {
        allStudents.remove(id);
    }

    public static void displayElements() {
        System.out.println("All the students are displayed below:");
        allStudents.forEach((key, value) -> System.out.println("> " + key + " : " + value));
    }
}
