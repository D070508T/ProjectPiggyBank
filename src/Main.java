public class Main {
    public static void main(String[] args) {
        Spending bank = new Spending();

        bank.setCoins(new int[]{5, 5, 5, 5, 5});

        bank.withdrawCoins(0.4, new int[]{5, 5, 5, 5, 5});
    }
}