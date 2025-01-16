package repositories;

import entities.Student;
import entities.Teacher;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TeacherRepo {
    private TeacherRepo() {
    }

    private static final Map<Long, Teacher> allTeachers = new HashMap<>();

    public static void addElement(Teacher obj) {
        allTeachers.put(obj.getId(), obj);
        System.out.println("\nThe provided teacher is added to our system.");
    }

    public static void addElements(List<Teacher> objs) {
        objs.forEach(TeacherRepo::addElement);
    }

    public static void removeElement(long id) {
        if (TeacherRepo.containsID(id)) {
            allTeachers.remove(id);
            System.out.println("Teacher removed successfully.");
        } else {
            System.out.println("Teacher with ID: " + id + " id not present.");
        }
    }

    public static boolean containsID(long id){
        return allTeachers.containsKey(id);
    }

    public static boolean isEmpty() {
        return allTeachers.isEmpty();
    }

    public static void displayElements() {
        if (TeacherRepo.isEmpty()) {
            System.out.println("\nThere are currently no teachers in the system.");
        } else {
            System.out.println("\nAll the teachers are displayed below:");
            allTeachers.forEach((key, value) -> System.out.println("> " + key + " : " + value));
        }
    }
}
