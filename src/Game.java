import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public  class Game {
    Cards[] board;
    Cards[] computer;
    Cards[] computerHand;
    Cards[] player;
    Cards[] playerHand;
    public Game(){

    }
    Cards cards = new Cards();
    Cards[] deck = cards.createDeck() ;

    public void play(){
        Scanner sc = new Scanner(System.in);
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
        System.out.println("now cards are dealt");
        playerHand = cards.moveCards(deck);
        deck = cards.moveCardsFromDeck(deck);
        computerHand = cards.moveCards(deck);
        deck = cards.moveCardsFromDeck(deck);
        System.out.println("your cards are here: ");
         for(int i = 0; i< playerHand.length;i++){
            System.out.println(playerHand[i].symbol + playerHand[i].card);
        }
        System.out.println("-----------");




    }

    public boolean isPişti(Cards cards ){
        if(cards.equals(board[board.length-1])){
            return true;
        }
        return false;
    }
    public Cards playComputer(){
        Random random = new Random();
        int number = random.nextInt(0,computerHand.length-1);
        Cards card = computerHand[number];
        Cards[] newCard= new Cards[computerHand.length-1];
        if(number!=computerHand.length-1) {
            System.arraycopy(computerHand, 0, newCard, 0, number - 1);
            System.arraycopy(computerHand, number + 1, newCard, number-1,computerHand.length );
        }
        else{
            System.arraycopy(computerHand,0,newCard,0,computerHand.length-2);
        }
        computerHand=newCard;
        return card;
    }

    public void beginningParts(){
        System.out.println("welcome the game :) ");
        System.out.println("before the start game. we will tell some rules");
        System.out.println("firstly, each player has 4 cards and 4 cards in the board but all player only see last card.");
        System.out.println("we decide to who start ");
        System.out.println("and then you check your card and try to find same card in the board");
        System.out.println("if your card and board ara match you do pişti and you gain 10 puan. ");
        System.out.println("if you do not the same card you have to add other card which you want.");
        System.out.println("let's get start. you can learn while we playing");
        System.out.println("the dealer are shuffling the cards now ");
        System.out.println("and now, you can cut the deck. ");



    }
}
