public class Main {

    public static void main(String[] args) {
        Manager GameManger = new Manager(new Player("Test Subject",2,2,0));
        Weapon fireDeathAxe = new Weapon(true,6,"Death Axe");
        Enemy Hunter = new Enemy("Hunter",2,3,2,12,fireDeathAxe);
        GameManger.addEnemy(Hunter);
        World Tunnel = new World(1);
        Floor Path = new Floor(12,12);
        Path.buildRectangle(3,3,1,1);
        Path.buildRectangle(3,3,8,8);
        Tunnel.customFloor(Path,0);
        GameManger.addWorld(Tunnel);
        GameManger.PrintFull();

/*
        Hunter.print();
        Jared.attack(Jared.getWeaponBackpack(0),Hunter);
        Hunter.attack(Hunter.getWeaponBackpack(0),Jared);
        Jared.printInfo();
        Hunter.printInfo();
        Weapon fireDeathAxe = new Weapon(true,6,"Death Axe");
        Enemy Hunter = new Enemy("Hunter",2,3,2,12,fireDeathAxe);
        Enemy Minon = new Enemy("Minon",5,5,0,2,fireDeathAxe);
*/

    }
}
