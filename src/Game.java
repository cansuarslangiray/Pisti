import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public  class Game {
    static int computerNumber=1;
    public static Player[] scoreList = new Player[10];
    Cards[] board;
    Player player;
    Player computer;
    Scanner sc = new Scanner(System.in);
    // empty game constructor
    public Game(){
    }

    public void play(){
        Cards cards = new Cards();
        Cards[] deck = cards.createDeck() ;
        board = new Cards[4];
        Player.readText();
        computer = new Player("computer" + computerNumber);
        deck = cards.shuffle(deck);// shuffle deck
        deck = cards.shuffle(deck);// shuffle deck
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
                    deck = cards.cut(deck,number);// cut the deck
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
        for(int i=0;i<4;i++){// cards are given to players by one by.
                player.playerHand[i] = deck[i*3];//(0-3-6-9)
                computer.playerHand[i] = deck[(i * 3)+1];//(1-4-7-10)
                board[i]=deck[(i*3)+2]; //(2-5-8-11)
        }
        deck = cards.moveCardsFromDeck(deck);
        deck = cards.moveCardsFromDeck(deck);
        deck = cards.moveCardsFromDeck(deck);
        while (cards.checkDeck(deck)) {//loop  if deck is not empty.
            if(player.playerHand.length==0 && computer.playerHand.length==0) {// check player hand and computer hand are empty or not
                System.out.println("cards are dealt again");
                player.playerHand = new Cards[4];
                computer.playerHand = new Cards[4];
                for(int i=0;i<4;i++){// cards are given to players by one by.
                        player.playerHand[i] = deck[i*2];//(0-2-4-6)
                        computer.playerHand[i] = deck[(i * 2) + 1];//(1-3-5-7)
                }
                deck = cards.moveCardsFromDeck(deck);// remove the cards from deck
                deck = cards.moveCardsFromDeck(deck);// remove the cards from deck
            }
            printBoard();
            playPlayer();
            playComputer();
        }

        while (!cards.checkDeck(deck) && computer.playerHand.length!=0 && player.playerHand.length!=0){
            printBoard();
            playPlayer();
            playComputer();
            if(player.playerHand.length==1 || computer.playerHand.length==1){
                System.out.println("the last round in thıs game");
            }
        }
        if(computer.playerHand.length==0 && player.playerHand.length==0) {// check the all player's deck are empty ot not
            printBoard();
            System.out.println("computer table.....................");
            computer.printTable();
            System.out.println();
            System.out.println("player table.....................");
            player.printTable();
            System.out.println();
            System.out.println("now game is end");
            System.out.println("game winner is......");
            if (computer.calculateScore() > player.calculateScore()) {
                System.out.println(computer.name);
            } else if (computer.calculateScore()== player.calculateScore()) {
                System.out.println("there is no winner in this game");
                System.out.println("tied");
            } else {
                System.out.println(player.name);
            }
            Player.writeText(player);
        }
    }
    // print all the objects of board.
    public void printBoard() {
        System.out.println("the top card in the first row, the others show the bottom cards");
        System.out.println("cards on the table....");
        System.out.println("----------");
        if (board != null) {
            System.out.println(board[board.length - 1].symbol+" " + board[board.length - 1].card);// print the last object of the board.
            for (int i = board.length-2; i >=0; i--) {
                System.out.print(board[i].symbol +" "+board[i].card + "\t");// print the other board's object.
            }
            System.out.println();
        }
        else{
            System.out.println("table is empty now...");
        }
        System.out.println("-------------");
    }
    // check the card has J or the given card's value same as on the board.
    public boolean isPişti(Cards cards ){
        if(board!=null) {
            return cards.card.equalsIgnoreCase("J") || board[board.length - 1].card.equalsIgnoreCase(cards.card) ;
        }
        return false;
    }
    public void playComputer(){
        System.out.println("the computer is playing now...");
        Random random = new Random();
        boolean chosen=false;
        int number = 0;
        if(board!=null){
        for (int i = 0; i <computer.playerHand.length ; i++) {
            if (computer.playerHand[i].card.equalsIgnoreCase(board[board.length - 1].card) || computer.playerHand[i].card.equalsIgnoreCase("j")) {
                chosen = true;
                number = i;
                break;
            }
        }
        }if(!chosen) {
            number = random.nextInt(computer.playerHand.length);
        } 
        Cards card = computer.playerHand[number];
        Cards[] newCard = new Cards[computer.playerHand.length - 1];// crate new cards array and size is decremented because one card played.
        System.out.println("computer played " + card.symbol+" " +card.card);// print the card which are played by computer.
        if(isPişti(card)){
            System.out.println("computer did pişti...");
            computer.score+=10;

            if(computer.playerTable.length==1){// check the computer table length one or not.
                computer.playerTable = new Cards[board.length+1];// create new cards array and change the size according to board's size(now computer.playerTable is equal to new array)
                System.arraycopy(board,0,computer.playerTable,0,board.length);//added the all card on the board in computer player table.
            }else{
                int oldIndex=computer.playerTable.length;
                Cards[] old = new Cards[oldIndex];// create new cards array
                System.arraycopy(computer.playerTable,0,old,0,computer.playerTable.length);//added the all cards on the computer.playerTable in old array.
                computer.playerTable = new Cards[board.length+computer.playerTable.length+1];// now computer.playerTable is equal to new array(added an extra one because the played card will also be taken)
                System.arraycopy(old,0,computer.playerTable,0,old.length);// added the all cards on the old in computer player table.
                System.arraycopy(board,0,computer.playerTable,oldIndex,board.length);// added the all cards on the board in computer player table.
            }

            computer.playerTable[computer.playerTable.length-1]= card;
            board = null;
        }
        else{
            if(board==null){// check board is null or not
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
        //all index except last index which index's cards played in the game remove from array and other cards index are decremented.
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
            else {//last index's cards remove from array.
                System.arraycopy(computer.playerHand, 0, newCard, 0, computer.playerHand.length - 1);

                }
            computer.playerHand = newCard;

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
                    player.printHand();
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
            for (int i = 0; i < player.playerHand.length; i++) {
                if (player.playerHand[i].symbol.equalsIgnoreCase(cardToPlay[0]) && player.playerHand[i].card.equalsIgnoreCase(cardToPlay[1])) {
                    if (isPişti(player.playerHand[i])) {
                        System.out.println("you did pişti...");
                        player.score += 10;

                        if(player.playerTable.length==1){
                            player.playerTable= new Cards[board.length+1];
                            System.arraycopy(board, 0, player.playerTable, 0, board.length); //added the all card on the board in player's player table.
                        }else{
                            int oldIndex=player.playerTable.length;
                            Cards[] old = new Cards[oldIndex];//create new cards array and change the size according to board's size(now player.playerTable is equal to new array)
                            System.arraycopy(player.playerTable,0,old,0,player.playerTable.length);
                            player.playerTable= new Cards[player.playerTable.length+board.length+1];// now player.playerTable is equal to new array(added an extra one because the played card will also be taken)
                            System.arraycopy(old,0,player.playerTable,0,old.length);// added the all cards on the old in player table.
                            System.arraycopy(board, 0, player.playerTable, oldIndex, board.length); //added the all card on the board in player's player table.
                        }
                        player.playerTable[player.playerTable.length-1] = player.playerHand[i];
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
                    //all index except last index which index's cards played in the game remove from array and other cards index are decremented.
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
                        else {//last index's cards remove from array.
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
    // some introduction lines.
    public void beginningParts(){
        System.out.println("welcome the game :) ");
        System.out.println("before the start game. we will tell some rules");
        System.out.println("firstly, each player has 4 cards and 4 cards in the board but all player only see last card.");
        System.out.println("First player start the game");
        System.out.println("and then you check your card and try to find same card's value in the board");
        System.out.println("if your card and board ara match you do pişti and you gain 10 puan. ");
        System.out.println("if you do not the same card you have to add other card which you want.");
        System.out.println("let's get start. you can learn while we playing");

    }
}
