import java.util.Objects;
import java.util.Scanner;
enum COMMAND {
    H,
    S,
    I
}

public class GamePlay {

    Player dealer;
    CardDeck deck;

    public GamePlay() {
        dealer = new Player("Dealer", 10000);
        deck = new CardDeck();
    }

    public void runGame() {

        // Init system
        Scanner sc = new Scanner(System.in);

        // Init player
        Player luan = new Player("Luan", 100);

        // Deal cards
        dealer.addCard(deck.getTop());
        luan.addCard(deck.getTop());
        dealer.addCard(deck.getTop());
        luan.addCard(deck.getTop());

        System.out.println("Cards are dealt");
        dealer.printHandCard(true);
        luan.printHandCard(false);

        String command;
        boolean playerEnd = false;
        boolean dealerEnd = false;
        while (!playerEnd) {
            printHandValue(luan);
            System.out.println("Hit(H) or Stand(S): ");
            command = sc.next();
            if (command.equals(COMMAND.H.toString())) {
                playerEnd = !luan.addCard(deck.getTop());   // add card good, player is not busted yet
                luan.printHandCard(false);
            } else if (command.equals(COMMAND.S.toString())) {
                playerEnd = true;
            }
        }
        while (!dealerEnd && luan.getHandSum() <= 21) {
            while (lowDealerHand(luan)) {
                printHandValue(dealer);
                dealerEnd = !dealCard(dealer);
            }
        }
        if (luan.getHandSum() > 21) {
            System.out.println("Dealer wins");
        } else {
            if (!lowDealerHand(luan)){
                System.out.println("Dealer wins");
            } else {
                System.out.println("Player wins");
            }
        }
    }

    private static void printHandValue(Player player) {
        System.out.printf("%s's hand value: %d\n", player.getName(), player.getHandSum());
    }

    private boolean lowDealerHand(Player player) {
        return this.dealer.getHandSum() < player.getHandSum();
    }

    private boolean dealCard(Player player) {
        return player.addCard(deck.getTop());
    }
}