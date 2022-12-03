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
    String name;

    /**
     * number of cards are currently in player hand
     */
    int currentCardNumber;

    /**
     * Type of game card
     */
    int gameNumCard = TYPE.BLACKJACK.getMaxHandCard();


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
        Arrays.fill(cards, null);
        this.currentCardNumber = 0;
    }

    public boolean addCard(Card aCard) {
        if (currentCardNumber == 10) {
            return false;
        }
        cards[currentCardNumber] = aCard;
        currentCardNumber++;
        return true;
    }

    public int getHandSum() {
        int sum = 0;
        int numAce = 0;
        for (int i = 0; i < currentCardNumber; i++) {
            if (cards[i].getRank() == Rank.ACE) {
                numAce += 1;
                sum += 11;
            } else {
                sum += cards[i].getRankValue();
            }
        }
        for (int i = 0; i < numAce; i++) {
            if (sum > 21) {
                sum -= 10;
            }
        }
        return sum;
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
}
