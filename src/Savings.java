public class Savings extends PiggyBank {
    public Savings() {
        super();
        cost = 200;
        capacity = 100;
        canGainInterest = false;
        canWithdraw = true;
        canInvest = false;
        name = "Savings Bank";
    }

    //pre: takes in a double, percent
    //post: doesn't return anything
    //This method allows the piggy bank to gain interest
    public void collectInterest(int percent) {
        coins = changeCoins(0.03*getMoney(), new int[]{coins[0], coins[1], coins[2], coins[3], coins[4]}, true, false);
    }

    //pre: doesn't take in anything
    //post: returns a String
    //This method displays the information for the specific PiggyBank
    @Override
    public String info() {
        return """
                Cost: $200
                
                Capacity: 100
                
                Description:
                  This piggy bank can gain 3% interest
                """;
    }
}
