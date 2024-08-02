/*
PIGGY BANK PROJECT
David Tarandach Tabatschnic
*/

import java.util.Random;
import java.util.Scanner;

public abstract class PiggyBank {
    //Instance variables
    protected int cost;
    protected int volume;
    protected int capacity;
    protected double money;
    protected int[] coins;
    protected int amountOfCoins;
    protected String name;
    protected boolean canGainInterest;
    protected boolean canWithdraw;
    protected boolean canInvest;

    //Class variables
    public static final double[] coinValues = {0.05, 0.10, 0.25, 1, 2};

    //Constructors
    public PiggyBank() {
        name = "";
        volume = 0;
        money = 0;
        coins = new int[5];
        amountOfCoins = 0;
    }

    // Getters and setters
    public int getCost() {
        return cost;
    }

    public void setCost(int Cost) {
        cost = Cost;
    }

    public int getSellCost() {
        return (int) (0.8 * cost);
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

    //pre: takes in a double, amount, and a PiggyBank, goTO
    //post: returns nothing
    //This method transfers money from one piggy bank to the other
    public void transferMoney(double amount, PiggyBank goTo) {
        double oldMoney = goTo.getMoney();
        changeCoinsByAmount(amount, false, false);
        goTo.changeCoinsByAmount(amount, true, false);
        if (goTo.getMoney() == oldMoney && amount != 0) {
            changeCoinsByAmount(amount, true, false);
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

        if (spaceLeft() < 1) {
            System.out.println("You do not have enough space in this piggy bank.");
        } else {
            coins[type - 1] += amount;
            amountOfCoins += amount;
            updateMoney();
        }
    }

    //pre: takes in a double, amount, and a boolean, add
    //post: doesn't return anything
    //This method changes (adds or removes) coins from the piggy bank by amount
    public void changeCoinsByAmount(double amount, boolean add, boolean ask) {
        if (add || (!add && money >= amount)) {
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
            updateMoney();
        } else {
            System.out.println("You do not have enough money.");
        }
    }

    //pre: doesn't take in anything
    //post: returns a String
    //This method displays the PiggyBank object in an organized way
    public String toString() {
        return
                "---------------------------------------------\n" +
                        "Name: " + name + "\n\n" +

                        "Cost: $" + cost + "\n" +
                        "Sell cost: $" + getSellCost() + "\n\n" +

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
                        "---------------------------------------------\n";
    }
}