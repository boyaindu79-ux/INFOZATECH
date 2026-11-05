import java.util.*;
import java.io.*;
class Student {

    String name;
    int rollNumber;
    String grade;

    Student(String name, int rollNumber, String grade) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
    }

    void display() {

        System.out.println("Name: " + name);
        System.out.println("Roll Number: " + rollNumber);
        System.out.println("Grade: " + grade);
        System.out.println("----------------------");
    }
}
class StudentManager {

    ArrayList<Student> students = new ArrayList<>();

    String fileName = "students.txt";
    void addStudent(Student s) {

        if (searchStudent(s.rollNumber) != null) {
            System.out.println("Roll number already exists.");
            return;
        }

        students.add(s);
        saveData();

        System.out.println("Student added successfully.");
    }
    Student searchStudent(int rollNumber) {

        for (Student s : students) {

            if (s.rollNumber == rollNumber) {
                return s;
            }
        }

        return null;
    }
    void displayStudents() {

        if (students.isEmpty()) {
            System.out.println("No student records found.");
            return;
        }

        for (Student s : students) {
            s.display();
        }
    }
    void removeStudent(int rollNumber) {

        Student s = searchStudent(rollNumber);

        if (s != null) {
            students.remove(s);
            saveData();
            System.out.println("Student removed successfully.");
        }
        else {
            System.out.println("Student not found.");
        }
    }
    void updateStudent(int rollNumber,
                       String name, String grade) {

        Student s = searchStudent(rollNumber);

        if (s != null) {

            s.name = name;
            s.grade = grade;

            saveData();

            System.out.println("Student updated successfully.");
        }
        else {
            System.out.println("Student not found.");
        }
    }
    void saveData() {

        try (PrintWriter writer =
                new PrintWriter(new FileWriter(fileName))) {

            for (Student s : students) {

                writer.println(s.name + "|" +
                        s.rollNumber + "|" + s.grade);
            }

        }
        catch (IOException e) {
            System.out.println("Error saving student data.");
        }
    }
    void loadData() {

        File file = new File(fileName);

        if (!file.exists()) {
            return;
        }

        try (Scanner reader = new Scanner(file)) {

            while (reader.hasNextLine()) {

                String line = reader.nextLine();

                String[] data = line.split("\\|", -1);

                if (data.length == 3) {

                    try {
                        String name = data[0];
                        int roll = Integer.parseInt(data[1]);
                        String grade = data[2];

                        students.add(
                                new Student(name, roll, grade)
                        );
                    }
                    catch (NumberFormatException e) {
                        System.out.println("Skipping invalid record.");
                    }
                }
            }

        }
        catch (IOException e) {
            System.out.println("Error loading student data.");
        }
    }
}
public class StudentManagementSystem {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        StudentManager manager = new StudentManager();

        manager.loadData();

        int choice;

        do {

            System.out.println("\n===== STUDENT MANAGEMENT SYSTEM =====");

            System.out.println("1. Add Student");
            System.out.println("2. Display Students");
            System.out.println("3. Search Student");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Exit");

            System.out.print("Enter your choice: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:

                    System.out.print("Enter student name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter roll number: ");
                    int roll = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter grade: ");
                    String grade = sc.nextLine();

                    if (name.trim().isEmpty()
                            || grade.trim().isEmpty()
                            || roll <= 0) {

                        System.out.println("Invalid student details.");
                        break;
                    }

                    Student s = new Student(name, roll, grade);

                    manager.addStudent(s);

                    break;

                case 2:

                    manager.displayStudents();

                    break;

                case 3:

                    System.out.print("Enter roll number: ");
                    int searchRoll = sc.nextInt();

                    Student found = manager.searchStudent(searchRoll);

                    if (found != null) {
                        found.display();
                    }
                    else {
                        System.out.println("Student not found.");
                    }

                    break;

                case 4:

                    System.out.print("Enter roll number: ");
                    int updateRoll = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter new name: ");
                    String newName = sc.nextLine();

                    System.out.print("Enter new grade: ");
                    String newGrade = sc.nextLine();

                    if (newName.trim().isEmpty()
                            || newGrade.trim().isEmpty()) {

                        System.out.println("Fields cannot be empty.");
                        break;
                    }

                    manager.updateStudent(
                            updateRoll, newName, newGrade
                    );

                    break;

                case 5:

                    System.out.print("Enter roll number: ");
                    int deleteRoll = sc.nextInt();

                    manager.removeStudent(deleteRoll);

                    break;

                case 6:

                    System.out.println("Thank you!");

                    break;

                default:

                    System.out.println("Invalid choice.");
            }

        } while (choice != 6);

        sc.close();
    }
}
