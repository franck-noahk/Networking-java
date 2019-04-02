import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server{

    ServerSocket server;
    Socket connection;
    ObjectInputStream input;
    String message;
    public void runServer() {
        try {
            server = new ServerSocket(12345, 100);
            while(true){
            connection = server.accept();
            output = new ObjectOutputStream(connection.getOutputStream());
            input = new ObjectInputStream(connection.getInputStream());
            output.writeObject("welcome to my server");
                try {
                    message=(String) input.readObject();
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }
            System.out.println("recieved: " + message);
            input.close(); output.close();
        }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public static void main(String[] args){
        Server my = new Server();
        my.runServer();
    }
}