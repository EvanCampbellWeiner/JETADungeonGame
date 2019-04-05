public class Weapon extends Item{
    private int damage;
    private boolean damageType;//if true pirce damage

    Weapon(boolean type, int damage,String Name){
        super(Name);
        this.damageType = type;
        this.damage = damage;
    }

    //use by print full && attack loop
    public void printWeapon(){
        if(damageType) {
            System.out.print("  > "+getName()+" pierce "+damage+"\n");
        }else{
            System.out.print("  > "+getName()+" blunt "+damage+"\n");
        }
    }

    public int getDamage() {
        return damage;
    }
    public boolean getDamageType(){return  damageType;}
}
