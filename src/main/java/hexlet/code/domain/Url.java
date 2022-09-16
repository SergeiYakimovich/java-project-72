package hexlet.code.domain;

import io.ebean.Model;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import io.ebean.annotation.WhenCreated;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Entity
public class Url extends Model {

    @Id
    @GeneratedValue
    private long id;

    private String name;

    @WhenCreated
    private Timestamp createdAt;

    @OneToMany
    private List<UrlCheck> urlChecks;

    public Url() {
    }

    public Url(String nameValue) {
        name = nameValue;
        urlChecks = new ArrayList<>();
    }

    public final long getId() {
        return id;
    }

    public final String getName() {
        return name;
    }

    public final Timestamp getCreatedAt() {
        return createdAt;
    }

    public final List<UrlCheck> getUrlChecks() {
        return urlChecks;
    }

    public final UrlCheck getLastUrlCheck() {
        return urlChecks.stream()
                .max(Comparator.comparing(x -> x.getCreatedAt()))
                .orElse(null);
    }

}
