// the virtual pet and its name, life stage, hunger, happiness and training levels.
class Pet {
    String name;
    String stage = "BABY"; // BABY, TEEN or ADULT
    int hunger = 50;        // lower is better: 0 means full
    int happiness = 10;
    int training = 0;
    private GameSettings settings;

    Pet(String name, GameSettings settings) {
        this.name = name;
        this.settings = settings;
    }

    void changeHunger(int points) {
        hunger = bound(hunger + points);
    }

    void changeHappiness(int points) {
        happiness = bound(happiness + points);
    }

    void changeTraining(int points) {
        training = bound(training + points);
    }

    int getAge() {
        return settings.turnCounter;
    }

    private int bound(int level) {
        if (level > settings.maxLevel) {
            return settings.maxLevel;
        } else if (level < 0) {
            return 0;
        }
        return level;
    }
}
