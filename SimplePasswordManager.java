/**
 * Created by mwatson on 11/10/15.
 */
import java.util.List;

public class SimplePasswordManager {
    // construct a SimplePasswordManager with 4000 places and default hash parameters
    // multiplier = 1 and modulus = 4271
    public SimplePasswordManager(){
        //return null;
    }
    // construct a SimplePasswordManager with the supplied parameters
    public SimplePasswordManager(int size, int multiplier, int modulus){
        //return null;
    }
    // hashing
    public Long hashPassword(String password){
        return null;
    }
    // interface methods
    // return an array of the usernames of the users currently stored
    public List<String> listUsers(){
        return null;
    }
    public String authenticate(String username, String password){
        return null;
    }
    public String addNewUser(String username, String password){
        return null;
    }
    public String deleteUser(String username, String password){
        return null;
    }
    public String resetPassword(String username, String oldPassword, String newPassword){
        return null;
    }
}