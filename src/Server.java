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
    private int total = 0;

    public void runServer() {
        System.out.println("Running Server on \n");
        try {
            server = new ServerSocket(12345, 100);
            while(true){
                connection = server.accept();
                output = new ObjectOutputStream(connection.getOutputStream());
                input = new ObjectInputStream(connection.getInputStream());
                output.writeObject("welcome to my server");     //Writing to the screen and message
                total += 1; System.out.println("Number of Connections: "+ total);
                output.flush();
                //try {
                //   message=(String) input.readObject();total += 1; System.out.println("Number of Connections: "+ total);
                //} catch (ClassNotFoundException ex) {
                //   Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                //}
                // System.out.println("recieved: " + message);
                input.close();
                output.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(com.noahfranck.Server.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void main(String[] args) {
        Server server = new Server();
        server.runServer();
    }
}
