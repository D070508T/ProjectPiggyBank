public class Savings extends PiggyBank {
    public Savings() {
        super();
        cost = 200;
        capacity = 100;
        canGainInterest = true;
        canWithdraw = false;
        canInvest = false;
        name = "Savings Bank";
    }

    //pre: takes in a double, percent
    //post: doesn't return anything
    //This method allows the piggy bank to gain interest
    public void collectInterest() {
        coins = changeCoins(0.03*getMoney(), newCoins(), true, false);
    }
}
