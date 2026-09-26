/*Maven Commands
sudo apt update
sudo apt install maven -y
sudo apt install libpcap-dev -y
Go to Maven project folder
mvn clean compile
sudo mvn exec:java -Dexec.mainClass="com.example.App"

Make sure your pom.xml contains:
<dependency>
    <groupId>org.pcap4j</groupId>
    <artifactId>pcap4j-core</artifactId>
    <version>1.8.2</version>
</dependency>
*/


package com.example;
import org.pcap4j.core.*;
import org.pcap4j.packet.*;


public class App 
{
    public static void main( String[] args ) throws Exception
    {
        PcapNetworkInterface nif=Pcaps.findAllDevs().get(0);
        System.out.println("Network Interface: "+nif.getName());
        int snapLen=65536;
        int timeout=10;
        PcapHandle handle=nif.openLive(snapLen,PcapNetworkInterface.PromiscuousMode.PROMISCUOUS,timeout);
        System.out.println("Packet Capturing Started...");
        while(true){
            Packet packet=handle.getNextPacket();
            if(packet != null){
                System.out.println("--------------------------------------------");
                if(packet.contains(EthernetPacket.class)){
                    EthernetPacket ethernet=packet.get(EthernetPacket.class);
                    System.out.println("Source MAC Address: " + ethernet.getHeader().getSrcAddr());
                    System.out.println("Destination MAC Address: " + ethernet.getHeader().getDstAddr());
                }
                if(packet.contains(IpV4Packet.class)){
                    IpV4Packet ip=packet.get(IpV4Packet.class);
                    System.out.println("Source IP Address: " + ip.getHeader().getSrcAddr());
                    System.out.println("Destination IP Address: " + ip.getHeader().getDstAddr());
                }
                if(packet.contains(TcpPacket.class)){
                    System.out.println("TCP Packet");
                    TcpPacket tcp=packet.get(TcpPacket.class);
                    System.out.println("Source TCP Port: " + tcp.getHeader().getSrcPort().valueAsInt());
                    System.out.println("Destination TCP Port: " + tcp.getHeader().getDstPort().valueAsInt());
                }
                if(packet.contains(UdpPacket.class)){
                    System.out.println("UDP Packet");
                    UdpPacket udp=packet.get(UdpPacket.class);
                    System.out.println("Source UDP Port: " + udp.getHeader().getSrcPort().valueAsInt());
                    System.out.println("Destination UDP Port: " + udp.getHeader().getDstPort().valueAsInt());
                }
                if(packet.contains(IcmpV4CommonPacket.class)){
                    System.out.println("ICMP Packet");
                }
            }
        }
    }
}
