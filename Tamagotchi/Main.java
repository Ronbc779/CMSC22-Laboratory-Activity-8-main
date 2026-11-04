import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String petName = "";
        while (petName.isEmpty()) {
            System.out.print("Enter a name for your pet >> ");
            if (!scanner.hasNextLine()) {
                return;
            }
            petName = scanner.nextLine().trim();
        }

        Game game = new Game(petName);
        game.settings.maxLevel = 80;

        String input = "";
        while (!input.equals("exit")) {
            game.showMenu();
            System.out.print("What do you do? >> ");
            if (scanner.hasNextLine()) {
                input = scanner.nextLine().trim().toLowerCase();
            } else {
                input = "exit";
            }
            game.processInput(input);
        }
    }
}
