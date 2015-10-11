/**
 * Created by mwatson on 11/10/15.
 */
import java.util.List;

public class SimplePasswordManager {
    // construct a SimplePasswordManager with 4000 places and default hash parameters
    // multiplier = 1 and modulus = 4271
    public SimplePasswordManager()
    // construct a SimplePasswordManager with the supplied parameters
    public SimplePasswordManager(int size, int multiplier, int modulus)
    // hashing
    public Long hashPassword(String password)
    // interface methods
    // return an array of the usernames of the users currently stored
    public List<String> listUsers()
    public String authenticate(String username, String password)
    public String addNewUser(String username, String password)
    public String deleteUser(String username, String password)
    public String resetPassword(String username, String oldPassword, String newPassword)
}