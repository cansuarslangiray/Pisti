import java.util.Random;

public class Cards {
    protected String symbol;
    protected int point;
    protected String card;

    public Cards(String symbol, String card, int point){
        this.symbol = symbol;
        this.point = point;
        this.card = card;
    }
    public Cards(){
    }

    public Cards[] createDeck(){
        Cards[] cards = new Cards[52];
        String[] carsType= {"♤","♡","♢","♧"};
        int index =0;
        for(int i=0; i<carsType.length;i++) {
            for (int j = 1; j <= 13; j++) {
                if (j == 1) {
                    Cards cards1 = new Cards(carsType[i], "A", 1);
                    cards[index] = cards1;
                    index++;
                }
                else if (j == 2) {
                    Cards cards1 = new Cards(carsType[i], "2", 2);
                    cards[index] = cards1;
                    index++;
                }
                else if (j == 10) {
                    Cards cards1 = new Cards(carsType[i], "10", 3);
                    cards[index] = cards1;
                    index++;
                }
                else if (j == 11) {
                    Cards cards1 = new Cards(carsType[i], "J", 1);
                    cards[index] = cards1;
                    index++;
                }
                else if (j == 12) {
                    Cards cards1 = new Cards(carsType[i], "Q", 1);
                    cards[index] = cards1;
                    index++;
                }
                else if (j == 13) {
                    Cards cards1 = new Cards(carsType[i], "K", 1);
                    cards[index] = cards1;
                    if (i != 3) {
                        index++;
                    }
                }
                else {
                    Cards cards1 = new Cards(carsType[i], Integer.toString(j), 1);
                    cards[index] = cards1;
                    index++;
                }
            }
        }
        return cards;
    }

    public Cards[] shuffle(Cards[] oldCards){
        Random random = new Random();
        int index = oldCards.length;
        int current = 0;
        while (current< oldCards.length/2) {
                int newIndex = random.nextInt(0, index);
                Cards temp = oldCards[index-1];
                oldCards[index-1] = oldCards[newIndex];
                oldCards[newIndex] = temp;
                index--;
                current++;
        }
        return oldCards;
    }


 }
