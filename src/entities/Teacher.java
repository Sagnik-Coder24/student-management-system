package entities;

import java.util.ArrayList;
import java.util.List;

public class Teacher extends User {
    private List<Course> courses = new ArrayList<>();

    public Teacher(long id, String name, int age, List<Course> courses) {
        super(id, name, age);
        setCourses(courses);
    }

    public Teacher(long id, String name, int age) {
        this(id, name, age, null);
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        if (courses != null)
            this.courses = courses;
    }

    public void addCourse(Course course) {
        if (courses.contains(course))
            courses.add(course);
    }

    public void removeCourse(long code) {
        long count = courses.stream().filter(course -> course.getCode() == code).count();
        if (count > 0) {
            courses = courses.stream().filter(course -> course.getCode() == code).toList();
        } else {
            System.out.println("No course found with CODE: " + code);
        }
    }

    public String toString() {
        return "Teacher { id = " + super.getId() +
                ", name = " + super.getName() +
                ", age = " + super.getAge() +
                ", courses = " + courses + " }";
    }
}
