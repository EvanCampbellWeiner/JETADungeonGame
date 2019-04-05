import java.util.Arrays;
import java.util.Scanner;

public class Player extends Character {
    Scanner scanner = new Scanner(System.in);
    private int level;
    private int experance;
    private final int infintorySize = 2;//we only saves your first two items,

    //Full Creator
    Player(String Name, int x, int y, int z, Manager Management, int health, int armor, Weapon[] Swords, Consumable[] Potions, int exp, int level) {
        super(Name, x, y, z, -1, Management, health, armor, Swords, Potions);
        this.experance = exp;
        this.level = level;
        setfriendly(false);
        getMyManager().playerLocation(x, y, z);
    }

    //load New Save
    Player(String Name, int x, int y, int z, Manager Management, Weapon Sword, Consumable Potion) {
        super(Name, x, y, z, -1, Management, 10, 1, Sword, Potion);
        this.level = 0;
        this.experance = 0;
        setfriendly(false);
        getMyManager().playerLocation(x, y, z);
    }

    //increase Max health // reset Epx
    private void levelUp() {
        this.experance = 0;
        level++;
        setMaxHealth(getMaxHealth() + 2);
    }

    @Override//when killed
    public void gameOver() {
        System.out.print("You Were killed");
        setAlive(false);
        getMyManager().exitGame(Player.this);
        //Exit Game Saves, yes we do save Even on death
    }

    @Override//increase Exp
    public void attack(Weapon Sword, Character Target) {
        super.attack(Sword, Target);
        experance++;
    }

    @Override//Take loot May need to discard if infintory full
    public void takeLoot(Weapon Looted) {
        int pick;
        Looted.printWeapon();
        System.out.print("\nWould you like to take this Weapon?'\n0: No\n1: Yes");
        pick = scanner.nextInt();

        if (pick == 1) {//Yes, i Want it.
            if (getSheath().length < infintorySize) {
                //Just Take it
                pickUpNewWeapon(Looted);
            } else {
                //To much Stuff
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

    @Override//Pick Weapon to Attack With
    public Weapon pickWeapon() {
        System.out.print("\n");
        printWeapon();
        int pick;
        do {
            pick = scanner.nextInt();
        } while (!(pick >= 0 && pick <= this.getSheath().length - 1));
        return getWeaponBackpack(pick);
    }

    @Override//Waiting State Chart,
    public void startTurn() {
        int pick;
        boolean validator = true;

        getMyManager().printMapWide(false, 4);//nice mapping

        //int Map[][] = getMyManager().buildMap(3);
        //System.out.print(Arrays.deepToString(Map));

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

    @Override//update placement in Manager
    public void teleport(int x, int y, int z) {
        super.teleport(x, y, z);
        getMyManager().setPlayerLocation(getX(), getY(), getZ());
    }

    public int getLevel() {
        return level;
    }
    public int getExperance() {
        return experance;
    }
}

