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
        if (amount == 0) {
            for (int c : newCoins) {
                System.out.println(c);
            }
        } else {
            for (int i = 4; i >= 0; i--) {
                if (newCoins[i] > 0) {
                    if (amount > coinValues[i]) {
                        newCoins[i]--;
                        withdrawCoins(amount - coinValues[i], newCoins);
                    } else if (amount == coinValues[i]) {
                        newCoins[i]--;
                        for (int c : newCoins) {
                            System.out.println(c);
                        }
                    }
                }
            }
        }
    }
}
