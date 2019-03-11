public class Enemy extends Charator{

    Enemy(String Name,int x,int y,int z,int health,Weapon Sword){
        super(Name,x,y,z,health,Sword);
    }

    @Override
    public void gameOver() {
        //call game manager to kill
    }

    @Override
    public void startTurn() {
        //should be automated
    }
}
