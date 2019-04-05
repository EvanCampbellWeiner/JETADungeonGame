public class Slime extends Enemy {
    Slime(String Name, int x, int y, int z, Manager Management, int health, int armor, Weapon Sword) {
        super(Name,x,y,z,3,Management,health,armor,Sword);
    }

    @Override
    public void attack(Weapon Sword, Character Target) {
        super.attack(Sword, Target);
        if(Target.getArmor()>=1){
            Target.setArmor((getArmor()-1));
        }
    }
}
