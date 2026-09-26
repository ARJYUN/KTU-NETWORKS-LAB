import java.net.*;
import java.util.Date;

public class Server {
    public static void main(String[] args) throws Exception {

        DatagramSocket serverSocket = new DatagramSocket(5000);

        byte[] receiveData = new byte[1024];
        byte[] sendData;

        System.out.println("Time Server is Running...");

        while (true) {
            DatagramPacket receivePacket =
                    new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);

            String currentTime = new Date().toString();
            sendData = currentTime.getBytes();

            DatagramPacket sendPacket = new DatagramPacket(
                    sendData,
                    sendData.length,
                    receivePacket.getAddress(),
                    receivePacket.getPort());

            serverSocket.send(sendPacket);
        }
    }
}
