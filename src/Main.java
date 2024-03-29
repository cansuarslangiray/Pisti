import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Game game = new Game();
        boolean playAgain1 = true;
        boolean scoreBoard = true;
        String answer1 = null;
        while (playAgain1) {
            game.play();
            do {
                try {
                    System.out.println("Do you want to see score board ?(if your answer is yes, please enter 'scoreboard'.else, please enter 'no') ");
                    answer1 = sc.next();
                    if (answer1.equalsIgnoreCase("scoreboard")) {
                        Player.displayScoreboard();
                        scoreBoard=false;
                    }
                    if(answer1.equalsIgnoreCase("no")){
                        scoreBoard=false;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("please enter yes or no");
                    sc.next();
                }
            } while (scoreBoard);
            boolean playAgain = true;
            String answer = null;
            do {
                try {
                    System.out.println("Do you want to play again: (if your answer is yes, please enter 'yes'.else, please enter 'no') ");
                    answer = sc.next();
                    if(answer.equalsIgnoreCase("no")){
                        playAgain = false;
                        playAgain1=false;
                        System.exit(0);
                    }
                    else if(answer.equalsIgnoreCase("yes")){
                        playAgain=false;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("please enter yes or no");
                    sc.next();
                }
            } while (playAgain);

        }
    }
}
