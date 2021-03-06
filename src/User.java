import java.io.Serializable;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User implements Serializable {
    private final String name;
    private final int id;
    private final String email;
    private String password;

    public User(String name, int id, String email, String password) {
        this.name = name;
        this.id = id;
        this.email = email;
        try {
            this.password = toHexString(getSHA(password));
        } catch (NoSuchAlgorithmException e) {
            this.password = null;
            System.out.println("Incorrect algorithm: " + e);
        }
    }

    public boolean isCorrectPassword(String incomingPassword) {
        boolean passwordsMatch = false;
        try {
            // hash incoming password
            // compare hash to users password hash
            passwordsMatch = this.password.equals(toHexString(getSHA(incomingPassword)));
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Incorrect algoritm: " + e);
        }
        return passwordsMatch;
    }

    public static byte[] getSHA(String input) throws NoSuchAlgorithmException {
        // Static getInstance method is called with hashing SHA
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        // digest() method called
        // to calculate message digest of an input
        // and return array of byte
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }

    public static String toHexString(byte[] hash) {
        // Convert byte array into signum representation
        BigInteger number = new BigInteger(1, hash);

        // Convert message digest into hex value
        StringBuilder hexString = new StringBuilder(number.toString(16));

        // Pad with leading zeros
        while (hexString.length() < 32) {
            hexString.insert(0, '0');
        }

        return hexString.toString();
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "" + this.name;
    }
}
