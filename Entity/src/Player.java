import java.util.Scanner;

public class Player extends Character{
    Scanner scanner = new Scanner(System.in);

    Player(String Name, int x, int y, int z){
        super(Name,x,y,z);
    }


    @Override
    public void gameOver() {
        //calls game manager to kill this case or respawn
    }

    @Override
    public void startTurn(Floor Map) {
        int pick;
        boolean madeMove;
        do{
           madeMove = false;
            System.out.print("What to do?\n0: Quit 1:Up 2:Right 3:Down 4:Left\n5: Consume:"+getConsumiblePocket(0).printString()+"\n6: Consume"+getConsumiblePocket(1).printString()+"\n7: Consume "+getConsumiblePocket(2).printString()+"\n   :");
            pick = scanner.nextInt();
            if(pick==0){

            }

            if(pick<=4&&pick>=1){
                madeMove = Move(Map,pick);
            }
        }while(!madeMove);

    }

}
