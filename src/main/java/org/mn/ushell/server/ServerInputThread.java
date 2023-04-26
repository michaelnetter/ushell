package org.mn.ushell.server;

import java.io.*;

public class ServerInputThread implements Runnable {
    private InputStream sckIn;
    private BufferedWriter processOut;
    public ServerInputThread(InputStream sckIn, BufferedWriter processOut) {
        this.sckIn = sckIn;
        this.processOut = processOut;
    }

    @Override
    public void run(){
        try {
            while(true) {
                DataInputStream  sckObjIn = new DataInputStream (sckIn);
                String str = sckObjIn.readUTF();
                processOut.write(str);
                processOut.newLine();
                processOut.flush();
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

}
