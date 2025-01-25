package entities;

import repositories.CourseRepo;
import repositories.StudentRepo;
import repositories.TeacherRepo;
import utility.Relationships;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

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
        if (this.isCourseCodePresent(code)) {
            courses = courses
                    .stream()
                    .filter(course -> course.getCode() != code)
                    .collect(Collectors.toCollection(ArrayList::new));
        }
    }

    public boolean isCourseCodePresent(long code) {
        return this.getCourses().stream().anyMatch(course -> course.getCode() == code);
    }

    public void listStudents() {
        StudentRepo.displayElements();
    }

    public void listTeachers() {
        TeacherRepo.displayElements();
    }

    public void listCourses() {
        CourseRepo.displayElements();
    }

    public void deleteCourseFromSystem() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nEnter course code:");
        long code = scanner.nextLong();
        scanner.nextLine();
        if (CourseRepo.containsCode(code)) {
            Course course = CourseRepo.getCourse(code);
            System.out.println("---------------------------------------------------");
            System.out.println("Please review the below details:");
            System.out.println("Teachers associates with this course: " + course.getTeachers());
            System.out.println("Students associates with this course: " + course.getStudents());
            System.out.println("\nDo you wish to continue? ( Y / N )");
            System.out.println("---------------------------------------------------");
            String input = scanner.nextLine();
            char ch = input.toLowerCase().charAt(0);
            if (ch == 'y') {
                for (Teacher teacher : course.getTeachers()) {
                    teacher.removeCourse(code);
                    System.out.println("Course - " + course.getName() + " removed from teacher - " + teacher.getName() + ".");
                }
                for (Student student : course.getStudents()) {
                    student.removeCourse(code);
                    System.out.println("Course - " + course.getName() + " removed from student - " + student.getName() + ".");

                }
                CourseRepo.removeElement(code);
                System.out.println("\nCourse - " + course.getName() + " has been removed from our system.");
            } else {
                System.out.println("Going back...");
            }
        } else {
            System.out.println("Course with CODE: " + code + " is not present in our system.");
        }
    }

    public void assignGrade() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nEnter code of one of the below specialized course to grade:");
        for (Course course : this.getCourses()) {
            System.out.println("> " + course.getCode() + " : " + course.getName());
        }
        long code = scanner.nextLong();
        scanner.nextLine();
        if (this.isCourseCodePresent(code)) {
            Course course = CourseRepo.getCourse(code);
            System.out.println(course.getName() + " is assigned to the below students:");
            for (Student student : course.getStudents()) {
                System.out.println("> " + student.getId() + " : " + student.getName()
                        + (student.getGrades().get(code) == -1 ? "" : " : Grade - " + student.getGrades().get(code)));
            }
            System.out.println("Enter students ID to grade:");
            long id = scanner.nextLong();
            scanner.nextLine();
            if (course.isStudentIdPresent(id)) {
                Student student = StudentRepo.getStudent(id);
                System.out.println("Grade must be between 0 to 10.");
                System.out.println("Enter " + student.getName() + "'s grade for " + course.getName() + ":");
                double grade = scanner.nextDouble();
                scanner.nextLine();
                if (grade < 0 || grade > 10) {
                    System.out.println("Invalid grade. Try again.");
                    assignGrade();
                } else {
                    student.updateGrades(code, grade);
                }
            } else {
                System.out.println("Invalid student ID. Try again.");
                assignGrade();
            }
        } else {
            System.out.println("Invalid course CODE. Try again.");
            assignGrade();
        }

//        System.out.println("Enter student ID:");
//        long id = scanner.nextLong();
//        scanner.nextLine();
//        if (StudentRepo.containsID(id)) {
//            Student student = StudentRepo.getStudent(id);
//            System.out.println("Student name - " + student.getName());
//            if (student.getFinalGrade() == -1) {
//                System.out.println("Enter student's grade:");
//                double grade = scanner.nextDouble();
//                scanner.nextLine();
//                student.calcFinalGrade(grade);
//            } else {
//                System.out.println("The student's current grade is " + student.getFinalGrade() + ".");
//                System.out.println("Enter new grade: ( Enter -1 to exit )");
//                double grade = scanner.nextDouble();
//                scanner.nextLine();
//                if (grade == -1) return;
//                student.calcFinalGrade(grade);
//            }
//            System.out.println("The grade has been updated for " + student.getName() + ".");
//        } else {
//            System.out.println("Student with ID: " + id + " is not present in our system.");
//        }
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
            System.out.println("Student name - " + student.getName());
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
            System.out.println("Student name - " + student.getName());
            System.out.println("Enter course CODE:");
            long code = scanner.nextLong();
            scanner.nextLine();
            if (student.getCourses().stream().anyMatch(course -> course.getCode() == code)) {
                Course course = CourseRepo.getCourse(code);
                Relationships.removeStudentCourse(student, course);
                System.out.println("Course - " + course.getName() + " has been deleted for student - " + student.getName() + ".");
            } else {
                System.out.println("Course with CODE: " + code + " is not assigned to student: " + student.getName() + ".");
            }
        } else {
            System.out.println("Student with ID: " + id + " is not present in our system.");
        }
    }

    public void updateDetails() {
        printDetails();
        int user_ip;
        do {
            Scanner scanner = new Scanner(System.in);
            System.out.println("\nSelect one of the below options to perform:");
            System.out.println("1 > Update name");
            System.out.println("2 > Update age");
            System.out.println("3 > Add specialized courses");
            System.out.println("4 > Remove a specialized courses");
            System.out.println("0 > Go back");
            user_ip = scanner.nextInt();
            scanner.nextLine();
            switch (user_ip) {
                case 1:
                    this.updateName();
                    break;
                case 2:
                    System.out.println("Enter new age:");
                    int age = scanner.nextInt();
                    scanner.nextLine();
                    this.setAge(age);
                    break;
                case 3:
                    Relationships.teacherAddingCourse(this);
                    break;
                case 4:
                    System.out.println("Enter course code:");
                    long code = scanner.nextLong();
                    scanner.nextLine();
                    if (this.isCourseCodePresent(code)) {
                        Course course = CourseRepo.getCourse(code);
                        Relationships.removeTeacherCourse(this, course);
                        System.out.println("Course CODE: - " + code + " has been removed.");
                    } else {
                        System.out.println("Course CODE: - " + code + " is not present.");
                    }
                    break;
                case 0:
                    System.out.println("Going back to the previous menu...");
                    return;
                default:
                    System.out.println("Invalid input.. Please select a valid option.");
                    break;
            }
        } while (true);
    }

    @Override
    public void printDetails() {
        printDetails(0);
    }

    public void printDetails(int indent) {
        String indentation = " ".repeat(indent);
        System.out.println(indentation + "Teacher ID: " + getId());
        System.out.println(indentation + "Name: " + getName());
        System.out.println(indentation + "Age: " + getAge());
        System.out.println(indentation + "Specialized Courses:");
        if (this.getCourses() == null || this.getCourses().isEmpty()) {
            System.out.println(indentation + "No specialized courses assigned.");
        } else {
            for (Course course : this.getCourses()) {
                System.out.println(indentation + "---> " + course.getCode() + " : " + course.getName());
            }
        }
    }

    public String toString() {
        return "Teacher { id = " + super.getId() +
                ", name = " + super.getName() +
                ", age = " + super.getAge() + " }";
    }
}
