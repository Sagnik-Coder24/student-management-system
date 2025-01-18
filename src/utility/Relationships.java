package utility;

import entities.Course;
import entities.Student;
import entities.Teacher;
import repositories.CourseRepo;

import java.util.Scanner;

public class Relationships {
    public static void teacherCourse(Teacher teacher, Course course) {
        teacher.addCourse(course);
        course.addTeacher(teacher);
    }

    public static void studentCourse(Student student, Course course) {
        student.addCourse(course);
        course.addStudent(student);
    }

    public static void removeStudentCourse(Student student, Course course) {
        student.removeCourse(course.getCode());
        course.removeStudent(student.getId());
    }

    public static void teacherAddingCourse(Teacher teacher) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter course code:");
        long code = scanner.nextLong();
        scanner.nextLine();
        if (CourseRepo.containsCode(code)) {
            Course course = CourseRepo.getCourse(code);
            if (teacher.getCourses().contains(course)) {
                System.out.println("Course - " + course.getName() + " already present.");
                return;
            }
            teacherCourse(teacher, course);
            System.out.println("Course - " + course.getName() + " has been added.");
        } else {
            System.out.println("Enter course name:");
            String name = scanner.nextLine();
            Course course = new Course(code, name);
            CourseRepo.addElement(course);
            teacherCourse(teacher, course);
            System.out.println("Course - " + course.getName() + " has been added.");
        }
    }
}
