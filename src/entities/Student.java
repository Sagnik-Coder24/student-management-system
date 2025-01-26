package entities;

import repositories.CourseRepo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Student extends User {
    public Map<Long, Double> grades = new HashMap<>();
    private List<Course> courses = new ArrayList<>();

    public Student(long id, String name, int age) {
        super(id, name, age);
    }

    public double getFinalGrade() {
        return calcFinalGrade();
    }

    public Map<Long, Double> getGrades() {
        return grades;
    }

    public void displayGrades() {
        if (this.getGrades().isEmpty() || this.getGrades() == null) {
            System.out.println("No courses assigned.");
        } else {
            System.out.println("\n(" + this.getName() + ") Course CODE | Course Name | Grade");
            this.getGrades().forEach((code, grade) -> {
                Course course = CourseRepo.getCourse(code);
                System.out.println("> " + code + " | " + course.getName() + " | " + (grade == -1 ? "Not yet set" : grade));
            });
        }
    }

    public double calcFinalGrade() {
        if (grades.values().stream().anyMatch(g -> g == -1.0)) {
            return -1;
        } else {
            return grades.values().stream()
                    .mapToDouble(Double::doubleValue)
                    .average()
                    .orElse(-1.0);
        }
    }

    public void updateGrades(long code, double grade) {
        grades.put(code, grade);
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void addCourse(Course course) {
        if (!courses.contains(course)) {
            courses.add(course);
            grades.put(course.getCode(), -1.0);
        }
    }

    public void removeCourse(long code) {
        long count = courses.stream().filter(course -> course.getCode() == code).count();
        if (count > 0) {
            courses = courses
                    .stream()
                    .filter(course -> course.getCode() != code)
                    .collect(Collectors.toCollection(ArrayList::new));
            grades.remove(code);
        }
    }

    public void printDetails() {
        printDetails(0);
    }

    public void printDetails(int indent) {
        String indentation = " ".repeat(indent);
        System.out.println(indentation + "Student ID: " + getId());
        System.out.println(indentation + "Name: " + getName());
        System.out.println(indentation + "Age: " + getAge());
        System.out.println(indentation + "Courses assigned:");
        if (this.getCourses() == null || this.getCourses().isEmpty()) {
            System.out.println(indentation + "No courses assigned.");
        } else {
            for (Course course : this.getCourses()) {
                System.out.println(indentation + "---> " + course.getCode() + " : " + course.getName());
            }
        }
    }

    @Override
    public String toString() {
        return "Student { id = " + super.getId() +
                ", name = " + super.getName() +
                ", age = " + super.getAge() +
                ", grade = " +
                (getFinalGrade() == -1 ? "Grades not assigned for this particular student." : getFinalGrade())
                + " }";
    }
}
