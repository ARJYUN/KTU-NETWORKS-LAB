import java.io.*;
import java.net.*;

public class Server {

    public static void main(String[] args) throws Exception {

        ServerSocket ss = new ServerSocket(5001);

        long pid = ProcessHandle.current().pid();

        System.out.println("Server started...");
        System.out.println("Server PID: " + pid);

        while (true) {

            Socket s = ss.accept();

            DataInputStream dis =
                    new DataInputStream(s.getInputStream());

            DataOutputStream dos =
                    new DataOutputStream(s.getOutputStream());

            ClientHandler ch =
                    new ClientHandler(s, dis, dos, pid);

            Thread t = new Thread(ch);
            t.start();
        }
    }
}

class ClientHandler implements Runnable {

    Socket s;
    DataInputStream dis;
    DataOutputStream dos;
    long pid;

    ClientHandler(Socket s, DataInputStream dis,
                  DataOutputStream dos, long pid) {

        this.s = s;
        this.dis = dis;
        this.dos = dos;
        this.pid = pid;
    }

    public void run() {

        try {

            String fileName = dis.readUTF();

            dos.writeUTF("Server PID: " + pid);

            File file = new File(fileName);

            if (file.exists() && file.isFile()) {

                dos.writeUTF("File found: " + fileName);
                dos.writeUTF("----- File Contents -----");

                BufferedReader br =
                        new BufferedReader(new FileReader(file));

                String line;

                while ((line = br.readLine()) != null) {
                    dos.writeUTF(line);
                }

                br.close();

                dos.writeUTF("----- End of File -----");

            } else {

                dos.writeUTF("File not found: " + fileName);
            }

            dos.writeUTF("END");

            dos.flush();
            s.close();

        } catch (Exception e) {

            System.out.println("Client error: " + e);
        }
    }
}
