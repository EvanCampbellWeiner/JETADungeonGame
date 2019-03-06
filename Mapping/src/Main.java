public class Main {

    public static void main(String[] args) {
        ;
        //System.out.println("Hello World!");
        World SlimeLand = new World(3);
        Floor Maze = new Floor(12,12);
        Floor Cayos = new Floor(14,14);
        //Floor Tunnel = new Floor(30,30);

        Cayos.autoFillFloor(8);

        Maze.buildRectangle(4,3,1,1);
        Maze.buildRectangle(8,1,2,2);
        Maze.buildRectangle(2,8,8,2);
        Maze.buildRectangle(8,4,3,7);
        Maze.buildRectangle(1,1,0,0);


       //if(Maze.GetTile(-2,-2)){System.out.print("Yes");}

        //Tunnel.buildTunnel(28,28,2,2);


        SlimeLand.customFloor(Cayos,0);
        SlimeLand.customFloor(Maze,1);
       //SlimeLand.customFloor(Tunnel,2);

        SlimeLand.printWorld();


    }
}
