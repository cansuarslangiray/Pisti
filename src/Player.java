public class Player {
    protected String name;
    protected Cards[] playerTable;
    protected Cards[] playerHand;
    protected int score;
    public Player(String name) {
        this.name = name;
        this.playerTable = new Cards[1];
        this.playerHand = new Cards[4];
        this.score = 0;
    }
    public void printHand(){ // print the player hand.
        for (Cards cards : playerHand) {
            System.out.println(cards.symbol + cards.card);
        }
    }
    public void printTable(){// print what the player gained.
        for (Cards cards : playerTable) {
            System.out.print(cards.symbol + cards.card + "\t");
        }
    }
}

