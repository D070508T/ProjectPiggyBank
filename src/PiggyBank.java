/*
PIGGY BANK PROJECT
David Tarandach Tabatschnic
*/

import java.util.Random;

public class PiggyBank {
    //Instance variables
    protected int cost;
    protected int capacity;
    protected int[] coins;
    protected String name;
    protected boolean canGainInterest;
    protected boolean canWithdraw;
    protected boolean canInvest;

    //Class variables
    public static final double[] coinValues = {0.05, 0.10, 0.25, 1, 2};

    //Constructors
    public PiggyBank() {
        name = "";
        coins = new int[5];
    }

    // Getters and setters
    public int getCost() {
        return cost;
    }

    public int amountOfCoins() {
        int c = 0;
        for (int coin : coins) {
            c += coin;
        }

        return c;
    }

    public void setCost(int Cost) {
        cost = Cost;
    }

    public int getSellCost() {
        return (int) (0.8 * cost);
    }

    public void setCapacity(int _capacity) {
        capacity = _capacity;
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
        return capacity - amountOfCoins();
    }

    //pre: takes in a double, amount, and a PiggyBank, goTO
    //post: returns nothing
    //This method transfers money from one piggy bank to the other
    public void transferTo(PiggyBank goTo, double amount) {
        int[] newCoins = changeCoins(amount, newCoins(), false, true);
        if (newCoins[0] != -1) {
            int coinAmount = 0;
            int[] coinsUsed = new int[5];
            for (int i = 0; i < 5; i++) {
                coinsUsed[i] = coins[i] - newCoins[i];
                coinAmount += coinsUsed[i];
            }

            if (goTo.spaceLeft() >= coinAmount) {
                coins = newCoins;
                for (int i = 0; i < 5; i++) {
                    goTo.addCoin(i, coinsUsed[i]);
                }

                System.out.println("Transaction successful");
            }

            System.out.println("Transaction failed");
        }

        System.out.println("Transaction failed");
    }

    public int[] newCoins() {
        return new int[]{coins[0], coins[1], coins[2], coins[3], coins[4]};
    }

    protected int[] changeCoins(double amount, int[] newCoins, boolean add, boolean returnSuccess) {
        for (int i = 4; i >= 0; i--) {
            if (add || newCoins[i] > 0) {

                int amountOfCoins = 0;
                for (int j = 0; j < 5; j++) {
                    amountOfCoins += newCoins[j];
                }

                // Check if we can use the current coin value
                while (amount >= coinValues[i]) {
                    if (add && amountOfCoins < capacity) {
                        newCoins[i]++;
                        amount -= coinValues[i];  // Subtract the value of the coin
                    } else if (!add) {
                        newCoins[i]--;
                        amount -= coinValues[i];  // Subtract the value of the coin
                    }
                }

                // If the amount is exactly a coin value, handle it
                if (amount == 0) {
                    return newCoins;
                }
            }
        }

        if (add || !returnSuccess) {
            return newCoins;
        }

        // Return an array indicating no valid combinations found
        return new int[]{-1, -1, -1, -1, -1};
    }

    //pre: doesn't take in anything
    //post: doesn't return anything
    //This method allows the piggy bank to invest 30% of their money for a 50/50 chance of profit
    public void invest() {
        Random random = new Random();

        int randInt = random.nextInt(1, 3);

        if (randInt == 1) {
            coins = changeCoins(0.5 * getMoney(), newCoins(), true, false);
        } else {
            changeCoins(0.5 * getMoney(), newCoins(), false, false);
        }
    }

    //pre: takes in a double, percent
    //post: doesn't return anything
    //This method allows the piggy bank to gain interest
    public void collectInterest() {
        coins = changeCoins(0.03*getMoney(), newCoins(), true, false);
    }

    //pre: takes in a double and an int
    //post: returns a boolean to check if the transaction was successful
    //returns the new combination of coins after using the needed ones to fulfil the amount of money (all values are -1 if there are no possible combos)
    public boolean withdrawCoins(double amount) {
        int[] c = changeCoins(amount, newCoins(), false, true);

        if (c[0] != -1) {
            coins = c;
            System.out.println("Withdrawal successful.");
            return true;
        } else {
            System.out.println("This withdrawal was not possible.");
            return false;
        }
    }


    //pre: takes in two ints
    //post: returns nothing
    //Gets which coin and how many, and adds it to the coins
    public void addCoin(int c, int amount) {
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

                        "Capacity: " + capacity + " coins\n" +
                        "Space left: " + spaceLeft() + " coins\n\n" +

                        "Money: $" + getMoney() + "\n" +
                        "Amount of coins: " + amountOfCoins() + "\n" +
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