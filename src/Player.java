import java.io.*;
import java.nio.file.Paths;
import java.security.PublicKey;
import java.util.Formatter;
import java.util.Scanner;

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
    public static void readText(){
        Scanner sc = null;
        try{
            sc = new Scanner(Paths.get("score.txt"));
            int i = 0;
            sc.nextLine();
            while (sc.hasNextLine()){
                String input = sc.nextLine();
                String[] playerData =input.split(",");
                Player player = new Player(playerData[0]);
                player.score = Integer.parseInt(playerData[1]);
                Game.scoreList[i] = player;
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeText(Player player){
        Formatter f = null;
        if (Game.scoreList[9].score < player.score){
            Game.scoreList[9] = player;
            int n = Game.scoreList.length;
            for (int i = 0; i < n - 1; i++) {
                for (int j = 0; j < n - i - 1; j++) {
                    if (Game.scoreList[j].score < Game.scoreList[j + 1].score) {
                        // swap arr[j+1] and arr[j]
                        Player temp = Game.scoreList[j];
                        Game.scoreList[j] = Game.scoreList[j + 1];
                        Game.scoreList[j + 1] = temp;
                    }
                }
            }
        }
        try{
            f = new Formatter("score.txt");
            f.format("player's name:,score:");
            for(int i=0;i<10;i++){
                f.format("\n" + "%s,%s", Game.scoreList[i].name, Game.scoreList[i].score);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            if(f!=null){
                f.close();
            }
        }
    }

    public static void displayScoreboard(){
        System.out.println("------------ Scoreboard -----------------");
        for (int i = 0; i < 10; i++) {
            System.out.println((i + 1) + ". " + Game.scoreList[i].name + "\tScore: " + Game.scoreList[i].score);
        }
    }

}

