public class Vampire extends Enemy{
    //Creating vampire and its parameters
    Vampire(String Name, int x, int y, int z, Manager Management,int health,int armor, Weapon Sword) {
        super(Name,x,y,z,11,Management,health,armor,Sword);
    }

    @Override
    //Method that gives vampire hath for the amount of attack it does
    public void attack(Weapon Sword, Character Target) {
        upCurrentHealth(Sword.getDamage());
        super.attack(Sword, Target);
    }
}
