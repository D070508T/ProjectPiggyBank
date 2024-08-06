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

        //CONTINUE METHOD
    }

    public static void catalogue(double cash, Scanner scanner) {
        System.out.println("Your current balance: $" + cash);
        System.out.print("""
                
                   ---<<< CATALOGUE >>>---
                
                -------------------------------------------------------
                <1>     Spending Piggy Bank ($50)
                
                Base cost: $5 * capacity
                
                Added cost: $10 per ability
                
                Abilities:
                  Gain interest
                  Withdraw
                  Invest
                
                Description:
                  This Piggy Bank is fully customizable. You can
                  choose exactly how you want it and price it
                  accordingly
                -------------------------------------------------------
                <2>     Savings Piggy Bank ($100)
                
                Cost: $50
                
                Capacity: 30
                
                Description:
                  This piggy bank can withdraw coins to
                  keep in your wallet.
                -------------------------------------------------------
                <3>     Investing Piggy Bank ($70)
                
                Cost: $200
                
                Capacity: 100
                
                Description:
                  This piggy bank can gain 3% interest
                -------------------------------------------------------
                <4>     Custom Piggy Bank ($Custom pricing)
                
                Cost: $70
                
                Capacity: 55
                
                Description:
                  This piggy bank can invest
                  with a 50/50 chance of either
                  doubling or halving your balance
                -------------------------------------------------------
                Enter a number to purchase
                
                Or
                
                Enter anything else to go back
                
                 >>>\s""");

        switch (scanner.nextLine()) {
            case "1": {

            }

            case "2": {

            }

            case "3": {

            }

            case "4": {

            }

            default: {

            }
        }
    }
}