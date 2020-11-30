import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;

public class EntryManager implements Serializable {
    private final ArrayList<Entry> entries;
    private final ArrayList<User> users;
    private int userIDCount;

    public EntryManager() {
        users = new ArrayList<>();
        entries = new ArrayList<>();
        userIDCount = 0;
        createUser("default", "default", "default");
    }

    public ArrayList<Entry> getEntries() {
        return entries;
    }

    public Entry createEntry(String title, String content, User author) {
        Entry entry = new Entry(title, content, author);
        entries.add(entry);
        return entry;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public static Timestamp getCurrentTime() {
        return new Timestamp(System.currentTimeMillis());
    }

    public User createUser(String name, String email, String password) {
        int id = userIDCount + 1;
        userIDCount++;
        User user = new User(name, id, email, password);
        users.add(user);
        return user;
    }
}
