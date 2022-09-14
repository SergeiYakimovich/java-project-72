package hexlet.code;

import java.net.URL;

public class UrlParser {
    public static String getUrl(String str) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        try {
            URL url = new URL(str);
            String result = url.getProtocol() + "://" + url.getHost();
            int port = url.getPort();
            if (port > 0) {
                result = result + ":" + port;
            }
            return result;
        } catch (Exception e) {
            return null;
        }

    }

}
