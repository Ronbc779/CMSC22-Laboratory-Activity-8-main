// creature type, HP, attack, speed, moves, status and potions.
class Creature {
    String name;
    String type; // FIRE, WATER or GRASS
    int maxHp;
    int hp;
    int attack;
    int speed;
    String[] moves;
    String status = "NORMAL"; // NORMAL, BURNED, ASLEEP or PARALYZED
    int potions = 2;

    Creature(String name, String type, int maxHp, int attack, int speed, String[] moves) {
        this.name = name;
        this.type = type;
        this.maxHp = maxHp;
        this.hp = maxHp;
        this.attack = attack;
        this.speed = speed;
        this.moves = moves;
    }

    boolean isFainted() {
        return hp <= 0;
    }
}
