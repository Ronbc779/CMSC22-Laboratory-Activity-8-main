import java.util.Random;

// plays the rounds, applies moves, types and statuses, and prints the result.
class Battle {
    private Creature player;
    private Creature rival;
    private String difficulty; // "EASY" or "HARD"
    private Random random;
    private int round = 0;

    Battle(Creature player, Creature rival, String difficulty, Random random) {
        this.player = player;
        this.rival = rival;
        this.difficulty = difficulty;
        this.random = random;
    }

    public boolean isOver() {
        return player.isFainted() || rival.isFainted();
    }

    public void showStatus() {
        System.out.println("\n--- Round " + (round + 1) + " ---");
        printCreature(rival);
        printCreature(player);
    }

    private void printCreature(Creature creature) {
        int filled = creature.hp * 10 / creature.maxHp;
        if (creature.hp > 0 && filled == 0) {
            filled = 1;
        }
        String bar = "";
        for (int i = 0; i < 10; i++) {
            bar += (i < filled) ? "#" : "-";
        }
        String statusText = "";
        if (creature.status.equals("BURNED")) {
            statusText = " (burned)";
        } else if (creature.status.equals("ASLEEP")) {
            statusText = " (asleep)";
        } else if (creature.status.equals("PARALYZED")) {
            statusText = " (paralyzed)";
        }
        System.out.println(creature.name + " [" + creature.type + "] HP " + creature.hp + "/" + creature.maxHp
                + " [" + bar + "]" + statusText);
    }

    public void showMoveMenu() {
        System.out.println("What will " + player.name + " do?");
        for (int i = 0; i < player.moves.length; i++) {
            String move = player.moves[i];
            System.out.println("  " + (i + 1) + ". " + move + " (" + getMoveType(move) + ", power " + getPower(move) + ")");
        }
        System.out.println("  4. Use potion (" + player.potions + " left)");
    }

    // choice: 0 to 2 is one of the player's moves, 3 is a potion.
    public void playRound(int choice) {
        round++;
        int rivalChoice = chooseRivalAction();
        if (getSpeed(player) >= getSpeed(rival)) {
            takeAction(player, rival, choice);
            if (!isOver()) {
                takeAction(rival, player, rivalChoice);
            }
        } else {
            takeAction(rival, player, rivalChoice);
            if (!isOver()) {
                takeAction(player, rival, choice);
            }
        }
        if (!isOver()) {
            endOfTurn(player);
            endOfTurn(rival);
        }
    }

    public void printResult() {
        System.out.println();
        printCreature(rival);
        printCreature(player);
        if (player.isFainted()) {
            System.out.println("\n" + player.name + " fainted. You lost the battle!");
        } else {
            System.out.println("\n" + rival.name + " fainted. You won the battle!");
        }
    }

    private void takeAction(Creature actor, Creature target, int choice) {
        if (!canAct(actor)) {
            return;
        }
        if (choice == 3) {
            usePotion(actor);
        } else {
            useMove(actor, target, actor.moves[choice]);
        }
    }

    private boolean canAct(Creature creature) {
        if (creature.status.equals("ASLEEP")) {
            // a 1 in 3 chance to wake up each turn.
            if (random.nextInt(3) == 0) {
                creature.status = "NORMAL";
                System.out.println(creature.name + " woke up!");
                return true;
            }
            System.out.println(creature.name + " is fast asleep.");
            return false;
        } else if (creature.status.equals("PARALYZED")) {
            // a 1 in 4 chance of not being able to move.
            if (random.nextInt(4) == 0) {
                System.out.println(creature.name + " is paralyzed and can't move!");
                return false;
            }
        }
        return true;
    }

    private void usePotion(Creature creature) {
        if (creature.potions == 0) {
            System.out.println(creature.name + " has no potions left!");
            return;
        }
        creature.potions--;
        creature.hp = Math.min(creature.maxHp, creature.hp + 30);
        System.out.println(creature.name + " used a potion and recovered HP. (" + creature.potions + " left)");
    }

