import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public  class Game {
    Cards[] board;
    Cards[] computer = new Cards[26];
    Cards[] computerHand;
    Cards[] player = new Cards[26];
    Cards[] playerHand;
    int playerScore =0;
    int computerSore=0;
    Scanner sc = new Scanner(System.in);
    public Game(){

    }
    Cards cards = new Cards();
    Cards[] deck = cards.createDeck() ;

    public void play(){
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
        System.out.println("cards are dealt");
        playerHand = cards.moveCards(deck);
        deck = cards.moveCardsFromDeck(deck);
        computerHand = cards.moveCards(deck);
        deck = cards.moveCardsFromDeck(deck);
        board = cards.moveCards(deck);
        deck = cards.moveCardsFromDeck(deck);
        System.out.println("and the board is top card on the board");
        System.out.println("----------------");
        System.out.println(board[board.length-1].symbol+board[board.length-1].card);
        playPlayer();

    }

    public boolean isPişti(Cards cards ){
        String[] arr = cards.card.split("");
        String[] arr2 = board[board.length-1].card.split("");
        return arr[1].equalsIgnoreCase("j") || arr[1].equalsIgnoreCase(arr2[1]);
    }
    public Cards playComputer(){// geliştirrr
        Random random = new Random();
        int number = random.nextInt(0,computerHand.length-1);
        Cards card = computerHand[number];
        Cards[] newCard= new Cards[computerHand.length-1];
        if(number!=computerHand.length-1 ) {
            System.arraycopy(computerHand, 0, newCard, 0, number - 1);
            System.arraycopy(computerHand, number , newCard, number-1,computerHand.length );//bak tekrar!!
        }
        else{
            System.arraycopy(computerHand,0,newCard,0,computerHand.length-2);
        }
        computerHand=newCard;
        return card;
    }

    public Cards playPlayer(){
        boolean canSeeHands = true;
        System.out.println("if you want to see your hands, enter 'my hand'");
        do {
            try {
                String hands = sc.nextLine();
                if (hands.equalsIgnoreCase("my hand")) {
                    System.out.println("your cards are here... ");
                    System.out.println("-----------");
                    for (Cards value : playerHand) {
                        System.out.println(value.symbol + value.card);
                    }
                    System.out.println("-----------");
                    canSeeHands = false;
                }
            } catch (InputMismatchException e) {
                System.out.println("please enter 'my hand'");
            }
        }while (canSeeHands);
        boolean check = true;
        System.out.println("which card do you want to play (\"♤(Spades)\",\"♡(Hearts)\",\"♢(Diamonds)\",\"♧(Clubs)\") ");
        System.out.println("please enter type of cards and number: (leave a space between type and numbers)");
        do {
            try {
                String cards = sc.nextLine();
                String[] cardToPlay = cards.split(" ");
                if(checkTheCard(cardToPlay)){
                    check = false;
                }
            } catch (InputMismatchException e) {
                System.out.println("either you entered a wrong card or a card you don't have");
                System.out.println("please try again.");
            }
        }while (check);

return null;
    }
    public boolean checkTheCard(String[] cardToPlay){
        Cards[] newCard= new Cards[playerHand.length-1];
        if(cardToPlay[0].equalsIgnoreCase("Spades")){
            cardToPlay[0]="♤";
        }
        else if(cardToPlay[0].equalsIgnoreCase("Hearts")){
            cardToPlay[0]="♡";
        }
        else if(cardToPlay[0].equalsIgnoreCase("Diamonds")){
            cardToPlay[0]="♢";
        }
        else if(cardToPlay[0].equalsIgnoreCase("Clubs")){
            cardToPlay[0]="♧";
        }

        for(int i = 0; i< playerHand.length;i++) {
            if (playerHand[i].symbol.equalsIgnoreCase(cardToPlay[0])) {
                if(playerHand[i].card.equalsIgnoreCase(cardToPlay[1])){
                    if(isPişti(playerHand[i])){
                        System.out.println("you did pişti...");
                        playerScore+=10;
                        System.arraycopy(board,0,player,0,board.length);
                        player[board.length]= playerHand[i];
                        if(i!=playerHand.length-1 ) {
                            System.arraycopy(playerHand, 0, newCard, 0, i);
                            System.arraycopy(computerHand, i+ 1, newCard, i,computerHand.length );
                        }
                        else{
                            System.arraycopy(computerHand,0,newCard,0,computerHand.length-2);
                        }
                        board = new Cards[0];// bak tekrardan!!
                    }

                }
                else{
                    System.out.println("it's the other player's turn to play...");
                }
                return true;
            }
        }
        return false;
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
