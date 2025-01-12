package entities;

public class User {
    private long id;
    private String name;
    private int age;

    protected User(long id, String name, int age) {
        setId(id);
        setName(name);
        setAge(age);
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
        } else {
            throw new IllegalArgumentException("Entered age is not valid.");
        }
    }

}
