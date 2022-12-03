import java.sql.SQLOutput;

enum Suit {
    CLUBS(1), DIAMONDS(2), SPADES(3), HEARTS(4);
    final int value;
    Suit(int i) {
        value = i;
    }

    int getValue() {
        return value;
    }
}

enum Rank {
    TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10), JACK(10), QUEEN(10), KING(10), ACE(1);
    final int value;
    Rank (int p) {
        this.value = p;
    }

    int getValue() {
        return value;
    }
}

class Card {
    private Suit suit;
    private Rank rank;

    Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public Suit getSuit() {
        return suit;
    }

    public Rank getRank() {
        return rank;
    }

    public int getRankValue() {
        return rank.getValue();
    }

    public int getSuitValue() {
        return suit.getValue();
    }

    public void printCard() {
        System.out.printf("%d of %s\n", rank.getValue(), suit.toString());
    }
}
