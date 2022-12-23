import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public  class Game {
    static int computerNumber=1;
    Cards[] board;
    Player player;
    Player computer;
    Scanner sc = new Scanner(System.in);
    // empty game constructor
    public Game(){
    }
    Cards cards = new Cards();
    Cards[] deck = cards.createDeck() ;

    public void play(){
        computer = new Player("computer" + computerNumber);
        deck = cards.shuffle(deck);
        beginningParts();
        String name = null;
        boolean nameIsCorrect= true;
        do {

            try {
                System.out.println("please enter your name: ");
                name = sc.nextLine();
                    nameIsCorrect= false;


            } catch (InputMismatchException e) {
                System.out.println("please enter your name: ");
                sc.next();
            }
        }while (nameIsCorrect);
        player = new Player(name);
        System.out.println("the dealer are shuffling the cards now ");
        System.out.println("and now, you can cut the deck. ");
        boolean a = true;
        do {
            try {
                System.out.println("please enter a number between 1 and 52 ");
                int number = sc.nextInt();
                if (number>=0 && number<=51) {
                    deck = cards.cut(deck,number);
                    a = false;
                }
                else{
                    System.out.println("please enter a number between 0 and 51 (not including 1 and 52)");
                }
            } catch (InputMismatchException e) {
                System.out.println("please enter a number between 0 and 51 ( not including 1 and 52)");
                sc.next();
            }
        }while (a);

        System.out.println("cards are dealt");
        for(int i=0;i<4;i++){
                player.playerHand[i] = deck[i*2];
                computer.playerHand[i] = deck[(i * 2) + 1];

        }
        deck = cards.moveCardsFromDeck(deck);
        deck = cards.moveCardsFromDeck(deck);
        board = cards.moveCards(deck);
        deck = cards.moveCardsFromDeck(deck);
        while (cards.checkDeck(deck)) {
            if(player.playerHand.length==0 && computer.playerHand.length==0) {
                System.out.println("cards are dealt again");
                player.playerHand = new Cards[4];
                computer.playerHand = new Cards[4];
                for(int i=0;i<4;i++){
                        player.playerHand[i] = deck[i*2];
                        computer.playerHand[i] = deck[(i * 2) + 1];

                }
                deck = cards.moveCardsFromDeck(deck);
                deck = cards.moveCardsFromDeck(deck);
            }
            printBoard();
            playPlayer();
            playComputer();
        }
        while (!cards.checkDeck(deck) && computer.playerHand.length!=0 && player.playerHand.length!=0){
            System.out.println("the last round in thıs game");
            printBoard();
            playPlayer();
            playComputer();
        }
        if(computer.playerHand.length==0 && player.playerHand.length==0) {
            printBoard();
            System.out.println("now game is end");
            System.out.println("game winner is......");
            if (computer.score > player.score) {
                System.out.println(computer.name);
            } else if (computer.score == player.score) {
                System.out.println("there is no winner in this game");
                System.out.println("tied");
            } else {
                System.out.println(player.name);
            }

        }
    }

    public void printBoard() {
        System.out.println("the top card in the first row, the others show the bottom cards");
        System.out.println("cards on the table....");
        if (board != null) {
            System.out.println(board[board.length - 1].symbol + board[board.length - 1].card);
            for (int i = board.length-2; i >=0; i--) {
                System.out.print(board[i].symbol + board[i].card + "\t");
            }
            System.out.println();
        }
        else{
            System.out.println("table is empty now...");
        }
    }
    public boolean isPişti(Cards cards ){
        if(board!=null) {
            return cards.card.equalsIgnoreCase("J") || board[board.length - 1].card.equalsIgnoreCase(cards.card) ;
        }
        return false;
    }
    public void playComputer(){
        System.out.println("the computer is playing now...");
        Random random = new Random();
        int number = random.nextInt(computer.playerHand.length);
        Cards card = computer.playerHand[number];
        Cards[] newCard = new Cards[computer.playerHand.length - 1];
        System.out.println("computer played " + card.symbol+card.card);
        if(isPişti(card)){
            System.out.println("computer did pişti...");
            computer.score+=10;
            computer.playerTable = new Cards[board.length+1];
            System.arraycopy(board,0,computer.playerTable,0,board.length);
            computer.playerTable[board.length]= card;
            board = null;
        }
        else{
            if(board==null){
                board = new Cards[1];
                board[0] = card;
            }
            else {
                Cards[] newBoards = new Cards[board.length + 1];
                System.arraycopy(board, 0, newBoards, 0, board.length);
                newBoards[board.length] = card;
                board = newBoards;
            }
        }
        if(computer.playerHand!=null) {
            if (number == 0) {
                System.arraycopy(computer.playerHand, 1, newCard, 0, computer.playerHand.length - 1);
            } else if (number==1 && computer.playerHand.length>=3) {
              newCard[0]=computer.playerHand[0];
              System.arraycopy(computer.playerHand, 2, newCard, 1, computer.playerHand.length - 2);

            } else if(number==2 && computer.playerHand.length==4){
                System.arraycopy(computer.playerHand, 0, newCard, 0, number);
                newCard[number]=computer.playerHand[computer.playerHand.length-1];
            }
            else {
                System.arraycopy(computer.playerHand, 0, newCard, 0, computer.playerHand.length - 1);

                }
            computer.playerHand = newCard;

        }

        if(computer.playerHand==null){
            System.out.println("computer hand is empty");
            computer.playerHand =null;
        }
    }

    public void playPlayer(){
        boolean canSeeHands = true;
        System.out.println("if you want to see your hands, enter 'my hand'");
        do {
            try {
                String hands = sc.nextLine();
                if (hands.equalsIgnoreCase("my hand")) {
                    System.out.println("your cards are here... ");
                    System.out.println("-----------");
                    for (Cards value : player.playerHand) {
                        System.out.println(value.symbol + value.card);
                    }
                    System.out.println("-----------");
                    canSeeHands = false;
                }else{
                    System.out.println("please enter 'my hand' ");
                }
            } catch (InputMismatchException e) {
                System.out.println("please enter 'my hand'");
            }
        }while (canSeeHands);
        boolean check = true;
        System.out.println("which card do you want to play (\"♤ -> (Spades)\",\"♡ -> (Hearts)\",\"♢ -> (Diamonds)\",\"♧ -> (Clubs)\") ");
        System.out.println("please enter type of cards and number: (leave a space between type and numbers)");
        do {
            try {
                String cards = sc.nextLine();
                String[] cardToPlay = cards.split(" ");
                if(checkTheCard(cardToPlay)){
                    check = false;
                }
                else{
                    System.out.println("either you entered a wrong card or a card you don't have");
                    System.out.println("please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("either you entered a wrong card or a card you don't have");
                System.out.println("please try again.");
            }
        }while (check);


    }
    public boolean checkTheCard(String[] cardToPlay) {
        if(cardToPlay.length==2) {
            Cards[] newCard = new Cards[player.playerHand.length - 1];
            if (cardToPlay[0].equalsIgnoreCase("Spades")) {
                cardToPlay[0] = "♤";
            } else if (cardToPlay[0].equalsIgnoreCase("Hearts")) {
                cardToPlay[0] = "♡";
            } else if (cardToPlay[0].equalsIgnoreCase("Diamonds")) {
                cardToPlay[0] = "♢";
            } else if (cardToPlay[0].equalsIgnoreCase("Clubs")) {
                cardToPlay[0] = "♧";
            }else{
                return false;
            }

            for (int i = 0; i < player.playerHand.length; i++) {
                if (player.playerHand[i].symbol.equalsIgnoreCase(cardToPlay[0]) && player.playerHand[i].card.equalsIgnoreCase(cardToPlay[1])) {
                    if (isPişti(player.playerHand[i])) {
                        System.out.println("you did pişti...");
                        player.score += 10;
                        player.playerTable = new Cards[board.length+1];
                        System.arraycopy(board, 0, player.playerTable, 0, board.length);
                        player.playerTable[board.length] = player.playerHand[i];
                        board = null;
                        System.out.println("-------------------");
                    } else {
                        if (board != null) {
                            Cards[] newBoards = new Cards[board.length + 1];
                            System.arraycopy(board, 0, newBoards, 0, board.length);
                            newBoards[board.length] = player.playerHand[i];
                            board = newBoards;
                        } else {
                            board = new Cards[1];
                            board[0] = player.playerHand[i];
                        }
                    }
                    if (player.playerHand != null) {
                        if (i == 0) {
                            System.arraycopy(player.playerHand, 1, newCard, 0, player.playerHand.length - 1);
                        } else if (i==1 && player.playerHand.length>=3) {// bak
                            newCard[0]=player.playerHand[0];
                            System.arraycopy(player.playerHand, 2, newCard, 1, player.playerHand.length - 2);
                        } else if(i==2 && player.playerHand.length==4){
                            System.arraycopy(player.playerHand, 0, newCard, 0, i);
                            newCard[i]=player.playerHand[player.playerHand.length-1];
                        }
                        else {
                            System.arraycopy(player.playerHand, 0, newCard, 0, player.playerHand.length - 1);
                        }

                        player.playerHand = newCard;
                    }

                    return true;
                }
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

    }
}
