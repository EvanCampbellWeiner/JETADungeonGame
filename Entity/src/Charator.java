public abstract class Charator extends Entity {
    private String Name;
    private Weapon[] backPack = new Weapon[3];
    private Consumible[] pocket = new Consumible[3];
    private int maxHealth;
    private int currentHealth;
    private int level;
    private int experance;

    Charator(String Name, int x, int y, int z) {
        super(x, y, z);
        Weapon Punch = new Weapon(true, 1, "Punch");
        backPack[0] = Punch;
        backPack[1] = Punch;
        backPack[2] = Punch;

        Consumible Waterbottle = new Consumible(1, "placebo");
        pocket[0] = Waterbottle;
        pocket[1] = Waterbottle;
        pocket[2] = Waterbottle;

        this.Name = Name;
        this.maxHealth = (10 + (level * 2));
        this.currentHealth = maxHealth;
        this.experance = 5;
        this.level = 1;
    }
    Charator(String Name, int x, int y, int z, int health, Weapon Sword) {
        super(x, y, z);
        Weapon WeaponArray[] = new Weapon[3];
        Weapon Punch = new Weapon(true, 1, "Punch");
        WeaponArray[0] = Sword;
        WeaponArray[1] = Punch;
        WeaponArray[2] = Punch;

        Consumible Waterbottle = new Consumible(1, "placebo");
        pocket[0] = Waterbottle;
        pocket[1] = Waterbottle;
        pocket[2] = Waterbottle;

        this.backPack = WeaponArray;
        this.Name = Name;
        this.maxHealth = health;
        this.currentHealth = maxHealth;
        this.experance = 0;
        this.level = 0;
    }

    public abstract void startTurn();
    public abstract void gameOver();

    @Override
    public boolean interact(Charator Attacker){
        return true;
    }

    public boolean Move(int move){
        //    true is success
        //           1
        //         4 x 2
        //           3
        //
        switch (move) {
            case 1:
                setY((getY()+1));
                return true;
            case 2:
                setX((getX())+1);
                return true;
            case 3:
                setY((getY()-1));
                return true;
            case 4:
                setX(getX()-1);
                return true;
                default:
                    return false;
        }
    }


    public void Consume(int potion) {
        if (potion <= 0 || potion >= pocket.length - 1) {
            upCurrentHealth(pocket[potion].use());
            pocket[potion] = null;
            for (int loop = potion; loop + 1 == pocket.length - 1; loop++) {
                pocket[loop] = pocket[loop + 1];
            }
        }
    }

    public void defence(int damage, boolean type) {
        if (type) {
            upCurrentHealth(damage * -1);
        } else if ((level - damage) <= 1) {
            upCurrentHealth(level - damage);
        }
    }

    public void attack(Weapon Sword, Charator Target) {
        Target.defence((Sword.getDamage()), (Sword.getDamageType()));
    }

    private void upCurrentHealth(int update) {
        currentHealth += update;
        if (currentHealth <= 0) {
            gameOver();
        }
    }

    private double armourLevel(int value) {
        switch (value) {
            case 1:
                return .7;
            case 2:
                return .5;
            case 3:
                return .3;
            default:
                return 1;
        }
    }//fix this with combat

    private void levelUp(){
        level+=1;
        maxHealth+=2;
        currentHealth = maxHealth;
        experance=0;
    }

    @Override
    public void print() {
        System.out.print(" " + Name + ", Lv " + level + " + " + experance + "xp ("+getX()+","+getY()+","+getZ()+")\n Hp(" + currentHealth + "\\" + maxHealth + ")\n");
        for (int loop = 0; loop <= backPack.length - 1; loop++) {
            //System.out.print(loop);
            backPack[loop].printWeapon();
        }
        for (int loop2 = 0; loop2 <= pocket.length - 1; loop2++) {
            //System.out.print(loop2);
            pocket[loop2].printEffect();
        }
        System.out.print("\n");
    }

    public int getlevel() { return level; }
    public int getExperance() { return experance; }
    public String getName() { return Name; }
    public Weapon getWeaponBackpack(int selected) {
        if(selected<=0||selected>=backPack.length-1){
            return backPack[selected];
        }
        else{
            return new Weapon(true,1,"Error Weapon Error //Failed to pick item");
        }
    }
    public Consumible getConsumiblePocket(int selected){
        if(selected<=0||selected>=pocket.length-1){
            return pocket[selected];
        }else{
            return new Consumible(0,"Error Consumible Error// Failed to pick item");
        }
    }
}
