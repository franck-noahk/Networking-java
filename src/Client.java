import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;

public class Client {

    private static InetAddress ip;
    private static ObjectInputStream input;
    private static ObjectOutputStream output;
    private static String message;
    private static Socket client;
    private static String StringB;

    public static void main(String[] args) {

        //------------------------------Code from teacher --------------------------------------------------------------
        try{
            ip = InetAddress.getLocalHost();

            System.out.println("Current IP address : " + ip.getHostAddress());

            NetworkInterface network = null;

            network = NetworkInterface.getByInetAddress(ip);


            byte[] mac = network.getHardwareAddress();

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
        client.runclient(args[1],StringB);
    }


    private void runclient(String commandline, String macAddress){
        try {
            client = new Socket(commandline, 12345);
            output = new ObjectOutputStream(client.getOutputStream());
            input = new ObjectInputStream(client.getInputStream());
            message = (String) input.readObject();
            System.out.println("Recieved: " + message);
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }



}
