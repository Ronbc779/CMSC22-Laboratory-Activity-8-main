// creates a Creature from its name, with its stats and moves already set
class CreatureFactory {

    static Creature create(String name) {
        if (name.equals("Sarimanok")) {
            return new Creature("Sarimanok", "FIRE", 90, 15, 14, new String[] {"Ember", "Wing Slash", "Sun Flare"});
        } else if (name.equals("Sirena")) {
            return new Creature("Sirena", "WATER", 100, 13, 11, new String[] {"Water Gun", "Lullaby", "Tail Slap"});
        } else if (name.equals("Kapre")) {
            return new Creature("Kapre", "GRASS", 120, 12, 8, new String[] {"Vine Whip", "Spore Cloud", "Smash"});
        }
        return null;
    }
}
