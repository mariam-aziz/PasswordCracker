import org.mindrot.BCrypt;

import java.io.File;
import java.io.FileNotFoundException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class DictionaryAttack {
        private static boolean isFound = false;
        private static String userPassword = "";
        private static String libHash = "";
        private static String correctPW = "";

        public static String dictionaryCrack( String hashedUserPw, int hashType, String salt) throws FileNotFoundException, NoSuchAlgorithmException {
            userPassword = hashedUserPw;
            //imports text file with 10,000 most common passwords
            File file = new File("C:\\Users\\100133214\\OneDrive - Clear Creek ISD\\2022-2023\\4th-Cybersecurity\\PasswordCracker\\src\\10-million-password-list-top-10000.txt");
            Scanner scan = new Scanner(file);
            //goes through the entire dictionary until password is found
            while (scan.hasNextLine() && isFound==false){
                String unhashed = scan.nextLine();
                //hashes the password guess and compares
                if(hashType == 1) {
                    libHash = MD5Hash.getMd5(unhashed);

                    if (checkPassword(libHash)){
                        correctPW=unhashed;
                        return "Is a Match!";
                    }
                }
                else if(hashType == 2)
                {
                    libHash = SHA256Hash.toHexString(SHA256Hash.getSHA(unhashed));
                    if (checkPassword(libHash)){
                        correctPW=unhashed;
                        return "Is a Match!";
                    }
                }
                else    //BCrypt
                {
                    if (BCrypt.hashpw(unhashed, salt).equals(hashedUserPw)){
                        correctPW=unhashed;
                        return "Is a Match!";
                    }
                }
            }
            return "Not a Match";
        }
        //checks if hashed guess and hashed user password are equal
        public static boolean checkPassword(String s){
            //s is the next line in dictionary
            if (s.equals(userPassword)){
                return true;
            }
            return false;
        }
        public static String getPW(){
            return correctPW;
        }

}
