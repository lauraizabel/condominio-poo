package validation;

import org.mindrot.jbcrypt.BCrypt;

public class bcrypt {
    static public String hash(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(10));
    }
    static public boolean verifyHash(String password, String hash) {
        return BCrypt.checkpw(password, hash);
    }
}
