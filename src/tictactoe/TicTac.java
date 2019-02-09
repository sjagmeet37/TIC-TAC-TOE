package tictactoe;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class TicTac {

    String[] board = {" ", " ", " ", " ", " ", " ", " ", " ", " ", " "};
    String player1_marker, player2_marker;

    void display_board() throws IOException {
        
        System.out.println("   |   |");
        System.out.println(" " + board[7] + " | " + board[8] + " | " + board[9]);
        System.out.println("   |   |");
        System.out.println("-----------");
        System.out.println("   |   |");
        System.out.println(" " + board[4] + " | " + board[5] + " | " + board[6]);
        System.out.println("   |   |");
        System.out.println("-----------");
        System.out.println("   |   |");
        System.out.println(" " + board[1] + " | " + board[2] + " | " + board[3]);
        System.out.println("   |   |");
    }

    void player_input() {
        String marker = " ";
        while (!(marker.equals("X") || marker.equals("O"))) {
            Scanner s = new Scanner(System.in);
            System.out.println("Player 1: Do you want to be X or O? ");
            marker = s.nextLine();

            if (marker.equals("X")) {
                player1_marker = "X";
                player2_marker = "O";
            } else {
                player1_marker = "O";
                player2_marker = "X";
            }
        }

    }

    void place_marker(String marker, int position) {
        board[position] = marker;
    }

    boolean win_check(String mark) {

        if ((board[7].equals(mark) && board[8].equals(mark) && board[9].equals(mark))
                || (board[4].equals(mark) && board[5].equals(mark) && board[6].equals(mark))
                || (board[1].equals(mark) && board[2].equals(mark) && board[3].equals(mark))
                || (board[7].equals(mark) && board[4].equals(mark) && board[1].equals(mark))
                || (board[8].equals(mark) && board[5].equals(mark) && board[2].equals(mark))
                || (board[9].equals(mark) && board[6].equals(mark) && board[3].equals(mark))
                || (board[7].equals(mark) && board[5].equals(mark) && board[3].equals(mark))
                || (board[9].equals(mark) && board[5].equals(mark) && board[1].equals(mark))) {
            return true;
        }
        return false;
    }

    String choose_first() {
        Random rand = new Random();
        if (rand.nextInt(2) == 0) {
            return "Player 2";
        } else {
            return "Player 1";
        }
    }

    boolean space_check(int position) {

        return board[position].equals(" ");
    }

    boolean full_board_check() {
        for (int i = 0; i < 10; i++) {
            if (space_check(i)) {
                return false;
            }
        }
        return true;
    }

    int player_choice() {
        int position = 0;
        Scanner s = new Scanner(System.in);
        while (true) {
            System.out.println("Choose your next position: (1-9) ");
            position = s.nextInt();
            if (position > 0 || position < 10 || space_check(position)) {
                break;
            }
        }
        return position;
    }

    boolean replay() {
        System.out.println("Do you want to play again? Enter Yes or No: ");
        Scanner s = new Scanner(System.in);
        String reply = s.nextLine();
        reply = reply.toLowerCase();
        if (reply.contains("y")) {
            return true;
        } else {
            return false;
        }
    }

    void playGame() throws IOException {
        System.out.println("Welcome to Tic Tac Toe!");

        while (true) {
            for (int i = 0; i < 10; i++) {
                board[i] = " ";
            }
            player_input();
            String turn = choose_first();
            System.out.println(turn + " will go first.");

            boolean game_on = true;
            int position;
            while (game_on) {
                if (turn.equals("Player 1")) {
                    display_board();
                    position = player_choice();
                    place_marker(player1_marker, position);
                    display_board();
                    if (win_check(player1_marker)) {
                        display_board();
                        System.out.println("Congratulations! You have won the game!");
                        game_on = false;
                    } else {
                        if (full_board_check()) {
                            display_board();
                            System.out.println("The game is a draw!");
                            break;
                        } else {
                            turn = "Player 2";
                        }
                    }
                } else {
                    display_board();
                    position = player_choice();
                    place_marker(player2_marker, position);

                    if (win_check(player2_marker)) {
                        display_board();
                        System.out.println("Player 2 has won!");
                        game_on = false;
                    } else {
                        if (full_board_check()) {
                            display_board();
                            System.out.println("The game is a draw!");
                            break;
                        } else {
                            turn = "Player 1";
                        }
                    }
                }

            }
            if (!replay()) {
                break;
            }
        }
    }
}
