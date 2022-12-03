enum TYPE {
    BLACKJACK(2);

    final int handCard;
    TYPE(int i) {
        handCard = i;
    }
    int getHandCard() {
        return handCard;
    }
}
public class Player {
    private int coin;
    private Card[] cards;

    Player() {
        coin = 0;
        cards = new Card[TYPE.BLACKJACK.handCard];
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
}
