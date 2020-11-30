import java.io.*;

public class FileIO {
    public void writeEntryManagerToFile(EntryManager entryManager, String fileName) throws IOException, ClassNotFoundException {
        ObjectOutputStream out = null;

        try {
            out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(fileName)));
            out.writeObject(entryManager);
        } finally {
            out.close();
        }
    }

    public void writeToTxtFile(String data, String fileName) throws IOException {
        DataOutputStream out = null;

        try {
            out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(fileName)));
            out.writeUTF(data);
        } finally {
            out.close();
        }
    }

    public String readTxtFile(String fileName) throws IOException {
        DataInputStream in = null;
        String data = null;

        try {
            in = new DataInputStream(new BufferedInputStream(new FileInputStream(fileName)));
            data = in.readUTF();
        } finally {
            in.close();
        }

        return data;
    }

    public EntryManager readEntryManagerFile(String fileName) throws IOException, ClassNotFoundException {
        ObjectInputStream in = null;
        EntryManager entryManager;

        try {
            in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(fileName)));
            entryManager = (EntryManager) in.readObject();
        } finally {
            in.close();
        }

        return entryManager;
    }
}