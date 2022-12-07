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
            File file = new File("C:\\Users\\100133214\\OneDrive - Clear Creek ISD\\2022-2023\\4th-Cybersecurity\\PasswordCracker\\src\\10-million-password-list-top-10000.txt");
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine() && isFound==false){
                String unhashed = scan.nextLine();
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
                    //System.out.println(unhashed);
                    if (BCrypt.hashpw(unhashed, salt).equals(hashedUserPw)){
                        correctPW=unhashed;
                        return "Is a Match!";
                    }
                }
            }
            return "Not a Match";
        }
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
