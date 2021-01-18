import javax.swing.*;
import java.awt.event.ItemEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 * A controller class that acts as a link between the view, model and database in this project.
 * This class calls methods in the EntryManager (the model), View (the view) and MySQLHandler (the database
 * connection) classes to transfer data between them. For example, the controller handles all listeners on the view
 * to more easily be able to transfer the data in the view to the model.
 */

public class Controller {
    private final EntryManager entryManager;
    private final View view;
    private final FileIO fileIO;
    private final String fileName = "entrymanager.obj";
    private final MySQLHandler mySQLHandler;

    // TODO: when saving to file, add all entries and users
    // TODO: when selecting a new user, show that users first entry

    /**
     * Creates a new Controller that can read and write data from files as well as a MySQL database and pass data
     * between a model and a view.
     * @param em The model the controller will interact with.
     * @param v The view the controller will interact with.
     * @param f The file input-output handler that the model will use to write data to files.
     * @param m The database handler the controller will use to manage reads and writes to the
     *          database.
     */
    public Controller(EntryManager em, View v, FileIO f, MySQLHandler m) {
        view = v;
        fileIO = f;
        entryManager = em;
        mySQLHandler = m;

        // load all users and entries from database,
        // update the entry manager's userIDCount
        // to match that of the database
        loadUsers();
        loadEntries();
        updateUserIDCount();
        // id to keep track of which users were added this session
        setNewUsersStartingID();
        updateEntryIDCount();
        setNewEntriesStartingID();
        // Add entries and users to JComboBox in view
        refreshUserSelector();
        refreshEntrySelector();
        // Add listeners to view
        addListeners();
    }

//    public Controller(View v, FileIO f) {
//        view = v;
//        fileIO = f;
//        entryManager = readEntryManagerFile();
//        // Add entries and users to JComboBox in view
//        refreshUserSelector();
//        refreshEntrySelector();
//        // Add listeners to view
//        addListeners();
//        if (!getUserEntries(0).isEmpty()) showSelectedEntry();
//    }

    private void setNewEntriesStartingID() {
        entryManager.setNewEntriesStartingID(mySQLHandler.getEntryIDAIValue());
    }

    private void updateEntryIDCount() {
        entryManager.updateEntryIDCount(mySQLHandler.getEntryIDAIValue());
    }

    private void setNewUsersStartingID() {
        entryManager.setNewUsersStartingID(getUserIDAIValue());
    }

    private void updateUserIDCount() {
        entryManager.updateUserIDCount(getUserIDAIValue());
    }

    private void loadUsers() {
        entryManager.populateUsers(readUsersFromDB());
    }

    private void loadEntries() {
        entryManager.populateEntries(readEntriesFromDB());
    }

    private int getUserIDAIValue() {
        return mySQLHandler.getUserIDAIValue();
    }

    private ArrayList<Map<String, String>> readEntriesFromDB() {
        return mySQLHandler.readEntries();
    }

    private ArrayList<Map<String, String>> readUsersFromDB() {
        return mySQLHandler.readUsers();
    }

    private void addListeners() {
        addAddListener();
        addSaveToFileListener();
        addUserSelectorListener();
        addEntrySelectorListener();
        addAddUserListener();
    }

    private void refreshUserSelector() {
        view.populateUserSelector(entryManager.getUsers());
    }

    private void refreshEntrySelector() {
        view.populateEntrySelector(getUserEntries(getSelectedUser().getId()));
    }

    private void addCreateUserListener(AddUserView addUserView) {
        addUserView.getCreateUserButton().addActionListener(e -> {
            addUser(addUserView.getNameContent(), addUserView.getEmailContent(), addUserView.getPasswordContent());
            mySQLHandler.setUserIDAIValue(entryManager.getUserIDCount());
            refreshUserSelector();
        });
    }

    private void addAddUserListener() {
        view.getAddUserButton().addActionListener(e -> {
            AddUserView addUserView = new AddUserView("Add user");
            addCreateUserListener(addUserView);
        });
    }

    private void addUserSelectorListener() {
        view.getUserSelector().addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED)
                view.populateEntrySelector(getUserEntries(getSelectedUser().getId()));
        });
    }

    private void addEntrySelectorListener() {
        view.getEntrySelector().addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) showSelectedEntry();
        });
    }

    private void showSelectedEntry() {
        Entry selectedEntry = getSelectedEntry();
        view.setEntryContent(selectedEntry.getContent());
        view.setEntryTitle(selectedEntry.getTitle());
    }

    private Entry getUserEntry(int userID, int index) {
        ArrayList<Entry> entries = getUserEntries(userID);
        return entries.get(index);
    }

    /**
     * Gets the entries of a specific user.
     * @param userID The id of the user whose entries will be returned.
     * @return Returns the entries of the specified user.
     */
    public ArrayList<Entry> getUserEntries(int userID) {
        ArrayList<Entry> entries = entryManager.getEntries();
        ArrayList<Entry> userEntries = new ArrayList<>();
        for (Entry currentEntry : entries) {
            if (currentEntry.getAuthor().getId() == userID) {
                userEntries.add(currentEntry);
            }
        }

        return userEntries;
    }

    public void writeEntryManagerToFile() {
        try {
            fileIO.writeEntryManagerToFile(entryManager, fileName);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void addSaveToFileListener() {
        view.getSaveToFileButton().addActionListener(e -> {
//            writeEntryManagerToFile();
            writeEntriesToDB();
            writeUsersToDB();
        });
    }

    /**
     * Writes all the entries currently stored in entryManager to the MySQL database.
     */
    private void writeEntriesToDB() {
        ArrayList<Entry> entries = entryManager.getEntries();
        for (Entry entry : entries) {
            if (entry.getId() >= entryManager.getNewEntriesStartingID()) {
                mySQLHandler.addEntry(entry);
            }
        }
    }

    private void writeUsersToDB() {
        ArrayList<User> users = entryManager.getUsers();
        for (User user : users) {
            if (user.getId() >= entryManager.getNewUsersStartingID()) {
                mySQLHandler.addUser(user);
            }
        }
    }

    public EntryManager readEntryManagerFile() {
        EntryManager entryManager = null;
        try {
            entryManager = fileIO.readEntryManagerFile(fileName);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return entryManager;
    }

    private User getSelectedUser() {
        JComboBox<User> userSelector = view.getUserSelector();
        return (User) userSelector.getSelectedItem();
    }

    private Entry getSelectedEntry() {
        JComboBox<Entry> entrySelector = view.getEntrySelector();
        return (Entry) entrySelector.getSelectedItem();
    }

    private void addAddListener() {
        view.getAddEntryButton().addActionListener(e -> {
            User author = getSelectedUser();
            System.out.println(addEntry(view.getEntryTitle(), view.getEntryContent(), author));
            mySQLHandler.setEntryIDAIValue(entryManager.getEntryIDCount());
            refreshEntrySelector();
        });
    }

    private Entry addEntry(String title, String content, User author) {
        return entryManager.createEntry(title, content, author);
    }

    private User addUser(String name, String email, String password) {
        return entryManager.createUser(name, email, password);
    }
}
