import java.util.Random;

public class Investing extends PiggyBank {
    public Investing() {
        super();
        cost = 70;
        capacity = 55;
        canGainInterest = false;
        canWithdraw = false;
        canInvest = true;
        name = "Investing Bank";
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
}
