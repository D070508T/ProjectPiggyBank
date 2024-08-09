public class Spending extends PiggyBank {
    public Spending() {
        super();
        cost = 50;
        capacity = 30;
        canGainInterest = false;
        canWithdraw = true;
        canInvest = false;
        name = "Spending Bank";
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
}
