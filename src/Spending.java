public class Spending extends PiggyBank {
    public Spending() {
        super();
        cost = 50;
        capacity = 30;
        canGainInterest = true;
        canWithdraw = false;
        canInvest = false;
        name = "Spending Bank";
    }

    //pre: takes in a double and an int
    //post: returns an int[]
    //returns the new combination of coins after using the needed ones to fulfil the amount of money (all values are -1 if there are no possible combos)
    public int[] withdrawCoins(double amount, int[] newCoins) {
        return changeCoins(amount, newCoins, false, true);
    }
}
