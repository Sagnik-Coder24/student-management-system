package input_output;

import entities.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import repositories.CourseRepo;
import repositories.StudentRepo;
import repositories.TeacherRepo;
import utility.IDgenerator;
import utility.Relationships;

import java.io.*;
import java.util.*;

public class IpOp {
    private static final String admin_file_path = "src/input_output/files/admin.csv";
    private static final String students_file_path = "src/input_output/files/students.json";
    private static final String teachers_file_path = "src/input_output/files/teachers.csv";
    private static final String courses_file_path = "src/input_output/files/courses.csv";

    private static long maxId = 0;

    private IpOp() {
    }

    private static void creatingFiles() {
        try {
            File f1 = new File(admin_file_path);
            File f2 = new File(students_file_path);
            File f3 = new File(teachers_file_path);
            File f4 = new File(courses_file_path);

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
        readCoursesFromFile();
        readStudentsFromFile();
        readTeachersFromFile();
        calcAndSetMaxId();
    }

    public static void allWrites() {
        saveCoursesToFile(CourseRepo.getAllCourses());
        saveStudentsToFile(StudentRepo.getAllStudents());
        saveTeachersToFile(TeacherRepo.getAllTeachers());
    }

    public static void saveAdminToFile(Admin admin, String pass) {
        String header = "ID,NAME,AGE,PASSWORD\n";
        String text = admin.getId() + "," + admin.getName() + "," + admin.getAge() + "," + pass;

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(admin_file_path))) {
            bw.write(header);
            bw.write(text);
            bw.newLine();
            bw.flush();
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private static void saveStudentsToFile(Map<Long, Student> allStudents) {
//        String header = "ID,NAME,AGE,GRADE,COURSES\n";
//
//        try (BufferedWriter bw = new BufferedWriter(new FileWriter(students_file_path))) {
//            bw.write(header);
//
//            String text;
//            for (Student s : allStudents.values()) {
//                String course = s.getCourses().isEmpty() ? "null" : "|";
//                for (Course c : s.getCourses()) {
//                    course = course + c.getCode() + "|";
//                }
//                text = s.getId() + "," + s.getName() + "," + s.getAge() + "," + course;
//                bw.write(text);
//                bw.newLine();
//            }
//            bw.flush();
//        } catch (IOException e) {
//            System.out.println("An error occurred: " + e.getMessage());
//        }

        try {
            JSONArray jsonArray = new JSONArray();
            for (Student s : allStudents.values()) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", s.getId());
                jsonObject.put("name", s.getName());
                jsonObject.put("age", s.getAge());
                JSONObject grades = new JSONObject();
                s.getGrades().forEach((code, grade) -> grades.put(String.valueOf(code), grade));
                jsonObject.put("grades", grades);
                jsonArray.put(jsonObject);
            }

            try (FileWriter fileWriter = new FileWriter(students_file_path, false)) {
                fileWriter.write(jsonArray.toString(4));
            }
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        } catch (org.json.JSONException e) {
            System.out.println("Invalid JSON format: " + e.getMessage());
        }
    }

