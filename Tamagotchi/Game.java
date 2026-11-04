// this class is responsible for reading each command, moving the pet between places, and 
// applying the rules for feed, play and train
class Game {
    GameSettings settings = new GameSettings();
    private Pet pet;
    private Location currentLocation = LocationFactory.create("lobby");

    Game(String petName) {
        pet = new Pet(petName, settings);
        System.out.println("Welcome to " + settings.gameName + "! Say hi to " + petName + " the " + pet.stage.toLowerCase() + ".");
    }

    public void showMenu() {
        System.out.println("[" + currentLocation.name + "] You can: " + currentLocation.actions + ", go <place>, exit");
    }

    public void processInput(String input) {
        input = input.toLowerCase().trim();
        System.out.println();
        if (input.startsWith("go ")) {
            goTo(input.substring(3));
        } else if (input.equals("exit")) {
            System.out.println("Goodbye! " + pet.name + " will miss you.");
        } else {
            performAction(input);
        }
    }

    private void goTo(String target) {
        Location place = LocationFactory.create(target);
        if (place == null) {
            System.out.println("Invalid location!");
            return;
        }
        if (place.name.equals(currentLocation.name)) {
            System.out.println("You are already here!");
            return;
        }
        currentLocation = place;
        System.out.println("Moving to..... " + currentLocation.name);
    }

    // a negative hunger change means the pet is less hungry
    private void performAction(String action) {
        if (!currentLocation.actions.contains(action)) {
            System.out.println("You can't do that in the " + currentLocation.name + "!");
            return;
        }
        settings.turnCounter++;
        System.out.println("Turn " + settings.turnCounter + ": " + action);

        if (action.equals("status")) {
            printStatus();
            return;
        } else if (action.equals("feed")) {
            if (pet.stage.equals("BABY")) {
                pet.changeHunger(-20);
            } else if (pet.stage.equals("TEEN")) {
                pet.changeHunger(-30);
            } else if (pet.stage.equals("ADULT")) {
                pet.changeHunger(-40);
            }
            pet.changeHappiness(10);
            System.out.println("You fed " + pet.name);
        } else if (action.equals("play")) {
            if (pet.stage.equals("BABY")) {
                pet.changeHunger(10);
                pet.changeHappiness(25);
            } else if (pet.stage.equals("TEEN")) {
                pet.changeHunger(15);
                pet.changeHappiness(20);
            } else if (pet.stage.equals("ADULT")) {
                pet.changeHunger(20);
                pet.changeHappiness(10);
            }
            System.out.println("You played with " + pet.name);
        } else if (action.equals("train")) {
            if (pet.stage.equals("BABY")) {
                System.out.println(pet.name + " is too young to train!");
                return;
            } else if (pet.stage.equals("TEEN")) {
                pet.changeHunger(25);
                pet.changeHappiness(-10);
                pet.changeTraining(10);
            } else if (pet.stage.equals("ADULT")) {
                pet.changeHunger(30);
                pet.changeHappiness(-15);
                pet.changeTraining(5);
            }
            System.out.println("You trained " + pet.name);
        }
        applyLocationBonus(action);
        checkEvolution();
    }

    private void applyLocationBonus(String action) {
        if (currentLocation.name.equals("Lobby") && action.equals("play")) {
            pet.changeHappiness(5);
        } else if (currentLocation.name.equals("Playground") && action.equals("play")) {
            pet.changeHappiness(10);
        } else if (currentLocation.name.equals("Training Room") && action.equals("train")) {
            pet.changeTraining(10);
        } else if (currentLocation.name.equals("Kitchen") && action.equals("feed")) {
            pet.changeHunger(-10);
        }
    }

    private void checkEvolution() {
        if (pet.stage.equals("BABY") && pet.happiness >= 40) {
            pet.stage = "TEEN";
            System.out.println("*** " + pet.name + " evolved into a TEEN! ***");
        } else if (pet.stage.equals("TEEN") && pet.training >= 30) {
            pet.stage = "ADULT";
            System.out.println("*** " + pet.name + " evolved into an ADULT! ***");
        }
    }

    private void printStatus() {
        System.out.println("========================");
        System.out.println("Virtual Pet: " + pet.name + " (" + pet.stage + ")");
        System.out.println("Hunger Level: " + pet.hunger + " / " + settings.maxLevel);
        System.out.println("Happiness Level: " + pet.happiness + " / " + settings.maxLevel);
        System.out.println("Training Level: " + pet.training + " / " + settings.maxLevel);
        System.out.println("Age: " + pet.getAge() + " turns");
        if (pet.stage.equals("BABY")) {
            System.out.println(pet.name + " is tiny and needs lots of playtime.");
        } else if (pet.stage.equals("TEEN")) {
            System.out.println(pet.name + " is full of energy and ready to train.");
        } else if (pet.stage.equals("ADULT")) {
            System.out.println(pet.name + " is fully grown and strong.");
        }
        System.out.println("========================");
    }
}
