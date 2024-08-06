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
            coins = changeCoins(0.5 * getMoney(), new int[]{coins[0], coins[1], coins[2], coins[3], coins[4]}, true, false);
        } else {
            changeCoins(0.5 * getMoney(), new int[]{coins[0], coins[1], coins[2], coins[3], coins[4]}, false, false);
        }
    }

    //pre: doesn't take in anything
    //post: returns a String
    //This method displays the information for the specific PiggyBank
    @Override
    public String info() {
        return """
                Cost: $70
                
                Capacity: 55
                
                Description:
                  This piggy bank can invest
                  with a 50/50 chance of either
                  doubling or halving your balance
                """;
    }
}
