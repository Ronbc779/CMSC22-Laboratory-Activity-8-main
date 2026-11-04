import java.util.Scanner;

// pays with coins: asks how many of each coin the customer inserts and returns the total
class CoinPayment implements PaymentMethod {
    private Scanner scanner;

    CoinPayment(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public int pay(int cost) {
        System.out.println("Please insert coins.");
        int twenties = askInt("How many Php 20 coins? >> ");
        int tens = askInt("How many Php 10 coins? >> ");
        int fives = askInt("How many Php 5 coins? >> ");
        int ones = askInt("How many Php 1 coins? >> ");
        int total = twenties * 20 + tens * 10 + fives * 5 + ones;
        System.out.println("You inserted Php " + total + ".");
        return total;
    }

    // invalid input counts as 0 coins
    private int askInt(String prompt) {
        System.out.print(prompt);
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
