package repositories;

import entities.Student;
import entities.Teacher;
import input_output.IpOp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class TeacherRepo {
    private TeacherRepo() {
    }

    private static final Map<Long, Teacher> allTeachers = new HashMap<>();

    public static void addElement(Teacher obj) {
        allTeachers.put(obj.getId(), obj);
    }

    public static void removeElement(long id) {
        if (TeacherRepo.containsID(id)) {
            allTeachers.remove(id);
            System.out.println("Teacher removed successfully.");
        } else {
            System.out.println("Teacher with ID: " + id + " id not present.");
        }
    }

    public static boolean containsID(long id) {
        return allTeachers.containsKey(id);
    }

    public static boolean isEmpty() {
        return allTeachers.isEmpty();
    }

    public static Teacher getTeacher(long id) {
        return allTeachers.get(id);
    }

    public static Map<Long, Teacher> getAllTeachers() {
        return allTeachers;
    }

    public static void displayElements() {
        if (TeacherRepo.isEmpty()) {
            System.out.println("\nThere are currently no teachers in the system.");
        } else {
            System.out.println("\nAll the teachers are displayed below:\n");
            AtomicInteger counter = new AtomicInteger(1);
            allTeachers
                    .values()
                    .stream()
                    .sorted((a, b) -> Math.toIntExact(a.getId() - b.getId()))
                    .forEach(t -> {
                        System.out.println("------------------- " + counter.get() + " ------------------- ");
                        t.printDetails();
                        System.out.println("-----------------------------------------\n");
                        counter.getAndIncrement();
                    });
        }
    }
}
