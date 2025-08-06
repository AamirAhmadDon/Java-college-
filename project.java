import java.io.*;
import java.util.Scanner;

abstract class User {
    protected String id;
    protected String name;
    protected String email;

    public User(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public abstract void showMenu();
}

class Student extends User {
    public Student(String id, String name, String email) {
        super(id, name, email);
    }

    public void enrollInCourse(String courseName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("enrollments.txt", true))) {
            writer.write(id + "," + courseName);
            writer.newLine();
            System.out.println("Enrolled in course: " + courseName);
        } catch (IOException e) {
            System.out.println("Error enrolling in course: " + e.getMessage());
        }
    }

    public void viewEnrolledCourses() {
        try (BufferedReader reader = new BufferedReader(new FileReader("enrollments.txt"))) {
            String line;
            System.out.println("Enrolled Courses:");
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(id)) {
                    System.out.println(" - " + parts[1]);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading enrollments: " + e.getMessage());
        }
    }

    public void showMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Student Menu ---");
            System.out.println("1. Enroll in Course");
            System.out.println("2. View Enrolled Courses");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // clear
            switch (choice) {
                case 1:
                    System.out.print("Enter course code: ");
                    String courseCode = scanner.nextLine();
                    enrollInCourse(courseCode);
                    break;
                case 2:
                    viewEnrolledCourses();
                    break;
                case 3:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}

class Instructor extends User {
    public Instructor(String id, String name, String email) {
        super(id, name, email);
    }

    public void createCourse(String courseCode, String title) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("courses.txt", true))) {
            writer.write(courseCode + "," + title + "," + id);
            writer.newLine();
            System.out.println("Course created: " + courseCode);
        } catch (IOException e) {
            System.out.println("Error writing course file.");
        }
    }

    public void showMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Instructor Menu ---");
            System.out.println("1. Create Course");
            System.out.println("2. Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // flush

            if (choice == 1) {
                System.out.print("Enter Course Code: ");
                String code = scanner.nextLine();
                System.out.print("Enter Course Title: ");
                String title = scanner.nextLine();
                createCourse(code, title);
            } else if (choice == 2) {
                return;
            } else {
                System.out.println("Invalid option.");
            }
        }
    }
}

public class project {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Course Management System");
        while (true) {
            System.out.println("\nLogin as:");
            System.out.println("1. Student");
            System.out.println("2. Instructor");
            System.out.println("3. Exit");
            System.out.print("Choice: ");
            int userType = scanner.nextInt();
            scanner.nextLine(); // flush
            String id = "", name = "", email = "";
            if (userType == 1 || userType == 2) {
                System.out.print("Enter ID: ");
                id = scanner.nextLine();
                System.out.print("Enter Name: ");
                name = scanner.nextLine();
                System.out.print("Enter Email: ");
                email = scanner.nextLine();
            }
            if (userType == 1) {
                Student student = new Student(id, name, email);
                saveUser("students.txt", student);
                student.showMenu();
            } else if (userType == 2) {
                Instructor instructor = new Instructor(id, name, email);
                saveUser("instructors.txt", instructor);
                instructor.showMenu();
            } else if (userType == 3) {
                break;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
        System.out.println("Bye!!!");
    }

    public static void saveUser(String filename, User user) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            writer.write(user.id + "," + user.name + "," + user.email);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error saving user.");
        }
    }
}
