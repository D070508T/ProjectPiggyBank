public class Main {
    public static void main(String[] args) {
        Spending bank = new Spending();

        bank.setCoins(new int[]{5, 5, 5, 5, 5});

        for (int i : bank.withdrawCoins(0.81, new int[]{5, 5, 5, 5, 5})) {
            System.out.println(i);
        }
    }
}