    private static void saveTeachersToFile(Map<Long, Teacher> allTeachers) {
        String header = "ID,NAME,AGE,COURSES\n";

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(teachers_file_path))) {
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

    private static void saveCoursesToFile(Map<Long, Course> allCourses) {
        String header = "CODE,NAME\n";

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(courses_file_path))) {
            bw.write(header);

            String text;
            for (Course s : allCourses.values()) {
                text = s.getCode() + "," + s.getName();
                bw.write(text);
                bw.newLine();
            }
            bw.flush();
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

//    private static void saveMaxIdToFile() {
//        try (BufferedReader br = new BufferedReader(new FileReader(utils_file_path))) {
//            StringBuilder content = new StringBuilder();
//            String line;
//            while ((line = br.readLine()) != null) {
//                content.append(line);
//            }
//
//            JSONObject jsonObject = content.isEmpty() ? new JSONObject() : new JSONObject(new JSONTokener(content.toString()));
//            jsonObject.put("maxId", IDgenerator.getId());
//
//            try (FileWriter fileWriter = new FileWriter(utils_file_path)) {
//                fileWriter.write(jsonObject.toString(4));
//            }
//        } catch (IOException e) {
//            System.out.println("An error occurred: " + e.getMessage());
//        } catch (org.json.JSONException e) {
//            System.out.println("Invalid JSON format: " + e.getMessage());
//        }
//    }
//
//    private static void readMaxIdFromFile() {
//        try (BufferedReader br = new BufferedReader(new FileReader(utils_file_path))) {
//            StringBuilder content = new StringBuilder();
//            String line;
//            while ((line = br.readLine()) != null) {
//                content.append(line);
//            }
//
//            if (!content.isEmpty()) {
//                JSONObject jsonObject = new JSONObject(new JSONTokener(content.toString()));
//                Number maxIdValue = (Number) jsonObject.get("maxId");
//                if (maxIdValue != null) {
//                    IDgenerator.setId(maxIdValue.longValue());
//                }
//            }
//        } catch (IOException e) {
//            System.out.println("An error occurred: " + e.getMessage());
//        } catch (org.json.JSONException e) {
//            System.out.println("Invalid JSON format: " + e.getMessage());
//        }
//    }

    private static void readAdminFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(admin_file_path))) {
            String line;
            br.readLine();

            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] adminData = line.split(",");
                long id = Long.parseLong(adminData[0]);
                String name = adminData[1];
                int age = Integer.parseInt(adminData[2]);
                String password = adminData[3];

                Admin.getInstance(id, name, age, password);
                maxId = Math.max(maxId, id);
            }

        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private static void readStudentsFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(students_file_path))) {
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line);
            }

            if (!content.isEmpty()) {
                JSONArray students = new JSONArray(new JSONTokener(content.toString()));
                for (int i = 0; i < students.length(); i++) {
                    JSONObject student = students.getJSONObject(i);
                    long id = student.getLong("id");
                    String name = student.getString("name");
                    int age = student.getInt("age");
                    JSONObject grades = student.getJSONObject("grades");

                    Student temp = new Student(id, name, age);
                    Set<String> gradeKeys = grades.keySet();
                    List<Long> gradeKeyList = new ArrayList<>();
                    for (String key : gradeKeys) {
                        gradeKeyList.add(Long.parseLong(key));
                    }
                    courseMap(temp, gradeKeyList);
                    StudentRepo.addElement(temp);

                    Iterator<String> keys = grades.keys();
                    while (keys.hasNext()) {
                        Long courseCode = Long.valueOf(keys.next());
                        double grade = grades.getDouble(String.valueOf(courseCode));
                        temp.updateGrades(courseCode, grade);
                    }

                    maxId = Math.max(maxId, id);
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        } catch (org.json.JSONException e) {
            System.out.println("Invalid JSON format: " + e.getMessage());
        }
    }

    private static void readTeachersFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(teachers_file_path))) {
            String line;
            br.readLine();

            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] teacherData = line.split(",");
                long id = Long.parseLong(teacherData[0]);
                String name = teacherData[1];
                int age = Integer.parseInt(teacherData[2]);
                List<Long> courses = teacherData[3].equals("null") ? null : Arrays.stream(teacherData[3]
                                .split("\\|"))
                        .filter(s -> !s.isEmpty())
                        .map(Long::parseLong)
                        .toList();

                Teacher temp = new Teacher(id, name, age);
                courseMap(temp, courses);
                TeacherRepo.addElement(temp);
                maxId = Math.max(maxId, id);
            }

        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private static void readCoursesFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(courses_file_path))) {
            String line;
            br.readLine();

            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] courseData = line.split(",");
                long code = Long.parseLong(courseData[0]);
                String name = courseData[1];

                Course temp = new Course(code, name);
                CourseRepo.addElement(temp);
            }

        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private static void courseMap(User user, List<Long> courseCodes) {
        if (courseCodes == null) return;
        for (Long code : courseCodes) {
            Course course = CourseRepo.getCourse(code);

            if (user instanceof Student) {
                Relationships.studentCourse((Student) user, course);
            } else if (user instanceof Teacher) {
                Relationships.teacherCourse((Teacher) user, course);
            }
        }
    }

    private static void calcAndSetMaxId() {
        IDgenerator.setId(maxId);
    }
}
