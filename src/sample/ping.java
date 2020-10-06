package sample;

import javafx.fxml.FXML;
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

public class ping extends Thread{
    private String server="";
    private int maxPing=0;
    private String nationality="";
    private int redPing=0;
    private boolean start=true;
    private int packetLoss=0;

    public long getPing(String server, int maxPing, String nationality, int redPing) throws IOException {



        this.server = server;
        this.maxPing = maxPing;
        this.nationality = nationality;
        this.redPing = redPing;


        long currentTime = System.currentTimeMillis();
        boolean isPinged;
        try{
            isPinged = InetAddress.getByName(this.server).isReachable(maxPing);
        }catch(java.net.UnknownHostException ue){
            isPinged=false;
        }

        currentTime = System.currentTimeMillis() - currentTime;

        if (isPinged) {
            return currentTime;

        } else {
            System.out.println("Ping failed.");
            packetLoss++;
            return -1;
        }

    }


    public int getPacketLoss(){
        return this.packetLoss;
    }
    public int getMaxPing(){
        return this.maxPing;
    }
    public int getServer(){
        return this.packetLoss;
    }
    public int getNationality(){
        return this.packetLoss;
    }

    public int getRedPing() {
        return redPing;
    }
}
