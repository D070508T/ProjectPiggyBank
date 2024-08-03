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

    public void withdrawCoins(double amount) {
        int[] oldCoins = new int[5];
        System.arraycopy(coins, 0, oldCoins, 0, 5);
        recur(oldCoins, amount);
    }

    public void recur(int[] newCoins, double amount) {
        for (int i = 4; i >= 0; i--) {
            if (newCoins[i] > 0) {
                newCoins[i]--;

                if (amount > coinValues[i]) {
                    recur(newCoins, amount - coinValues[i]);
                } else if (amount == coinValues[i]) {
                    for (int c : newCoins) {
                        System.out.println(c);
                    }
                }

            }
        }
    }
}
