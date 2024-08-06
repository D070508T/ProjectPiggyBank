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

        PiggyBank bank = null;
        boolean validInput;

        do {
            validInput = true;
            String userInput = scanner.nextLine();

            switch (userInput) {
                case "1": {
                    bank = new Spending();
                    break;
                }
                case "2": {
                    bank = new Savings();
                    break;
                }
                case "3": {
                    bank = new Investing();
                    break;
                }
                case "4": {
                    System.out.print("Enter a capacity ($5 per coin): ");

                    boolean valid = true;
                    String input;

                    do {
                        input = scanner.nextLine();
                        valid = true;  // Assume valid input initially

                        // Validate input
                        for (int i = 0; i < input.length(); i++) {
                            int num = (int) input.charAt(i);
                            if (num < 48 || num > 57) {  // If not a digit
                                valid = false;
                                System.out.print("INVALID\n\n >>> ");
                                break;
                            }
                        }
                    } while (!valid);

                    int capacity = Integer.parseInt(input);

                    System.out.print("($10 now, $20 later) Would you like to be able to withdraw (y/n):");
                    boolean withdraw = yes_no(scanner);
                    System.out.print("($10 now, $20 later) Would you like to be able to collect interest (y/n):");
                    boolean interest = yes_no(scanner);
                    System.out.print("($10 now, $20 later) Would you like to be able to invest (y/n):");
                    boolean invest = yes_no(scanner);

                    System.out.print("Enter a name for the bank: ");
                    String name = scanner.nextLine();

                    bank = new Custom(name, capacity, withdraw, interest, invest);
                    break;
                }
                default: {
                    validInput = false;
                    System.out.print("INVALID\n\n >>> ");
                    break;
                }
            }
        } while (!validInput);

        if (cash >= bank.getCost()) {
            // Code to execute if bank is not null and cash is sufficient
        }
    }

    public static boolean yes_no(Scanner scanner) {
        String input;

        while (true) {
            input = scanner.nextLine();

            if (input.equalsIgnoreCase("y")) {
                return true;
            } else if (input.equalsIgnoreCase("n")) {
                return false;
            }

            System.out.print("INVALID\n\n >>> ");
        }
    }
}