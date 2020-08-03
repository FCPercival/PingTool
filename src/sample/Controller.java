package sample;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

import javax.xml.soap.Text;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Controller {

    @FXML
    Pane bg_pane;
    @FXML
    Text t_ping;
    @FXML
    Text t_avg;
    @FXML
    Text t_pkloss;

    private String servername="google.it";
    private long cont=0,avg=0,fin=0;
    private int packetLoss=0;
    private int t_sleep=250;
    private int maxPing=200;
    private boolean run=true;

    //!CREARE UN THREAD
    //!NON FUNZIONA

    //*https://stackoverflow.com/questions/42909287/how-do-i-use-radio-buttons-groups-in-javafx
    //*Radio BUTTON HIGH MED LOW GROUPS
    @FXML
    protected void m_reload() throws IOException, InterruptedException {
        cont=0;
        avg=0;
        fin=0;
        packetLoss=0;
        while(run)
            startPing();

    }
    @FXML
    protected void m_s_high() throws IOException, InterruptedException {
        t_sleep=150;
        while(run)
            startPing();

    }
    @FXML
    protected void m_s_medium() throws IOException, InterruptedException {
        t_sleep=250;
        while(run)
            startPing();

    }
    @FXML
    protected void m_s_low() throws IOException, InterruptedException {
        t_sleep=150;
        while(run)
            startPing();

    }
    @FXML
    protected void m_s_pause(){
        System.out.println("Pause");


    }
    @FXML
    protected void m_host(){
        String host="google.com";
        servername=host;
        System.out.println(servername);

    }
    @FXML
    protected void m_quit(){
        System.exit(1);

    }
    public void startPing() throws IOException, InterruptedException {


            ping pin=new ping();
            pin.getPing(servername,maxPing,"it",20, true,bg_pane,t_ping);




            //Thread.sleep(t_sleep);




    }



    public void initialize() throws IOException, InterruptedException {
        //ping();
        startPing();






    }
}
