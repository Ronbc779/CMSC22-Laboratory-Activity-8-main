// one cup size. A size changes the price and scales how much of each supply the drink uses.
// Percentages use integer math, so 75% gives the same result as the original "* 3 / 4".
class Size {
    private String name;
    private int priceChange;
    private int liquidPercent;
    private int coffeePercent;

    Size(String name, int priceChange, int liquidPercent, int coffeePercent) {
        this.name = name;
        this.priceChange = priceChange;
        this.liquidPercent = liquidPercent;
        this.coffeePercent = coffeePercent;
    }

    String getName() { return name; }
    int getPriceChange() { return priceChange; }

    // scales an amount of water or milk (ml) for this size
    int scaleLiquid(int ml) { return ml * liquidPercent / 100; }

    // scales an amount of coffee (g) for this size
    int scaleCoffee(int grams) { return grams * coffeePercent / 100; }
}
