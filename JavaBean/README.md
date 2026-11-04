# JavaBean: The Object-Oriented Coffee Machine

Coffee vending machines have been around for decades. You walk up, choose a drink, put in your coins, and out comes a cup of hot coffee. The machine has to keep track of its water, milk and coffee, make sure you paid enough, give back change, and let the staff check how much money it has made. The coffee machine is also a classic practice problem for programmers learning how to organize a program into classes.

This program is a console version of one of those machines.

## How to play

1. Run the program. The machine asks what you would like.
2. Type the name of a drink: `espresso`, `americano`, `latte` or `cappuccino`.
3. Type the size of the cup: `small`, `regular` or `large`.
4. Add extras if you like. Type one extra at a time (`extra shot`, `syrup` or `whipped cream`), as many as you want, and type `done` when you are finished.
5. The machine tells you the price. Pay with `coins` (you type how many Php 20, Php 10, Php 5 and Php 1 coins you put in) or with `gcash` (you type your number).
6. If you paid enough, you get your drink and your change. If the machine is out of water, milk or coffee, it tells you so.
7. Staff can type `report` to see the supplies left and the money earned, and `off` to turn the machine off.

The table below is for a **regular** cup. The other sizes change the price and how much the drink uses:

| Size | Price | Water and milk | Coffee |
|---|---|---|---|
| Small | Php 10 less | 3/4 of the regular amount | same as regular |
| Regular | listed price | listed amount | listed amount |
| Large | Php 20 more | 1.5 times the regular amount | 1.5 times the regular amount |

The extras are the same for every drink and every size:

| Extra | Price | What it does |
|---|---|---|
| Extra shot | Php 20 more | Uses 60 ml more water and 18 g more coffee |
| Syrup | Php 15 more | Adds "+ syrup" to your order |
| Whipped cream | Php 15 more | Adds "+ whipped cream" to your order |

You can add the same extra more than once, and each one adds to the price.

| Drink (regular) | Price | Water | Milk | Coffee | What it is |
|---|---|---|---|---|---|
| Espresso | Php 90 | 60 ml | 0 ml | 18 g | A strong, small shot of coffee |
| Americano | Php 100 | 200 ml | 0 ml | 18 g | An espresso shot with extra hot water |
| Latte | Php 130 | 60 ml | 150 ml | 18 g | An espresso shot with lots of steamed milk |
| Cappuccino | Php 150 | 60 ml | 100 ml | 18 g | An espresso shot with steamed milk and a little foam |

The machine starts with 500 ml of water, 400 ml of milk and 200 g of coffee. Each drink uses up the water, milk and coffee for its size, and the machine will not make a drink if any one of them is not enough. The staff gets a text message when a supply runs out or runs low.

## How to run

Open a terminal in this folder and type:

```
javac *.java
java Main
```
