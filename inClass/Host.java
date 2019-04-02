public class Host{
    Socket client;
    
    public static void main(String[] args) {
        Client c = new Client();
        c.runclient();
    }

    private void runclient() {
        client = new Socket("127.0.0.1", 12345);
    }
    
}