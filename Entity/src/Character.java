import sun.rmi.runtime.NewThreadAction;

public abstract class Character extends Entity {
    private String Name;
    private Weapon[] Sheath;
    private Consumable[] PoshionSlot;
    private int maxHealth;
    private int currentHealth;
    private int level;
    private int experance;

    Character(String Name, int x, int y, int z) {
        super(x, y, z);
        Weapon Punch = new Weapon(true, 1, "Punch");
        Weapon WeaponArray[] = new Weapon[1];
        WeaponArray[0]=Punch;
        this.Sheath = WeaponArray;

        Consumable Waterbottle = new Consumable(1, "drink of placebo");
        Consumable ConsumibleArray[] = new Consumable[1];
        ConsumibleArray[0]=Waterbottle;
        this.PoshionSlot=ConsumibleArray;

        this.Name = Name;
        this.maxHealth = (10 + (level * 2));
        this.currentHealth = maxHealth;
        this.experance = 5;
        this.level = 1;
    }
    Character(String Name, int x, int y, int z, int health, Weapon Sword) {
        super(x, y, z);
        Weapon WeaponArray[] = new Weapon[1];
        WeaponArray[0] = Sword;
        this.Sheath = WeaponArray;


        Consumable Waterbottle = new Consumable(1, "placebo");
        Consumable ConsumibleArray[] = new Consumable[1];
        ConsumibleArray[0]=Waterbottle;
        this.PoshionSlot = ConsumibleArray;


        this.Name = Name;
        this.maxHealth = health;
        this.currentHealth = maxHealth;
        this.experance = 0;
        this.level = 0;
    }

    public abstract void startTurn(Floor Map);
    public abstract void gameOver();

    @Override
    public boolean interact(Character Attacker){
        return true;
    }

    public boolean Move(Floor Map,int move) {
        if (move >= 1 && move <= 4) {
            int xChange=0;
            int yChange=0;
            switch (move) {
                case 1:
                    xChange = 0;
                    yChange = 1;
                    break;
                case 2:
                    xChange = 1;
                    yChange = 0;
                    break;
                case 3:
                    xChange = 0;
                    yChange = -1;
                    break;
                case 4:
                    xChange = -1;
                    yChange = 0;
                    break;
            }
            if (!Map.GetTile(getX()+xChange, getY()+yChange)) {
                if ((Map.getTileEnemy(getX() + xChange, getY() + yChange)) == 0) {
                    setX(getX()+xChange);
                    setY(getY()+yChange);
                    return true;
                }else{
                    int Enemy = Map.getTileEnemy(getX()+xChange,getY()+yChange);
                    System.out.print(Enemy+" Should Fight");
                    //fight condishio
                    setX(getX()+xChange);
                    setY(getY()+yChange);
                    return true;
                }
            }else{
                return false;
            }
        }else{
            return false;
        }
    }//move might work "untested" // can't find enemy to attack it's a scoping problem

    public void Consume(int potion) {
        if (potion <= 0 || potion >= PoshionSlot.length - 1) {
            upCurrentHealth(PoshionSlot[potion].use());
            removeConsumible(potion);
        }else{
            System.out.print("Error no poshion found // can't be used");
        }

    }//also untested but with will most likely work

    public void defence(int damage, boolean type) {
        if (type) {
            upCurrentHealth(damage * -1);
        } else if ((level - damage) <= 1) {
            upCurrentHealth(level - damage);
        }
    }//needs to use arrmor chart

    public void attack(Weapon Sword, Character Target) {
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
    }//fix defence to use arrmor charts

    private void levelUp(){
        level+=1;
        maxHealth+=2;
        currentHealth = maxHealth;
        experance=0;
    }

    @Override
    public void print() {
        System.out.print(" " + Name + ", Lv " + level + " + " + experance + "xp ("+getX()+","+getY()+","+getZ()+")\n Hp(" + currentHealth + "\\" + maxHealth + ")\n");
        for (int loop = 0; loop <= Sheath.length - 1; loop++) {
            //System.out.print(loop);
            Sheath[loop].printWeapon();
        }
        if(PoshionSlot.length!=0) {
            for (int loop2 = 0; loop2 <= PoshionSlot.length - 1; loop2++) {
                //System.out.print(loop2);
                PoshionSlot[loop2].printEffect();
            }
        }
        System.out.print("\n");
    }

    public int getlevel() { return level; }
    public int getExperance() { return experance; }
    public String getName() { return Name; }
    public Weapon getWeaponBackpack(int selected) {
        if(selected<=0||selected>=Sheath.length-1){
            return Sheath[selected];
        }
        else{
            return new Weapon(true,1,"Error Weapon Error //Failed to pick item");
        }
    }
    public Consumable getConsumiblePocket(int selected){
        if(selected<=0||selected>=PoshionSlot.length-1){
            return PoshionSlot[selected];
        }else{
            return new Consumable(0,"Error Consumible Error// Failed to pick item");
        }
    }
    public void pickUpNewWeapon(Weapon Sword){
        Weapon[] NewWeaponArray = new Weapon[Sheath.length+1];
        System.arraycopy(Sheath,0,NewWeaponArray,0,Sheath.length);
        NewWeaponArray[NewWeaponArray.length-1]=Sword;
        this.Sheath=NewWeaponArray;
    }
    public void pickUpNewConsumible(Consumable Postion){
        Consumable[] NewConsumible = new Consumable[PoshionSlot.length+1];
        System.arraycopy(PoshionSlot,0,NewConsumible,0,PoshionSlot.length);
        NewConsumible[NewConsumible.length-1]=Postion;
        this.PoshionSlot=NewConsumible;
    }
    public void removeWeapon(int toRemove){
        //System.out.print("="+Sheath.length+"\n\n");
        Weapon[] newWeaponArray = new Weapon[Sheath.length-1];
        for(int loop=0;loop<=newWeaponArray.length-1;loop++){
            //System.out.print(loop);
            if(loop<toRemove){
                newWeaponArray[loop]=Sheath[loop];
            }else{
                newWeaponArray[loop]=Sheath[loop+1];
            }
        }
        this.Sheath=newWeaponArray;


    }
    public void removeConsumible(int toRemove){
       Consumable[] newPoshionSlot = new Consumable[PoshionSlot.length-1];
       for(int loop=0;loop<=newPoshionSlot.length-1;loop++){
           if(loop<toRemove){
               newPoshionSlot[loop]=PoshionSlot[loop];
           }else{
               newPoshionSlot[loop]=PoshionSlot[loop+1];
           }
       }
        this.PoshionSlot=newPoshionSlot;
    }

}
