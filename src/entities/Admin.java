package entities;

import repositories.CourseRepo;
import repositories.StudentRepo;
import utility.IDgenerator;

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

    public void updatePassword(String password) {
        if (password == null) {
            System.out.println("Password cannot be null.");
        } else if (password.isEmpty()) {
            System.out.println("Password cannot be empty.");
        } else if (Admin.password.equals(password)) {
            System.out.println("You have entered the same password.");
        } else {
            Admin.password = password;
            System.out.println("\nSuccessfully updated password.");
        }
    }

    public void addStudent() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nEnter the details of the new student.");
        System.out.println("Enter name:");
        String name = sc.nextLine();
        System.out.println("enter age:");
        int age = sc.nextInt();
        sc.nextLine();
        Student newSt = new Student(IDgenerator.getNewId(), name, age);
        StudentRepo.addElement(newSt);
    }

    public void removeStudent() {
    }

    public void addTeacher() {
    }

    public void removeTeacher() {
    }

    public String toString() {
        return "Admin { id = " + super.getId() +
                ", name = " + super.getName() +
                ", age = " + super.getAge() + " }";
    }
}
