import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;

public class Entry implements Serializable {
    private String content;
    private final Timestamp createdAt;
    private Timestamp updatedAt;
    private final User author;
    private final ArrayList<Edit> edits;
    private final String title;

    public Entry(String title, String content, User author) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.createdAt = EntryManager.getCurrentTime();
        this.updatedAt = EntryManager.getCurrentTime();
        this.edits = new ArrayList<>();
        logEdit(content, author);
    }

    public void edit(String content, User author) {
        Edit edit = new Edit(content, author);
        this.content = edit.getContent();
        this.updatedAt = edit.getCreationDate();
        logEdit(edit);
    }

    private void logEdit(String content, User author) {
        this.edits.add(new Edit(content, author));
    }

    private void logEdit(Edit edit) {
        this.edits.add(edit);
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Timestamp getCreationDate() {
        return createdAt;
    }

    public Timestamp getEditDate() {
        return updatedAt;
    }

    public User getAuthor() {
        return author;
    }

    public ArrayList<Edit> getEdits() {
        return edits;
    }

    @Override
    public String toString() {
        return title;
    }
}
