package sample;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
/**
 * @author FPercival
 * @GitHub https://github.com/FCPercival
 */

public class InfoBox {
    public static void display(String title,String message){

        Stage window=new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);

        Label label=new Label();
        label.setText(message);
        Button closeButton=new Button("Close");
        Button copyButton=new Button("Copy to clipboard");
        Button GitButton=new Button("My GitHub");
        closeButton.setOnAction(event -> window.close());
        copyButton.setOnAction(event -> copy());
        GitButton.setOnAction(event -> openGit());

        VBox layout=new VBox(10);
        layout.getChildren().addAll(label,GitButton,copyButton,closeButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene=new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
    public static void copy(){
        String myString = "18q9TJHbCPkWt6RUdFyx4TmZeLt7LsTPXa";
        StringSelection stringSelection = new StringSelection(myString);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
    }
    public static void openGit(){
        Desktop desktop = java.awt.Desktop.getDesktop();
        try {
            //specify the protocol along with the URL
            URI oURL = new URI(
                    "https://github.com/FCPercival");
            desktop.browse(oURL);
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }

}
