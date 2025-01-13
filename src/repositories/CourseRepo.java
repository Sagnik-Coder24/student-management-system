package repositories;

import entities.Course;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseRepo implements Repository<Course> {

    private static CourseRepo instance;
    private final Map<Long, Course> allCourses = new HashMap<>();

    private CourseRepo() {
    }

    public static CourseRepo getInstance() {
        if (instance == null) {
            instance = new CourseRepo();
        }
        return instance;
    }

    @Override
    public void addElement(Course obj) {
        allCourses.put(obj.getCode(), obj);
    }

    @Override
    public void addElements(List<Course> objs) {
        objs.forEach(this::addElement);
    }

    @Override
    public void removeElement(long id) {
        allCourses.remove(id);
    }

    @Override
    public void displayElements() {
        System.out.println("All the students are displayed below:");
        allCourses.forEach((key, value) -> System.out.println("> " + key + " : " + value));
    }

}
