/*
PIGGY BANK PROJECT
David Tarandach Tabatschnic
*/

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

    //pre: takes in a double, an int, and two booleans
    //post: returns an int[]
    //returns the new combination of coins after using the needed ones to fulfil the amount of money (all values are -1 if there are no possible combos)
    protected int[] changeCoins(double amount, int[] newCoins, boolean add, boolean returnSuccess) {
        for (int i = 4; i >= 0; i--) {
            if (add || newCoins[i] > 0) {

                int amountOfCoins = 0;

                for (int j = 0; j < 5; j++) {
                    amountOfCoins += newCoins[j];
                }

                if (amount > coinValues[i]) {
                    if (add && amountOfCoins < capacity) {
                        newCoins[i]++;
                    } else if (!add) {
                        newCoins[i]--;
                    }
                    return changeCoins((double)((int)(amount*100) - (int)(coinValues[i]*100))/100, newCoins(), add, returnSuccess);
                } else if (amount == coinValues[i]) {
                    if (add && amountOfCoins < capacity) {
                        newCoins[i]++;
                    } else if (!add) {
                        newCoins[i]--;
                    }
                    return newCoins;
                }
            }
        }
        if (add || !returnSuccess) {
            return newCoins;
        }
        return new int[]{-1, -1, -1, -1, -1};
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