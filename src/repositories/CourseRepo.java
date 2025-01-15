package repositories;

import entities.Course;

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

    public static void displayElements() {
        System.out.println("All the students are displayed below:");
        allCourses.forEach((key, value) -> System.out.println("> " + key + " : " + value));
    }

}
