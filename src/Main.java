import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            Character player1 = chooseCharacter(1);
            Character player2 = chooseCharacter(2);
            battleLoop(player1, player2);
        } catch (Exception e) {
            System.out.println("Fatal error: " + e.getMessage());
        } finally {
            System.out.println("Game session ended.");
        }
    }

    static Character chooseCharacter(int playerNumber) {
        while (true) {
            try {
                System.out.println("Player " + playerNumber + ", choose your class:");
                System.out.println("1. Warrior\n2. Mage\n3. Archer\n4. Paladin\n5. Assassin");
                int choice = Integer.parseInt(scanner.nextLine());

                if (choice < 1 || choice > 5) {
                    throw new IllegalArgumentException("Invalid class selection");
                }

                System.out.print("Enter character name: ");
                String name = scanner.nextLine();

                return switch (choice) {
                    case 1 -> new Warrior(name);
                    case 2 -> new Mage(name);
                    case 3 -> new Archer(name);
                    case 4 -> new Paladin(name);
                    case 5 -> new Assassin(name);
                    default -> throw new IllegalArgumentException();
                };
            } catch (NumberFormatException e) {
                System.out.println("❌ Input must be a number. Try again.");
            } catch (IllegalArgumentException e) {
                System.out.println("❌ " + e.getMessage() + " Try again.");
            }
        }
    }




    static void battleLoop(Character p1, Character p2) {
        Character[] turnOrder = {p1, p2};
        int turn = 0;
        while (p1.isAlive() && p2.isAlive()) {
            Character attacker = turnOrder[turn % 2];
            Character defender = turnOrder[(turn + 1) % 2];

            System.out.println("\nTurn " + (turn + 1) + ": " + attacker.name + "'s move");
            System.out.println("1. Attack\n2. Special Ability");
            String action = scanner.nextLine();
            try {
                if (action.equals("1")) {
                    attacker.attack(defender);
                } else if (action.equals("2")) {
                    attacker.specialAbility(defender);
                } else {
                    throw new InvalidActionException("Unknown action: " + action);
                }
            } catch (InvalidActionException e) {
                System.out.println("❌ " + e.getMessage());
                continue;
            }
            System.out.println("Status: " + p1 + " vs " + p2);
            turn++;
        }

        System.out.println("\n🏆 Winner: " + (p1.isAlive() ? p1.name : p2.name));
    }
}
