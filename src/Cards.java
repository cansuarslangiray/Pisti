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
                if (j == 1) {// check if it is 1 or not because deck have A insteade of 1.
                    Cards cards1 = new Cards(carsType[i], "A", 1);
                    cards[index] = cards1;
                    index++;
                }
                else if (j == 2) { // check if it is 2 or not because if it is 2, card must has 2 point.
                    Cards cards1 = new Cards(carsType[i], "2", 2);
                    cards[index] = cards1;
                    index++;
                }
                else if (j == 10) { // check if it is 10 or not because if it is 10, card must has 10 point.
                    Cards cards1 = new Cards(carsType[i], "10", 3);
                    cards[index] = cards1;
                    index++;
                }
                else if (j == 11) { // check if it is 11 or not because deck have J insteade of 11.
                    Cards cards1 = new Cards(carsType[i], "J", 1);
                    cards[index] = cards1;
                    index++;
                }
                else if (j == 12) { // check if it is 12 or not because deck Q instade of 12.
                    Cards cards1 = new Cards(carsType[i], "Q", 1);
                    cards[index] = cards1;
                    index++;
                }
                else if (j == 13) { // check if it is 13 or not because deck K instade of 13.
                    Cards cards1 = new Cards(carsType[i], "K", 1);
                    cards[index] = cards1;
                    if (i != 3) {
                        index++;
                    }
                }
                else { // not need to check number because all the other number have same point.(1)
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
        while (current< oldCards.length/2) { // diveded by 2 because we change random number and index. index is decreasing.
                int newIndex = random.nextInt(0, index);// pick a random number between index and 0.
                Cards temp = oldCards[index-1];
                oldCards[index-1] = oldCards[newIndex];
                oldCards[newIndex] = temp;
                index--;
                current++;
        }
        return oldCards;
    }

    public Cards[] cut(Cards[] oldCards,int number){
        Cards[] newCards = new Cards[52];
        System.arraycopy(oldCards,number,newCards,0,oldCards.length-number);
        System.arraycopy(oldCards,0,newCards,oldCards.length-number,number);
        return newCards;
    }

    public Cards[] moveCards(Cards[] cards){
        Cards[] newCards= new Cards[4];
        System.arraycopy(cards,0,newCards,0,4);
        return newCards;
    }
    public Cards[] moveCardsFromDeck(Cards[] cards){
        Cards[] newCards = new Cards[cards.length-4];
        System.arraycopy(cards,4,newCards,0,newCards.length);
        return newCards;
    }

    public boolean checkDeck(Cards[] deck){
        return deck.length>0;
    }
 }
