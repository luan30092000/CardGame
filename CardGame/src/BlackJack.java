public class BlackJack {
    private CardDeck cardDeck;
    private Player[] players;
    int numPlayers;
    int tableCoin;
    public BlackJack(int numPlayers) {
        setupGame();
        this.numPlayers = numPlayers;
        players = new Player[numPlayers];
        tableCoin = 0;
    }

    private void setupGame() {
        cardDeck = new CardDeck(true);
    }

}
