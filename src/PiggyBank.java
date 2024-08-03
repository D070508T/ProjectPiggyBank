/*
PIGGY BANK PROJECT
David Tarandach Tabatschnic
*/

import java.util.Scanner;

public abstract class PiggyBank {
    //Instance variables
    protected int cost;
    protected int volume;
    protected int capacity;
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
        double money = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < coins[i]; j++) {
                money += coinValues[i];
            }
        }
        return money;
    }

    public int[] getCoins() {
        return coins;
    }

    public void setCoins(int[] newCoins) {
        coins = newCoins;
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
    public void transferTo(PiggyBank goTo, double amount) {
        int[] newCoins = withdrawCoins(amount, new int[]{coins[0], coins[1], coins[2], coins[3], coins[4]});
        if (newCoins[0] != -1) {
            int coinAmount = 0;
            int[] coinsUsed = new int[5];
            for (int i = 0; i < 5; i++) {
                coinsUsed[i] = coins[i] - newCoins[i];
                coinAmount += coinsUsed[i];
            }

            if (goTo.spaceLeft() >= coinAmount) {
                setCoins(newCoins);
                for (int i = 0; i < 5; i++) {
                    goTo.addCoins(i, coinsUsed[i]);
                }
            }
        }
    }

    //pre: takes in a double and an int
    //post: returns an int[]
    //returns the new combination of coins after using the needed ones to fulfil the amount of money (all values are -1 if there are no possible combos)
    private int[] withdrawCoins(double amount, int[] newCoins) {
        for (int i = 4; i >= 0; i--) {
            if (newCoins[i] > 0) {
                if ((int)(amount*100) > (int)(coinValues[i]*100)) {
                    newCoins[i]--;
                    return withdrawCoins((double)((int)(amount*100) - (int)(coinValues[i]*100))/100, new int[]{newCoins[0], newCoins[1], newCoins[2], newCoins[3], newCoins[4]});
                } else if ((int)(amount*100) == (int)(coinValues[i]*100)) {
                    newCoins[i]--;
                    return newCoins;
                }
            }
        }
        return new int[]{-1, -1, -1, -1, -1};
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
        }
    }

    //pre: takes in a double, amount, and a boolean, add
    //post: doesn't return anything
    //This method changes (adds or removes) coins from the piggy bank by amount
    public void changeCoinsByAmount(double amount, boolean add, boolean ask) {
        double currentAmount = 0;
        int oldAmountOfCoins = amountOfCoins;
        int[] newCoins = new int[5];
        System.arraycopy(coins, 0, newCoins, 0, 5);

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
    }

    //pre: takes in two ints
    //post: returns nothing
    //Gets which coin and how many, and adds it to the coins
    private void addCoin(int c, int amount) {
        coins[c] += amount;
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

                        "Money: $" + getMoney() + "\n" +
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