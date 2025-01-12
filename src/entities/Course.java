package entities;

import java.util.ArrayList;
import java.util.List;

public class Course {

    private long code;
    private String name;
    private List<Teacher> teachers = new ArrayList<>();
    private List<Student> students = new ArrayList<>();

    public Course(long code, String name, List<Teacher> teachers, List<Student> students) {
        setCode(code);
        setName(name);
        setTeachers(teachers);
        setStudents(students);
    }

    public Course(long code, String name, List<Teacher> teachers) {
        this(code, name, teachers, null);
    }

    public Course(long code, String name) {
        this(code, name, null);
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        if (teachers != null) {
            this.teachers = teachers;
        }
    }

    public void addTeacher(Teacher teacher) {
        if (!teachers.contains(teacher)) {
            teachers.add(teacher);
        }
    }

    public void removeTeacher(long id) {
        long count = teachers.stream().filter(teacher -> teacher.getId() == id).count();
        if (count > 0) {
            teachers = teachers.stream().filter(teacher -> teacher.getId() != id).toList();
        } else {
            System.out.println("No teacher found with ID: " + id);
        }
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        if (students != null) {
            this.students = students;
        }
    }

    public void addStudent(Student student) {
        if (!students.contains(student)) {
            students.add(student);
        }
    }

    public void removeStudent(long id) {
        long count = students.stream().filter(student -> student.getId() == id).count();
        if (count > 0) {
            students = students.stream().filter(student -> student.getId() != id).toList();
        } else {
            System.out.println("No student found with ID: " + id);
        }
    }

    @Override
    public String toString() {
        return "Course { " +
                "code=" + code +
                ", name=" + name +
                ", teachers=" + teachers +
                ", students=" + students +
                " }";
    }
}
