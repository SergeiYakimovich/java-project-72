package hexlet.code;

import hexlet.code.domain.Url;
import hexlet.code.domain.UrlCheck;
import java.net.URL;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class UrlHandler {
    public static UrlCheck checkUrl(Url url) {
        try {
            HttpResponse response = Unirest
                    .get(url.getName())
                    .asString();
            long statusCode = response.getStatus();
            Document doc = Jsoup.parse((String) response.getBody());
            String title = doc.title();
            String h1 = doc.getElementsByTag("h1").text();
            String description = getMetaTag(doc, "description");
            return new UrlCheck(statusCode, title, h1, description, url);
        } catch (Exception e) {
            return null;
        }
    }

    public static String getMetaTag(Document document, String attr) {
        Elements elements = document.select("meta[name=" + attr + "]");
        for (Element element : elements) {
            final String s = element.attr("content");
            if (s != null) {
                return s;
            }
        }
        elements = document.select("meta[property=" + attr + "]");
        for (Element element : elements) {
            final String s = element.attr("content");
            if (s != null) {
                return s;
            }
        }
        return "";
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
