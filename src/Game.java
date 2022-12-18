public  class Game {
    public static void main(String[] args) {
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
    }
}
