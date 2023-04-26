package org.mn.ushell.client;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class ClientOutputThread implements Runnable {
    private OutputStream sckOut;
    public ClientOutputThread(OutputStream sckOut) {
        this.sckOut = sckOut;
    }

    @Override
    public void run() {
        try {
            BufferedReader sysIn = new BufferedReader(new InputStreamReader(System.in));

            while(true) {
                String str;
                if((str = sysIn.readLine()) != null) {
                    DataOutputStream sckObjOut = new DataOutputStream (sckOut);
                    sckObjOut.writeUTF(str);
                    sckObjOut.flush();
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

}
