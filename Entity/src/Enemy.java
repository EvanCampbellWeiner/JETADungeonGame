public class Enemy extends Character{

    Enemy(String Name,int x,int y,int z,int health,Weapon Sword){
        super(Name,x,y,z,health,Sword);
    }

    @Override
    public void gameOver() {
        //call game manager to kill
    }

    @Override
    public void startTurn(Floor Map) {
        //should be automated
    }
}
