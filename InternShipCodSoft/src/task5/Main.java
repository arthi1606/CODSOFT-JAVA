package task5;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<Course> courses = new ArrayList<>();
    private static List<Student> students = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializeData();
        int choice;

        do {
            displayMenu();
            choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    listCourses();
                    break;
                case 2:
                    registerStudentForCourse();
                    break;
                case 3:
                    dropStudentFromCourse();
                    break;
                case 4:
                    listStudents();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
    }

    private static void displayMenu() {
        System.out.println("\nCourse Registration System");
        System.out.println("1. List available courses");
        System.out.println("2. Register student for a course");
        System.out.println("3. Drop student from a course");
        System.out.println("4. List students and their registered courses");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void initializeData() {
        courses.add(new Course("CS101", "Introduction to Computer Science", "Basic concepts of computer science", 30, "MWF 10-11am"));
        courses.add(new Course("MATH101", "Calculus I", "Introduction to calculus", 40, "TTh 9-10:30am"));
        courses.add(new Course("PHYS101", "Physics I", "Introduction to physics", 35, "MWF 1-2pm"));

        students.add(new Student("S001", "Alice"));
        students.add(new Student("S002", "Bob"));
    }

    private static void listCourses() {
        System.out.println("\nAvailable Courses:");
        for (Course course : courses) {
            System.out.printf("%s: %s (%d/%d)\n", course.getCourseCode(), course.getTitle(), course.getRegisteredStudents(), course.getCapacity());
            System.out.printf("Description: %s\nSchedule: %s\n\n", course.getDescription(), course.getSchedule());
        }
    }

    private static void registerStudentForCourse() {
        System.out.print("Enter student ID: ");
        String studentID = scanner.nextLine();
        Student student = findStudentById(studentID);

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.print("Enter course code: ");
        String courseCode = scanner.nextLine();
        Course course = findCourseByCode(courseCode);

        if (course == null) {
            System.out.println("Course not found.");
            return;
        }

        if (student.registerCourse(course)) {
            System.out.println("Registration successful.");
        } else {
            System.out.println("Failed to register. Course may be full or already registered.");
        }
    }

    private static void dropStudentFromCourse() {
        System.out.print("Enter student ID: ");
        String studentID = scanner.nextLine();
        Student student = findStudentById(studentID);

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.print("Enter course code: ");
        String courseCode = scanner.nextLine();
        Course course = findCourseByCode(courseCode);

        if (course == null) {
            System.out.println("Course not found.");
            return;
        }

        if (student.dropCourse(course)) {
            System.out.println("Course dropped successfully.");
        } else {
            System.out.println("Failed to drop course. Course may not be registered.");
        }
    }

    private static Student findStudentById(String studentID) {
        for (Student student : students) {
            if (student.getStudentID().equals(studentID)) {
                return student;
            }
        }
        return null;
    }

    private static Course findCourseByCode(String courseCode) {
        for (Course course : courses) {
            if (course.getCourseCode().equals(courseCode)) {
                return course;
            }
        }
        return null;
    }

    private static void listStudents() {
        System.out.println("\nStudents and their registered courses:");
        for (Student student : students) {
            System.out.printf("%s (%s)\n", student.getName(), student.getStudentID());
            for (Course course : student.getRegisteredCourses()) {
                System.out.printf("  - %s: %s\n", course.getCourseCode(), course.getTitle());
            }
        }
    }
}


