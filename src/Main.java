import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static double cash = 1500;
    static ArrayList<PiggyBank> userBanks = new ArrayList<>();

    public static void main(String[] args) {
        main_menu();
    }

    public static void main_menu() {
        while (true) {
            System.out.println(
                    "     -----<<< MAIN MENU >>>-----\n\n" +
                            "Cash: $" + cash + "\n"
            );

            int amount = userBanks.size();

            if (amount == 0) {
                System.out.print("You do not own a Piggy Bank. Here is the catalogue of Piggy Banks");
                catalogue();
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
                        case "1" -> {
                            displayBanks();
                            valid = true;
                        }
                        case "2" -> {
                            catalogue();
                            valid = true;
                        }
                        case "3" -> {
                            System.out.println("Goodbye.");
                            System.exit(0);
                        }
                        default -> {
                            System.out.print("INVALID RESPONSE\n\n >>> ");
                            valid = false;
                        }
                    }
                } while (!valid);
            }
        }
    }

    public static void displayBanks() {
        System.out.println("""
                Choose a Piggy Bank to see its full information and be able to use it
                
                Enter anything else to go back.
                """);

        PiggyBank b = chooseBank();

        if (b == null) {
            return;
        }

        useBank(b);
    }

    public static PiggyBank chooseBank() {
        for (int i = 1; i <= userBanks.size(); i++) {
            System.out.println("<"+i+">" + userBanks.get(i-1).getName());
        }

        System.out.print("Choose a bank: ");

        String input = scanner.nextLine();

        if (notDigit(input, true) || Integer.parseInt(input) < 1 && Integer.parseInt(input) > userBanks.size()) {
            return null;
        }

        return userBanks.get(Integer.parseInt(input) - 1);
    }

    public static boolean notDigit(String input, boolean integer) {
        boolean isPeriod = false;
        int l = input.length();
        for (int i = 0; i < l; i++) {
            int c = (input.charAt(i));
            if (c < 48 || c > 57) {
                if (integer) {
                    return true;
                }

                if (c != 46) {
                    return true;
                }

                if (isPeriod) {
                    return true;
                }

                isPeriod = true;
            }
        }

        return false;
    }

    public static void useBank(PiggyBank bank) {
        System.out.println(bank + "\n\n");

        System.out.println("< T >     Transfer");
        System.out.println("< D >     Deposit coins");

        if (bank.canWithdraw) {
            System.out.println("< W >     Withdraw");
        }

        if (bank.canGainInterest) {
            System.out.println("< G >     Gain 3% Interest");
        }

        if (bank.canInvest) {
            System.out.println("< I >     Invest");
        }

        if (bank instanceof Custom) {
            System.out.println("< U >     Upgrade");
        }

        System.out.print(" >>> ");

        String input;
        boolean valid = true;

        do {
            input = scanner.nextLine();

            if (input.equalsIgnoreCase("W") && bank.canWithdraw()) {
                withdraw(bank);
            } else if (input.equalsIgnoreCase("G") && bank.canGainInterest()) {
                bank.collectInterest();
            } else if (input.equalsIgnoreCase("I") && bank.canInvest()) {
                bank.invest();
            } else if (input.equalsIgnoreCase("T")) {
                transfer(bank);
            } else if (input.equalsIgnoreCase("D")) {
                deposit(bank);
            } else if (input.equalsIgnoreCase("U") && bank instanceof Custom) {
                upgrade(bank);
            } else {
                System.out.print("INVALID\n\n >>> ");
                valid = false;
            }
        } while (!valid);
    }

    public static void upgrade(PiggyBank bank) {
        if (cash < 20) {
            System.out.println("You do not have enough cash to upgrade your piggy bank.");
            return;
        }

        System.out.println("For $20, would you like to upgrade to (Enter anything to go back):");

        if (!bank.canWithdraw()) {
            System.out.println("< W >     Withdraw function");
        }

        if (!bank.canInvest()) {
            System.out.println("< I >     Invest function");
        }

        if (!bank.canGainInterest()) {
            System.out.println("< G >     Gaining interest function");
        }

        System.out.print(" >>> ");

        String[][] arr = {
                {"W", "Withdraw"},
                {"I", "Invest"},
                {"G", "Interest"}
        };

        String input = scanner.nextLine();

        for (int i = 0; i < 3; i++) {
            if (input.equalsIgnoreCase(arr[i][0])) {
                ((Custom) bank).upgradeAbility(arr[i][1]);
                System.out.println("Successfully upgraded.");
            }
        }
    }

    public static void deposit(PiggyBank bank) {
        System.out.print("""
                        Would you rather add coins (enter anything else to go back):
                        
                        <1>   By amount
                        <2>   By individual coins
                        
                         >>>\s""");

        String choice = scanner.nextLine();

        if (choice.equalsIgnoreCase("1")) {
            depositAmount(bank);
        } else if (choice.equalsIgnoreCase("2")) {
            depositCoin(bank);
        }
    }

    public static void depositAmount(PiggyBank bank) {
        System.out.print("Enter amount (enter anything else to go back): ");

        String input = scanner.nextLine();

        if (notDigit(input, false)) {
            return;
        }

        double amount = Double.parseDouble(input);

        int[] newCoins = bank.changeCoins(amount, bank.newCoins(), true, true);

        if (newCoins[0] == -1) {
            System.out.println("Deposit failed.");
        } else {
            System.out.println("Successful deposit");
            bank.setCoins(newCoins);
            cash -= amount;
        }
    }

    public static void depositCoin(PiggyBank bank) {
        System.out.print("""
                    Choose a type of coin (Enter anything else to go back):
                    <1>   Nickel
                    <2>   Dime
                    <3>   Quarter
                    <4>   Loonie
                    <5>   Toonie
                    
                     >>>\s""");

        String c = scanner.nextLine();

        if (c.length() != 1) {
            return;
        }

        int n = c.charAt(0);

        if (n < 49 || n > 53) {
            return;
        }

        int space = bank.spaceLeft();

        System.out.print("Enter an amount (you have " + space + " space left): ");

        String amount;

        while (true) {
            amount = scanner.nextLine();

            if (!notDigit(amount, true) && Integer.parseInt(amount) <= space) {
                break;
            }

            System.out.print("INVALID\n\n >>> ");
        }

        int coin = Integer.parseInt(c) - 1;

        bank.addCoin(coin, Integer.parseInt(amount));
        cash -= Double.parseDouble(amount) * PiggyBank.coinValues[coin];
    }

    public static void transfer(PiggyBank bank) {
        System.out.print("Choose a bank to transfer coins to\n\nEnter anything else to go back\n");

        PiggyBank b = chooseBank();

        if (b == null) {
            return;
        }

        System.out.print("Enter an amount\n\nEnter anything else to go back\n\n >>> ");

        String amount = scanner.nextLine();

        if (notDigit(amount, false)) {
            return;
        }

        bank.transferTo(b, Integer.parseInt(amount));
    }

    public static void withdraw(PiggyBank bank) {
        System.out.print("Enter an amount of money: ");
        String input;

        while (true) {
            input = scanner.nextLine();

            if (!notDigit(input, false)) {
                break;
            }

            System.out.print("INVALID\n\n >>> ");
        }

        double amount = Double.parseDouble(input);

        if (bank.withdrawCoins(amount)) {
            cash += amount;
        }
    }

    public static void catalogue() {
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
                    bank = new PiggyBank("Spending");
                    break;
                }
                case "2": {
                    bank = new PiggyBank("Savings");
                    break;
                }
                case "3": {
                    bank = new PiggyBank("Investing");
                    break;
                }
                case "4": {
                    System.out.print("Enter a capacity ($5 per coin): ");

                    boolean valid;
                    String input;

                    do {
                        input = scanner.nextLine();
                        valid = true;

                        for (int i = 0; i < input.length(); i++) {
                            int num = input.charAt(i);
                            if (num < 48 || num > 57) {  // If not a digit
                                valid = false;
                                System.out.print("INVALID\n\n >>> ");
                                break;
                            }
                        }
                    } while (!valid);

                    int capacity = Integer.parseInt(input);

                    System.out.print("($10 now, $20 later) Would you like to be able to withdraw (y/n):");
                    boolean withdraw = yes_no();
                    System.out.print("($10 now, $20 later) Would you like to be able to collect interest (y/n):");
                    boolean interest = yes_no();
                    System.out.print("($10 now, $20 later) Would you like to be able to invest (y/n):");
                    boolean invest = yes_no();

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
            userBanks.add(bank);
            cash -= bank.getCost();
            System.out.println("Successfully purchased " + bank.getName());
        } else {
            System.out.println("You do not have enough money to purchase this bank.");
        }
    }

    public static boolean yes_no() {
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