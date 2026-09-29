import java.io.*;
import java.net.*;
import java.util.*;

public class Client {

    public static void main(String[] args) throws Exception {

        Socket s = new Socket("localhost", 5001);

        DataInputStream dis =
                new DataInputStream(s.getInputStream());

        DataOutputStream dos =
                new DataOutputStream(s.getOutputStream());

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter file name: ");
        String fileName = sc.nextLine();

        // Send file name to server
        dos.writeUTF(fileName);

        String response;

        while (true) {

            response = dis.readUTF();

            if (response.equals("END"))
                break;

            System.out.println(response);
        }

        s.close();
        sc.close();
    }
}
