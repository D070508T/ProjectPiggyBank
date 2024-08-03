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
