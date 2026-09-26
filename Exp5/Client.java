import java.io.*;
import java.net.*;
import java.util.*;

public class Client{
    public static void main(String[] args) throws Exception{
        Socket s=new Socket("localhost",5000);
        Scanner sc=new Scanner(System.in);
        DataInputStream dis=new DataInputStream(s.getInputStream());
        DataOutputStream doc=new DataOutputStream(s.getOutputStream());
        System.out.print("Enter Order: ");
        int n=sc.nextInt();
        Random random=new Random();
        doc.writeInt(n);
        int[][] matrix=new int[n][n];
        System.out.print("Enter Matrix: ");
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                matrix[i][j]=random.nextInt(50)+1;
                doc.writeInt(matrix[i][j]);
            }
        }
        String result=dis.readUTF();
        System.out.println(result);
        s.close();
        dis.close();
        doc.close();
    }
}
