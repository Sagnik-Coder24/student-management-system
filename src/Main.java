import entities.*;
import repositories.CourseRepo;
import repositories.TeacherRepo;
import utility.IDgenerator;
import utility.Relationships;

import java.util.Scanner;

public class Main {
    private static void student_called() {
    }

    private static void teacher_called() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nHey, welcome to our teacher's section. Please enter your ID to proceed.");
        long id = scanner.nextLong();
        scanner.nextLine();
        if (TeacherRepo.containsID(id)) {
            Teacher teacher = TeacherRepo.getTeacher(id);
            System.out.println("\n\nWelcome, " + teacher.getName() + ".");

            if (teacher.getCourses() == null || teacher.getCourses().isEmpty()) {
                System.out.println("Your specialized courses are not yet set. Please add them.");
                int user_ip = 1;
                do {
                    if (user_ip == 1) {
                        Relationships.teacherAddingCourse(teacher);
                    } else {
                        System.out.println("Invalid input.. Please select a valid option.");
                    }
                    System.out.println("\nSelect one option.\n1 > Add another specialized course\n2 > Exit");
                    user_ip = scanner.nextInt();
                    scanner.nextLine();
                    if (user_ip == 2) System.out.println("Thank you for adding...");
                } while (user_ip != 2);
            }

            int user_ip;
            do {

                System.out.println("\n\nSelect one of the below operations to perform:");
                System.out.println("1 > Add course");
                System.out.println("2 > Remove course");
                System.out.println("3 > Assign course to a student");
                System.out.println("4 > Remove course for a student");
                System.out.println("5 > Assign grades to a student.");
                System.out.println("6 > See your details");
                System.out.println("7 > Update your details");
                System.out.println("8 > List of all students");
                System.out.println("9 > List of all teachers");
                System.out.println("10 > List of all courses");
                System.out.println("0 > Go Back");
                user_ip = scanner.nextInt();
                scanner.nextLine();

                switch (user_ip) {
                    case 1:
                        System.out.println("\nEnter course code:");
                        long code = scanner.nextLong();
                        scanner.nextLine();
                        if (CourseRepo.containsCode(code)) {
                            Course course = CourseRepo.getCourse(code);
                            System.out.println("Course - " + course.getName() + " already present in our system.");
                        } else {
                            System.out.println("Enter course name:");
                            String name = scanner.nextLine();
                            Course course = new Course(code, name);
                            CourseRepo.addElement(course);
                            System.out.println("Course - " + course.getName() + " has been added in our system.");
                        }
                        break;
                    case 2:
                        System.out.println("\nEnter course code:");
                        long code2 = scanner.nextLong();
                        scanner.nextLine();
                        if (CourseRepo.containsCode(code2)) {
                            String name = CourseRepo.getCourse(code2).getName();
                            CourseRepo.removeElement(code2);
                            System.out.println("Course - " + name + " has been removed from our system.");
                        } else {
                            System.out.println("Course with CODE: " + code2 + " is not present in our system.");
                        }
                        break;
                    case 3:
                        teacher.assignCourse();
                        break;
                    case 4:
                        teacher.deleteStudentCourse();
                        break;
                    case 5:
                        teacher.assignGrade();
                        break;
                    case 6:
                        teacher.printDetails();
                        break;
                    case 7:
                        teacher.updateDetails();
                        break;
                    case 8:
                        teacher.listStudents();
                        break;
                    case 9:
                        teacher.listTeachers();
                        break;
                    case 10:
                        teacher.listCourses();
                        break;
                    case 0:
                        System.out.println("Going back to the previous menu...");
                        return;
                    default:
                        System.out.println("Invalid input.. Please select a valid option.");
                        break;
                }


            } while (true);
        } else {
            System.out.println("There are no teachers with ID: " + id + " present in our system.");
        }
    }

    private static void admin_called() {
        Scanner scanner = new Scanner(System.in);

        if (Admin.checkPassSet()) {
            Admin admin = Admin.getInstance();
            System.out.println("\n\nWelcome, " + admin.getName() + ".");
            System.out.println("Enter your password:");
            String pass;
            int counter = 6;

            do {
                if (counter == 0) return;

                pass = scanner.nextLine();
                if (admin.checkPassMatch(pass)) {
                    System.out.println("Correct password.");
                } else {
                    System.out.println("Wrong password." +
                            (counter > 1 ? " Try again!" : "") +
                            " ( " + (counter - 1) + " attempts remaining. )");
                    counter--;
                }

            } while (!admin.checkPassMatch(pass));

            int user_ip;

            do {

                System.out.println("\n\nSelect one of the below operations to perform:");
                System.out.println("1 > Add Student");
                System.out.println("2 > Remove Student");
                System.out.println("3 > Add Teacher");
                System.out.println("4 > Remove Teacher");
                System.out.println("5 > Update your password");
                System.out.println("6 > See your details");
                System.out.println("7 > List of all students");
                System.out.println("8 > List of all teachers");
                System.out.println("9 > List of all courses");
                System.out.println("0 > Go Back");
                user_ip = scanner.nextInt();
                scanner.nextLine();

                switch (user_ip) {
                    case 1:
                        admin.addStudent();
                        break;
                    case 2:
                        admin.removeStudent();
                        break;
                    case 3:
                        admin.addTeacher();
                        break;
                    case 4:
                        admin.removeTeacher();
                        break;
                    case 5:
                        admin.updatePassword();
                        break;
                    case 6:
                        System.out.println();
                        admin.printDetails();
                        break;
                    case 7:
                        admin.listStudents();
                        break;
                    case 8:
                        admin.listTeachers();
                        break;
                    case 9:
                        admin.listCourses();
                        break;
                    case 0:
                        System.out.println("Going back to the previous menu...");
                        return;
                    default:
                        System.out.println("Invalid input.. Please select a valid option.");
                        break;
                }


            } while (true);

        } else {
            System.out.println("\n( ADMIN ) It seems you are new here. Please create your profile.");
            System.out.println("Enter your Name:");
            String name = scanner.nextLine();
            System.out.println("Enter your Age:");
            int age = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Create your Password:");
            String pass = scanner.nextLine();

            Admin admin = Admin.getInstance(IDgenerator.getNewId(), name, age, pass);

            System.out.println("\nYour Profile Details:");
            System.out.println("ID: " + admin.getId());
            System.out.println("Name: " + admin.getName());
            System.out.println("Age: " + admin.getAge());
            System.out.println("Password: " + pass);

            admin_called();
        }
    }

    private static void create_cli_interface() {
        Scanner scanner = new Scanner(System.in);
        int userType;

        System.out.println("\nWelcome to our Student Management Application 🙏");

        do {
            System.out.println("\nYou are a ? ( Please select one of the following ) - \n1 > Student\n2 > Teacher\n3 > Admin\n4 > Exit");
            userType = scanner.nextInt();
            scanner.nextLine();

            switch (userType) {
                case 1:
                    student_called();
                    break;
                case 2:
                    teacher_called();
                    break;
                case 3:
                    admin_called();
                    break;
                case 4:
                    System.out.println("\nExiting application. Goodbye! 🙏");
                    break;
                default:
                    System.out.println("Invalid input..");
            }
        } while (userType != 4);


        scanner.close();
    }


    public static void main(String[] args) {
        Main.create_cli_interface();
    }
}
