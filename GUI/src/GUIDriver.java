
import java.io.FileInputStream;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

//Driver for the GUI elements of the game
public class GUIDriver extends Application implements EventHandler<KeyEvent>{
    //string variable that holds a representation of the last key pressed
    public static   String keyPress = new String();

    public static Parent root;


    public static void main(String[] args) {
        //new Manager();

        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        root = FXMLLoader.load(getClass().getResource("StartMenu.fxml"));
        primaryStage.setTitle("Map display");
        Scene scene = new Scene (root, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.show();

    }


    @Override
    //Event handler that sets the variable keyPress to the string value of the last key pressed by the user
    public void handle(KeyEvent event){
        keyPress = ((event.getCode()).toString());
        //Test line to test functionality
        // System.out.println(keyPress);
    }
}


