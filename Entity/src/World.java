public class World {
    private Floor[] Level;
    World(int numberOfFloors){
        this.Level= new Floor[numberOfFloors];
        for(int loop=0;loop<=numberOfFloors-1;loop++){
            this.Level[loop]=new Floor(8,8);
        }
    }

    public void printWorld(){
        for(int loop=0;loop<=Level.length-1;loop++){
            System.out.print("lv_"+loop+"\n");
            Level[loop].printFloor();
        }
    }

    public void customFloor(Floor Floor,int placement) {
        Level[placement] = Floor;
    }
}
