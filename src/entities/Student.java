package entities;

import java.util.ArrayList;
import java.util.List;

public class Student extends User {
    private double grade;
    private List<Course> courses = new ArrayList<>();

    public Student(long id, String name, int age, double grade, List<Course> courses) {
        super(id, name, age);
        setGrade(grade);
        setCourses(courses);
    }

    public Student(long id, String name, int age, double grade) {
        this(id, name, age, grade, null);
    }

    public Student(long id, String name, int age) {
        this(id, name, age, 0.0);
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        if (grade >= 0) {
            this.grade = grade;
        } else {
            throw new IllegalArgumentException("Grade should be greater than 0.");
        }
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        if (courses != null)
            this.courses = courses;
    }

    public void addCourse(Course course) {
        if (!courses.contains(course))
            courses.add(course);
    }

    public void removeCourse(long code) {
        long count = courses.stream().filter(course -> course.getCode() == code).count();
        if (count > 0) {
            courses = courses.stream().filter(course -> course.getCode() == code).toList();
        }
    }

    @Override
    public String toString() {
        return "Student { id = " + super.getId() +
                ", name = " + super.getName() +
                ", age = " + super.getAge() +
                ", grade = " + grade + " }";
    }
}
