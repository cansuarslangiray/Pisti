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
        System.out.println("ilk başta comuter: ");
        System.out.println("-------------------");
        for(int k = 0; k<computerHand.length;k++){
            System.out.println(computerHand[k].symbol+computerHand[k].card);
        }
        System.out.println("-------------------------------");
        while (cards.checkDeck(deck)) {
            if(playerHand.length==0 && computerHand.length==0) {
                playerHand = cards.moveCards(deck);
                deck = cards.moveCardsFromDeck(deck);
                computerHand = cards.moveCards(deck);
                deck = cards.moveCardsFromDeck(deck);
            }
            printBoard();
            playPlayer();
            playComputer();
        }
        while (!cards.checkDeck(deck) && computerHand!=null){
            System.out.println("the last round ın thıs game");
            printBoard();
            playPlayer();
            playComputer();
        }
        System.out.println("now game is end");
    }

    public void printBoard() {
        System.out.println("the top card in the first row, the others show the bottom cards");
        System.out.println("cards on the table....");
        System.out.println("----------------");
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
            return cards.card.equalsIgnoreCase("J") || board[board.length - 1].card.equalsIgnoreCase(cards.card) && cards.symbol.equalsIgnoreCase(board[board.length - 1].symbol);
        }
        return false;
    }
    public void playComputer(){
        System.out.println("the computer is playing now...");
        Random random = new Random();
        int number = random.nextInt(computerHand.length);
        Cards card = computerHand[number];
        Cards[] newCard = new Cards[computerHand.length - 1];
        if(isPişti(card)){
            System.out.println("computer did pişti...");
            computerSore+=10;
            System.arraycopy(board,0,computer,0,board.length);
            computer[board.length]= card;
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
        if(computerHand!=null) {
            if (number == 0) {
                System.arraycopy(computerHand, 1, newCard, 0, computerHand.length - 1);
            } else if (number==1) {
              newCard[0]=computerHand[0];
                if (computerHand.length - 2 >= 0)
                    System.arraycopy(computerHand, 2, newCard, 1, computerHand.length - 2);

            } else if(number==2){
                System.arraycopy(computerHand, 0, newCard, 0, number);
                newCard[number]=computerHand[computerHand.length-1];
            }
            else {
                System.arraycopy(computerHand, 0, newCard, 0, computerHand.length - 1);

                }
                computerHand = newCard;
                System.out.println("--------------- new computer hand");
            for (Cards value : computerHand) {
                System.out.println(value.symbol + value.card);
            }

        }

        if(computerHand==null){
            System.out.println("computer hand is empty");
            computerHand =null;
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
                    for (Cards value : playerHand) {
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
        System.out.println("which card do you want to play (\"♤(Spades)\",\"♡(Hearts)\",\"♢(Diamonds)\",\"♧(Clubs)\") ");
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
            Cards[] newCard = new Cards[playerHand.length - 1];
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

            for (int i = 0; i < playerHand.length; i++) {
                if (playerHand[i].symbol.equalsIgnoreCase(cardToPlay[0]) && playerHand[i].card.equalsIgnoreCase(cardToPlay[1])) {
                    System.out.println("your card secessfuly selected");
                    if (isPişti(playerHand[i])) {
                        System.out.println("you did pişti...");
                        playerScore += 10;
                        System.arraycopy(board, 0, player, 0, board.length);
                        player[board.length] = playerHand[i];
                        board = null;
                        if (playerHand.length == 1) {
                            System.out.println("hand is empty...");
                            playerHand = null;
                        }
                    } else {
                        if (board != null) {
                            Cards[] newBoards = new Cards[board.length + 1];
                            System.arraycopy(board, 0, newBoards, 0, board.length);
                            newBoards[board.length] = playerHand[i];
                            board = newBoards;
                        } else {
                            board = new Cards[1];
                            board[0] = playerHand[i];
                        }
                    }
                    if (playerHand != null) {
                        if (i == 0) {
                            System.arraycopy(playerHand, 1, newCard, 0, playerHand.length - 1);
                        } else if (i== 1) {// bak
                            newCard[0]=playerHand[0];
                            if (playerHand.length - 2 >= 0)
                                System.arraycopy(playerHand, 2, newCard, 1, playerHand.length - 2);
                        } else if(i==2){
                            System.arraycopy(playerHand, 0, newCard, 0, i);
                            newCard[i]=playerHand[playerHand.length-1];
                        }
                        else {
                            System.arraycopy(playerHand, 0, newCard, 0, playerHand.length - 1);
                        }

                        playerHand = newCard;
                        System.out.println("------------------------ player new hand");
                        for (Cards value : playerHand) {
                            System.out.println(value.symbol + value.card);
                        }
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
        System.out.println("the dealer are shuffling the cards now ");
        System.out.println("and now, you can cut the deck. ");
        System.out.println("-----------------------------");

    }
}
