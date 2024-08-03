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

    public void withdrawCoins(double amount, int[] newCoins) {
        System.out.println("amount: " + amount);
        for (int i = 4; i >= 0; i--) {
            if (newCoins[i] > 0) {
                if ((int)(amount*100) > (int)(coinValues[i]*100)) {
                    newCoins[i]--;
                    withdrawCoins((double)((int)(amount*100) - (int)(coinValues[i]*100))/100, newCoins);
                } else if ((int)(amount*100) == (int)(coinValues[i]*100)) {
                    newCoins[i]--;
                    for (int c : newCoins) {
                        System.out.println(c);
                    }
                    System.out.println("\n\n");
                }
            }
        }
    }
}
