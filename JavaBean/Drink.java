//water and milk are in ml, coffee is in grams
class Drink {
    private String name;
    private int price;
    private int water;
    private int milk;
    private int coffee;

    Drink(String name, int price, int water, int milk, int coffee) {
        this.name = name;
        this.price = price;
        this.water = water;
        this.milk = milk;
        this.coffee = coffee;
    }

    //getters
    String getName() { return name; }
    int getPrice() { return price; }
    int getWater() { return water; }
    int getMilk() { return milk; }
    int getCoffee() { return coffee; }
}
