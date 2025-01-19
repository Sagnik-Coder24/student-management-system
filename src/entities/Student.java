package entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Student extends User {
    private double grade;
    private List<Course> courses = new ArrayList<>();

    public Student(long id, String name, int age, double grade, List<Course> courses) {
        super(id, name, age);
        this.grade = grade;
        setCourses(courses);
    }

    public Student(long id, String name, int age, double grade) {
        this(id, name, age, grade, null);
    }

    public Student(long id, String name, int age) {
        this(id, name, age, -1);
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        if (grade >= 0) {
            this.grade = grade;
        } else {
            System.out.println("Grade should be greater than or equal to 0.");
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
            courses = courses
                    .stream()
                    .filter(course -> course.getCode() != code)
                    .collect(Collectors.toCollection(ArrayList::new));
        }
    }

    @Override
    public String toString() {
        return "Student { id = " + super.getId() +
                ", name = " + super.getName() +
                ", age = " + super.getAge() +
                ", grade = " +
                (getGrade() == -1 ? "Grades not assigned for this particular student." : getGrade())
                + " }";
    }
}
