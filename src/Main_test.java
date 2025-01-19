import entities.Course;
import entities.Student;
import entities.Teacher;

public class Main_test {
    public static void main(String[] args) {
        Course c1 = new Course(12,"12");
        Course c2 = new Course(13,"13");
        Teacher t1 = new Teacher(2,"t",23);
        Student s1 = new Student(3,"s",12);
        System.out.println(c1);
        System.out.println(t1);
        System.out.println(s1);
        s1.addCourse(c1);
        s1.addCourse(c2);
        s1.removeCourse(c1.getCode());
        System.out.println(s1.getCourses());
        s1.addCourse(c1);
        System.out.println(s1.getCourses());
    }
}
