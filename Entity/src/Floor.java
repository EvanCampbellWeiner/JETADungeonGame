import java.util.Random;
public class Floor{
    private Random random=new Random();
    private Tile[][] Map;

    //floors must be at least 8 by 8
    Floor(int xSize,int ySize){
        if(xSize<8||ySize<8){
            this.Map= new Tile[8][8];
        }else {
            this.Map = new Tile[xSize][ySize];
        }
        for(int x=0;x<=Map.length-1;x++){
            for(int y=0;y<=Map[0].length-1;y++){
                Map[x][y]=new Tile();
            }
        }

    }



    //build's a room by setting all tilts to empty
    public void buildRectangle(int xSize,int ySize, int xDistance,int yDistance){

        if(xSize+xDistance>=Map.length||ySize+yDistance>=Map[0].length){
            System.out.println("Out of bounds Error");
        }else{
            for(int x =0;x<=xSize-1;x++){
                for(int y =0;y<=ySize-1;y++){
                    Map[xDistance+x][yDistance+y] = new Tile(false);
                }
            }
        }
    }

    //print's the floor as a binary map (*use for testing replace with visual effects later)
    public void printFloor(){
        //this needs to be replaced with a visual system but for now i'm testing with it

        for(int y =Map[0].length-1 ;y>=0;y--){
            //System.out.print(x);
            for (int x=0;x<=Map.length-1;x++){
                //System.out.print(y+"\n");
                if(Map[x][y].getWall()){
                    System.out.print(0);
                }else{
                    System.out.print(" ");
                }
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }

    public boolean GetTile(int x,int y){
        if(x<0||x>Map.length-1||y<0||y<Map[0].length-1){
            return true;
        }else{
            return Map[x][y].getWall();
        }

    }

    //Tile class is a single point on the map you should't touch this ideally
    private class Tile{
        private boolean wall;
        //Add tile info here

        Tile(boolean wall){
            this.wall=wall;
        }
        Tile(){
            this.wall=true;
        }
        public boolean empty(){
            return wall;
        }

        public void setWall(boolean wall) {
            this.wall = wall;
        }
        public boolean getWall() {
            return wall;
        }
    }

    //builds a path from point A to B
    public void buildTunnel(int xPointA,int yPointA,int xPointB,int yPointB){
        //this should cut a 1 wide path between two point's using build rectangle

        /*
            A
            0
            0
            0
            0
            0
            00000B
                turning all the into a hallway in other's words a path.
         */
    }

    //Randomly generate a floor
    public void autoFillFloor(int rectangles){

        if(Map.length<12||Map[0].length<12){
            buildRectangle(Map.length-2,Map[0].length-2,1,1);
        }else{
            int x, y ,spaceX, spaceY; //place space x joke here

            for(int loop=0;loop<=rectangles;loop++){
                spaceX = random(1,Map.length-8);
                spaceY = random(1,Map[0].length-8);
                x = random(4,8);
                y =random(4,8);
                //System.out.print("("+x+","+y+")");
                buildRectangle(x,y,spaceX,spaceY);
            }
        }




    }

    //spits out random numbers
    private int random(int low,int high){
        if(high<low) {
            return (high + random.nextInt((low - high)));
        }else {
            return (low+random.nextInt((high-low)));
        }
    }

}