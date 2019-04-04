import java.util.Arrays;
import java.util.Scanner;

public class Player extends Character {
    //Declaring Integers and scanner
    Scanner scanner = new Scanner(System.in);
    private int level;
    private int experance;
    private final int infintorySize = 2;

    //Declaring what parameters player needs
    Player(String Name, int x, int y, int z, Manager Management, int health, int armor, Weapon[] Swords, Consumable[] Potions, int exp, int level) {
        super(Name, x, y, z, -1, Management, health, armor, Swords, Potions);
        this.experance = exp;
        this.level = level;
        setfriendly(false);
        getMyManager().playerLocation(x, y, z);
    }

    //Creating new characters
    Player(String Name, int x, int y, int z, Manager Management, Weapon Sword, Consumable Potion) {
        super(Name, x, y, z, -1, Management, 10, 1, Sword, Potion);
        this.level = 0;
        this.experance = 0;
        setfriendly(false);
        getMyManager().playerLocation(x, y, z);
    }

    //Getter for level
    public int getLevel() {
        return level;
    }

    //Getter for experience
    public int getExperance() {
        return experance;
    }

    //method for leveling up
    private void levelUp() {
        this.experance = 0;
        level++;
        setMaxHealth(getMaxHealth() + 2);
    }

    @Override
    //game over message by calling game manager to kill this case or respawn
    public void gameOver() {
        System.out.print("You Were killed");
        setAlive(false);
        getMyManager().exitGame(Player.this);

    }

    @Override
    //Attack method
    public void attack(Weapon Sword, Character Target) {
        super.attack(Sword, Target);
        experance++;
    }

    @Override
    //looting the enemy method
    public void takeLoot(Weapon Looted) {
        int pick;
        Looted.printWeapon();
        System.out.print("\nWould you like to take this Weapon?'\n0: No\n1: Yes");
        pick = scanner.nextInt();

        if (pick == 1) {
            if (getSheath().length < infintorySize) {
                pickUpNewWeapon(Looted);
            } else {
                System.out.print("Discard a Weapon\n");
                printWeapon();
                System.out.print(getSheath().length + " ");
                Looted.printWeapon();
                pick = scanner.nextInt();
                if (pick > 0 && pick < getSheath().length) {
                    if (pick != getSheath().length)
                        removeWeapon(pick);
                    pickUpNewWeapon(Looted);
                }
            }
        }
    }

    @Override
    //weapon to pick what weapon to use
    public Weapon pickWeapon() {
        System.out.print("\n");
        printWeapon();
        int pick;
        do {
            pick = scanner.nextInt();
        } while (!(pick >= 0 && pick <= this.getSheath().length - 1));
        return getWeaponBackpack(pick);
    }

    @Override
    //Method that starts the players turn allowing you to move
    public void startTurn() {
        int pick;
        boolean validator = true;

        getMyManager().printMapWide(false, 4);//nice mapping

        //int Map[][] = getMyManager().buildMap(3);
        //1System.out.print(Arrays.deepToString(Map));

        if (experance > Math.pow(level, 2)) {
            levelUp();
        }

        if (getCurrentHealth() > getMaxHealth()) {
            upCurrentHealth(-1);
        } else if (getCurrentHealth() < getMaxHealth()) {
            upCurrentHealth(1);
        }

        do {


            System.out.print("\n " + getName() + " lv:" + level + " ep:" + experance + "\n  1  \n4-x-2\n  3  \n5: Inventory\n0: ExitGame");
            print();
            pick = scanner.nextInt();
            validator = true;
            switch (pick) {
                case 0:
                    getMyManager().exitGame(Player.this);
                    break;
                case 1:
                    validator = Move(getX(), (getY() + 1));
                    break;
                case 2:
                    validator = Move((getX() + 1), getY());
                    break;
                case 3:
                    validator = Move(getX(), (getY() - 1));
                    break;
                case 4:
                    validator = Move((getX() - 1), getY());
                    break;
                case 5:
                    validator = openInfintoryUsePotion();
                    break;
                default:
                    validator = false;
            }
        } while (!validator);
        getMyManager().setPlayerLocation(getX(), getY(), getZ());
    }

    @Override
    //Method that makes the moves of the player
    public void teleport(int x, int y, int z) {
        super.teleport(x, y, z);
        getMyManager().setPlayerLocation(getX(), getY(), getZ());
    }
}
