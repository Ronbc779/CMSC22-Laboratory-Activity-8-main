import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.LinkedHashMap;

// takes orders, keeps track of supplies and earnings, and tells the staff when supplies run low
//Design Notes: drinks, sizes and extras are enums
class CoffeeMachine {
    private static final String STAFF_NUMBER = "0917-555-0100";

    // the machine warns the staff when any supply drops below its limit
    private static final int LOW_WATER = 100;
    private static final int LOW_MILK = 50;
    private static final int LOW_COFFEE = 30;

    private Scanner scanner;
    private SmsSender sms;
    private boolean isOn = true;
    private Map<String, PaymentMethod> paymentMethods = new HashMap<>();
    //keeps the order we added the items
    private Map<String, Drink> drinks = new LinkedHashMap<>();
    private Map<String, Size> sizes = new LinkedHashMap<>();
    private Map<String, Extra> extras = new LinkedHashMap<>();

    // water and milk are in ml, coffee is in grams
    private int water = 500;
    private int milk = 400;
    private int coffee = 200;
    private int profit = 0;

    CoffeeMachine(Scanner scanner, SmsSender sms) {
        this.scanner = scanner;
        this.sms = sms;
        paymentMethods.put("coins", new CoinPayment(scanner));
        paymentMethods.put("gcash", new GCashPayment(scanner));
        setUpMenu();
    }

        // all drinks, sizes and extras are listed here. To add one, add one more line.
    private void setUpMenu() {
        Drink[] drinkList = {
            new Drink("espresso", 90, 60, 0, 18),
            new Drink("americano", 100, 200, 0, 18),
            new Drink("latte", 130, 60, 150, 18),
            new Drink("cappuccino", 150, 60, 100, 18)
        };
        for (Drink drink : drinkList) {
            drinks.put(drink.getName(), drink);
        }

        // name, price change, water/milk %, coffee %
        Size[] sizeList = {
            new Size("small", -10, 75, 100),
            new Size("regular", 0, 100, 100),
            new Size("large", 20, 150, 150)
        };
        for (Size size : sizeList) {
            sizes.put(size.getName(), size);
        }

        // name, price, water, coffee
        Extra[] extraList = {
            new Extra("extra shot", 20, 60, 18),
            new Extra("syrup", 15, 0, 0),
            new Extra("whipped cream", 15, 0, 0)
        };
        for (Extra extra : extraList) {
            extras.put(extra.getName(), extra);
        }
    }

    public void run() {
        System.out.println("Welcome to JavaBean: The Object-Oriented Coffee Machine!");
        System.out.println("(Type 'report' to see the supplies, or 'off' to turn the machine off.)");
        while (isOn) {
            System.out.print("\nWhat would you like? (" + String.join("/", drinks.keySet()) + ") >> ");
            if (!scanner.hasNextLine()) {
                break;
            }
            String choice = scanner.nextLine().trim().toLowerCase();
            handleCommand(choice);
        }
    }

    private void handleCommand(String choice) {
        Drink drink = drinks.get(choice);
        if (choice.equals("off")) {
            isOn = false;
            System.out.println("Turning off. Goodbye!");
        } else if (choice.equals("report")) {
            printReport();
        } else if (drink != null) {
            makeDrink(drink);
        } else {
            System.out.println("Sorry, we don't have '" + choice + "'.");
        }
    }

    // returns (price in Php, water in ml, milk in ml, coffee in g), or null for an unknown drink.
    private int[] getRecipe(String drink) {
        if (drink.equals("espresso")) {
            return new int[] {90, 60, 0, 18};
        } else if (drink.equals("americano")) {
            return new int[] {100, 200, 0, 18};
        } else if (drink.equals("latte")) {
            return new int[] {130, 60, 150, 18};
        } else if (drink.equals("cappuccino")) {
            return new int[] {150, 60, 100, 18};
        }
        return null;
    }

    private void makeDrink(Drink drink) {
        Size size = askSize();
        if (size == null) {
            return;
        }
        Order order = new Order(drink, size);
        askExtras(order);

        System.out.println("A " + order.getDescription() + " costs Php " + order.getCost() + ".");
        if (!hasEnoughResources(order)) {
            return;
        }
        if (!takePayment(order)) {
            return;
        }
        serve(order);
    }

    private Size askSize() {
        String text = ask("What size? (" + String.join("/", sizes.keySet()) + ") >> ");
        Size size = sizes.get(text);
        if (size == null) {
            System.out.println("Sorry, we don't have a '" + text + "' cup.");
        }
        return size;
    }

    private void askExtras(Order order) {
        while (true) {
            String text = ask("Add an extra? (" + String.join("/", extras.keySet()) + "/done) >> ");
            if (text.equals("done")) {
                break;
            }
            Extra extra = extras.get(text);
            if (extra == null) {
                System.out.println("Sorry, we don't have '" + text + "'.");
            } else {
                order.addExtra(extra);
            }
        }
    }

    private boolean takePayment(Order order) {
        String method = ask("Pay with coins or GCash? (coins/gcash) >> ");
        PaymentMethod payment = paymentMethods.get(method);
        if (payment == null) {
            System.out.println("Sorry, we don't accept '" + method + "'.");
            return false;
        }
        int cost = order.getCost();
        int paid = payment.pay(cost);

        if (paid < cost) {
            System.out.println("Sorry, that's not enough money. Money refunded.");
            return false;
        }
        if (paid > cost) {
            System.out.println("Here is Php " + (paid - cost) + " in change.");
        }
        profit += cost;
        return true;
    }

    private void serve(Order order) {
        water -= order.getWater();
        milk -= order.getMilk();
        coffee -= order.getCoffee();
        System.out.println("Here is your " + order.getDescription() + ". Enjoy!");
        checkLowSupplies();
    }

    private boolean hasEnoughResources(int waterNeeded, int milkNeeded, int coffeeNeeded) {
        boolean enough = true;
        if (water < waterNeeded) {
            System.out.println("Sorry, there is not enough water.");
            sms.send("0917-555-0100", "Coffee machine is out of water!");
            enough = false;
        }
        if (milk < milkNeeded) {
            System.out.println("Sorry, there is not enough milk.");
            sms.send("0917-555-0100", "Coffee machine is out of milk!");
            enough = false;
        }
        if (coffee < coffeeNeeded) {
            System.out.println("Sorry, there is not enough coffee.");
            sms.send("0917-555-0100", "Coffee machine is out of coffee!");
            enough = false;
        }
        return enough;
    }

    private void checkLowSupplies() {
        //replaced magic numbers
        if (water < LOW_WATER || milk < LOW_MILK || coffee < LOW_COFFEE) {
            System.out.println("Warning: supplies are running low.");
            sms.send(STAFF_NUMBER, "Low supplies: water " + water + "ml, milk " + milk + "ml, coffee " + coffee + "g");
        }
    }

    private void printReport() {
        System.out.println("Water: " + water + "ml");
        System.out.println("Milk: " + milk + "ml");
        System.out.println("Coffee: " + coffee + "g");
        System.out.println("Money: Php " + profit);
    }

    //shows the prompt and returns what the customer typed
    private String ask(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim().toLowerCase();
    }
}
