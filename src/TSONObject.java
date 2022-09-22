import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class TSONObject {
    // Method 1: JSON -> Object
    public Map<String, String> fromJSON(String body) throws IOException {
        Map<String, String> params = new HashMap<>();

        if (body.startsWith("{") && body.endsWith("}")) {
            StringBuilder builder = new StringBuilder(body);
            builder.deleteCharAt(0);
            builder.deleteCharAt(builder.length() - 1);
            for (String param : builder.toString().split("[,]")) {
                String[] paramData = param.split("[:]");

                if (paramData.length > 1) {
                    params.put(
                            paramData[0]
                                    .replace("\n", "")
                                    .replace("\r", "")
                                    .replace("\t", "")
                                    .replace("'", "")
                                    .replace("\"", "")
                                    .trim(),
                            paramData[1]
                                    .replace("\n", "")
                                    .replace("\r", "")
                                    .replace("\t", "")
                                    .replace("\"", "")
                                    .trim());
                }
            }
        }
        return params;
    }

    // Method 2: User -> JSON
    public String toJSON(User user) {
        return user.toString();
    }
}
