
import java.io.FileInputStream;
import java.sql.*;
import java.util.Scanner;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
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
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.geometry.Insets;

public class GUIController {
    //Rectangle objects serving as tiles in map gui
    public Rectangle tile1;
    public Rectangle tile2;
    public Rectangle tile3;
    public Rectangle tile4;
    public Rectangle tile5;
    public Rectangle tile6;
    public Rectangle tile7;
    public Rectangle tile8;
    public Rectangle tile9;
    public Rectangle tile10;
    public Rectangle tile11;
    public Rectangle tile12;
    public Rectangle tile13;
    public Rectangle tile14;
    public Rectangle tile15;
    public Rectangle tile16;
    public  Rectangle tile17;
    public  Rectangle tile18;
    public Rectangle tile19;
    public Rectangle tile20;
    public  Rectangle tile21;
    public Rectangle tile22;
    public Rectangle tile23;
    public Rectangle tile24;
    public Rectangle tile25;
    public Rectangle tile26;
    public Rectangle tile27;
    public Rectangle tile28;
    public  Rectangle tile29;
    public  Rectangle tile30;
    public  Rectangle tile31;
    public Rectangle tile32;
    public Rectangle tile33;
    public Rectangle tile34;
    public Rectangle tile35;
    public Rectangle tile36;
    public Rectangle tile37;
    public Rectangle tile38;
    public Rectangle tile39;
    public Rectangle tile40;
    public  Rectangle tile41;
    public  Rectangle tile42;
    public  Rectangle tile43;
    public Rectangle tile44;
    public  Rectangle tile45;
    public Rectangle tile46;
    public Rectangle tile47;
    public Rectangle tile48;
    public Rectangle tile49;
    //Labels for displaying player attributes and inventory
    public Label name;
    public  Label health;
    public Label weapon1;
    public Label weapon2;
    public Button item1Button;
    public  Button item2Button;
    public Button item3Button;
    public Button backButton2;
    //buttons for inventory management
    public Button button1;
    public Button button2;
    public Button button3;
    public Button button4;
    public Button button5;
    public Button button6;
    public Button button7;
    //buttons for character selection
    public Button save1Button;
    public Button save2Button;
    public Button save3Button;
    public Button backButton1;
    //buttons for start menu
    public Button newGameButton;
    public Button loadGameButton;
    public Button exitButton;
    //buttons for world selection menu
    public Button world1Button;
    public Button newWorldButton;
    public Button worldSelectionBackButton;
    //Images
    public Image bear = new Image ("/Images/Bear-1.png.png");
    public Image bat = new Image ("/Images/Bat-1.png.png");
    public Image demon = new Image ("/Images/Demon-1.png.png");
    public Image dude = new Image ("/Images/Dude-1.png.png");
    public Image goblin = new Image ("/Images/Goblin-1.png.png");
    public Image skeleton = new Image ("/Images/Skeleton-1.png.png");
    public Image slime= new Image ("/Images/Slime-1.png.png");
    public Image spider= new Image ("/Images/Spider-1.png.png");
    public Image vampire= new Image ("/Images/Vampire-1.png.png");
    public Image wall= new Image ("/Images/wall-1.png.png");
    public Image witch = new Image ("/Images/Witch-1.png.png");
    public Image zombie= new Image ("/Images/Zombie-1.png.png");
    public Image downLadder = new Image ("/Images/ladderhole-1.png (2).png");
    public Image upLadder = new Image("/Images/New Piskel-1.png.png");


    public Color red=new Color(0.5,0,0,0);
    //buttons, image,  text and labels for CombatInterface
    public Button combatWeapon1Button;
    public Button combatWeapon2Button;
    public Button combatItem1Button;
    public Button combatItem2Button;
    public Button combatItem3Button;
    public Label playerHealthValueDisplay;
    public Label enemyHealthValueDisplay;
    public Text combatText;
    public ImageView enemyImage;
    //Buttons and TextField for Character creation page
    public Button enterNameButton;
    public Button characterCreationBackButton;
    public TextField enterNameField;

    public Button btnUp;
    public Button btnDown;
    public Button btnRight;
    public Button btnLeft;
    //Buttons for Game Over Screen
    public Button loadCharBtn;
    public Button gameOverExitButton;


    private int[][] tiles=new int[7][7];
    private Rectangle[][]grid=new Rectangle[7][7];


    //Method that changes the colour of a given rectangle
    public void setColour (Rectangle tile, String colourCode){
        tile.setFill(Paint.valueOf(colourCode));
    }


    //Method that takes in an array and updates the board accordingly
    public void updateBoard(int[][] array,Rectangle[][] tile){
        for(int i=0; i<7; i++){
            for(int t=0; t<7; t++){
                if(array[i][t]==0){setTileImage(tile[i][t],wall);}
                else if(array[i][t]==-1){setTileImage(tile[i][t], dude);}
                else if(array[i][t]==0){setTileImage(tile[i][t], wall);}
                else if(array[i][t]==2){setTileImage(tile[i][t], downLadder);}
                else if(array[i][t]==3){setTileImage(tile[i][t], slime);}
                else if(array[i][t]==4){setTileImage(tile[i][t], spider);}
                else if(array[i][t]==5){setTileImage(tile[i][t], bat);}
                else if(array[i][t]==6){setTileImage(tile[i][t], goblin);}
                else if(array[i][t]==8){setTileImage(tile[i][t], zombie);}
                else if(array[i][t]==9){setTileImage(tile[i][t], skeleton);}
                else if(array[i][t]==10){setTileImage(tile[i][t], bear);}
                else if(array[i][t]==11){setTileImage(tile[i][t], witch);}
                else if(array[i][t]==12){setTileImage(tile[i][t], vampire);}
                else if(array[i][t]==13){setTileImage(tile[i][t], demon);}
                else if(array[i][t]==14){setTileImage(tile[i][t], upLadder);}
                else{setColour((tile[i][t]),"#F32323");}
            }
        }
    }

