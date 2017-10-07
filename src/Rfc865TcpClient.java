import java.io.*;
import java.net.Socket;

public class Rfc865TcpClient {
    public static void main(String[] argv) {
        int port = Integer.parseInt(argv[1]);
        String host = argv[0];
        Socket socket;
        String req;
        String res;

        try {
            socket = new Socket(host, port);
            try {
                BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
                DataOutputStream outToServer = new DataOutputStream(socket.getOutputStream());
                BufferedReader inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                while (true) {
                    req = inFromUser.readLine();
                    if (req.length() == 0) break;
                    outToServer.writeBytes(req + '\n');
                    res = inFromServer.readLine();
                    System.out.println("FROM SERVER: " + res);
                }
            } catch (IOException e) {}
        } catch (Exception e) {}

    }
}