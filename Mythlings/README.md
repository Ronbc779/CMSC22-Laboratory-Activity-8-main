# Mythlings: The Object-Oriented Monster Battle

Turn-based battles are one of the most popular kinds of computer games. You pick a character, take turns choosing moves against an opponent, and try to knock out the other character before yours is knocked out.

Many well-known games work this way, such as the classic Final Fantasy, Baldur's Gate, Fire Emblem, and even board games like chess, where players take turns making a move.

Another example is the Pokémon Trading Card Game, where players take turns playing cards and attacking with their Pokémon. Every Pokémon has a type, such as Water, Fire or Grass, and some types are strong against others. For example, a Grass Pokémon is usually weak to Fire, so a Fire attack does double damage to it.

Mythlings takes inspiration from that idea. It is a small console version of those battles, with the same kind of types, and it is built out of objects.

## How to play

1. Run the program and choose your character:
   - **Sarimanok** (Fire): fast and hits hard, but has the least HP.
   - **Sirena** (Water): a balanced fighter that can put enemies to sleep.
   - **Kapre** (Grass): slow but very tough, and can paralyze enemies.
2. Choose the difficulty of your opponent: **Easy** (it picks moves at random) or **Hard** (it picks the best move).
3. Each round, choose one of your character's three moves, or use a potion to recover 30 HP. You have 2 potions. The creature with the higher speed moves first each round.
4. Pay attention to types:
   - Fire beats Grass, Grass beats Water, Water beats Fire.
   - A strong type does double damage, and a weak type does half.
   - Moves that are not Fire, Water or Grass (like Wing Slash, Tail Slap and Smash) are normal, so they are never strong or weak.
5. Some moves cause a status:
   - **Burned:** loses a little HP every round and hits weaker.
   - **Asleep:** may skip turns until it wakes up.
   - **Paralyzed:** moves slower and sometimes can't move.
6. The battle ends when one character faints.

## How to run

Open a terminal in this folder and type:

```
javac *.java
java Main
```

Battles use random numbers, so the same choices can end differently each time. To repeat a battle exactly, give the program a number (a random seed) after `java Main`:

```
java Main 42
```

With the same seed and the same choices, you get the same battle every time. You can use this to check that your refactored program still behaves like the original.