    //When key is pressed, do something.
    public void move(int number) {
        setArray(number);
        if(grid[0][0]==null){
            grid[0][0]=tile1;grid[0][1]=tile2;grid[0][2]=tile3;grid[0][3]=tile4;grid[0][4]=tile5; grid[0][5]=tile6; grid[0][6]=tile7;
            grid[1][0]=tile8;grid[1][1]=tile9;grid[1][2]=tile10;grid[1][3]=tile11;grid[1][4]=tile12;grid[1][5]=tile13;grid[1][6]=tile14;
            grid[2][0]=tile15;grid[2][1]=tile16;grid[2][2]=tile17;grid[2][3]=tile18;grid[2][4]=tile19;grid[2][5]=tile20;grid[2][6]=tile21;
            grid[3][0]=tile22;grid[3][1]=tile23;grid[3][2]=tile24;grid[3][3]=tile25;grid[3][4]=tile26;grid[3][5]=tile27;grid[3][6]=tile28;
            grid[4][0]=tile29;grid[4][1]=tile30;grid[4][2]=tile31;grid[4][3]=tile32;grid[4][4]=tile33;grid[4][5]=tile34;grid[4][6]=tile35;
            grid[5][0]=tile36;grid[5][1]=tile37;grid[5][2]=tile38;grid[5][3]=tile39;grid[5][4]=tile40;grid[5][5]=tile41;grid[5][6]=tile42;
            grid[6][0]=tile43;grid[6][1]=tile44;grid[6][2]=tile45;grid[6][3]=tile46;grid[6][4]=tile47;grid[6][5]=tile48;grid[6][6]=tile49;
        }
        updateBoard(tiles,grid);
    }

    //When button up is pressed
    public void pressButtonUp(ActionEvent event){
        move(1);
    }
    public void pressButtonRight(ActionEvent event){
        move(2);
    }
    public void pressButtonLeft(ActionEvent event){
        move(3);
    }
    public void pressButtonDown(ActionEvent event){
        move(4);
    }

    public void setArray(int number){
        for(int i=0; i<tiles.length;i++){
            for (int t=0; t<tiles.length;t++){
                tiles[i][t]=number;
            }
        }
    }



    //Method to set an Tile (rectangle) on the map to an image
    public void setTileImage(Rectangle tile, Image image){
        tile.setFill(new ImagePattern(image));
    }
    //Start menu button methods
    public void pressNewGameButton(ActionEvent event){
        loadScene(event, "CharacterCreation.fxml");
    }
    public void pressLoadGameButton(ActionEvent event){
        loadScene(event, "CharacterSelection.fxml");
    }
    public void pressExitButton(ActionEvent event){
        Platform.exit();
    }

    //Character selection button methods

    public void pressSave1Button(ActionEvent event){
        EntityMain.selectStart(1,null);
        loadScene(event, "CharacterSelection.fxml");
    }
    public void pressSave2Button(ActionEvent event){
        EntityMain.selectStart(2,null);
        loadScene(event, "WorldSelectionMenu.fxml");
    }
    public void pressSave3Button(ActionEvent event){
        EntityMain.selectStart(3,null);
        loadScene(event, "WorldSelectionMenu.fxml");


    }
    public void pressBackButton1(ActionEvent event){
        loadScene(event, "StartMenu.fxml");
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
        setTileImage(tile1, bear);
    }
    public void pressItem2Button(ActionEvent event){
        setColour(tile1, "#111111");
    }
    public void pressItem3Button(ActionEvent event){

    }
    public void pressBackButton2(ActionEvent event){
        //Have it save the game here
        loadScene(event, "StartMenu.fxml");
    }

    //World selection screen button methods
    public void pressWorld1Button(ActionEvent event){
        EntityMain.loadWorld(0);
        loadScene(event, "WorldSelectionMenu.fxml");

    }
    public void pressNewWorldButton(ActionEvent event){
        EntityMain.loadWorld(1);
        loadScene(event, "WorldSelectionMenu.fxml");
    }
    public void pressWorldSelectionBackButton(ActionEvent event)
    {

        loadScene(event, "StartMenu.fxml");
    }

    //Combat interface button methods
    public void pressCombatWeapon1Button(ActionEvent event){
        enemyImage.setImage(bear);
    }
    public void pressCombatWeapon2Button(ActionEvent event){

    }
    public void pressCombatItem1Button(ActionEvent event){

    }
    public void pressCombatItem2Button(ActionEvent event){

    }
    public void pressCombatItem3Button(ActionEvent event){

    }

    //Character creation interface button methods
    public void pressCharacterCreationBackButton(ActionEvent event){

        loadScene(event, "StartMenu.fxml");
    }
    public void pressEnterNameButton(ActionEvent event){

        EntityMain.selectStart(0, enterNameField.getText());
        loadScene(event, "WorldSelectionMenu.fxml");

    }
    //Game over button methods
    public void pressLoadCharBtn(ActionEvent event){
        loadScene(event, "WorldSelectionMenu.fxml");
    }
    public void pressGameOverExitButton(ActionEvent event){
        Platform.exit();
    }


    //Scene switching methods
    private void loadScene(ActionEvent event, String FXMLName){
        try {
            Parent cCParent = FXMLLoader.load(getClass().getResource(FXMLName));
            Scene cCScene = new Scene(cCParent);

            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

            window.setScene(cCScene);
            window.show();
        }
        catch(Exception e){

        }

    }


}
