import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) throws Exception {

        Socket socket = new Socket("localhost", 5000);

        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Enter file name: ");
        String fileName = br.readLine();

        out.writeUTF(fileName);

        System.out.println(in.readUTF()); // Display PID

        while (true) {
            String msg = in.readUTF();
            if (msg.equals("END"))
                break;
            System.out.println(msg);
        }

        socket.close();
    }
}
