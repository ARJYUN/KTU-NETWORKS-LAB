import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) throws Exception {

        ServerSocket server = new ServerSocket(5000);
        System.out.println("File Server Running...");

        while (true) {
            Socket socket = server.accept();

            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            String fileName = in.readUTF();

            long pid = ProcessHandle.current().pid();
            out.writeUTF("Server PID: " + pid);

            File file = new File(fileName);

            if (file.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line;
                while ((line = br.readLine()) != null) {
                    out.writeUTF(line);
                }
                out.writeUTF("END");
                br.close();
            } else {
                out.writeUTF("File not found");
                out.writeUTF("END");
            }

            socket.close();
        }
    }
}
