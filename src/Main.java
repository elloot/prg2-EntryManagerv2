public class Main {
    public static void main(String[] args) {
        EntryManager entryManager = new EntryManager();
        View view = new View("EntryManager");
//        Controller controller = new Controller(entryManager, view, new FileIO());
        Controller controller = new Controller(view, new FileIO());
    }
}
