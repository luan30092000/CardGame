import java.util.Scanner;
enum COMMAND {
    H,
    S,
    I
}

public class BlackJack {

    Player dealer;
    CardDeck deck;
    Player[] players;
    int numPlayer;
    Scanner sc;

    public BlackJack(int numPlayers) {
        this.numPlayer = numPlayers;
        sc = new Scanner(System.in);
        players = new Player[this.numPlayer];
        System.out.println("Enter players' name: ");
        for (int i = 0; i < this.numPlayer; i++) {
            players[i] = new Player(sc.nextLine());
        }
    }

    public BlackJack() {
        this(1);
    }

    public void runGame() {

        // Deal cards
        initialDeal();

        dealer.printHandCard(false);

        String command;
        boolean dealerEnd = false;
        for (Player i : players) {
            boolean playerEnd = false;
            while (!playerEnd) {
                printHandValue(i);
                System.out.println("Hit(H) or Stand(S): ");
                command = sc.next();
                if (command.equals(COMMAND.H.toString())) {
                    playerEnd = !dealCard(i);   // add card good, player is not busted yet
                    i.printHandCard(false);
                } else if (command.equals(COMMAND.S.toString())) {
                    playerEnd = true;
                } else {
                    System.out.println("Wrong command");
                }
                if (i.getHandSum() > 21) {
                    break;
                }
            }
        }
        for (Player i : players) {
            while (!dealerEnd && i.getHandSum() <= 21) {
                printHandValue(dealer);
                while (dealerHandLow(i)) {
                    dealCard(dealer);
                    printHandValue(dealer);
                }
                dealerEnd = true;
            }
            if (i.getHandSum() > 21) {
                System.out.println("Dealer wins");
            } else {
                if (!dealerHandLow(i) && dealer.getHandSum() <= 21) {
                    System.out.println("Dealer wins");
                } else {
                    System.out.println("Player wins");
                }
            }
        }
        resetPlayer(dealer);
        resetPlayer(players);
    }

    private static void printHandValue(Player player) {
        System.out.printf("%s's hand value: %d\n", player.getName(), player.getHandSum());
    }

    private boolean dealerHandLow(Player player) {
        return this.dealer.getHandSum() < player.getHandSum();
    }

    private void printHand(Player player, boolean printHalf) {
        player.printHandCard(printHalf);
    }

    private void resetPlayer(Player player) {
        player.resetPlayerCard();
    }

    private void initialDeal() {
        dealer.addCard(deck.getTop());
        for (Player i : players) {i.addCard(deck.getTop());}
        dealer.addCard(deck.getTop());
        for (Player i : players) {i.addCard(deck.getTop());}
        System.out.println("Cards have been dealt");
    }

    private boolean dealCard(Player player) {
        return player.addCard(deck.getTop());
    }

    private void openCard() {
        for (Player i : players) {
            i.printHandCard(false);
        }
    }
}