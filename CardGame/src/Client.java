
public class Client {
    public static void main(String[] args) {
        CardDeck deck = new CardDeck();
//        Card top = deck.getTop();
        deck.shuffle();
        deck.printDeck();
    }

}