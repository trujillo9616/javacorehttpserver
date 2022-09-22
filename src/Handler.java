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
                UsersRoutes.getUser(exchange);
            }

            else if (Objects.equals(method, "POST")) {
                UsersRoutes.createUser(exchange);
            }

            else if (Objects.equals(method, "PUT")) {
                UsersRoutes.updateUser(exchange);
            }

            else if (Objects.equals(method, "DELETE")) {
                UsersRoutes.deleteUser(exchange);
            }

            else {
                System.out.println("Called a method not yet implemented!");
                throw new IOException();
            }
        }
    }
}
