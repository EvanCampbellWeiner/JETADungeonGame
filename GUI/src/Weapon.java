public class Weapon extends Item{
    //declaration of damage
    private int damage;
    private boolean damageType;

    //Declaring weapons with parameters
    Weapon(boolean type, int damage,String Name){
        super(Name);
        this.damageType = type;
        this.damage = damage;
    }

    //Print Function for the weapon stats
    public void printWeapon(){
        if(damageType) {
            System.out.print("  > "+getName()+" pierce "+damage+"\n");
        }else{
            System.out.print("  > "+getName()+" blunt "+damage+"\n");
        }
    }

    //Getter for Damage
    public int getDamage() {
        return damage;
    }

    //Getter for damage type
    public boolean getDamageType(){return  damageType;}
}
