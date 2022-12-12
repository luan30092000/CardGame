import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BlackJack game = new BlackJack(5);
        for (int i = 0; i < 10; i++) {
            System.out.println("====================");
            game.runGame(sc);
        }


    }
}
