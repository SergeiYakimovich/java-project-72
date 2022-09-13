package hexlet.code.domain;

import io.ebean.Model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import io.ebean.annotation.WhenCreated;
import javax.persistence.Entity;
import java.sql.Timestamp;

@Entity
public class Url extends Model {

    @Id
    @GeneratedValue
    private long id;

    private String name;

    @WhenCreated
    private Timestamp createdAt;

    public Url() {
    }

    public Url(String nameValue) {
        name = nameValue;
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

}
