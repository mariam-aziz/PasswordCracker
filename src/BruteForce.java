import org.mindrot.BCrypt;

import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.util.*;
public class BruteForce {
        private static String[] possibleChars = {
                "0", "1", "2", "3","4","5","6","7", "8","9",
                "a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z",
                "A","B","C","D", "E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z",
                "~", "`", "!", "@", "#", "$", "%", "^", "&", "*", "(", ")", "_", "-", "+", "=", "{", "[", "}", "]", "|", "\\", ":", ";", "\"", "'", "<", ",", ">", ".", "?", "/" };

        private static String[] currentGuess = {"0", "0", "0", "0", "0"};

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

                                if (bruteCompare(currentGuess, hashType, hashedPW, salt)) {
                                    String guess = "";
                                    for (int m = 0; m < currentGuess.length; m++) {
                                        guess += currentGuess[m];
                                    }

                                    return "Password is " + guess;
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
            for (int i = 0; i<arr.length; i++){
                guess+=currentGuess[i];
            }
            if (hashType==1){
                guess = MD5Hash.getMd5(guess);
            }
            else if (hashType==2){
                guess = SHA256Hash.toHexString(SHA256Hash.getSHA(guess));
            }
            else{

                guess = BCrypt.hashpw(guess, salt);
            }
            if (guess.equals(hashedPW)){
                return true;
            }
            else{
                return false;
            }
        }

}