import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        double cash = 0;
        ArrayList<PiggyBank> userBanks = new ArrayList<>();
        main_menu(userBanks, cash, new Scanner(System.in));
    }

    public static void main_menu(ArrayList<PiggyBank> userBanks, double cash, Scanner scanner) {
        System.out.println(
                "     -----<<< MAIN MENU >>>-----\n\n" +
                        "Cash: $" + cash + "\n"
        );

        int amount = userBanks.size();

        if (amount == 0) {
            System.out.print("You do not own a Piggy Bank. Here is the catalogue of Piggy Banks");
            catalogue(cash, scanner);
        } else {
            System.out.println("""
                    <1>          Access Piggy Banks
                    <2>              Open Catalogue
                    <3>                        Quit
                    """);

            switch (scanner.nextLine()) {
                case "1":
                    displayBanks();
                    break;
                case "2":
                    catalogue(cash, scanner);
                    break;
                case "3":
                    System.out.println("Goodbye");
                    System.exit(0);
            }
        }
    }

    public static void displayBanks() {

    }

    public static void catalogue(double cash, Scanner scanner) {

    }
}