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
        deck = new CardDeck();
        dealer = new Player("Dealer", 1000000);
    }

    public BlackJack() {
        this(1);
    }

    public void runGame(Scanner sc) {

        // Deal cards
        initialDeal();

        String command;

        // Taking bet
        for (Player i : players) {
            System.out.println("Enter betting amount: ");
            i.bet(Integer.parseInt(sc.nextLine()));
        }

        dealer.printHandCard(true);

        // Each player turn if bet
        for (Player i : players) {
            if (i.getBetCoin() != 0) {
                boolean playerEnd = false;
                System.out.println("Hit(H) or Stand(S): ");
                printHand(i, false);
                while (!playerEnd) {
                    command = this.sc.next();
                    if (command.equals(COMMAND.H.toString())) {
                        playerEnd = !dealCard(i);   // add card good, player is not busted yet
                        i.printHandCard(false);
                        if (getHandValue(i) > 21) {
                            dealer.addCoin(i);
                            i.removeCoin();
                        }
                    } else if (command.equals(COMMAND.S.toString())) {
                        playerEnd = true;
                    } else {
                        System.out.println("Wrong command");
                    }
                }
            }
        }

        printHand(dealer, false);
        while(getHandValue(dealer) < 17){
            dealCard(dealer);
            printHand(dealer, false);
        }

        if (getHandValue(dealer) > 21) {
            for (Player i : players) {
                i.addCoin();
                dealer.removeCoin(i);
            }
         } else {
            for (Player i : players) {
                if (i.getBetCoin() > 0) {
                    if (getHandValue(dealer) > getHandValue(i)) {
                        System.out.println("Dealer win");
                        dealerWon(i);
                    } else if (getHandValue(dealer) < getHandValue(i)) {
                        System.out.println(i.getName() + " win");
                        dealerLost(i);
                    }
                }
            }
        }

        for (Player i : players) {
            System.out.println("Player " + i.getName() + i.getCoin());
        }
        System.out.println("dealer: " + dealer.getCoin());
    }

    private void printHand(Player player, boolean printHalf) {
        player.printHandCard(printHalf);
    }

    private void printHand() {
        printHand(this.dealer, true);
    }

    private void resetPlayer(Player player) {
        player.resetPlayerCard();
    }

    private void initialDeal() {
        dealer.addCard(deck.getTop());
        for (Player i : players) {
            if (i.getBetCoin() > 0) {
                i.addCard(deck.getTop());
            }
        }
        dealer.addCard(deck.getTop());
        for (Player i : players) {
            if (i.getBetCoin() > 0) {
                i.addCard(deck.getTop());
            }
        }
        System.out.println("Cards have been dealt");
    }

    private boolean dealCard(Player player) {
        return player.addCard(deck.getTop());
    }

    private int getHandValue(Player player) {
        return player.getHandSum();
    }

    private void dealerWon(Player p1) {
        this.dealer.addCoin(p1);
        p1.removeCoin();
    }

    private void dealerLost(Player p1) {
        this.dealer.removeCoin(p1);
        p1.addCoin();
    }
}