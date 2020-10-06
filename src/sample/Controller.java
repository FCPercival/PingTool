package sample;

import javafx.fxml.FXML;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author FPercival
 * @GitHub https://github.com/FCPercival
 */

public class Controller extends Thread{
    Controller controller;

    @FXML
    Pane bg_pane;
    @FXML
    Text t_ping;
    @FXML
    Text t_avg;
    @FXML
    Text t_pkloss;
    @FXML
     ToggleButton b_button;
    @FXML
    RadioMenuItem m_high;
    @FXML
    RadioMenuItem m_medium;
    @FXML
    RadioMenuItem m_low;
    @FXML
    RadioMenuItem m_pause;
    @FXML
    Text t_offline;
    

    private String servername="google.com";
    private long cont=0,avg=0,fin=0;
    private int packetLoss=0;
    private long t_sleep=250L;
    private int maxPing=200;
    private boolean run=true;



    @FXML
    protected void m_reload() throws IOException, InterruptedException {
        cont=0;
        avg=0;
        fin=0;
        packetLoss=0;
        while(run)
            run();

    }
    @FXML
    protected void m_s_high() throws IOException, InterruptedException {
        t_sleep=125L;


    }
    @FXML
    protected void m_s_medium() throws IOException, InterruptedException {
        t_sleep=250L;



    }
    @FXML
    protected void m_s_low() throws IOException, InterruptedException {
        t_sleep=1500L;


    }
    @FXML
    protected void m_s_pause(){
        System.out.println("Pause");
        b_button.setText("OFF");
        b_button.setSelected(false);


    }
    @FXML
    protected void m_host() throws IOException {
        AddInfo addInfo=new AddInfo();
        String host=addInfo.display();

        servername=host;
        System.out.println(servername);

    }
    @FXML
    protected void m_quit(){
        System.exit(1);

    }
    @FXML
    protected void m_info(){
        InfoBox.display("Info","Ping Tool coded by FPercival (Follow me on GitHub), if you want to support me, \nsend some bitcoin to this address (click the button to copy the address)");

    }
    @FXML
    protected void getButtonState() throws IOException, InterruptedException {
        if(b_button.getText().matches("OFF")){
            System.out.println("OFF TO ON");
            b_button.setSelected(true);
            b_button.setText("ON");
            Controller sp=new Controller();



            new Thread(() -> {
                if(t_sleep==125L){
                    m_high.setSelected(true);}else if(t_sleep==250L){
                    m_medium.setSelected(true);}else if(t_sleep==1500L){
                    m_low.setSelected(true);}




                ping pin = new ping();


                long ping = 0;
                int cont=0;
                int pkloss=0;
                long fin=0;

                while(true) {
                    if(b_button.getText().matches("OFF")){
                        cont=0;
                        avg=0;
                        pkloss=0;
                        ping=0;
                        m_pause.setSelected(true);
                        return;
                    }


                    try {
                        if(cont==0){
                            for (int i = 0; i < 5; i++) {
                                ping = pin.getPing(servername, 2000, "it", 20);
                            }
                        }
                        ping = pin.getPing(servername, 2000, "it", 20);
                    } catch (IOException e) {
                        System.out.println(e);
                    }



                    cont++;
                    if (ping == -1) {
                        pkloss+=1;
                        System.out.println("PING FAILED");
                    }else{
                        System.out.println("Ping " + ping + " ms");
                        avg += ping;

                        fin = avg / cont;
                        System.out.println("AVG: " + fin + " ms");

                    }
                    if(ping == -1){
                        t_ping.setText("PING: "+"LOST"+" ");
                    }else{
                        t_offline.setText("");
                        t_avg.setOpacity(1);
                        t_ping.setText("PING: "+ping+" ms");
                    }

                    t_pkloss.setText("PK LOSS: "+pkloss);
                    t_avg.setText("AVG: "+fin);
                    //controller =new Controller();
                    //controller.setAll(ping,cont,fin,pkloss);


                    //*Se superiore a maxPing schermata rossa
                    if (ping >= maxPing) {
                        bg_pane.setStyle("-fx-background-color:#F51D25;");
                    } else {
                        bg_pane.setStyle("-fx-background-color:white;");
                    }

                    try {
                        Thread.sleep(t_sleep);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }



            }).start();

        }else{
            System.out.println("ON TO OFF");
            b_button.setSelected(false);
            b_button.setText("OFF");

        }




    }






    public void initialize() throws IOException, InterruptedException {
        t_pkloss.setText("PK LOSS: ");
        t_avg.setText("AVG: ");
        t_ping.setText("PING: ");
        //m_pause.setSelected(true);

        b_button.setSelected(false);
        b_button.setText("OFF");

        ping pin=new ping();
        short offline=0;
        for (int i = 0; i < 10; i++) {
            try{
                InetAddress.getByName(servername).isReachable(maxPing);
            }catch (java.net.UnknownHostException ue){
                offline++;
            }

        }
        if(offline<=3){
            getButtonState();}
        else{
            t_offline.setText("OFFLINE");
            t_avg.setOpacity(0.05);

        }
        //ping();
        //Controller newThread=new Controller();
        //newThread.start();
        //startPing();

    }
}
