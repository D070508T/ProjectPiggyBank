public class Custom extends PiggyBank{
    public Custom(String _name, int _volume, boolean _canGainInterest, boolean _canWithdraw, boolean _canInvest) {
        super();
        name = _name;
        volume = _volume;
        money = 0;
        coins = new int[5];
        amountOfCoins = 0;
        canGainInterest = _canGainInterest;
        canWithdraw = _canWithdraw;
        canInvest = _canInvest;
        capacity = (int) (volume / 0.08);
        cost = 10 * volume;
        if (canGainInterest) {cost += 10;}
        if (canWithdraw) {cost += 10;}
        if (canInvest) {cost += 10;}
    }

    //pre: takes in a String, ability
    //post: doesn't return anything
    //This method allows the piggy bank to upgrade its abilities
    public void upgradeAbility(String ability) {
        if (ability.equals("Interest")) {
            canGainInterest = true;
        } else if (ability.equals("Withdraw")) {
            canWithdraw = true;
        } else if (ability.equals("Invest")) {
            canInvest = true;
        }
    }
}
