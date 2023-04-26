package org.mn.ushell.server;

import java.io.*;

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
