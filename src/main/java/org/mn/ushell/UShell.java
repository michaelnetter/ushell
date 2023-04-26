package org.mn.ushell;

import org.mn.ushell.client.ClientInputThread;
import org.mn.ushell.client.ClientOutputThread;
import org.mn.ushell.server.ClientHandler;
import org.mn.ushell.util.OSUtil;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class UShell {
    private static final int PORT = 55000;

    public static void main(String[] args) {
        OSUtil.printBanner();

        if(args.length != 1 || args.length != 2) {
            printUsage();
            System.exit(1);
        }
        else if(args.length == 1) {
            String type = args[0];
            if (type.equalsIgnoreCase("server")) {
                // Start server
                new UShell().startServer();
            }
            else {
                printUsage();
                System.exit(1);
            }
        }
        else if (args.length == 2){
            String type = args[0];
            if(type.equalsIgnoreCase("client")) {
                // Start client
                new UShell().startClient(args[1]);
            }
            else {
                printUsage();
                System.exit(1);
            }
        }
    }

    /**
     * Start a server and listen to the given port
     */
    private void startServer() {
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("Server started at port: " + PORT);
            System.out.println("Waiting for client...");
            while (true) {
                Socket sck = serverSocket.accept();
                System.out.println("Client accepted: " + sck.getInetAddress() + ":" + sck.getPort());
                new Thread(new ClientHandler(sck)).start();
                // quick sleep
                Thread.sleep(1200);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Start a client and connect to the given host
     * @param host The server's ip or hostname
     */
    private void startClient(String host) {
        try {
            System.out.println("Connecting to " + host + ":" + PORT + "...");
            Socket sck = new Socket(host, PORT);
            System.out.println("Connected");
            new Thread(new ClientInputThread(sck.getInputStream())).start();
            new Thread(new ClientOutputThread(sck.getOutputStream())).start();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Show command line usage
     */
    private static void printUsage(){
        System.out.println("Usage: java -jar ushell.jar [type] [host]\n");
        System.out.println("Examples: \n" +
                "Start server: java -jar ushell.jar server\n" +
                "Start client: java -jar ushell.jar client 192.168.1.2\n");
    }

}
