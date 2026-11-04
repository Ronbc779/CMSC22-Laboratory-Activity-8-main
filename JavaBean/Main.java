import java.util.Scanner;

// creates the coffee machine and turns it on
public class Main {

    public static void main(String[] args) {
        CoffeeMachine machine = new CoffeeMachine(new Scanner(System.in), new SmsSender());
        machine.run();
    }
}
