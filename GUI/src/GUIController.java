
import java.io.FileInputStream;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
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
    //Labels for displaying player attributes and inventory
    public static Label name;
    public static Label health;
    public static Label weapon1;
    public static Label weapon2;
    public static Button item1Button;
    public static Button item2Button;
    public static Button item3Button;
    //buttons for inventory management
    public static Button button1;
    public static Button button2;
    public static Button button3;
    public static Button button4;
    public static Button button5;
    public static Button button6;
    public static Button button7;
    //buttons for character selection
    public static Button save1Button;
    public static Button save2Button;
    public static Button save3Button;
    public static Button backButton1;
    //buttons for start menu
    public static Button newGameButton;
    public static Button loadGameButton;
    public static Button exitButton;
    //buttons for world selection menu
    public static Button world1Button;
    public static Button newWorldButton;
    //buttons, image,  text and labels for CombatInterface
    public static Button combatWeapon1Button;
    public static Button combatWeapon2Button;
    public static Button combatItem1Button;
    public static Button combatItem2Button;
    public static Button combatItem3Button;
    public static Label playerHealthValueDisplay;
    public static Label enemyHealthValueDisplay;
    public static Text combatText;
    public static ImageView enemyImage;






    private int[][] tiles=new int[5][5];
    private Rectangle[][]grid=new Rectangle[5][5];


//Method that changes the colour of a given rectangle
    public static void setColour (Rectangle tile, String colourCode){
        tile.setFill(Paint.valueOf(colourCode));
    }


    //future method that will get passes an array and will update board tile colours accordingly
    public static void updateBoard(int[][]array,Rectangle[][]tile){
        setColour(tile13, "#0ef402");
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
            grid[0][0]=tile1;grid[0][1]=tile2;grid[0][2]=tile3;grid[0][3]=tile4;grid[0][4]=tile5;
            grid[1][0]=tile6;grid[1][1]=tile7;grid[1][2]=tile8;grid[1][3]=tile9;grid[1][4]=tile10;
            grid[2][0]=tile11;grid[2][1]=tile12;grid[2][2]=tile13;grid[2][3]=tile14;grid[2][4]=tile15;
            grid[3][0]=tile16;grid[3][1]=tile17;grid[3][2]=tile18;grid[3][3]=tile19;grid[3][4]=tile20;
            grid[4][0]=tile21;grid[4][1]=tile22;grid[4][2]=tile23;grid[4][3]=tile24;grid[4][4]=tile25;
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

    //Character selection button methods

    public void pressSave1Button(ActionEvent event){

    }
    public void pressSave2Button(ActionEvent event){

    }
    public void pressSave3Button(ActionEvent event){

    }
    public void pressBackButton1(ActionEvent event){

    }
    //GUI main screen button methods
    public void pressButton1(ActionEvent event){

    }

    public void pressButton2(ActionEvent event){

    }
    public void pressButton3(ActionEvent event){

    }
    public void pressButton4(ActionEvent event){

    }
    public void pressButton5(ActionEvent event){

    }
    public void pressButton6(ActionEvent event){

    }
    public void pressButton7(ActionEvent event){

    }
    public void pressItem1Button(ActionEvent event){

    }
    public void pressItem2Button(ActionEvent event){

    }
    public void pressItem3Button(ActionEvent event){

    }

    //World selection screen button methods
    public void pressWorld1Button(ActionEvent event){

    }
    public void pressNewWorldButton(ActionEvent event){

    }

    //Combat interface button methods
    public void pressCombatWeapon1Button(ActionEvent event){

    }
    public void pressCombatWeapon2Button(ActionEvent event){

    }
    public void pressCombatItem1Button(ActionEvent event){

    }
    public void pressCombatItem2Button(ActionEvent event){

    }
    public void pressCombatItem3Button(ActionEvent event){

    }



}
