abstract class Character {
    String name;
    int health;
    int attackPower;

    public Character(String name, int health, int attackPower) {
        this.name = name;
        this.health = health;
        this.attackPower = attackPower;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public void takeDamage(int damage) {
        health -= damage;
        if (health < 0) health = 0;
    }

    public abstract void specialAbility(Character target) throws InvalidActionException;

    public void attack(Character target) throws InvalidActionException {
        if (!this.isAlive()) throw new InvalidActionException(name + " is already defeated!");
        if (!target.isAlive()) throw new InvalidActionException(target.name + " is already defeated!");

        target.takeDamage(attackPower);
        System.out.println(name + " attacks " + target.name + " for " + attackPower + " damage.");
    }

    @Override
    public String toString() {
        return name + " [HP: " + health + "]";
    }
}

class Warrior extends Character {
    public Warrior(String name) {
        super(name, 100, 15);
    }

    @Override
    public void specialAbility(Character target) throws InvalidActionException {
        if (!isAlive()) throw new InvalidActionException(name + " can't use abilities when defeated.");
        System.out.println(name + " uses 'Berserker Rage' to deal double damage!");
        target.takeDamage(attackPower * 2);
    }
}

class Mage extends Character {
    public Mage(String name) {
        super(name, 70, 10);
    }

    @Override
    public void specialAbility(Character target) throws InvalidActionException {
        if (!isAlive()) throw new InvalidActionException(name + " can't cast spells when defeated.");
        System.out.println(name + " casts 'Fireball' dealing magic damage!");
        target.takeDamage(attackPower + 10);
    }
}

class Archer extends Character {
    public Archer(String name) {
        super(name, 80, 12);
    }

    @Override
    public void specialAbility(Character target) throws InvalidActionException {
        if (!isAlive()) throw new InvalidActionException(name + " can't shoot when defeated.");
        System.out.println(name + " performs 'Piercing Shot' ignoring armor!");
        target.takeDamage(attackPower + 5);
    }
}

class Paladin extends Character {
    public Paladin(String name) {
        super(name, 110, 10);
    }

    @Override
    public void specialAbility(Character target) throws InvalidActionException {
        if (!isAlive()) throw new InvalidActionException(name + " is defeated and can't heal.");
        System.out.println(name + " uses 'Holy Light' to heal self.");
        this.health += 20;
    }
}

class Assassin extends Character {
    public Assassin(String name) {
        super(name, 60, 18);
    }

    @Override
    public void specialAbility(Character target) throws InvalidActionException {
        if (!isAlive()) throw new InvalidActionException(name + " is defeated and can't backstab.");
        System.out.println(name + " uses 'Backstab' for critical damage!");
        target.takeDamage(attackPower * 2);
    }
}