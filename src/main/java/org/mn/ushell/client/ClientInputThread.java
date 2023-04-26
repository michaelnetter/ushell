package org.mn.ushell.client;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ClientInputThread implements Runnable {
    private InputStream sckIn;
    public ClientInputThread(InputStream sckIn) {
        this.sckIn = sckIn;
    }

    @Override
    public void run() {
        try {
            DataInputStream sckObjIn = new DataInputStream (sckIn);
            while(true) {
                String str = sckObjIn.readUTF();
                System.out.println(str);
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

}
