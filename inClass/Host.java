import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.*;

public class Host{
    Socket client;
    
    public static void main(String[] args) {
        Host c = new Host();
        c.runclient();
    }

    private void runclient() {
        client = new Socket("127.0.0.1", 12345);
    }
    
}