package repositories;

import entities.Teacher;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TeacherRepo implements Repository<Teacher> {

    private static TeacherRepo instance;
    private final Map<Long, Teacher> allTeachers = new HashMap<>();

    private TeacherRepo() {
    }

    public static TeacherRepo getInstance() {
        if (instance == null) {
            instance = new TeacherRepo();
        }
        return instance;
    }

    @Override
    public void addElement(Teacher obj) {
        allTeachers.put(obj.getId(), obj);
    }

    @Override
    public void addElements(List<Teacher> objs) {
        objs.forEach(this::addElement);
    }

    @Override
    public void removeElement(long id) {
        allTeachers.remove(id);
    }

    @Override
    public void displayElements() {
        System.out.println("All the students are displayed below:");
        allTeachers.forEach((key, value) -> System.out.println("> " + key + " : " + value));
    }

}
