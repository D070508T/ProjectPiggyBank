public class Spending extends PiggyBank {
    public Spending() {
        super();
        cost = 50;
        volume = 8;
        canGainInterest = true;
        canWithdraw = false;
        canInvest = false;
        name = "Spending Bank";
    }

    //pre: takes in a double and an int
    //post: returns an int[]
    //returns the new combination of coins after using the needed ones to fulfil the amount of money (all values are -1 if there are no possible combos)
    public int[] withdrawCoins(double amount, int[] newCoins) {
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
}
