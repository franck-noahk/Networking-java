import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {
    private Socket connection;
    private ServerSocket server;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private String message;
    private int totalNumberOfConnections = 0;
    private int totalNumberOfErrorConnections = 0;
    public void runServer() {
        System.out.println("Running Server on \n");
            try {
                server = new ServerSocket(12345, 100);
                totalNumberOfConnections += 1;
                connection = server.accept();
                output = new ObjectOutputStream(connection.getOutputStream());
                input = new ObjectInputStream(connection.getInputStream());
                //------------needs to see if connected computer is on the same network

                String message = (String) input.readObject();
                System.out.println(message);
                //
                output.writeObject("welcome to my server");     //Writing to the screen and message

                System.out.println("Number of Connections: " + totalNumberOfConnections);
                output.flush();
                //try {
                //   message=(String) input.readObject();total += 1; System.out.println("Number of Connections: "+ total);
                //} catch (ClassNotFoundException ex) {
                //   Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                //}
                // System.out.println("recieved: " + message);
                input.close();
                output.close();
            } catch (IOException e){
                System.out.println("IO exception");
                totalNumberOfErrorConnections++;
            }catch(ClassNotFoundException ex) {
                System.out.println(" class not found eception ");
                totalNumberOfErrorConnections++;
            }
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.runServer();
    }
}
