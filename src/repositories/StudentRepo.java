package repositories;

import entities.Student;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class StudentRepo {
    private StudentRepo() {
    }

    private static final Map<Long, Student> allStudents = new HashMap<>();

    public static void addElement(Student obj) {
        allStudents.put(obj.getId(), obj);
    }

    public static void removeElement(long id) {
        if (StudentRepo.containsID(id)) {
            allStudents.remove(id);
            System.out.println("Student removed successfully.");
        } else {
            System.out.println("Student with ID: " + id + " id not present.");
        }
    }

    public static boolean containsID(long id) {
        return allStudents.containsKey(id);
    }

    public static boolean isEmpty() {
        return allStudents.isEmpty();
    }

    public static Student getStudent(long id) {
        return allStudents.get(id);
    }

    public static Map<Long, Student> getAllStudents() {
        return allStudents;
    }

    public static void getStudentsGreaterThanGrade(double n) {
        if (StudentRepo.isEmpty()) {
            System.out.println("\nThere are currently no students in the system.");
        } else {
            System.out.println("\nAll the students with grade grater than " + n + " are displayed below:\n");
            AtomicInteger counter = new AtomicInteger(1);
            allStudents
                    .values()
                    .stream()
                    .filter(s -> s.getFinalGrade() >= n)
                    .forEach(s -> {
                        System.out.println("------------------- " + counter.get() + " ------------------- ");
                        s.printDetails();
                        System.out.println("-----------------------------------------\n");
                        counter.getAndIncrement();
                    });
        }
    }

    public static void displayElements() {
        if (StudentRepo.isEmpty()) {
            System.out.println("\nThere are currently no students in the system.");
        } else {
            System.out.println("\nAll the students are displayed below:\n");
            AtomicInteger counter = new AtomicInteger(1);
            allStudents
                    .values()
                    .stream()
                    .sorted((a, b) -> Math.toIntExact(a.getId() - b.getId()))
                    .forEach(s -> {
                        System.out.println("------------------- " + counter.get() + " ------------------- ");
                        s.printDetails();
                        System.out.println("-----------------------------------------\n");
                        counter.getAndIncrement();
                    });
        }
    }

    public static void displayStudentsSortedByAge(boolean ascending) {
        if (StudentRepo.isEmpty()) {
            System.out.println("\nThere are currently no students in the system.");
        } else {
            System.out.println("\nAll the students are displayed below:\n");
            AtomicInteger counter = new AtomicInteger(1);
            allStudents
                    .values()
                    .stream()
                    .sorted((a, b) -> ascending ? a.getAge() - b.getAge() : b.getAge() - a.getAge())
                    .forEach(s -> {
                        System.out.println("------------------- " + counter.get() + " ------------------- ");
                        s.printDetails();
                        System.out.println("-----------------------------------------\n");
                        counter.getAndIncrement();
                    });

        }
    }
}
