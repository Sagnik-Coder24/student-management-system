package input_output;

import entities.Admin;
import entities.Course;
import entities.Student;
import entities.Teacher;
import repositories.StudentRepo;
import repositories.TeacherRepo;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class IpOp {
    private IpOp() {
    }

    private static void creatingFiles() {
        try {
            File f1 = new File("admin.csv");
            File f2 = new File("students.csv");
            File f3 = new File("teachers.txt");
            File f4 = new File("courses.txt");

            f1.createNewFile();
            f2.createNewFile();
            f3.createNewFile();
            f4.createNewFile();
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    public static void allReadIns() {
        creatingFiles();

        readAdminFromFile();
        readStudentsFromFile();
        readTeachersFromFile();
        readCoursesFromFile();
    }

    public static void saveAdminToFile(Admin admin, String pass) {
        String filePath = "src/input_output/files/admin.csv";
        String header = "ID,NAME,AGE,PASSWORD\n";
        String text = admin.getId() + "," + admin.getName() + "," + admin.getAge() + "," + pass;

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            bw.write(header);
            bw.write(text);
            bw.newLine();
            bw.flush();
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    public static void saveStudentsToFile(Map<Long, Student> allStudents) {
        String filePath = "src/input_output/files/students.csv";
        String header = "ID,NAME,AGE,GRADE,COURSES\n";

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            bw.write(header);

            String text;
            for (Student s : allStudents.values()) {
                String course = s.getCourses().isEmpty() ? "null" : "|";
                for (Course c : s.getCourses()) {
                    course = course + c.getCode() + "|";
                }
                text = s.getId() + "," + s.getName() + "," + s.getAge() + "," + s.getGrade() + "," + course;
                bw.write(text);
                bw.newLine();
            }
            bw.flush();
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    public static void saveTeachersToFile(Map<Long, Teacher> allTeachers) {
        String filePath = "src/input_output/files/teachers.csv";
        String header = "ID,NAME,AGE,COURSES\n";

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            bw.write(header);

            String text;
            for (Teacher s : allTeachers.values()) {
                String course = s.getCourses().isEmpty() ? "null" : "|";
                for (Course c : s.getCourses()) {
                    course = course + c.getCode() + "|";
                }
                text = s.getId() + "," + s.getName() + "," + s.getAge() + "," + course;
                bw.write(text);
                bw.newLine();
            }
            bw.flush();
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    public static void saveCoursesToFile(Map<Long, Course> allCourses) {
    }

    private static void readAdminFromFile() {
        String filePath = "src/input_output/files/admin.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] adminData = line.split(",");
                long id = Long.parseLong(adminData[0]);
                String name = adminData[1];
                int age = Integer.parseInt(adminData[2]);
                String password = adminData[3];

                Admin.getInstance(id, name, age, password);
            }

        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private static void readStudentsFromFile() {
        String filePath = "src/input_output/files/students.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] studentData = line.split(",");
                long id = Long.parseLong(studentData[0]);
                String name = studentData[1];
                int age = Integer.parseInt(studentData[2]);
                double grade = Double.parseDouble(studentData[3]);
                List<String> courses = Arrays.stream(studentData[4]
                                .split("\\|"))
                        .filter(s -> !s.isEmpty()).toList();    // TODO: Link this

                Student temp = new Student(id, name, age, grade);
                StudentRepo.addElement(temp);
            }

        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private static void readTeachersFromFile() {
        String filePath = "src/input_output/files/teachers.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] teacherData = line.split(",");
                long id = Long.parseLong(teacherData[0]);
                String name = teacherData[1];
                int age = Integer.parseInt(teacherData[2]);
                List<String> courses = Arrays.stream(teacherData[3]
                                .split("\\|"))
                        .filter(s -> !s.isEmpty()).toList();

                Teacher temp = new Teacher(id, name, age);
                TeacherRepo.addElement(temp);
            }

        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private static void readCoursesFromFile() {
    }
}
