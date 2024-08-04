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
            System.out.print("""
                    <1>          Access Piggy Banks
                    <2>              Open Catalogue
                    <3>                        Quit
                    
                     >>>\s""");

            boolean valid = true;

            do {
                String input = scanner.nextLine();

                switch (input) {
                    case "1" -> displayBanks();
                    case "2" -> catalogue(cash, scanner);
                    case "3" -> {
                        System.out.println("Goodbye.");
                        System.exit(0);
                    }
                    default -> {
                        System.out.println("INVALID RESPONSE\n\n >>> ");
                        valid = false;
                    }
                }
            } while (!valid);
        }
    }

    public static void displayBanks() {

    }

    public static void catalogue(double cash, Scanner scanner) {

    }
}