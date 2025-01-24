package entities;

import input_output.IpOp;
import repositories.CourseRepo;
import repositories.StudentRepo;
import repositories.TeacherRepo;
import utility.IDgenerator;
import utility.NameValidator;

import java.util.Scanner;

public class Admin extends User {

    private static Admin instance;
    private static String password;

    private Admin(long id, String name, int age, String password) {
        super(id, name, age);
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty.");
        }
        Admin.password = password;
        IpOp.saveAdminToFile(this, password);
    }

    public static Admin getInstance(long id, String name, int age, String pass) {
        if (instance == null) {
            instance = new Admin(id, name, age, pass);
        } else {
            System.out.println("There is already one admin.");
        }
        return instance;
    }

    public static Admin getInstance() {
        if (instance == null) {
            System.out.println("Admin details are not set.");
        }
        return instance;
    }

    public static boolean checkPassSet() {
        if (password == null) return false;
        else return true;
    }

    public boolean checkPassMatch(String pass) {
        if (password.equals(pass)) return true;
        else return false;
    }

    public void updatePassword() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nEnter new password.");
        String pass;

        do {
            pass = sc.nextLine();

            if (!NameValidator.validatePassword(pass)) {
                System.out.println("Password is invalid. It must contain:");
                System.out.println("- At least 8 characters");
                System.out.println("- At least one uppercase letter");
                System.out.println("- At least one lowercase letter");
                System.out.println("- At least one digit");
                System.out.println("- At least one special character");
                System.out.print("Try again: ");
            }
        } while (!NameValidator.validatePassword(pass));

        System.out.println("Password is valid.");
        Admin.password = pass;
        IpOp.saveAdminToFile(this, password);
        System.out.println("Successfully updated password.");

    }

    public void addStudent() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nEnter the details of the new student.");
        System.out.println("Enter name:");
        String name = sc.nextLine();
        if (NameValidator.isValidName(name)) {
            System.out.println("enter age:");
            int age = sc.nextInt();
            sc.nextLine();
            Student newSt = new Student(IDgenerator.getNewId(), NameValidator.formatName(name), age);
            StudentRepo.addElement(newSt);
            System.out.println("\nThe provided student is added to our system.");
        } else {
            System.out.println("Name: " + name + " is not valid. Please try again.\n");
            addStudent();
        }

    }

    public void removeStudent() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nEnter the id of the student to remove.");
        long id = sc.nextLong();
        sc.nextLine();
        StudentRepo.removeElement(id);
    }

    public void addTeacher() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nEnter the details of the new teacher.");
        System.out.println("Enter name:");
        String name = sc.nextLine();
        if (NameValidator.isValidName(name)) {
            System.out.println("enter age:");
            int age = sc.nextInt();
            sc.nextLine();
            Teacher newT = new Teacher(IDgenerator.getNewId(), NameValidator.formatName(name), age);
            TeacherRepo.addElement(newT);
            System.out.println("\nThe provided teacher is added to our system.");
        } else {
            System.out.println("Name: " + name + " is not valid. Please try again.\n");
            addTeacher();
        }

    }

    public void removeTeacher() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nEnter the id of the teacher to remove.");
        long id = sc.nextLong();
        sc.nextLine();
        TeacherRepo.removeElement(id);
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

    public void getStudentsGreaterThanGrade(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nEnter the number N:");
        double n = scanner.nextDouble();
        scanner.nextLine();
        StudentRepo.getStudentsGreaterThanGrade(n);
    }

    public void callAdditionalOptions() {
        int user_ip;
        do {
            Scanner scanner = new Scanner(System.in);
            System.out.println("\nSelect one of the below operations:");
            System.out.println("1 > Display students sorted by age");
            System.out.println("2 > Update your name");
            System.out.println("3 > Update your age");
            System.out.println("4 > Update your password");
            System.out.println("5 > Students with grades > N");
            System.out.println("0 > Go back");
            user_ip = scanner.nextInt();
            scanner.nextLine();
            switch (user_ip) {
                case 1:
                    System.out.println("Ascending or Descending? ( A / D )");
                    String c = scanner.nextLine();
                    StudentRepo.displayStudentsSortedByAge(!c.equalsIgnoreCase("D"));
                    break;
                case 2:
                    this.updateName();
                    break;
                case 3:
                    System.out.println("Enter new age:");
                    int age = scanner.nextInt();
                    scanner.nextLine();
                    this.setAge(age);
                    break;
                case 4:
                    this.updatePassword();
                    break;
                case 5:
                    this.getStudentsGreaterThanGrade();
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

    public String toString() {
        return "Admin { id = " + super.getId() +
                ", name = " + super.getName() +
                ", age = " + super.getAge() + " }";
    }
}
