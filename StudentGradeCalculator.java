
import java.util.Scanner;

public class StudentGradeCalculator {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of subjects: ");
        int n = sc.nextInt();

        if (n <= 0) {
            System.out.println("Invalid number of subjects.");
            sc.close();
            return;
        }

        int[] marks = new int[n];
        int total = 0;
        for (int i = 0; i < n; i++) {

            System.out.print("Enter marks for subject "
                    + (i + 1) + ": ");

            marks[i] = sc.nextInt();

            if (marks[i] < 0 || marks[i] > 100) {
                System.out.println("Invalid marks! Enter 0 to 100.");
                i--;
                continue;
            }

            total += marks[i];
        }
        double percentage = (double) total / n;
        String grade;

        if (percentage >= 90) {
            grade = "A+";
        }
        else if (percentage >= 80) {
            grade = "A";
        }
        else if (percentage >= 70) {
            grade = "B";
        }
        else if (percentage >= 60) {
            grade = "C";
        }
        else if (percentage >= 50) {
            grade = "D";
        }
        else {
            grade = "Fail";
        }
        System.out.println("\n----- STUDENT RESULT -----");

        System.out.println("Total Marks: " + total
                + "/" + (n * 100));

        System.out.println("Average Percentage: "
                + percentage + "%");

        System.out.println("Final Grade: " + grade);

        sc.close();
    }
}

