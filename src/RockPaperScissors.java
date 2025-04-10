import java.util.Random;
import java.util.Scanner;

public class RockPaperScissors {
    private final Scanner scanner = new Scanner(System.in);
    private final Random random = new Random();

    private static final int ROCK = 0;
    private static final int PAPER = 1;
    private static final int SCISSORS = 2;

    public void displayWelcomeMessage() {
        printHeader();
        printRules();
        printStartMessage();
    }

    public void printHeader() {
        System.out.println("=".repeat(20));
        System.out.println(" WELCOME TO ROCK PAPER SCISSORS ");
        System.out.println("=".repeat(20));
    }

    public void printRules() {
        System.out.println("\nGame Rules:");
        System.out.println("- Rock crushes Scissors");
        System.out.println("- Scissors cuts Paper");
        System.out.println("- Paper covers Rock");
    }

    public void printStartMessage() {
        System.out.println("\nPress Enter to start the game...\n");
        scanner.nextLine();
    }

    public void displayChoiceOptions() {
        System.out.println("\nMake your choice:");
        System.out.println("1 - Rock");
        System.out.println("2 - Paper");
        System.out.println("3 - Scissors");
        System.out.print("Enter your choice (1-3): ");
    }

    public String getUserInput() {
        return scanner.nextLine().trim().toLowerCase();
    }

    private int convertInputToChoice(String input) {
        while (true) {
            if (isRockInput(input)) return ROCK;
            if (isPaperInput(input)) return PAPER;
            if (isScissorsInput(input)) return SCISSORS;

            System.out.println("Invalid input! Please enter 1, 2, or 3 (or rock, paper, scissors).");
            displayChoiceOptions();
            input = getUserInput();
        }
    }

    public boolean isRockInput(String input) {
        return input.equals("1") || input.equals("rock") || input.equals("r");
    }

    public boolean isPaperInput(String input) {
        return input.equals("2") || input.equals("paper") || input.equals("p");
    }

    public boolean isScissorsInput(String input) {
        return input.equals("3") || input.equals("scissors") || input.equals("s");
    }

}
