package hexlet.code;

import hexlet.code.domain.Url;
import hexlet.code.domain.UrlCheck;
import java.net.URL;

public class UrlHandler {
    public static UrlCheck checkUrl(Url url) {
        return new UrlCheck((long) Math.random(), "title", "h1", "description", url);
    }

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
