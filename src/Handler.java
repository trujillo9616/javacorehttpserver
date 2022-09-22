import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.util.Objects;

public class Handler {

    public static class UsersHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String method = exchange.getRequestMethod();

            if (Objects.equals(method, "GET")) {
                System.out.println("Called GET method");
            }

            else if (Objects.equals(method, "POST")) {
                System.out.println("Called POST method");
            }

            else if (Objects.equals(method, "PUT")) {
                System.out.println("Called POST method");
            }

            else if (Objects.equals(method, "DELETE")) {
                System.out.println("Called POST method");
            }

            else {
                System.out.println("Called a method not yet implemented!");
            }
        }
    }
}
