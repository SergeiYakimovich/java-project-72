package hexlet.code.domain;

import io.ebean.Model;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import io.ebean.annotation.WhenCreated;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;

@Entity
public class UrlCheck extends Model {

    @Id
    @GeneratedValue
    private long id;

    private long statusCode;
    private String title;
    private String h1;

    @Lob
    private String description;

    @ManyToOne
    @JoinColumn(name = "url_id")
    private Url url;

    @WhenCreated
    private Timestamp createdAt;

    public UrlCheck(long statusCode, String title, String h1, String description, Url url) {
        this.statusCode = statusCode;
        this.title = title;
        this.h1 = h1;
        this.description = description;
        this.url = url;
    }

    public final long getId() {
        return id;
    }

    public final long getStatusCode() {
        return statusCode;
    }

    public final String getTitle() {
        return title;
    }

    public final String getH1() {
        return h1;
    }

    public final String getDescription() {
        return description;
    }

    public final Url getUrl() {
        return url;
    }

    public final Timestamp getCreatedAt() {
        return createdAt;
    }

}

