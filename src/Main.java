public class Main {
    public static void main(String[] args) {
        PiggyBank bank = new PiggyBank();

        bank.setName("bank1");

        System.out.println(bank.getName());

        bank.setName("NEWBANK");

        System.out.println(bank.getName());
    }
}