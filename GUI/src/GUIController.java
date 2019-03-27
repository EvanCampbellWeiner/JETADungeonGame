
import java.io.FileInputStream;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GUIController {
//Rectangle objects serving as tiles in map gui
    public static Rectangle tile1;
    public static Rectangle tile2;
    public static Rectangle tile3;
    public static Rectangle tile4;
    public static Rectangle tile5;
    public static Rectangle tile6;
    public static Rectangle tile7;
    public static Rectangle tile8;
    public static Rectangle tile9;
    public static Rectangle tile10;
    public static Rectangle tile11;
    public static Rectangle tile12;
    public static Rectangle tile13;
    public static Rectangle tile14;
    public static Rectangle tile15;
    public static Rectangle tile16;
    public static Rectangle tile17;
    public static Rectangle tile18;
    public static Rectangle tile19;
    public static Rectangle tile20;
    public static Rectangle tile21;
    public static Rectangle tile22;
    public static Rectangle tile23;
    public static Rectangle tile24;
    public static Rectangle tile25;


    private int[][] tiles=new int[5][5];
    private Rectangle[][]grid=new Rectangle[5][5];


//Method that changes the colour of a given rectangle
    public static void setColour (Rectangle tile, String colourCode){
        tile.setFill(Paint.valueOf(colourCode));
    }


    //future method that will get passes an array and will update board tile colours accordingly
    public static void updateBoard(int[][]array,Rectangle[][]tile){
        for(int i=0; i<5; i++){
            for(int t=0; t<5; t++){
                if(array[i][t]==0){setColour(tile[i][t],"#000000");}
                else if(array[i][t]==1){setColour(tile[i][t],"#FFFFFF");}
                else  if(array[i][t]==-1){setColour(tile[i][t],"#AAAAAA");}
            }
        }
    }

    //When key is pressed, do something.
    public void keyPressed(KeyEvent event) {
        setArray();
        if(grid[0][0]==null){
            grid[0][0]=tile1;grid[0][1]=tile1;grid[0][2]=tile1;grid[0][3]=tile1;grid[0][4]=tile1;
            grid[1][0]=tile1;grid[1][1]=tile1;grid[1][2]=tile1;grid[1][3]=tile1;grid[1][4]=tile1;
            grid[2][0]=tile1;grid[2][1]=tile1;grid[2][2]=tile1;grid[2][3]=tile1;grid[2][4]=tile1;
            grid[3][0]=tile1;grid[3][1]=tile1;grid[3][2]=tile1;grid[3][3]=tile1;grid[3][4]=tile1;
            grid[4][0]=tile1;grid[4][1]=tile1;grid[4][2]=tile1;grid[4][3]=tile1;grid[4][4]=tile1;
        }
        updateBoard(tiles,grid);
    }
    public void setArray(){
        for(int i=0; i<tiles.length;i++){
            for (int t=0; t<tiles.length;t++){
                tiles[i][t]=0;
            }
        }
    }

   //future method that will get passes an array and will update board tile colours accordingly
  /*  public static void updateBoard( "array of some kind"){
        for(int i = 0; i<arr.length; i++){
            for(int t = 0; t<arr.length; t++){
            if (array[i][t] == whatever){
                   tilewhatever.setColour whatever colour

                   }
                   }
                   }
   */
//Start menu button methods
public void pressNewGameButton(ActionEvent event){

    }
    public void pressLoadGameButton(ActionEvent event){

    }
    public void pressExitButton(ActionEvent event){

    }



}
