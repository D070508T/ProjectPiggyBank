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
                    <1>        See Your Piggy Banks
                    <2>                  Open Store
                    <3>                        Quit
                    
                     >>>\s""");

            boolean valid = true;

            do {
                String input = scanner.nextLine();

                switch (input) {
                    case "1" -> displayBanks(userBanks);
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

    public static void displayBanks(ArrayList<PiggyBank> userBanks) {
        System.out.println("Choose a Piggy Bank to see its full information and be able to use it");
        for (int i = 0; i < userBanks.size(); i++) {
            System.out.println("<"+i+">" + userBanks.get(i).getName());
        }
    }

    public static void catalogue(double cash, Scanner scanner) {
        System.out.println("Your current balance: $" + cash);
        System.out.print("""
                
                   ---<<< CATALOGUE >>>---
                
                
                <1>     Spending Piggy Bank ($50)
                
                <2>     Savings Piggy Bank ($100)
                
                <3>     Investing Piggy Bank ($70)
                
                <4>     Custom Piggy Bank ($Custom pricing)
                
                
                Enter 'PURCHASE 1/2/3/4' to purchase a Piggy Bank
                
                Enter 'SEE 1/2/3/4' to see full Piggy Bank information
                
                 >>>\s""");

        boolean valid = true;

        String input = scanner.nextLine();

        if (input.length() == 10 && input.startsWith("PURCHASE ")) {

        } else if (input.length() == 5 && input.startsWith("SEE ")) {

        } else {
            valid = false;
        }
    }
}