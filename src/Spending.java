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
}
