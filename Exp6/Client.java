import java.net.*;
import java.util.*;


public class Client
{
    public static void main(String[] args) throws Exception{
        DatagramSocket ss=new DatagramSocket();
        
        Scanner sc=new Scanner(System.in);
        String msg=sc.nextLine();
        ss.send(new DatagramPacket(msg.getBytes(),msg.getLength()),InetAddress.getByName("localhost",5000));
        byte[] b=new byte[1024];
        DatagramPacket p=new DatagramPacket(b,b.length);
        ss.receive(p);
        System.out.println(new String(p.getData(),0,p.getLength()));
        ss.close();

    }
}
