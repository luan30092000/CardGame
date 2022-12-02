import java.util.ArrayList;
import java.util.Collections;

class DeckOfCard {
    private ArrayList<Card> cardDeck;
    public DeckOfCard() {
        cardDeck = new ArrayList<>(Suit.values().length * Rank.values().length);
        madeNewDeck();
    }

    private void madeNewDeck() {
        for (Rank r : Rank.values()) {
            for (Suit s : Suit.values()) {
                cardDeck.add(new Card(s, r));
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(cardDeck);
    }

    public void resetDeck() {
        cardDeck = new ArrayList<>();
        madeNewDeck();
    }

    public Card getTop() {
        return cardDeck.get(0);
    }

}
