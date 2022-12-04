import java.util.Scanner;
enum COMMAND {
    H,
    S,
    I
}

public class BlackJack {

    Player dealer;
    CardDeck deck;
    Player player1;

    public BlackJack() {
        dealer = new Player("Dealer", 10000);
        deck = new CardDeck();
        player1 = new Player("Luan", 100);
    }

    public void runGame(Scanner sc) {

        // Deal cards
        dealer.addCard(deck.getTop());

        player1.addCard(deck.getTop());
        dealer.addCard(deck.getTop());
        player1.addCard(deck.getTop());

        System.out.println("Cards are dealt");
        dealer.printHandCard(false);
        player1.printHandCard(false);

        String command;
        boolean playerEnd = false;
        boolean dealerEnd = false;
        while (!playerEnd) {
            printHandValue(player1);
            System.out.println("Hit(H) or Stand(S): ");
            command = sc.next();
            if (command.equals(COMMAND.H.toString())) {
                playerEnd = !dealCard(player1);   // add card good, player is not busted yet
                player1.printHandCard(false);
            } else if (command.equals(COMMAND.S.toString())) {
                playerEnd = true;
            } else {
                System.out.println("Wrong command");
            }
            if (player1.getHandSum() > 21) {
                break;
            }
        }
        while (!dealerEnd && player1.getHandSum() <= 21) {
            printHandValue(dealer);
            while (dealerHandLow(player1)) {
                dealCard(dealer);
                printHandValue(dealer);
            }
            dealerEnd = true;
        }
        if (player1.getHandSum() > 21) {
            System.out.println("Dealer wins");
        } else {
            if (!dealerHandLow(player1) && dealer.getHandSum() <= 21){
                System.out.println("Dealer wins");
            } else {
                System.out.println("Player wins");
            }
        }
        resetPlayer(dealer);
        resetPlayer(player1);
    }

    private static void printHandValue(Player player) {
        System.out.printf("%s's hand value: %d\n", player.getName(), player.getHandSum());
    }

    private boolean dealerHandLow(Player player) {
        return this.dealer.getHandSum() < player.getHandSum();
    }

    private boolean dealCard(Player player) {
        return player.addCard(deck.getTop());
    }

    private void printHand(Player player) {
        player.printHandCard(false);
    }

    private void resetPlayer(Player player) {
        player.resetPlayerCard();
    }
}