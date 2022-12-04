import java.util.Arrays;

enum TYPE {
    BLACKJACK(10);
    final int maxHandCard;
    TYPE(int i) {
        maxHandCard = i;
    }
    int getMaxHandCard() {
        return maxHandCard;
    }
}

public class Player {

    /**
     * Player's money
     */
    private int coin;

    /**
     * Number of maximum cards that player can have
     */
    private final Card[] cards;

    /**
     * Player's name
     */
    private String name;

    /**
     * number of cards are currently in player hand
     */
    private int currentCardNumber;

    /**
     * Type of game card
     */
    private int gameNumCard = TYPE.BLACKJACK.getMaxHandCard();

    /**
     * current hand value of player
     */
    private int currentHandSum;

    Player(String name, int coin) {
        this.name = name;
        this.coin = coin;
        this.cards = new Card[gameNumCard];
        this.currentCardNumber = 0;
    }

    public int bet(int amount) {
        if (amount > coin) {
            // No bet implementation  //todo Implement Error
            this.coin = 0;
            return 0;
        } else {
            coin = coin - amount;
            return amount;
        }
    }

    public Card[] fold() {
        return cards;
    }

    public int getCoin() {
        return coin;
    }

    public void resetPlayerCard() {
        Arrays.fill(this.cards, null);
        this.currentCardNumber = 0;
        this.currentHandSum = 0;
    }

    public boolean addCard(Card aCard) {
        if (currentHandSum > 21) {
            return false;
        }
        cards[currentCardNumber] = aCard;
        currentCardNumber++;
        currentHandSum += aCard.getRankValue();
        return true;
    }

    public int getHandSum() {
        return currentHandSum;
    }

    public void printHandCard(boolean firstCardOnly) {
        System.out.println("Name: " + this.name);
        if (firstCardOnly) {
            cards[0].printCard();
            System.out.println("Hidden");
        } else {
            for (int i = 0; i < currentCardNumber; i++) {
                cards[i].printCard();
            }
        }
    }

    public String getName() {
        return this.name;
    }
}
