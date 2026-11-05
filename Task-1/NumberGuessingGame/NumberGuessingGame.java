import java.util.Random;
import java.util.Scanner;
public class NumberGuessingGame {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        int score = 0;
        int rounds = 0;
        String choice;

        System.out.println("WELCOME TO NUMBER GUESSING GAME");

        do {
            int number = random.nextInt(100) + 1;
            int attempts = 7;
            boolean guessed = false;

            rounds++;

            System.out.println("\nRound " + rounds);
            System.out.println("Guess a number between 1 and 100");
            System.out.println("You have 7 attempts.");

            while (attempts > 0) {

                System.out.print("Enter your guess: ");
                int guess = sc.nextInt();

                if (guess < 1 || guess > 100) {
                    System.out.println("Enter a number between 1 and 100.");
                    continue;
                }

                attempts--;

                if (guess == number) {
                    System.out.println("Congratulations! Correct guess.");
                    score += 10;
                    guessed = true;
                    break;
                }
                else if (guess < number) {
                    System.out.println("Too low! Try a higher number.");
                }
                else {
                    System.out.println("Too high! Try a lower number.");
                }

                System.out.println("Attempts remaining: " + attempts);
            }

            if (!guessed) {
                System.out.println("Game over!");
                System.out.println("Correct number was: " + number);
            }

            System.out.println("Current Score: " + score);

            System.out.print("Do you want another round? (yes/no): ");
            choice = sc.next();

        } while (choice.equalsIgnoreCase("yes"));

        System.out.println("\nGAME COMPLETED");
        System.out.println("Total Rounds: " + rounds);
        System.out.println("Final Score: " + score);

        sc.close();
    }
}

