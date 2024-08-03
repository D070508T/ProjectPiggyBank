public class Savings extends PiggyBank {
    public Savings() {
        super();
        cost = 10;
        volume = 4;
        canGainInterest = false;
        canWithdraw = true;
        canInvest = false;
        name = "Savings Bank";
    }

    //pre: takes in a double, percent
    //post: doesn't return anything
    //This method allows the piggy bank to gain interest
    public void collectInterest(int percent) {
        changeCoinsByAmount(0.03*getMoney(), true, false);
    }
}
