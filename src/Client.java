import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.HashMap;

public class Client {

    private static InetAddress ip;
    private static ObjectInputStream input;
    private static ObjectOutputStream output;
    private static String message;
    private static Socket client;
    private static String StringB;
    private static Short subnetMaskLength;
    private static String IPAddress;

    public static void main(String[] args) {

        //------------------------------Code from teacher --------------------------------------------------------------
        try{
            ip = InetAddress.getLocalHost();
            NetworkInterface myInterface = NetworkInterface.getByInetAddress(ip);


            subnetMaskLength = myInterface.getInterfaceAddresses().get(0).getNetworkPrefixLength();    //--------- gives 2 digit number
            IPAddress = ip.getHostAddress();
            System.out.println("Current IP address : " + ip.getHostAddress());


            NetworkInterface network = null;

            network = NetworkInterface.getByInetAddress(ip);


            byte[] mac = network.getHardwareAddress();
            System.out.println(args[0]);
            System.out.print("Current MAC address : ");

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < mac.length; i++) {
                sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
            }
            StringB = sb.toString();        //My own code that should save the mac address to a string
            System.out.println(sb.toString());
        } catch(SocketException e) {
            e.printStackTrace();
        } catch(UnknownHostException s) {
            s.printStackTrace();
        }

        //-----------------------------------------end of code from teacher---------------------------------------------

        Client client = new Client();
        try{
            client.runclient(args[0],subnetMaskLength,IPAddress);

        }catch (ArrayIndexOutOfBoundsException x){
            client.runclient("127.0.0.1", subnetMaskLength, IPAddress);

        }

    }


    private void runclient(String commandline, short length, String IPAddress){
        try {
            client = new Socket(commandline, 12345);
            output = new ObjectOutputStream(client.getOutputStream());
            input = new ObjectInputStream(client.getInputStream());

            HashMap myMap = new HashMap();
            myMap.put(length, IPAddress);
            output.writeObject(myMap);  //-------sends the ip address
            //output.writeShort(length);      //-------should send the lan length
            message = (String) input.readObject();
            System.out.println("Recieved: " + message);
            input.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
