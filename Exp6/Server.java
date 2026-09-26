import java.net.*;

public class Server
{
    public static void main(String[] args) throws Exception{
        DatagramSocket ss=new DatagramSocket(5000);
        System.out.println("Server Started...");
        byte[] receivedata=new byte[1024];
        byte[] sendData;

        DatagramPacket receivepacket=new DatagramPacket(receivedata,receivedata.length);
        ss.receive(receivepacket);
        String sentence=new String(receivepacket.getData(),0,receivepacket.getLength());

        sentence=sentence.replaceAll("\\btbh\\b","to be honest");
        sentence=sentence.replaceAll("\\big\\b","i guess");
        sentence=sentence.replaceAll("\\btbf\\b","to be frank");
        sentence=sentence.replaceAll("\\batm\\b","at the most");
        sentence=sentence.replaceAll("\\birl\\b","in real life");
        sentence=sentence.replaceAll("\\blol\\b","laughing out loud");
        sentence=sentence.replaceAll("\\basap\\b","as soon as possible");
        sentence=sentence.replaceAll("\\bomg\\b","oh my god");
        sentence=sentence.replaceAll("\\bidk\\b","i don't know");
        sentence=sentence.replaceAll("\\bnvm\\b","never mind");

        sendData=sentence.getBytes();
        InetAddress address=receivepacket.getAddress();
        int port=receivepacket.getPort();
        DatagramPacket sendpacket=new DatagramPacket(sendData,sendData.length,address,port);
        ss.send(sendpacket);
        ss.close();

    }
}
