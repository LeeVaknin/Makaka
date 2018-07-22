package Utils;

import java.security.MessageDigest;

public class HashManager {
    public static String getId(String str) {
        String id = null;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(str.getBytes());
            id = new String(messageDigest.digest());
        } catch (Exception ex) {
            System.out.println("Board.setId(): Error details: " + ex.getMessage());
        }
        return id;
    }

}
