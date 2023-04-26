package org.mn.ushell.server;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;

public class ServerOutputThread implements Runnable {
    private DataOutputStream sckOut;
    private BufferedReader processIn;
    public ServerOutputThread(DataOutputStream sckOut, BufferedReader processIn) {
        this.sckOut = sckOut;
        this.processIn = processIn;
    }

    @Override
    public void run(){
        try {
            while(true) {
                String str;
                if((str = processIn.readLine()) != null) {
                    sckOut.writeUTF(str);
                    sckOut.flush();
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

}
