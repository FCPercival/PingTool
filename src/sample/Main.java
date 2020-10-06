package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.InetAddress;

/**
 * @author FPercival
 * @GitHub https://github.com/FCPercival
 */

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Ping");
        Image image=new Image("sample/icon.png"); //dove prende l'icona
        primaryStage.getIcons().add(image); //set icona
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, 266, 213)); // width 266 /height 203 //*forse va bene 280 213
        //nuovi 290 223
        primaryStage.show();

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent e) {
                System.exit(0);
            }
        });

    }


    public static void main(String[] args) {
        launch(args);
    }
}
