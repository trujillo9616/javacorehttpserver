public class Main {
    public static int port = 9000;

    public static void main(String[] args) {
        SimpleHttpServer httpServer = new SimpleHttpServer();
        httpServer.start(port);
    }
}
