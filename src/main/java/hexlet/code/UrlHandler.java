package hexlet.code;

import hexlet.code.domain.Url;
import hexlet.code.domain.UrlCheck;
import java.net.URL;
//import kong.unirest.HttpResponse;
//import kong.unirest.Unirest;
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;

//import javax.swing.text.Document;

public class UrlHandler {
    public static UrlCheck checkUrl(Url url) {
//        HttpResponse<String> response = Unirest
//                .get(url.getName())
//                .asString();
//        long statusCode = response.getStatus();
//        Document doc = Jsoup.parse(response.getBody());
//        String title = doc.title();
//        String h1 = doc.getElementsByTag("h1").text();
//        String description = ;
//        List<Element> metas = doc.select("meta");
//        for (var meta : metas) {
//            if (meta.attr("name").equals("description")) {
//                return meta.attr("content");
//            }
//        }
        return new UrlCheck((long) (Math.random() * 100), "title", "h1", "description", url);
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
