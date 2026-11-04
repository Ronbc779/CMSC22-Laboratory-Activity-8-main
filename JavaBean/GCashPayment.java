import java.util.Scanner;

// pays with GCash: asks for the customer's number and charges the price
class GCashPayment implements PaymentMethod {
    private Scanner scanner;

    GCashPayment(Scanner scanner) {
        this.scanner = scanner;
    }

    // gcash is charged the exact price
    @Override
    public int pay(int cost) {
        System.out.print("GCash number >> ");
        String number = scanner.nextLine().trim();
        System.out.println("GCash: charged Php " + cost + " to " + number);
        return cost;
    }
}
