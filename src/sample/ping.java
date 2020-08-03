package sample;

import javafx.scene.layout.Pane;

import javax.xml.soap.Text;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class ping {
    private String server="";
    private int maxPing=0;
    private String nationality="";
    private int redPing=0;
    private boolean start=true;

    public void getPing(String server, int maxPing, String nationality, int redPing, boolean start, Pane bg_pane, Text t_ping) throws IOException {
        this.server = server;
        this.maxPing = maxPing;
        this.nationality = nationality;
        this.redPing = redPing;
        this.start= start;
        int cont=0;
        int avg=0;
        int fin=0;
        int packetLoss=0;

        while (true){

            long currentTime = System.currentTimeMillis();
            boolean isPinged = InetAddress.getByName(this.server).isReachable(2000);
            currentTime = System.currentTimeMillis() - currentTime;
            if(isPinged) {
                System.out.println("Ping "+ currentTime+ " ms");
                //t_ping.setValue(Long.toString(currentTime));

                //*Se superiore a maxPing schermata rossa
                if (currentTime>=maxPing){
                    bg_pane.setStyle("-fx-background-color:#F51D25;");
                }else{
                    bg_pane.setStyle("-fx-background-color:white;");
                }

                avg+=currentTime;

                cont++;
                fin = avg / cont;
                System.out.println("AVG: "+fin+" ms");
            } else {
                System.out.println("Ping failed.");
                packetLoss++;
            }
        }

    }


}
