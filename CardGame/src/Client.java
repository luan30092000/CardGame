
public class Client {
    public static void main(String[] args) {
        Player luan = new Player("Luan", 1000);

        CardDeck deck = new CardDeck(true);
        deck.printDeck();
        luan.addCard(deck.getTop());
        luan.addCard(deck.getTop());
        luan.addCard(deck.getTop());
        luan.printHandCard(false);
        deck.printDeck();
        System.out.println(luan.getHandSum());

    }

}