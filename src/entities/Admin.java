package entities;

public class Admin extends User {

    private String password;

    public Admin(long id, String name, int age, String password) {
        super(id, name, age);
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty.");
        }
        this.password = password;
    }

    public void updatePassword(String password) {
        if (password == null) {
            System.out.println("Password cannot be null.");
        } else if (password.isEmpty()) {
            System.out.println("Password cannot be empty.");
        } else if (password == this.password) {
            System.out.println("You have entered the same password.");
        } else {
            this.password = password;
        }
    }

    public String toString() {
        return "Admin { id = " + super.getId() +
                ", name = " + super.getName() +
                ", age = " + super.getAge() + " }";
    }
}
