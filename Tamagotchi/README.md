# Tamagotchi

Tamagotchi is a game from the 1990s. It was a small handheld device, shaped like an egg and about the size of a keychain, with a tiny screen showing a virtual pet. Your job was to take care of it: feed it, play with it, and keep it happy so it could grow up. It became a huge hit around the world, and many kids carried one everywhere they went.

This program is a simple console version of that idea. You give your pet a name and take care of it, and as you look after it, it grows from a baby into a teen and then an adult.

## How to play

1. Run the program and type a name for your pet. It starts as a baby in the Lobby.
2. Each turn, you choose what to do by typing a command:
   - `feed` to give it food
   - `play` to play with it
   - `train` to train it (only when it is old enough)
   - `status` to see how it is doing
   - `go <place>` to move somewhere else, like `go kitchen`
   - `exit` to quit
3. Not every action is available everywhere. Each place allows these actions, and you can check `status` anywhere:

   | Place | Actions |
   |---|---|
   | Lobby | `feed`, `play`, `train` |
   | Playground | `play` |
   | Training Room | `train` |
   | Kitchen | `feed` |

   To move, type `go` and the place, like `go kitchen` or `go training room`. Doing the right thing in the right place gives an extra reward. For example, playing in the Playground makes your pet happier than playing in the Lobby.
4. Keep your pet well fed and happy. Play with it and it will grow into a teen, and train it and it will become an adult.
5. Hunger, happiness and training each go from 0 to 80. For hunger, lower is better: 0 means full. Every action you take counts as one turn (even `status`), and your pet's age is the number of turns so far.

## How to run

Open a terminal in this folder and type:

```
javac *.java
java Main
```
