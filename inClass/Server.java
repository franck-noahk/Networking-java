import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server{

    ServerSocket Server;
    Socket connection;
    public void runServer() {
        try{
            server = new ServerSocket(12345,100);
            while(true){
                connection = server.accept();
                ObjectOutputStream output = new ObjectOutputStream(connection.getOutputStream());
                input = new ObjectInputStream(connection.getInputStream());
                output.writeObject("Welcome to my Server");
                try{
                    message = (String) input.readObject();
                    System.out.println("recieved: " + message);
                }catch(ClassNotFoundException ex){

                }
            }
            connection.close();
        }catch(ioExceptionn ex{

        }
    }
    public static void main(String[] args){
        Server my = new Server();
        my.runServer();
    }
}