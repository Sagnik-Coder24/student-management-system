package repositories;

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
    }

    public static void addElements(List<Teacher> objs) {
        objs.forEach(TeacherRepo::addElement);
    }

    public static void removeElement(long id) {
        allTeachers.remove(id);
    }

    public static void displayElements() {
        System.out.println("All the students are displayed below:");
        allTeachers.forEach((key, value) -> System.out.println("> " + key + " : " + value));
    }

}
