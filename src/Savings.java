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
}
