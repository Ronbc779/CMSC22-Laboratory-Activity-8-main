//water in ml and coffee in grams
public class Extra {

    private String name;
    private int price;
    private int water;
    private int coffee;

    public Extra(String name, int price, int water, int coffee) {
        this.name = name;
        this.price = price;
        this.water = water;
        this.coffee = coffee;
    }

    //getters
    String getName() { return name;}
    int getPrice() {return price;}
    int getWater() {return water;}
    int getCoffee() {return coffee;}
}
