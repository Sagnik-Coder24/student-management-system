package repositories;

import entities.Student;
import entities.Teacher;
import input_output.IpOp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentRepo {
    private StudentRepo() {
    }

    private static final Map<Long, Student> allStudents = new HashMap<>();

    public static void addElement(Student obj) {
        allStudents.put(obj.getId(), obj);
        IpOp.saveStudentsToFile(allStudents);
    }

    public static void addElements(List<Student> objs) {
        objs.forEach(StudentRepo::addElement);
        IpOp.saveStudentsToFile(allStudents);
    }

    public static void removeElement(long id) {
        if (StudentRepo.containsID(id)) {
            allStudents.remove(id);
            System.out.println("Student removed successfully.");
            IpOp.saveStudentsToFile(allStudents);
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

    public static Student getStudent(long id){
        return allStudents.get(id);
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
