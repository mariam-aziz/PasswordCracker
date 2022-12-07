import org.mindrot.BCrypt;

import java.io.File;
import java.io.FileNotFoundException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class PasswordCrackerClass
{
    public static void main (String[] args) throws FileNotFoundException, NoSuchAlgorithmException {
        String salt = BCrypt.gensalt();
        int attackType;
        String userPW;
        String unhashedPW = "";
        int hashType = 0;
        int input;
        Scanner in = new Scanner (System.in);
        userPW=args[0];
        attackType = Integer.parseInt(args[1]);
        hashType = Integer.parseInt(args[2]);
        input = Integer.parseInt(args[3]);
        if (args.length==5){
            salt = args[4];
        }
            if (input==1){
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