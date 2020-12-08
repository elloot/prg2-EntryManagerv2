import javax.management.InstanceNotFoundException;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

    public void populateUsers(ArrayList<Map<String, String>> users) {
        // add every user from database
        for (Map<String, String> user : users) {
            int id = Integer.parseInt(user.get("id"));
            String name = user.get("name");
            String email = user.get("email");
            String password = user.get("password");
            createUser(id, name, email, password);
        }
    }

    public void populateEntries(ArrayList<Map<String, String>> entries) {
        // add every entry from database
        for (Map<String, String> entry : entries) {
//            int id = Integer.parseInt(entry.get("id"));
            String title = entry.get("title");
            String content = entry.get("content");
            int authorID = Integer.parseInt(entry.get("authorID"));
            User user;
            try {
                user = getUser(authorID);
                createEntry(title, content, user);
            } catch (NullPointerException e) {
                System.out.println("User with id " + authorID + " not found, " + e);
            }
        }
    }

    public ArrayList<Entry> getEntries() {
        return entries;
    }

    public Entry createEntry(String title, String content, User author) {
        Entry entry = new Entry(title, content, author);
        entries.add(entry);
        return entry;
    }

    public User getUser(int id) throws NullPointerException {
        for (User user : getUsers()) {
            if (user.getId() == id) {
                return user;
            }
        }
        throw new NullPointerException();
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

    public User createUser(int id, String name, String email, String password) {
        if (userIDCount < id + 1) {
            userIDCount = id + 1;
        }
        User user = new User(name, id, email, password);
        users.add(user);
        return user;
    }
}
