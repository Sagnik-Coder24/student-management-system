package repositories;

import entities.Course;
import entities.Teacher;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseRepo {
    private CourseRepo() {
    }

    private static final Map<Long, Course> allCourses = new HashMap<>();

    public static void addElement(Course obj) {
        allCourses.put(obj.getCode(), obj);
    }

    public static void addElements(List<Course> objs) {
        objs.forEach(CourseRepo::addElement);
    }

    public static void removeElement(long id) {
        allCourses.remove(id);
    }

    public static boolean containsCode(long code) {
        return allCourses.containsKey(code);
    }

    public static boolean isEmpty() {
        return allCourses.isEmpty();
    }

    public static Course getCourse(long code) {
        return allCourses.get(code);
    }

    public static void displayElements() {
        if (CourseRepo.isEmpty()) {
            System.out.println("\nThere are currently no courses in the system.");
        } else {
            System.out.println("\nAll the courses are displayed below:");
            allCourses.forEach((key, value) -> System.out.println("> " + key + " : " + value));
        }
    }

}
