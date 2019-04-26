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
    private static int totalNumberOfConnections = 0;
    private int totalNumberOfErrorConnections = 0;
    public void runServer() {
        System.out.println("Running Server on \n");
            try {
                server = new ServerSocket(12345, 100);
                connection = server.accept();
                output = new ObjectOutputStream(connection.getOutputStream());
                input = new ObjectInputStream(connection.getInputStream());
                //------------needs to see if connected computer is on the same network

                String message = (String) input.readObject();
                System.out.println(message);
                //
                output.writeObject("welcome to my server");     //Writing to the screen and message

                System.out.println("Number of Connections: " + totalNumberOfConnections);
            } catch (IOException e) {
                System.out.println("IO exception\n" + e.getMessage());
            } catch (ClassNotFoundException ex) {
                System.out.println(" class not found eception ");
            }
            totalNumberOfConnections ++;
    }

    public static void main(String[] args) {
        while(totalNumberOfConnections < 5) {
            Server server = new Server();
            server.runServer();
        }
    }
}
