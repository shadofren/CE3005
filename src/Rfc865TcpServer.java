import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class Rfc865TcpServer {
    public static void main(String[] argv) {
        ServerSocket parentSocket = null;
        try {
            parentSocket = new ServerSocket(4446);
        } catch (Exception e) {}
        while (true) {
            try {
                Socket childSocket = parentSocket.accept();

                ClientHandler client =
                        new ClientHandler(childSocket);
                Thread thread = new Thread(client);
                thread.start();
            } catch (IOException e) {}
        }
    }
}
class ClientHandler implements Runnable {
    private Socket socket;
    ClientHandler(Socket socket) {
        this.socket = socket;
    }
    public void run() {
        String quote = "Random Quote";
        String req;
        BufferedReader inFromClient = null;
        DataOutputStream outToClient = null;
        try {
            inFromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            outToClient = new DataOutputStream(socket.getOutputStream());
            while (true) {
                req = inFromClient.readLine();
                if (req == null) break;
                System.out.format("Received (%s): %s\n", socket.getRemoteSocketAddress(), req);
                outToClient.writeBytes(quote + '\n');
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}