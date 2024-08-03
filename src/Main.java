import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Spending bank = new Spending();
        Scanner scanner = new Scanner(System.in);

        bank.setCoins(new int[]{5, 5, 5, 5, 5});

        while (true) {
            for (int i : bank.withdrawCoins(scanner.nextDouble(), new int[]{5, 5, 5, 5, 5})) {
                System.out.println(i);
            }
        }
    }
}