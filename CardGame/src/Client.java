
public class Client {
    public static void main(String[] args) {
        DeckOfCard deck = new DeckOfCard();
        Card top = deck.getTop();
        top.printCard();
        deck.shuffle();
        deck.getTop().printCard();
    }

}