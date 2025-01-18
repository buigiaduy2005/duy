import java.util.Scanner;
import java.time.LocalDate;
import java.util.List;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        IStudentDAO studentDAO = new StudentDAOImpl();
        Scanner scanner = new Scanner(System.in);

        int choice;
        do {
            System.out.println("\nStudent Management:");
            System.out.println("1. Add student");
            System.out.println("2. Update student");
            System.out.println("3. Delete student");
            System.out.println("4. Display all students");
            System.out.println("5. Display students with total credits");
            System.out.println("6. Display student grades");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addStudent(studentDAO, scanner);
                case 2 -> updateStudent(studentDAO, scanner);
                case 3 -> deleteStudent(studentDAO, scanner);
                case 4 -> {
                    List<Student> students = studentDAO.getAllStudents();
                    students.forEach(System.out::println);
                }
                case 5 -> {
                    List<StudentWithTotalCredits> students = studentDAO.getStudentsWithTotalCredits();
                    students.forEach(System.out::println);
                }
                case 6 -> studentDAO.displayStudentGrades();
                case 7 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice! Try again.");
            }
        } while (choice != 7);
    }

    private static void addStudent(IStudentDAO studentDAO, Scanner scanner) {
        System.out.print("Enter ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter date of birth (yyyy-MM-dd): ");
        String dob = scanner.nextLine();
        System.out.print("Enter gender: ");
        String gender = scanner.nextLine();
        System.out.print("Enter class name: ");
        String className = scanner.nextLine();

        Student student = new Student(id, name, LocalDate.parse(dob), gender, className);
        studentDAO.addStudent(student);
    }

    private static void updateStudent(IStudentDAO studentDAO, Scanner scanner) {
        System.out.print("Enter ID of student to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter new name: ");
        String name = scanner.nextLine();
        System.out.print("Enter new date of birth (yyyy-MM-dd): ");
        String dob = scanner.nextLine();
        System.out.print("Enter new gender: ");
        String gender = scanner.nextLine();
        System.out.print("Enter new class name: ");
        String className = scanner.nextLine();

        Student student = new Student(id, name, LocalDate.parse(dob), gender, className);
        studentDAO.updateStudent(student);
    }

    private static void deleteStudent(IStudentDAO studentDAO, Scanner scanner) {
        System.out.print("Enter ID of student to delete: ");
        int id = scanner.nextInt();
        studentDAO.deleteStudent(id);
    }
    }



