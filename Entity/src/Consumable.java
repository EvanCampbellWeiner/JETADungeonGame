public class Consumable extends Item{
    private int restore;
    Consumable(int heal,String Name){
        super(Name);
        this.restore = heal;
    }
    public String printString(){return (""+getName()+" heals "+restore);}
    public void printEffect(){
        System.out.print("  > "+getName()+" heals "+restore+"\n");
    }
    public int use(){
        return restore;
    }
}
