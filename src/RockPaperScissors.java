import java.util.Random;
import java.util.Scanner;

public class RockPaperScissors {
    private final Scanner scanner = new Scanner(System.in);
    private final Random random = new Random();

    private int playerWins = 0;
    private int computerWins = 0;
    private int ties = 0;

    private static final int ROCK = 0;
    private static final int PAPER = 1;
    private static final int SCISSORS = 2;

    public void run() {
        displayWelcomeMessage();
        playGameLoop();
        displayFinalResults();
        closeResources();
    }

    public void playGameLoop() {
        boolean playAgain = true;

        while (playAgain) {
            int playerChoice = getPlayerChoice();
            int computerChoice = getComputerChoice();

            displayChoices(playerChoice, computerChoice);
            determineWinner(playerChoice, computerChoice);
            displayScore();

            playAgain = askToPlayAgain();
        }
    }

    public void displayWelcomeMessage() {
        printHeader();
        printRules();
        printStartMessage();
    }

    public void printHeader() {
        System.out.println("=".repeat(32));
        System.out.println(" WELCOME TO ROCK PAPER SCISSORS ");
        System.out.println("=".repeat(32));
    }

    public void printRules() {
        System.out.println("\nGame Rules:");
        System.out.println("- Rock crushes Scissors");
        System.out.println("- Scissors cuts Paper");
        System.out.println("- Paper covers Rock");
    }

    public void printStartMessage() {
        System.out.println("\nLet's play!\n");
    }

    public int getPlayerChoice() {
        displayChoiceOptions();
        String input = getUserInput();
        return convertInputToChoice(input);
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
            else if (isPaperInput(input)) return PAPER;
            else if (isScissorsInput(input)) return SCISSORS;
            else {
                System.out.println("Invalid input! Please enter 1, 2, or 3 (or rock, paper, scissors).");
                displayChoiceOptions();
                input = getUserInput();
            }
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

    private int getComputerChoice() {
        return random.nextInt(3);
    }

    private void displayChoices(int playerChoice, int computerChoice) {
        System.out.println("\n" + "=".repeat(32) );
        System.out.println("You chose: " + choiceToString(playerChoice));
        System.out.println("Computer chose: " + choiceToString(computerChoice));
        System.out.println("=".repeat(32));
    }

    public String choiceToString(int choice) {
        return switch(choice) {
            case ROCK -> "Rock";
            case PAPER -> "Paper";
            case SCISSORS -> "Scissors";
            default -> "UNKNOWN";
        };
    }

    private void determineWinner(int playerChoice, int computerChoice) {
        if (isTie(playerChoice, computerChoice)) {
            handleTie();
        } else if (isPlayerWin(playerChoice, computerChoice)) {
            handlePlayerWin();
        } else {
            handleComputerWin();
        }
    }

    public boolean isTie (int playerChoice, int computerChoice) {
        return playerChoice == computerChoice;
    }

    public void handleTie() {
        System.out.println("It's a tie! No one wins. \uD83E\uDD1D");
        ties++;
    }

    public boolean isPlayerWin (int playerChoice, int computerChoice) {
        return (playerChoice == ROCK && computerChoice == SCISSORS)  ||
                (playerChoice == PAPER && computerChoice == ROCK) ||
                (playerChoice == SCISSORS && computerChoice == PAPER);
    }

    public void handlePlayerWin() {
        System.out.println("Congratulations! You win this round! \uD83C\uDF89");
        playerWins++;
    }

    public void handleComputerWin() {
        System.out.println("Sorry! The computer wins this round. \uD83D\uDCBB");
        computerWins++;
    }

    public void displayScore() {
        System.out.println("\nCurrent Score:");
        System.out.println("You: " + playerWins);
        System.out.println("Computer: " + computerWins);
        System.out.println("Ties: " + ties);
    }

    public boolean askToPlayAgain() {
        while (true) {
            System.out.print("\nDo you wish to play again? (yes/no): ");
            String response = getUserInput();

            if (isYesResponse(response)) {
                return true;
            } else if (isNoResponse(response)) {
                return false;
            } else {
                System.out.println("Invalid response! Please enter 'yes' or 'no'.");
            }
        }
    }

    public boolean isYesResponse(String response) {
        return response.toLowerCase().startsWith("y");
    }

    public boolean isNoResponse(String response) {
        return response.toLowerCase().startsWith("n");
    }

    public void displayFinalResults() {
        int totalGames = calculateTotalGames();

        printFinalHeader();
        printGameStatistics(totalGames);
        printOverallWinner();
        printFarewell();
    }

    public int calculateTotalGames() {
        return playerWins + computerWins + ties;
    }

    public void printFinalHeader() {
        System.out.println("\n" + "=".repeat(32));
        System.out.printf("%10s%s%10s%n", "", "FINAL RESULT", "");
        System.out.println("=".repeat(32));
    }

    public void printGameStatistics(int totalGames) {
        System.out.println("Total games played: " + totalGames);
        System.out.println("Your wins: " + playerWins + " (" + calculatePercentage(playerWins, totalGames) + "%)");
        System.out.println("Computer wins: " + computerWins + " (" + calculatePercentage(computerWins, totalGames) + "%)");
        System.out.println("Ties: " + ties + " (" + calculatePercentage(ties, totalGames) + "%)");
    }

    public void printOverallWinner() {
        if (playerWins > computerWins) {
            System.out.println("\nCongratulations! You are the overall winner! \uD83C\uDF89");
        } else if (computerWins > playerWins) {
            System.out.println("\nThe computer is the overall winner. Better luck next time! \uD83D\uDCBB");
        } else {
            System.out.println("\nIt's a tie overall! Well played by both sides.");
        }
    }

    public void printFarewell() {
        System.out.println("\nThank you for playing Rock, Paper, Scissors!");
        System.out.println("=".repeat(32));
    }

    public double calculatePercentage(int wins, int totalGames) {
        if (totalGames == 0) return 0;
        return Math.round(((double) wins / totalGames) * 100);
    }

    public void closeResources() {
        scanner.close();
    }
}
