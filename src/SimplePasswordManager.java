/**
 * Created by mwatson on 11/10/15.
 */
import java.util.List;

public class SimplePasswordManager {
    private HashMap<String, Long> map;
    // construct a SimplePasswordManager with 4000 places and default hash parameters
    // multiplier = 1 and modulus = 4271
    public SimplePasswordManager(){
        map=new HashMap<String, Long>(4000,1,4271);
    }
    // construct a SimplePasswordManager with the supplied parameters
    public SimplePasswordManager(int size, int multiplier, int modulus){
        map=new HashMap<String, Long>(size,multiplier,modulus);
    }
    // hashing
    public Long hashPassword(String password){
        long hash=5381;
        for(int i=0; i<password.length(); i++){
            hash=hash*33+password.charAt(i);
        }
        return hash;
    }
    // interface methods
    // return an array of the usernames of the users currently stored
    public List<String> listUsers(){
        return map.keys();
    }

    public String authenticate(String username, String password){
        if(!map.keys().contains(username)){
            return "No such user exists.";
        }
        if(map.get(username).compareTo(hashPassword(password)) !=0){
            return "Failed to authenticate user.";
        }
        return username;
    }
    public String addNewUser(String username, String password){
        if(map.keys().contains(username)){
            return "User already exists.";
        }
        map.put(username, hashPassword(password));
        return username;
    }
    public String deleteUser(String username, String password){
        if(!map.keys().contains(username)){
            return "No such user exists.";
        }
        if(map.get(username).compareTo(hashPassword(password)) !=0){
            return "Failed to authenticate user.";
        }
        String deletedUsername=username; //store the username that is to be deleted
        map.remove(username); //remove the username
        return deletedUsername;

    }
    public String resetPassword(String username, String oldPassword, String newPassword){
        if(!map.keys().contains(username)){
            return "No such user exists.";
        }
        if(map.get(username).compareTo(hashPassword(oldPassword)) !=0){
            return "Failed to authenticate user.";
        }
        map.remove(username); //delete the old username
        map.put(username, hashPassword(newPassword)); //store the user with new password
        return username;
    }
}