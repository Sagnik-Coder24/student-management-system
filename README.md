# Student Management System

This is a comprehensive **Student Management System** application built in Java. The system allows students, teachers, and administrators to manage student data, courses, grades, and user information. The application supports multiple user roles with specific functionalities for each.

## Features

### Student:
- View personal details (name, age, etc.)
- Update name and age
- Check final grades (CGPA)
- View grades for individual subjects

### Teacher:
- Manage courses (add/remove courses)
- Assign and remove courses for students
- Assign grades to students
- View and update personal details
- View a list of students, teachers, and courses

### Admin:
- Manage users (add/remove students and teachers)
- List students, teachers, and courses
- Update admin details (name, age, password)
- Perform additional administrative tasks, such as sorting students by age (ascending/descending) or filtering students with grades above a specified number, among others.

## Requirements
- Java 8 or higher

## Installation

1. **Clone the repository** or **download** the project files.
2. **Compile and run the Java program**:
    - To compile:
      ```bash
      javac Main.java
      ```
    - To run the application:
      ```bash
      java Main
      ```

## User Interface

The application works through a simple command-line interface (CLI) where users can choose their role (Student, Teacher, Admin) and perform actions according to their privileges.

### Menu Options for Users:
- **Students** can view and update their personal details, grades, and more.
- **Teachers** can manage courses and assign grades to students.
- **Admins** can manage users (add/remove students/teachers), courses, and perform administrative tasks.

## Example Usage

- When a **Student** logs in with their ID, they can view or update their personal details, check their grades, or modify their information.
- A **Teacher** can add or remove courses, assign grades to students, and manage their course assignments.
- An **Admin** can manage the entire system: adding/removing users, viewing all data, and performing administrative tasks like updating their own details or handling additional options.

