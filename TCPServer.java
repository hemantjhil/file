import java.io.*;
import java.net.*;
import java.util.*;
class TCPServer {

    private final static String fileToSend = "/home/hemant/Documents/profile/dlw/proj/file transfer/Server/test1.pdf";

    public static void main(String args[]) {

        while (true) {
            ServerSocket welcomeSocket = null;
            Socket connectionSocket = null;
            BufferedOutputStream outToClient = null;

            try {
                welcomeSocket = new ServerSocket(3270);
                connectionSocket = welcomeSocket.accept();
                outToClient = new BufferedOutputStream(connectionSocket.getOutputStream());
            } catch (IOException ex) {
                // Do exception handling
                System.out.println("exception");
            }

            if (outToClient != null) {
                File myFile = new File( fileToSend );
                byte[] mybytearray = new byte[(int) myFile.length()];

                FileInputStream fis = null;

                try {
                    fis = new FileInputStream(myFile);
                } catch (FileNotFoundException ex) {
                    // Do exception handling
                }
                BufferedInputStream bis = new BufferedInputStream(fis);

                try {
                    bis.read(mybytearray, 0, mybytearray.length);
                    String str=new String(mybytearray);
                    System.out.println(str);
                    System.out.println("Enter the string to append");
                    Scanner sc=new Scanner(System.in);
                    String str1=sc.nextLine();
                    byte[] b1=str1.getBytes();
                    outToClient.write(mybytearray, 0, mybytearray.length);
                    outToClient.write(b1,0,b1.length);
                    outToClient.flush();
                    outToClient.close();
                    connectionSocket.close();

                    // File sent, exit the main method
                    return;
                } catch (IOException ex) {
                    // Do exception handling
                }
            }
        }
    }
}