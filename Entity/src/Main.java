import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        //Scanner scanner = new Scanner(System.in);

        Manager GameManger = new Manager();


        Weapon fireDeathAxe = new Weapon(true,10,"Death Axe");
        Weapon Slap = new Weapon(false,4,"Hand of Pain");
        Weapon Smite = new Weapon(true,1,"Spit");
        Consumable Heal = new Consumable(10,"Greater Healing");

        //Enemy Hunter = new Enemy("Hunter",5,5,0,GameManger,12,1,Slap);
        Enemy Slime = new Enemy("Green Slime",1,4,0,GameManger,2,0,Smite);
        Enemy AnotherSlime = new Enemy("Blue Slime",7,4,0,GameManger,2,0,Smite);
        Player Neo = new Player("Hero",4,1,0,GameManger,fireDeathAxe);


        GameManger.addEntity(Neo);
        //GameManger.addEntity(Hunter);
        GameManger.addEntity(Slime);
        GameManger.addEntity(AnotherSlime);
        GameManger.generateStairs(4,7,0,8,1,1);

        //GameManger.PrintFull();
        GameManger.RunGame();

        //Hunter.pickUpNewWeapon(Slap);
        //Hunter.pickUpNewWeapon(fireDeathAxe);
        //Hunter.removeWeapon(1);//used to remove a weapon
        //Hunter.pickUpNewConsumible(Heal);
        //Hunter.removeConsumible(0);//used to remove a consumible
        //GameManger.combat(TestSubject,Spike);

        //GameManger.addEntity(FloorOneToTwo);
        //GameManger.addEntity(FloorTwoToOne);
        //GameManger.addEntity(SpikeTrap);

        /*
        Floor Path = new Floor(12,12);
        Path.buildRectangle(3,3,1,1);
        Path.buildRectangle(3,3,8,8);
        Path.buildTunnel(2,2,10,10);
        //Path.getTile(3,3).setEnemyLocation(0);
        GameManger.getGameWorld().addFloor(Path);
        */






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
