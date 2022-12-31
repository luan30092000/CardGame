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

    private int betCoin;


    Player(String name, int coin) {
        this.name = name;
        this.coin = coin;
        this.cards = new Card[gameNumCard];
        this.currentCardNumber = 0;
    }

    Player(String name) {
        this(name, 100);
    }

    public void resetPlayerCard() {
        Arrays.fill(this.cards, null);
        this.currentCardNumber = 0;
        this.currentHandSum = 0;
        betCoin = 0;
    }

    public void bet(int amount) {
        if (amount > this.coin) {
            this.betCoin = this.coin;
        } else {
            this.betCoin = amount;
        }
    }

    /**
     * Method for player when player lost
     */
    public void removeCoin() {
        this.coin -= betCoin;
        betCoin = 0;
    }

    /**
     * Method for dealer
     * @param player player that dealer have to pay for
     */
    public void removeCoin(Player player) {
        this.coin -= player.getBetCoin();
    }

    /**
     * Method for player, add whatever player bet
     */
    public void addCoin() {
        this.coin += betCoin;
        betCoin = 0;
    }

    /**
     * Dealer coin will be added for player coin
     * @param player player that lost
     */
    public void addCoin(Player player) {
        this.coin += player.getBetCoin();
    }


    public boolean addCard(Card aCard) {
        cards[currentCardNumber] = aCard;
        currentCardNumber++;
        currentHandSum += aCard.getRankValue();
        if (currentHandSum > 21) {
            return false;
        }
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

    public int getBetCoin() {
        return this.betCoin;
    }

    public int getCoin() {
        return this.coin;
    }

    public void printStatus() {
        System.out.printf("Player %s: %d ",this.name,this.coin);
    }

}
