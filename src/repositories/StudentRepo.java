package repositories;

import entities.Student;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentRepo implements Repository<Student> {

    private static StudentRepo instance;
    private final Map<Long, Student> allStudents = new HashMap<>();

    private StudentRepo() {
    }

    public static StudentRepo getInstance() {
        if (instance == null) {
            instance = new StudentRepo();
        }
        return instance;
    }

    @Override
    public void addElement(Student obj) {
        allStudents.put(obj.getId(), obj);
    }

    @Override
    public void addElements(List<Student> objs) {
        objs.forEach(this::addElement);
    }

    @Override
    public void removeElement(long id) {
        allStudents.remove(id);
    }

    @Override
    public void displayElements() {
        System.out.println("All the students are displayed below:");
        allStudents.forEach((key, value) -> System.out.println("> " + key + " : " + value));
    }
}
