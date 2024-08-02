/*
PIGGY BANK PROJECT
David Tarandach Tabatschnic
*/

import java.util.Random;
import java.util.Scanner;

public class PiggyBank {
    //Instance variables
    private int cost;
    private int sellCost;
    private int volume;
    private int capacity;
    private double money;
    private int[] coins;
    private int amountOfCoins;
    private String name;
    private String tag;
    private boolean canGainInterest;
    private boolean canWithdraw;
    private boolean canInvest;

    //Class variables
    public static double[] coinValues = {0.05, 0.10, 0.25, 1, 2};
    public static double bankBalance = 3500;

    //Constructors
    public PiggyBank() {
        tag = "";
        name = "";
        volume = 0;
        money = 0;
        coins = new int[5];
        amountOfCoins = 0;
        canGainInterest = false;
        canWithdraw = false;
        canInvest = false;
        capacity = 0;
        cost = 0;
        sellCost = 0;
    }

    public PiggyBank(String _tag) {
        tag = _tag;
        if (tag.equals("Savings")) {
            cost = 50;
            volume = 8;
            canGainInterest = true;
            canWithdraw = false;
            canInvest = false;
        } else if (tag.equals("Spending")) {
            cost = 10;
            volume = 4;
            canGainInterest = false;
            canWithdraw = true;
            canInvest = false;
        } else if (tag.equals("Investment")) {
            cost = 30;
            volume = 6;
            canGainInterest = false;
            canWithdraw = false;
            canInvest = true;
        }

        tag = _tag;
        name = tag + " Piggy Bank";
        money = 0;
        coins = new int[5];
        amountOfCoins = 0;
        sellCost = (int) (0.8 * cost);
        capacity = (int) (volume / 0.08);
        bankBalance -= cost;
    }

    public PiggyBank(String _name, int _volume, boolean _canGainInterest, boolean _canWithdraw, boolean _canInvest) {
        tag = "Custom";
        name = _name;
        volume = _volume;
        money = 0;
        coins = new int[5];
        amountOfCoins = 0;
        canGainInterest = _canGainInterest;
        canWithdraw = _canWithdraw;
        canInvest = _canInvest;
        capacity = (int) (volume / 0.08);
        cost = 10 * volume;
        if (canGainInterest) {cost += 10;}
        if (canWithdraw) {cost += 10;}
        if (canInvest) {cost += 10;}
        sellCost = (int)(0.8*cost);
        bankBalance -= cost;
    }

    // Getters and setters
    public int getCost() {
        return cost;
    }

    public void setCost(int Cost) {
        cost = Cost;
        sellCost = (int)(0.8*cost);
    }

    public int getSellCost() {
        return sellCost;
    }

    public void setVolume(int Volume) {
        volume = Volume;
        capacity = (int) (volume / 0.08);
    }

    public int getVolume() {
        return volume;
    }

    public int getCapacity() {
        return capacity;
    }

    public double getMoney() {
        return money;
    }

    public int[] getCoins() {
        return coins;
    }

    public void setName(String Name) {
        name = Name;
    }

    public String getName() {
        return name;
    }

    public String getTag() {
        return tag;
    }

    public boolean canGainInterest() {
        return canGainInterest;
    }

    public boolean canWithdraw() {
        return canWithdraw;
    }

    public boolean canInvest() {
        return canInvest;
    }

    public int spaceLeft() {
        return capacity - amountOfCoins;
    }

    public static double getBalance() {
        return bankBalance;
    }

    //pre: takes in a double, amount, and a PiggyBank, goTO
    //post: returns nothing
    //This method transfers money from one piggy bank to the other
    public void transferMoney(double amount, PiggyBank goTo) {
        double oldMoney = goTo.getMoney();
        changeCoinsByAmount(amount, false, false, false);
        goTo.changeCoinsByAmount(amount, true, false, false);
        if (goTo.getMoney() == oldMoney && amount != 0) {
            changeCoinsByAmount(amount, true, false, false);
        }
    }

