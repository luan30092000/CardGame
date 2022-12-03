import java.util.ArrayList;
import java.util.Collections;

class CardDeck {
    private ArrayList<Card> cardDeck;
    int numCard;
    public CardDeck() {
        numCard = Suit.values().length * Rank.values().length;
        cardDeck = new ArrayList<>(numCard);

        reset();
    }

    private void reset() {
        for (Suit s : Suit.values()) {
            for (Rank r : Rank.values()) {
                cardDeck.add(new Card(s, r));
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(cardDeck);
    }

    public void resetDeck() {
        cardDeck = new ArrayList<>();
        reset();
    }

    public Card getTop() {
        numCard--;
        return cardDeck.remove(0);
    }

    public void printDeck() {
        for (int i = 0; i < numCard; i++) {
            System.out.printf("%2d/%2d: ", i + 1, numCard);
            cardDeck.get(i).printCard();
        }
    }
}
