import javax.swing.*;
import java.sql.*;

public class Main {
    public static void main(String[] args) {
        EntryManager entryManager = new EntryManager();
        View view = new View("EntryManager");
        MySQLHandler mySQLHandler = new MySQLHandler();
        Controller controller = new Controller(entryManager, view, new FileIO(), mySQLHandler);
//        Controller controller = new Controller(view, new FileIO());
    }
}
