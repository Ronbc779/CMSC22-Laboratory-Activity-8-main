import java.util.Random;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // a number after "java Main" repeats the same random choices (see README how to run with fixed seed)
        Random random = (args.length > 0) ? new Random(Long.parseLong(args[0])) : new Random();
        System.out.println("=== MYTHLINGS ===");

        String[] names = {"Sarimanok", "Sirena", "Kapre"};
        System.out.println("Choose your creature:");
        for (int i = 0; i < names.length; i++) {
            Creature c = CreatureFactory.create(names[i]);
            System.out.println("  " + (i + 1) + ". " + c.name + " [" + c.type + "] HP " + c.maxHp
                    + ", Attack " + c.attack + ", Speed " + c.speed);
        }
        Creature player = CreatureFactory.create(names[askNumber("Choose >> ", names.length) - 1]);

        Creature rival;
        do {
            rival = CreatureFactory.create(names[random.nextInt(names.length)]);
        } while (rival.name.equals(player.name));

        String[] levels = {"EASY", "HARD"};
        System.out.println("Choose the rival's difficulty:");
        System.out.println("  1. Easy (picks moves at random)");
        System.out.println("  2. Hard (picks the best move)");
        String difficulty = levels[askNumber("Choose >> ", levels.length) - 1];

        System.out.println("\nYou chose " + player.name + ". A wild " + rival.name + " appears!");
        Battle battle = new Battle(player, rival, difficulty, random);

        while (!battle.isOver()) {
            battle.showStatus();
            battle.showMoveMenu();
            int choice = askNumber("Choose >> ", 4);
            System.out.println();
            battle.playRound(choice - 1);
        }
        battle.printResult();
    }

    static int askNumber(String prompt, int max) {
        while (true) {
            System.out.print(prompt);
            if (!scanner.hasNextLine()) {
                System.out.println("\nGoodbye!");
                System.exit(0);
            }
            try {
                int number = Integer.parseInt(scanner.nextLine().trim());
                if (number >= 1 && number <= max) {
                    return number;
                }
            } catch (NumberFormatException e) {
                System.out.print("");
            }
            System.out.println("Please enter a number from 1 to " + max + ".");
        }
    }
}
