package sample;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.*;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * @author FPercival
 * @GitHub https://github.com/FCPercival
 */

public class AddInfo {

    String processata="";
    boolean aggiunta;





    public String display()throws IOException{

        Stage window=new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Set address to ping");
        window.setMinWidth(450);
        Image image=new Image("sample/icon.png"); //dove prende l'icona
        window.getIcons().add(image);


        TextField nome=new TextField();

        Label labelm=new Label("Domain or IP:");

        Button closeButton=new Button("Start");
        closeButton.setStyle("-fx-background-color:lightgray;-fx-border-color:gray;-fx-border-radius:6px;-fx-background-radius:6px;");

        closeButton.setOnAction(event -> {
            try {
                if(InetAddress.getByName(nome.getText()).isReachable(2000) && nome.getText().length()!=0){
                    aggiunta=true;
                    window.close();

                }
                else{

                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        });

        VBox layout=new VBox(10);
        layout.getChildren().addAll(labelm,nome,closeButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene=new Scene(layout);
        window.setScene(scene);

        window.showAndWait();
        //System.out.println("Processata:"+processata);

        if (aggiunta)
            return nome.getText();
        return "-1";



    }
    protected void save(String nome, Stage window, String indirizzo) throws IOException {
        window.close();




    }

}
