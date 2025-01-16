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
    }

    public static void addElements(List<Student> objs) {
        objs.forEach(StudentRepo::addElement);
    }

    public static void removeElement(long id) {
        if (StudentRepo.containsID(id)) {
            allStudents.remove(id);
            System.out.println("Student removed successfully.");
        } else {
            System.out.println("Student with ID: " + id + " id not present.");
        }
    }

    public static boolean containsID(long id){
        return allStudents.containsKey(id);
    }

    public static boolean isEmpty() {
        return allStudents.isEmpty();
    }

    public static void displayElements() {
        if (StudentRepo.isEmpty()) {
            System.out.println("\nThere are currently no students in the system.");
        } else {
            System.out.println("\nAll the students are displayed below:");
            allStudents.forEach((key, value) -> System.out.println("> " + key + " : " + value));
        }
    }
}
