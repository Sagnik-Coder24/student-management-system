package entities;

import repositories.CourseRepo;
import repositories.StudentRepo;
import utility.Relationships;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
        if (!courses.contains(course))
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

    public void assignGrade() {

    }

    public void conductExam() {
        System.out.println("The exam has started. All the best.");
    }

    public void assignCourse() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter student ID:");
        long id = scanner.nextLong();
        scanner.nextLine();
        if (StudentRepo.containsID(id)) {
            Student student = StudentRepo.getStudent(id);
            System.out.println("Enter course CODE:");
            long code = scanner.nextLong();
            scanner.nextLine();
            Course course;
            if (CourseRepo.containsCode(code)) {
                course = CourseRepo.getCourse(code);
                Relationships.studentCourse(student, course);
            } else {
                System.out.println("Course with CODE: " + code + " is not present in our system. Please add it.");
                System.out.println("Enter course name:");
                String name = scanner.nextLine();
                course = new Course(code, name);
                CourseRepo.addElement(course);
                System.out.println("Course - " + course.getName() + " has been added in our system.");
                Relationships.studentCourse(student, course);
            }
            System.out.println("Course - " + course.getName() + " has been added for student - " + student.getName() + ".");
        } else {
            System.out.println("Student with ID: " + id + " is not present in our system.");
        }
    }

    public void deleteStudentCourse() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter student ID:");
        long id = scanner.nextLong();
        scanner.nextLine();
        if (StudentRepo.containsID(id)) {
            Student student = StudentRepo.getStudent(id);
            System.out.println("Enter course CODE:");
            long code = scanner.nextLong();
            scanner.nextLine();
            if (student.getCourses().stream().anyMatch(course -> course.getCode() == code)) {
                student.removeCourse(code);
                Course course = CourseRepo.getCourse(code);
                System.out.println("Course - " + course.getName() + " has been added for student - " + student.getName() + ".");
            } else {
                System.out.println("Course with CODE: " + code + " is not assigned to student: " + student.getName() + ".");
            }
        } else {
            System.out.println("Student with ID: " + id + " is not present in our system.");
        }
    }

    public String toString() {
        return "Teacher { id = " + super.getId() +
                ", name = " + super.getName() +
                ", age = " + super.getAge() + " }";
    }
}
