public  class Game {
    Cards cards = new Cards();
    Cards[] deck = cards.createDeck() ;
    /*public static void main(String[] args) {
        Cards cards = new Cards();
        Cards[] deck = cards.createDeck() ;
        for(int i = 0; i< deck.length;i++){
            System.out.println(deck[i].symbol + deck[i].card);
        }
        System.out.println("---------------------------");
         deck = cards.shuffle(deck);
        for(int i = 0; i< deck.length;i++){
            System.out.println(deck[i].symbol + deck[i].card);
        }
        deck = cards.cut(deck);
        System.out.println("---------------------------");
        for(int i = 0; i< deck.length;i++){
            System.out.println(deck[i].symbol + deck[i].card);
        }
    }*/

    public void play(){
        Cards[] board;
        Cards[] computer;
        Cards[] player;
        deck = cards.shuffle(deck);
        deck = cards.cut(deck);



    }

    public void beginningParts(){
        System.out.println("the dealer are shuffling the cards now ");
        System.out.println(" and now, you can cut the deck. ");

    }
}
