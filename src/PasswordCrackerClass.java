// imports a Bcrypt package to implement the BCrypt hash
import org.mindrot.BCrypt;
// imports java classes to accomodate different hashing algorithms (MD5, SHA-256, BCrypt)
import java.io.File;
import java.io.FileNotFoundException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

// main runner class
public class PasswordCrackerClass
{
    // main method that runs our password cracking algorithms
    public static void main (String[] args) throws FileNotFoundException, NoSuchAlgorithmException {
        //declares and initializes local variables
        String salt = BCrypt.gensalt();
        int attackType;
        String userPW;
        String unhashedPW = "";
        int hashType = 0;
        int input;

        // takes in user password, attack type, hash type, and input type as command line arguments
        // user password is a string
        // attack type, hash type, and input type are integers
        userPW = args[0];
        attackType = Integer.parseInt(args[1]); // 1 (Dictionary) or 2 (Bruteforce)
        hashType = Integer.parseInt(args[2]); // 1 (MD5) or 2 (SHA-256) or 3 (BCrypt)
        input = Integer.parseInt(args[3]); // 1 (plain text) or 2 (hash)
        
        // if there is a 5th argument, assign it as the salt
        if (args.length==5){
            salt = args[4];
        }

            // if password is in plain text, hash according to the specified hash type
            if (input == 1){
                if (hashType == 1){
                    userPW = MD5Hash.getMd5(userPW);
                }
                else if (hashType == 2){
                    userPW = SHA256Hash.toHexString(SHA256Hash.getSHA(userPW));
                }
                else{
                    userPW = BCrypt.hashpw(userPW, salt);
                }
            }

        // runs attacks
        if (attackType == 1)
        {
            if(DictionaryAttack.dictionaryCrack(userPW, hashType, salt).equals("Is a Match!")){
                System.out.println("Password has been cracked!");
                System.out.println("Password is "+ DictionaryAttack.getPW());
            }
            else{
                System.out.println("Password is not in dictionary. Password has not been cracked.");
            }
        }
        else if (attackType==2){
           System.out.println(BruteForce.changeChar(hashType, userPW, salt));
        }

    }
}