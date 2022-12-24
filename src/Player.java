import java.io.*;

public class Player {
    protected String name;
    protected Cards[] playerTable;
    protected Cards[] playerHand;
    protected int score;
    public Player(String name) {
        this.name = name;
        this.playerTable= new Cards[1];
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
    public int calculateScore(){ // get the sum of the points of the cards that the player has collected and the points of the pisti the player has made.
        for (Cards cards : playerTable) {
            score += cards.point;
        }
        return score;
    }
    public void score() {
        try {
            File file = new File("score.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            String playersScores = "high score list "+"\n";
            FileWriter fWriter = new FileWriter(file, false);
            BufferedWriter bWriter = new BufferedWriter(fWriter);
            bWriter.write(playersScores);

            bWriter.close();
            FileReader fReader = new FileReader(file);
            String line;
            BufferedReader bReader = new BufferedReader(fReader);
            while ((line = bReader.readLine()) != null) {
                System.out.println(line);
            }
            bWriter.close();

        }
        catch (IOException e){

        }
    }
}