    private void useMove(Creature attacker, Creature target, String move) {
        System.out.println(attacker.name + " used " + move + "!");
        int power = getPower(move);
        if (power > 0) {
            double multiplier = getMultiplier(getMoveType(move), target.type);
            double attackStat = attacker.attack;
            if (attacker.status.equals("BURNED")) {
                attackStat = attackStat / 2;
            }
            // damage = power x attack / 30, then doubled or halved by the type matchup.
            int damage = (int) (power * attackStat / 30 * multiplier);
            target.hp = Math.max(0, target.hp - damage);
            System.out.println("  It dealt " + damage + " damage.");
            if (multiplier > 1) {
                System.out.println("  It's super effective!");
            } else if (multiplier < 1) {
                System.out.println("  It's not very effective...");
            }
        }
        applyEffect(move, target);
        if (target.isFainted()) {
            System.out.println(target.name + " fainted!");
        }
    }

    private void applyEffect(String move, Creature target) {
        if (target.isFainted() || !target.status.equals("NORMAL")) {
            return;
        }
        // ember has a 30% chance to burn. Lullaby and Spore Cloud always work on a creature with no status.
        if (move.equals("Ember") && random.nextInt(100) < 30) {
            target.status = "BURNED";
            System.out.println("  " + target.name + " was burned!");
        } else if (move.equals("Lullaby")) {
            target.status = "ASLEEP";
            System.out.println("  " + target.name + " fell asleep!");
        } else if (move.equals("Spore Cloud")) {
            target.status = "PARALYZED";
            System.out.println("  " + target.name + " is paralyzed!");
        }
    }

    private void endOfTurn(Creature creature) {
        if (creature.status.equals("BURNED")) {
            creature.hp = Math.max(0, creature.hp - 6);
            System.out.println(creature.name + " is hurt by its burn. (-6 HP)");
            if (creature.isFainted()) {
                System.out.println(creature.name + " fainted!");
            }
        }
    }

    private int getSpeed(Creature creature) {
        if (creature.status.equals("PARALYZED")) {
            return creature.speed / 2;
        }
        return creature.speed;
    }

    // returns the rival's choice in the same form as the player's: 0 to 2 for a move, 3 for a potion.
    private int chooseRivalAction() {
        if (difficulty.equals("EASY")) {
            return random.nextInt(3);
        } else if (difficulty.equals("HARD")) {
            if (rival.hp < 30 && rival.potions > 0) {
                return 3;
            }
            int best = 0;
            double bestScore = -1;
            for (int i = 0; i < rival.moves.length; i++) {
                String move = rival.moves[i];
                double score = getPower(move) * getMultiplier(getMoveType(move), player.type);
                if (getPower(move) == 0) {
                    // a status move is worth using only if the player has no status yet.
                    score = player.status.equals("NORMAL") ? 25 : 0;
                }
                if (score > bestScore) {
                    bestScore = score;
                    best = i;
                }
            }
            return best;
        }
        return 0;
    }

    private int getPower(String move) {
        if (move.equals("Ember")) {
            return 40;
        } else if (move.equals("Wing Slash")) {
            return 35;
        } else if (move.equals("Sun Flare")) {
            return 60;
        } else if (move.equals("Water Gun")) {
            return 40;
        } else if (move.equals("Tail Slap")) {
            return 30;
        } else if (move.equals("Vine Whip")) {
            return 40;
        } else if (move.equals("Smash")) {
            return 45;
        }
        return 0;
    }

    private String getMoveType(String move) {
        if (move.equals("Ember") || move.equals("Sun Flare")) {
            return "FIRE";
        } else if (move.equals("Water Gun") || move.equals("Lullaby")) {
            return "WATER";
        } else if (move.equals("Vine Whip") || move.equals("Spore Cloud")) {
            return "GRASS";
        }
        return "NORMAL";
    }

    private double getMultiplier(String moveType, String targetType) {
        if (moveType.equals("FIRE") && targetType.equals("GRASS")) {
            return 2.0;
        } else if (moveType.equals("FIRE") && targetType.equals("WATER")) {
            return 0.5;
        } else if (moveType.equals("WATER") && targetType.equals("FIRE")) {
            return 2.0;
        } else if (moveType.equals("WATER") && targetType.equals("GRASS")) {
            return 0.5;
        } else if (moveType.equals("GRASS") && targetType.equals("WATER")) {
            return 2.0;
        } else if (moveType.equals("GRASS") && targetType.equals("FIRE")) {
            return 0.5;
        }
        return 1.0;
    }
}
