import java.net.*;

public class Client {
    public static void main(String[] args) throws Exception {

        DatagramSocket clientSocket = new DatagramSocket();

        InetAddress serverAddress = InetAddress.getByName("localhost");

        byte[] sendData = "TIME".getBytes();
        byte[] receiveData = new byte[1024];

        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, 5000);

        clientSocket.send(sendPacket);

        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

        clientSocket.receive(receivePacket);

        String serverTime = new String(receivePacket.getData(), 0, receivePacket.getLength());

        System.out.println("Current Time: " + serverTime);

        clientSocket.close();
    }
}
