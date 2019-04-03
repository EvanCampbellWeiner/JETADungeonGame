import java.util.Scanner;

public abstract class Character extends Entity {
    private String Name;
    private Weapon[] Sheath;
    private Consumable[] potionSlot;
    private int maxHealth;
    private int currentHealth;
    private int armor;
    private boolean unmoved;
    private boolean friendly;
    private boolean player=false;
    private final int type;


    @Override
    public void interact(Character Attacker) {
        if(!(Attacker.getfriendly()&&this.friendly)) {
            getMyManager().combat(Attacker, this);
        }//prevents enemy's from attacking each other
    }

    Scanner scanner = new Scanner(System.in);


    Character(String Name, int x, int y, int z, int type,Manager Management,int health, int armor) {
        super(x, y, z, Management);
        this.Sheath=new Weapon[0];
        this.potionSlot = new Consumable[0];
        Weapon Punch = new Weapon(true, 1, "Error weapon");
        pickUpNewWeapon(Punch);
        this.Name = Name;
        this.maxHealth=health;
        this.currentHealth = health;
        this.armor=armor;
        this.type=type;
    }


    Character(String Name, int x, int y, int z, int type, Manager Management,int health,int armor, Weapon Sword) {
        super(x, y, z, Management);
        this.Sheath = new Weapon[0];
        pickUpNewWeapon(Sword);

        this.potionSlot = new Consumable[0];
        this.armor=armor;
        this.Name = Name;
        this.maxHealth = health;
        this.currentHealth = health;
        this.type=type;
    }


    Character(String Name, int x, int y, int z, int type ,Manager Management, int health, int armor, Weapon[] Swords, Consumable[] Potions){
        super(x,y,z,Management);
        this.armor=armor;
        this.maxHealth=health;
        this.currentHealth=health;
        this.Name=Name;
        this.potionSlot=Potions;
        this.Sheath=Swords;
        this.type=type;

        for(int loop=0;loop<=Sheath.length-1;loop++){
            if(Sheath[loop].getDamage()<=0){
                removeWeapon(loop);
                loop--;
            }
        }
        for(int loop=0;loop<=potionSlot.length-1;loop++){
            if(potionSlot[loop].use()<=0){
                removeConsumible(loop);
                loop--;
            }
        }


    }


    Character(String Name, int x, int y, int z, int type, Manager Management, int health, int armor, Weapon Sword, Consumable Potion){
        super(x,y,z,Management);
        this.armor=armor;
        this.maxHealth=health;
        this.currentHealth=health;
        this.Name=Name;
        this.type=type;

        this.potionSlot = new Consumable[0];
        this.Sheath= new Weapon[0];
        this.pickUpNewWeapon(Sword);
        this.pickUpNewConsumible(Potion);
    }
    public boolean Move(int toX,int toY) {
        unmoved=true;
        if((getMyManager().teleport(getZ(),toX,toY,getZ(),getX(),getY()))){
            if(unmoved) {
                setX(toX);
                setY(toY);
            }
            //System.out.print(getName());
            return true;
        }else{
            return false;
        }
    }
    //}//move might work "untested" // can't find enemy to attack it's a scoping problem */
    public void Consume(int potion) {
        if (potion <= 0 || potion >= potionSlot.length) {
            upCurrentHealth(potionSlot[potion].use());
            removeConsumible(potion);
        }else{
            System.out.print("Error no potion found // can't be used");
        }

    }//also untested but with will most likely work

    public void defence(int damage, boolean type) {
        if (type) {
            upCurrentHealth(damage * -1);
        } else{
            if(((damage*-1)+armor)<0) {
                upCurrentHealth(((damage*-1) + armor));
            }
        }
        if(currentHealth<=0){gameOver();}
    }

    public void attack(Weapon Sword, Character Target) {
        Target.defence((Sword.getDamage()), (Sword.getDamageType()));
    }

    public abstract Weapon pickWeapon();

    public abstract void takeLoot(Weapon Looted);

    public void upCurrentHealth(int update) {
        currentHealth += update;
        if (currentHealth <= 0) {
            gameOver();
        }
    }

    public void printSimpleCombat(){
        System.out.print(Name+" ("+currentHealth+"/"+maxHealth+")\n");
        Sheath[0].printWeapon();
    }
    @Override
    public void print() {
        System.out.print(" " + Name + " ("+getX()+","+getY()+","+getZ()+")\n Hp(" + currentHealth + "\\" + maxHealth + ")\n");
        for (int loop = 0; loop <= Sheath.length - 1; loop++) {
            if(Sheath[loop].getDamage()!=0) {
                Sheath[loop].printWeapon();
            }
        }
        if(potionSlot.length!=0) {
            for (int loop2 = 0; loop2 <= potionSlot.length - 1; loop2++) {
                if(potionSlot[loop2].use()!=0) {
                    potionSlot[loop2].printEffect();
                }
            }
        }
        System.out.print("\n");
    }
    public void printWeapon(){
        for (int loop = 0; loop <= Sheath.length - 1; loop++) {
            //System.out.print(loop);
            System.out.print(loop+"  ");
            Sheath[loop].printWeapon();
        }
    }

    public boolean openInfintoryUsePotion(){
        int select;
        System.out.print("0: Exit Inventory\n");
        for(int loop=0;loop<=potionSlot.length-1;loop++){
            System.out.print(loop+1+": ");
            potionSlot[loop].printEffect();
        }
        select=scanner.nextInt();
        if(select==0){
            return false;
        }else if(select>=1&&select<=potionSlot.length){
            Consume(select-1);
            return true;
        }else{
            return false;
        }
    }
    public String getName() { return Name; }
    public Weapon getWeaponBackpack(int selected) {
        if(selected>=0&&selected<=Sheath.length-1){
            return Sheath[selected];
        }
        else{
            return new Weapon(true,1,"Error Weapon Error //Failed to pick item");
        }
    }
    public Weapon[] getSheath(){return Sheath;}
    public Consumable getConsumiblePocket(int selected){
        if(selected>=0&&selected<=potionSlot.length-1){
            return potionSlot[selected];
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
        Consumable[] NewConsumible = new Consumable[potionSlot.length+1];
        System.arraycopy(potionSlot,0,NewConsumible,0,potionSlot.length);
        NewConsumible[NewConsumible.length-1]=Postion;
        this.potionSlot=NewConsumible;
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
       Consumable[] newPoshionSlot = new Consumable[potionSlot.length-1];
       for(int loop=0;loop<=newPoshionSlot.length-1;loop++){
           if(loop<toRemove){
               newPoshionSlot[loop]=potionSlot[loop];
           }else{
               newPoshionSlot[loop]=potionSlot[loop+1];
           }
       }
        this.potionSlot=newPoshionSlot;
    }
    public void setUnmoved(boolean unmoved){
        this.unmoved=unmoved;
    }
    public int getType(){
        return type;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public boolean getfriendly(){return friendly;}
    public void setfriendly(boolean friendly){
        this.friendly = friendly;
    }
    public boolean getplayer(){return player;}
    public void setplayer(boolean player){this.player=player;}

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public int getArmor() {
        return armor;
    }
}
