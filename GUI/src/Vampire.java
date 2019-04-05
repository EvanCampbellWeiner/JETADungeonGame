public class Vampire extends Enemy{
    Vampire(String Name, int x, int y, int z, Manager Management,int health,int armor, Weapon Sword) {
        super(Name,x,y,z,11,Management,health,armor,Sword);
    }

    @Override
    public void attack(Weapon Sword, Character Target) {
        upCurrentHealth(Sword.getDamage());
        super.attack(Sword, Target);
    }
}
