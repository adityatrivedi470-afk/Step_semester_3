package programming_fundamentals.class_problems;

import java.util.Random;

public class RockPaperScissors {

    public static String playRound(String playerMove, String computerMove) {
        if (playerMove.equalsIgnoreCase(computerMove)) {
            return "Draw";
        }
        if ((playerMove.equalsIgnoreCase("Rock") && computerMove.equalsIgnoreCase("Scissors"))
                || (playerMove.equalsIgnoreCase("Scissors") && computerMove.equalsIgnoreCase("Paper"))
                || (playerMove.equalsIgnoreCase("Paper") && computerMove.equalsIgnoreCase("Rock"))) {
            return "Player Wins";
        } else {
            return "Computer Wins";
        }
    }

    public static void main(String[] args) {
        String[] moves = {"Rock", "Paper", "Scissors"};
        String[] playerMoves = {"Rock", "Paper", "Scissors", "Rock", "Paper"};
        int n = 5;
        Random random = new Random();

        String[] computerMoves = new String[n];
        String[] results = new String[n];

        int wins = 0;
        int losses = 0;
        int draws = 0;

        for (int i = 0; i < n; i++) {
            computerMoves[i] = moves[random.nextInt(moves.length)];
            results[i] = playRound(playerMoves[i], computerMoves[i]);

            if (results[i].equals("Player Wins")) {
                wins++;
            } else if (results[i].equals("Computer Wins")) {
                losses++;
            } else {
                draws++;
            }
        }

        System.out.printf("%-6s | %-12s | %-14s | %-13s%n", "Round", "Player Move", "Computer Move", "Result");
        System.out.println("---------------------------------------------------------");
        for (int i = 0; i < n; i++) {
            System.out.printf("%-6d | %-12s | %-14s | %-13s%n", (i + 1), playerMoves[i], computerMoves[i], results[i]);
        }

        double winPercentage = ((double) wins / n) * 100;
        System.out.println("\nFinal Summary (after " + n + " rounds)");
        System.out.printf("Wins: %d | Losses: %d | Draws: %d | Win %% = %.1f%%%n", wins, losses, draws, winPercentage);
    }
}
