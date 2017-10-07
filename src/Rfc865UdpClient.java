/*
* Name: Tran Manh Tu
* Group:
* IP Address: YourClientIPAddress
* */

import java.io.*;
import java.net.*;

public class Rfc865UdpClient {

    public static void main(String[] argv) {
        String host = argv[0];
        int port = Integer.parseInt(argv[1]);
        DatagramSocket socket;
        String data = "Tran Manh Tu, YourLabGroup, YourClientIPAddress";
        try {
            socket = new DatagramSocket();
            InetAddress address = InetAddress.getByName(host);
            DatagramPacket packet = new DatagramPacket(data.getBytes(), data.length(),
                    address, port);
            socket.send(packet);

            byte[] buf = new byte[256];
            packet = new DatagramPacket(buf, buf.length);
            socket.receive(packet);
            String received = new String(packet.getData(), 0, packet.getLength());
            System.out.println("Quote of the Day: " + received);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}