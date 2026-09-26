import java.io.*;
import java.net.*;

public class Server
{
    public static String matrixtype(int[][] matrix, int n){
        boolean upper=true;
        boolean lower=true;
        boolean diagonal=true;

        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if (i>j && matrix[i][j]!=0)
                    upper=false;
                if (i<j && matrix[i][j]!=0)
                    lower=false;
                if (i!=j && matrix[i][j]!=0)
                    diagonal=false;
            }
        }
        if(diagonal)
            return "Diagonal Matrix";
        else if(lower)
            return "Lower Triangular Matrix";
        else if(upper)
            return "Upper Triangular Matrix";
        else return "Not Upper Triangular, Lower Triangular Matrix or Diagonal Matrix";
    }

    public static void main(String[] args) throws Exception{
        ServerSocket ss=new ServerSocket(5000);
        System.out.println("Server Started...");
        Socket s=ss.accept();
        DataInputStream dis=new DataInputStream(s.getInputStream());
        DataOutputStream doc=new DataOutputStream(s.getOutputStream());
        int n=dis.readInt();
        int[][] matrix=new int[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                matrix[i][j]=dis.readInt();
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }
        String result=matrixtype(matrix,n);
        doc.writeUTF(result);
        dis.close();
        doc.close();
        ss.close();
        s.close();
    }
}
