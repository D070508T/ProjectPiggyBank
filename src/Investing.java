import java.util.Random;

public class Investing extends PiggyBank {
    public Investing() {
        super();
        cost = 30;
        volume = 6;
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

        double amountOfMoney = 0.3*money;

        int randInt = random.nextInt(1, 3);

        if (randInt == 1) {
            changeCoinsByAmount(amountOfMoney, true, true, false);
        } else {
            changeCoinsByAmount(amountOfMoney, false, false, false);
        }
    }
}
