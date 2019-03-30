import java.sql.*;
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


        Player Neo = selectStart(GameManger);


        GameManger.addEntity(Neo);
        //GameManger.addEntity(Hunter);
        Enemy Demon = GameManger.generateEnemy(10);
        Demon.teleport(7,7,1);
        GameManger.addEntity(Demon);
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
    public static Player selectStart(Manager gameManager) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        String name = "Neo";
        int health = 100;
        int armour = 0;
        int weapon1 = 1;
        int weapon2 =-1;
        int consumable1 = -1;
        int consumable2 = -1;
        int consumable3 = -1;
        int exp = 0;
        int level =0;
        Weapon[] swords = new Weapon[2];
        Consumable[] potions = new Consumable[3];
        do {
            System.out.println("Would you like to load game (0) or start a new game (1)?");
            choice = scanner.nextInt();
        } while ((choice != 0) && (choice != 1));

        if (choice == 0) {
            try {

                Connection conn = DriverManager.getConnection("jdbc:sqlite:save1.db");
                Statement statement = conn.createStatement();
               /* statement.execute("CREATE TABLE IF NOT EXISTS \"Character\" (\n" +
                        "\t\"Name\"\tTEXT,\n" +
                        "\t\"Health\"\tINTEGER,\n" +
                        "\t\"Armour\"\tINTEGER,\n" +
                        "\t\"Weapon1\"\tINTEGER,\n" +
                        "\t\"Weapon2\"\tINTEGER,\n" +
                        "\t\"Consumable1\"\tINTEGER,\n" +
                        "\t\"Consumable2\"\tINTEGER,\n" +
                        "\t\"Consumable3\"\tINTEGER,\n" +
                        "\t\"Exp\"\tINTEGER,\n" +
                        "\t\"Level\"\tINTEGER\n" +
                        ")");*/

                ResultSet rs = statement.executeQuery("SELECT * FROM Character");
                //while the result set has a next row set values equal to the variables below and print
                while (rs.next()) {
                    name = rs.getString("Name");
                    health = rs.getInt("Health");
                    armour = rs.getInt("Armour");
                    weapon1 = rs.getInt("Weapon1");
                    weapon2 = rs.getInt("Weapon2");
                    consumable1 = rs.getInt("Consumable1");
                    consumable2 = rs.getInt("Consumable2");
                    consumable3 = rs.getInt("Consumable3");
                    exp = rs.getInt("Exp");
                    level = rs.getInt("Level");
                }

            /* we must close the statement and the connection as well. Does anyone know why we do that?
                If you close the connection first you will get an error.
             */
                statement.close();
                conn.close();

            } catch (SQLException e) {

                System.out.println("Something went wrong: " + e.getMessage());
                System.out.println("Will use default character.");

            }//end catch bracket
        }

        swords[0] = gameManager.generateWeapon(weapon1);
        swords[1] = gameManager.generateWeapon(weapon2);
        potions[0] = gameManager.generateConsumable(consumable1);
        potions[1] = gameManager.generateConsumable(consumable2);
        potions[2] = gameManager.generateConsumable(consumable3);
        Player neo = new Player(name, 4, 1, 0, gameManager,health, armour, swords, potions, exp, level);
            return(neo);
        }

    }
