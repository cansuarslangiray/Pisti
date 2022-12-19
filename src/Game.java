import java.util.InputMismatchException;
import java.util.Scanner;

public  class Game {
    public Game(){

    }
    Cards cards = new Cards();
    Cards[] deck = cards.createDeck() ;
    /*public static void main(String[] args) {
        Cards cards = new Cards();
        Cards[] deck = cards.createDeck() ;
        for(int i = 0; i< deck.length;i++){
            System.out.println(deck[i].symbol + deck[i].card);
        }
        System.out.println("---------------------------");
         deck = cards.shuffle(deck);
        for(int i = 0; i< deck.length;i++){
            System.out.println(deck[i].symbol + deck[i].card);
        }
        deck = cards.cut(deck);
        System.out.println("---------------------------");
        for(int i = 0; i< deck.length;i++){
            System.out.println(deck[i].symbol + deck[i].card);
        }
    }*/

    public void play(){
        Scanner sc = new Scanner(System.in);
        Cards[] board;
        Cards[] computer;
        Cards[] computerHand;
        Cards[] player;
        Cards[] playerHand;
        deck = cards.shuffle(deck);
        beginningParts();

        boolean a = true;
        do {
            try {
                System.out.println("please enter a number between 1 and 52 ");
                int number = sc.nextInt();
                if (number>=0 && number<=51) {
                    deck = cards.cut(deck,number);
                    a = false;
                }

            } catch (InputMismatchException e) {
                System.out.println("please enter a number between 0 and 51");
            }
        }while (a);
        System.out.println("now ");

    }

    public void beginningParts(){
        System.out.println("the dealer are shuffling the cards now ");
        System.out.println(" and now, you can cut the deck. ");



    }
}
