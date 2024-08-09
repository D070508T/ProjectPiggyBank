public class Custom extends PiggyBank{

    public Custom(String _name, int _capacity, boolean _canWithdraw, boolean _canGainInterest, boolean _canInvest) {
        super();
        name = _name;
        canGainInterest = _canGainInterest;
        canWithdraw = _canWithdraw;
        canInvest = _canInvest;
        capacity = _capacity;
        cost = 5 * capacity;
        if (canGainInterest) {cost += 10;}
        if (canWithdraw) {cost += 10;}
        if (canInvest) {cost += 10;}
    }

    //pre: takes in a String, ability
    //post: doesn't return anything
    //This method allows the piggy bank to upgrade its abilities
    public void upgradeAbility(String ability) {
        switch (ability) {
            case "Interest" -> canGainInterest = true;
            case "Withdraw" -> canWithdraw = true;
            case "Invest" -> canInvest = true;
        }
    }
}
