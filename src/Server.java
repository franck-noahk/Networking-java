import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {
    private Socket connection;
    private ServerSocket server;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private String message;
    private static InetAddress ip;
    private static int totalNumberOfConnections = 0;
    private int totalNumberOfErrorConnections = 0;
    private short clientLength;
    private String IPAddress;
    private short length;
    private static String clientIPAddress;
    public void runServer() {
        System.out.println("Running Server on \n");
        try {

                server = new ServerSocket(12345, 100);
             while(true) {
                 connection = server.accept();
                 output = new ObjectOutputStream(connection.getOutputStream());
                 input = new ObjectInputStream(connection.getInputStream());
                 ip = InetAddress.getLocalHost();
                 NetworkInterface myInterface = NetworkInterface.getByInetAddress(ip);
                 IPAddress = ip.getHostAddress();
                 length = myInterface.getInterfaceAddresses().get(0).getNetworkPrefixLength();
                 //------------needs to see if connected computer is on the same network

//                 String message = (String) input.readObject();
//                 System.out.println(message);
//                 Short length = input.readShort();
//                 System.out.println(length);

                 HashMap myHash = new HashMap();
                 myHash = (HashMap) input.readObject();
                 Set set = myHash.entrySet();
                 Iterator i = set.iterator();
                 while (i.hasNext()) {
                     Map.Entry entry = (Map.Entry) i.next();
                     clientLength =(short) entry.getKey();
                     clientIPAddress = (String) entry.getValue();
                 }



                 String clientIPAddress3 = clientIPAddress.replaceAll("\\.", "");
                 int fullAdd = Integer.parseInt(clientIPAddress3);
                 String binaryAdd = Integer.toBinaryString(fullAdd);
                 System.out.println(binaryAdd);
                 String finalAddress = (String)binaryAdd;
                 finalAddress = finalAddress.substring(0,clientLength);
                 System.out.println(finalAddress);


                 IPAddress = IPAddress.replaceAll("\\.", "");
                 int fulladd1 = Integer.parseInt(IPAddress);
                 String binaryAdd1 = Integer.toBinaryString(fulladd1);

                 String finalAddress1 = (String)binaryAdd1;
                 finalAddress1 = finalAddress1.substring(0,length);
                 System.out.println(finalAddress1);
                 // checking

                 if (finalAddress.equals(finalAddress1)) {
                     output.writeObject("You are on my LAN, Welcome home young jedi");     //Writing to the screen and messagev
                 }else{
                     output.writeObject("You are not on my LAN, Begone!");
                 }



                 //-----------Right now it just prints exactly what it recieves to the command line

                 totalNumberOfConnections++;

                 System.out.println("Number of Connections: " + totalNumberOfConnections);
                 output.close();
                 input.close();
                 connection.close();
             }
        } catch (IOException e) {
            System.out.println("IO exception\n" + e.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println(" class not found eception ");
        }

    }


    public static void main(String[] args) {
        Server server = new Server();
        server.runServer();
    }
}
