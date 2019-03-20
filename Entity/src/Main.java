import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Manager GameManger = new Manager();
        Player TestSubject = new Player("Test Subject",2,2,0,GameManger);

        Weapon Spike = new Weapon(false,8,"Spike");
        Trap SpikeTrap = new Trap(Spike,3,3,0,GameManger);


        Weapon fireDeathAxe = new Weapon(true,2,"Death Axe");
        Weapon Slap = new Weapon(false,4,"Hand of Pain");
        Enemy Hunter = new Enemy("Hunter",5,5,0,GameManger,12,1,fireDeathAxe);
        Consumable Heal = new Consumable(10,"Greater Healing");
        Hunter.pickUpNewWeapon(Slap);
        Hunter.pickUpNewWeapon(fireDeathAxe);
        //Hunter.removeWeapon(1);//used to remove a weapon
        //Hunter.pickUpNewConsumible(Heal);
        //Hunter.removeConsumible(0);//used to remove a consumible
        //GameManger.combat(TestSubject,Spike);
        GameManger.addEntity(TestSubject);
        GameManger.addEntity(Hunter);
        //GameManger.addEntity(SpikeTrap);

        /*
        Floor Path = new Floor(12,12);
        Path.buildRectangle(3,3,1,1);
        Path.buildRectangle(3,3,8,8);
        Path.buildTunnel(2,2,10,10);
        //Path.getTile(3,3).setEnemyLocation(0);
        GameManger.getGameWorld().addFloor(Path);
        */
        GameManger.PrintFull();
        GameManger.RunGame();





        //Hunter.print();
        //TestSubject.attack(TestSubject.getWeaponBackpack(0),Hunter);
        //Hunter.attack(Hunter.getWeaponBackpack(0),TestSubject);


        //TestSubject.startTurn();
        /*
        Weapon fireDeathAxe = new Weapon(true,6,"Death Axe");
        Enemy Hunter = new Enemy("Hunter",2,3,2,12,fireDeathAxe);
        Enemy Minon = new Enemy("Minon",5,5,0,2,fireDeathAxe);
*/

    }
}
