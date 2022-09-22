import com.sun.net.httpserver.HttpExchange;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static java.lang.System.getProperty;
import static java.net.URLDecoder.decode;

public class UsersRoutes {

    private static final UsersData usersData = new UsersData();
    private static final TSONObject tson = new TSONObject();

    // CREATE
    public static void createUser(HttpExchange exchange) throws IOException {
        System.out.println("Called POST Request!");
        Map<String, String> params = tson.fromJSON(getBodyParams(exchange));
        User user = usersData.create(params.get("name"), params.get("lastName"),
                params.get("age"), params.get("email"));
        String response;
        int code;
        if (user == null) {
            response = "Failed to create new user, check params sent!";
            code = 400;
        } else {
            response = tson.toJSON(user);
            code = 200;
        }
        exchange.sendResponseHeaders(code, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes(StandardCharsets.UTF_8));
        os.close();
    }

    // READ
    public static void getUser(HttpExchange exchange) throws IOException {
        System.out.println("Called GET Request!");
        Map<String, String> params = getQueryParams(exchange);
        String response;
        int code;

        // Users List
        if (params.isEmpty()) {
            response = usersData.listUsers();
            code = 200;
        } else {
            User user = usersData.get(params.get("email"));

            if (user == null) {
                response = "No User with given information!";
                code = 400;
            } else {
                response = tson.toJSON(user);
                code = 200;
            }
        }
        exchange.sendResponseHeaders(code, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes(StandardCharsets.UTF_8));
        os.close();
    }

    //UPDATE
    public static void updateUser(HttpExchange exchange) throws IOException {
        System.out.println("Called PUT Request!");
        Map<String, String> params = getQueryParams(exchange);
        User user = usersData.update(params.get("name"), params.get("lastName"),
                params.get("age"), params.get("email"));
        String response;
        int code;
        if (user == null) {
            response = "No User with given email!";
            code = 400;
        } else {
            response = tson.toJSON(user);
            code = 200;
        }
        exchange.sendResponseHeaders(code, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes(StandardCharsets.UTF_8));
        os.close();
    }

    // DELETE
    public static void deleteUser(HttpExchange exchange) throws IOException {
        System.out.println("Called DELETE Request!");
        Map<String, String> params = getQueryParams(exchange);
        String email = params.get("email");
        String response;
        int code;
        if (usersData.delete(email)) {
            response = "User with email: " + email + " was successfully removed!";
            code = 200;
        } else {
            response = "No User with given information!";
            code = 400;
        }
        exchange.sendResponseHeaders(code, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes(StandardCharsets.UTF_8));
        os.close();
    }

    private static String getBodyParams(HttpExchange exchange) {
        InputStream inputStream = exchange.getRequestBody();
        return new BufferedReader(new InputStreamReader(inputStream))
                .lines().parallel().collect(Collectors.joining("\n"));
    }

    private static Map<String, String> getQueryParams(HttpExchange exchange) throws UnsupportedEncodingException {
        String query = exchange.getRequestURI().getRawQuery();
        Map<String, String> parameters = new HashMap<>();
        // Parse Query
        if (query != null) {
            String[] params = query.split("[&]");
            for (String param : params) {
                String[] field = param.split("[=]");
                if (field.length > 1) {
                    String key = decode(field[0], getProperty("file.encoding"));
                    String val = decode(field[1], getProperty("file.encoding"));
                    parameters.put(key, val);
                }
            }
        }
        return parameters;
    }
}
