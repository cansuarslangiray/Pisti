import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc =new Scanner(System.in);
        Game game = new Game();
        game.play();
        boolean scoreBoard = true;
        String answer1= null;
        do {
            try {
                System.out.println("Do you want to see score board ?(if your answer is yes, please enter 'scoreboard'.else, please enter 'no') ");
                answer1 = sc.next();
                if(answer1.equalsIgnoreCase("scoreboard")){
                    Player.displayScoreboard();
                }
                scoreBoard = false;
            } catch (InputMismatchException e) {
                System.out.println("please enter yes or no");
                sc.next();
            }
        }while (scoreBoard);

        boolean playAgain = true;
        String answer = null;
        do {
            try {
                System.out.println("Do you want to play again: (if your answer is yes, please enter 'yes'.else, please enter 'no') ");
                answer = sc.next();
                playAgain=false;
            } catch (InputMismatchException e) {
                System.out.println("please enter yes or no");
                sc.next();
            }
        }while (playAgain);
        if(answer.equalsIgnoreCase("yes")){
            game.play();
        }
    }
}
