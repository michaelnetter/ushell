package org.mn.ushell.server;

import org.mn.ushell.util.OSUtil;

import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable{
    private Socket sck;
    public ClientHandler(Socket sck){
        this.sck = sck;
    }

    public void run(){
        String shell = OSUtil.getOSShellCommand();
        ProcessBuilder processBuilder = new ProcessBuilder(shell)
                .redirectInput(ProcessBuilder.Redirect.PIPE).
                redirectOutput(ProcessBuilder.Redirect.PIPE).redirectError(ProcessBuilder.Redirect.PIPE);
        Process process;
        try {
            process = processBuilder.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        BufferedReader processIn = new BufferedReader(new InputStreamReader(process.getInputStream()));
        BufferedReader processErr = new BufferedReader(new InputStreamReader(process.getErrorStream()));
        BufferedWriter processOut = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));

        DataOutputStream sckOut;
        InputStream sckIn;
        try {
            sckOut = new DataOutputStream(sck.getOutputStream());
            sckIn = sck.getInputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        new Thread(new ServerOutputThread(sckOut, processIn)).start();
        new Thread(new ServerOutputThread(sckOut, processErr)).start();
        new Thread(new ServerInputThread(sckIn, processOut)).start();
    }

}
