package entities;

import utility.NameValidator;

import java.util.InputMismatchException;
import java.util.Scanner;

public class User {
    private long id;
    private String name;
    private int age;

    protected User(long id, String name, int age) {
        setId(id);
        setName(name);
        this.age = age;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    private void setAge(int age) {
        this.age = age;
    }

    public void updateAge() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter new age:");
        while (true) {
            try {
                int age = scanner.nextInt();
                scanner.nextLine();
                if (age > 0 && age <= 110) {
                    this.age = age;
                    this.setAge(age);
                    System.out.println("Age updated, " + this.getAge());
                    return;
                } else {
                    System.out.println("Entered age is out of range. Try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. You have to enter a valid number. Try again.");
                scanner.nextLine();
            }
        }
    }

    public void updateName() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter new name:");
        String name = scanner.nextLine();
        if (NameValidator.isValidName(name)) {
            this.setName(NameValidator.formatName(name));
            System.out.println("Name updated, " + this.getName());
        } else {
            System.out.println("Name: " + name + " is not valid. Please try again.\n");
            updateName();
        }
    }

    public void printDetails() {
        String className = this.getClass().getSimpleName();
        System.out.println(className + " details: ( id = " + id + ", name = '" + name + "', age = " + age + " )");
    }

}