    //pre: doesn't take in anything
    //post: doesn't return anything
    //This method goes through all the coins to update the money
    public void updateMoney() {
        money = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < coins[i]; j++) {
                money += coinValues[i];
            }
        }
    }

    //pre: takes in an integer, amount, and an integer, type
    //post: doesn't return anything
    //This method adds coins of the type and amount chosen by the user
    public void addCoins(int amount, int type) {
        double value = 0;

        value = coinValues[type-1];

        if (bankBalance < value) {
            System.out.println("You do not have enough money.");
        } else if (spaceLeft() < 1) {
            System.out.println("You do not have enough space in this piggy bank.");
        } else {
            coins[type - 1] += amount;
            amountOfCoins += amount;
            updateMoney();
            bankBalance-= value * amount;
        }
    }

    //pre: takes in a double, percent
    //post: doesn't return anything
    //This method allows the piggy bank to gain interest
    public void collectInterest(int percent) {
        changeCoinsByAmount(0.1*percent*money, true, true, false);
    }

    //pre: doesn't take in anything
    //post: doesn't return anything
    //This method allows the piggy bank to invest 30% of their money for a 50/50 chance of profit
    public void invest() {
        Random random = new Random();

        double amountOfMoney = 0.3*money;

        int randInt = random.nextInt(1, 3);

        if (randInt == 1) {
            changeCoinsByAmount(amountOfMoney, true, true, false);
        } else {
            changeCoinsByAmount(amountOfMoney, false, false, false);
        }
    }

    //pre: takes in a double, amount, and a boolean, add
    //post: doesn't return anything
    //This method changes (adds or removes) coins from the piggy bank by amount
    public void changeCoinsByAmount(double amount, boolean add, boolean ask, boolean changeBalance) {
        if ((add && bankBalance >= amount) || (!add && money >= amount)) {
            double currentAmount = 0;
            int oldAmountOfCoins = amountOfCoins;
            int[] newCoins = new int[5];
            for (int i = 0; i < 5; i++) {
                newCoins[i] = coins[i];
            }

            for (int i = 4; i >= 0 && currentAmount < amount; i--) {
                for (int j = 0; currentAmount < amount && j >= 0; j++) {
                    if (currentAmount + coinValues[i] <= amount) {
                        currentAmount += coinValues[i];
                        if (add && spaceLeft() > 0) {
                            coins[i]++;
                            amountOfCoins++;
                        } else {
                            coins[i]--;
                            amountOfCoins--;
                        }
                    } else {
                        j = -2;
                    }
                }
            }

            if (currentAmount != amount) {
                if (ask) {
                    if (add) {
                        System.out.print("Cannot add $" + amount + ". Would you like to add $" + currentAmount + " instead? (y/n): ");
                    } else {
                        System.out.print("Cannot remove $" + amount + " from this bank. Would you like to remove $" + currentAmount + " instead? (y/n): ");
                    }

                    Scanner scanner = new Scanner(System.in);

                    if (!scanner.nextLine().equalsIgnoreCase("y")) {
                        coins = newCoins;
                        amountOfCoins = oldAmountOfCoins;
                    }
                }
            }

            if (changeBalance) {
                if (add) {
                    bankBalance -= amount;
                } else {
                    bankBalance += amount;
                }
            }

            updateMoney();
        } else {
            System.out.println("You do not have enough money.");
        }
    }

    //pre: takes in a String, ability
    //post: doesn't return anything
    //This method allows the piggy bank to upgrade its abilities
    public void upgradeAbility(String ability) {
        if (bankBalance >= 50) {
            if (ability.equals("Interest")) {
                canGainInterest = true;
            } else if (ability.equals("Withdraw")) {
                canWithdraw = true;
            } else if (ability.equals("Invest")) {
                canInvest = true;
            }

            bankBalance -= 50;
        } else {
            System.out.println("You do not have enough money");
        }
    }

    //pre: takes in a double, balance
    //post: returns nothing
    //This method sets the variable in the main for the bank balance to the correct value
    public static void updateBalance(double balance) {
        bankBalance = balance;
    }

    //pre: doesn't take in anything
    //post: returns a String
    //This method displays the PiggyBank object in an organized way
    public String toString() {
        return
                "---------------------------------------------\n" +
                        "Tag: " + tag + "\n" +
                        "Name: " + name + "\n\n" +

                        "Cost: $" + cost + "\n" +
                        "Sell cost: $" + sellCost + "\n\n" +

                        "Volume: " + volume + " cubic inches\n" +
                        "Capacity: " + capacity + " coins\n\n" +

                        "Money: $" + money + "\n" +
                        "Amount of coins: " + amountOfCoins + "\n" +
                        "Space left: " + (capacity - amountOfCoins) + " coins\n" +
                        "Amount of nickels: " + coins[0] + "\n" +
                        "Amount of dimes: " + coins[1] + "\n" +
                        "Amount of quarters: " + coins[2] + "\n" +
                        "Amount of loonies: " + coins[3] + "\n" +
                        "Amount of toonies: " + coins[4] + "\n\n" +

                        "Can gain interest?: " + canGainInterest + "\n" +
                        "Can withdraw?: " + canWithdraw + "\n" +
                        "Can invest?: " + canInvest + "\n\n" +
                        "User bank balance: $" + bankBalance + "\n\n" +
                        "---------------------------------------------\n";
    }
}