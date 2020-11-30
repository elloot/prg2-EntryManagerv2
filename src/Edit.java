import java.io.Serializable;
import java.sql.Timestamp;

public class Edit implements Serializable {
    private final String content;
    private final Timestamp createdAt;
    private final User author;

    public Edit(String content, User author) {
        this.content = content;
        this.author = author;
        this.createdAt = EntryManager.getCurrentTime();
    }

    public String getContent() {
        return content;
    }

    public Timestamp getCreationDate() {
        return createdAt;
    }

    public User getAuthor() {
        return author;
    }
}
