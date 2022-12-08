// imports a Bcrypt package to implement the BCrypt hash
import org.mindrot.BCrypt;
// imports java classes to accomodate different hashing algorithms (MD5, SHA-256, BCrypt)
import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.util.*;

// class that implements a brute force attack 
public class BruteForce {

        // all possible characters that can be cracked using our algorithm
        private static String[] possibleChars = {
                "0", "1", "2", "3","4","5","6","7", "8","9",
                "a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z",
                "A","B","C","D", "E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z",
                "~", "`", "!", "@", "#", "$", "%", "^", "&", "*", "(", ")", "_", "-", "+", "=", "{", "[", "}", "]", "|", "\\", ":", ";", "\"", "'", "<", ",", ">", ".", "?", "/" };

        // declares and initializes a password guess string array with 5 characters 
        private static String[] currentGuess = {"0", "0", "0", "0", "0"};

        // loops through every possible combination of characters
        // returns correct guess or an error message
        public static String changeChar(int hashType, String hashedPW, String salt) throws NoSuchAlgorithmException {
            int currentIndex = 0;
            for (int h = 0; h<possibleChars.length; h++){
                currentIndex = 0;
                currentGuess[currentIndex] = possibleChars[h];
                for (int i = 0; i < possibleChars.length; i++) {
                    currentIndex = 1;
                    currentGuess[currentIndex] = possibleChars[i];
                    for (int j = 0; j < possibleChars.length; j++) {
                        currentIndex = 2;
                        currentGuess[currentIndex] = possibleChars[j];
                        for (int k = 0; k < possibleChars.length; k++) {
                            currentIndex = 3;
                            currentGuess[currentIndex] = possibleChars[k];
                            for (int l = 0; l < possibleChars.length; l++) {
                                currentIndex = 4;
                                currentGuess[currentIndex] = possibleChars[l];

                                if (bruteCompare(currentGuess, hashType, hashedPW, salt)) { // calls method to check the guess against the hashed password
                                    String guess = "";
                                    for (int m = 0; m < currentGuess.length; m++) {
                                        guess += currentGuess[m];
                                    }

                                    return "Password is " + guess;          //returns the correct guess
                                }
                            }
                        }
                    }
                }
            }
            return "User Input not Allowed";

        }
        public static boolean bruteCompare(String[] arr, int hashType, String hashedPW, String salt) throws NoSuchAlgorithmException {
            String guess = "";
            for (int i = 0; i<arr.length; i++){     //converts string array to string
                guess+=currentGuess[i];
            }
            //hashes the guess
            if (hashType==1){
                guess = MD5Hash.getMd5(guess);
            }
            else if (hashType==2){
                guess = SHA256Hash.toHexString(SHA256Hash.getSHA(guess));
            }
            else{

                guess = BCrypt.hashpw(guess, salt);
            }
            //compares hashed guess to hashed user password
            if (guess.equals(hashedPW)){
                return true;
            }
            else{
                return false;
            }
        }

}