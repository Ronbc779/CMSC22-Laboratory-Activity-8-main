//keeps track of the total price, supplies needed, and description of customer's order
class Order {
    private int cost;
    private int water;
    private int milk;
    private int coffee;
    private String description;

    //starts from the drink's regular recipe, then the size

    Order(Drink drink, Size size) {
        cost = drink.getPrice() + size.getPriceChange();
        water = size.scaleLiquid(drink.getWater());
        milk = size.scaleLiquid(drink.getMilk());
        coffee = size.scaleCoffee(drink.getCoffee());
        description = size.getName() + " " + drink.getName();
    }

    //extras are added
    void addExtra(Extra extra) {
        cost += extra.getPrice();
        water += extra.getWater();
        coffee += extra.getCoffee();
        description += " + " + extra.getName();
    }

    //getters
    int getCost() { return cost; }
    int getWater() { return water; }
    int getMilk() { return milk; }
    int getCoffee() { return coffee; }
    String getDescription() { return description; }
}
