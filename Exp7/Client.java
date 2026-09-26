import java.io.*;
import java.util.*;
import java.net.*;

public class Client{
    public static void main(String[] args) throws Exception{
        Socket socket=new Socket("localhost",5000);
        DataInputStream dis=new DataInputStream(socket.getInputStream());
        DataOutputStream dos=new DataOutputStream(socket.getOutputStream());
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter your name: ");
        String name=sc.nextLine();
        Thread receive=new Thread(()->{
            try{
                while(true){
                    System.out.println(dis.readUTF());
                }
            }catch(Exception e){

            }
        });
        receive.start();
        while(true){
            String msg=sc.nextLine();
            dos.writeUTF(name+":"+msg);
        }
    }
}
