import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class SimpleHttpServer {
    private int port;
    private HttpServer server;

    public void start(int port) {
        try {
            this.port = port;
            server = HttpServer.create(new InetSocketAddress(port), 0);
            server.createContext("/users", new Handler.UsersHandler());
            server.setExecutor(null);
            server.start();
            System.out.println("Server started on port: " + port);
        } catch (IOException e) {
            System.out.println("Server failed to start!");
            e.printStackTrace();
        }
    }

    public void stop() {
        server.stop(0);
        System.out.println("Server stopped on port: " + port);
    }
}
