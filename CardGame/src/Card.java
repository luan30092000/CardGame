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
    TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10), JACK(11), QUEEN(12), KING(13), ACE(14);
    final int value;
    Rank (int p) {
        this.value = p;
    }

    int getValue() {
        return value;
    }
}

class Card {
    Suit suit;
    Rank rank;

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
        System.out.println("SUIT: " + suit.toString() + " | " + "RANK: " + rank.toString());
    }
}
