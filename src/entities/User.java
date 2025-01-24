package entities;

import utility.NameValidator;

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

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age > 0 && age <= 100) {
            this.age = age;
            System.out.println("Age updated, " + this.getAge());
        } else {
            System.out.println("Entered age is not valid.");
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
