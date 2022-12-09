# PasswordCracker
Java Password Cracker 2022 CSHS Cybersecurity  
Developers: Mariam Aziz, Caden Ryskoski, Janice Thomas
## Rubric (51/51)
- [x] Be able to load dataset of top 10K most common passwords
- [x] Implement brute force password cracking
- [x] Implement dictionary attack password cracking
- [x] Able to run program via command line
- [x] Able to add arguments when running program via command line
- [x] Implement the following types of password cracking: MD5, BCrypt, and SHA-256
- [x] Include a README.md
## Dependencies
[Top 10,000 Passwords](https://github.com/danielmiessler/SecLists/blob/master/Passwords/Common-Credentials/10-million-password-list-top-10000.txt)  
[MD5 Hash](https://www.geeksforgeeks.org/md5-hash-in-java/)  
[SHA-256 Hash](https://www.geeksforgeeks.org/sha-256-hash-in-java/)  
[BCrypt Hash](https://github.com/jeremyh/jBCrypt/blob/master/src/main/java/org/mindrot/BCrypt.java)

## Command Line Arguments
1 = Dictionary Attack  
2 = Brute Force Attack  
  
1 = MD5  
2 = SHA-256  
3 = BCrypt  
  
1 = Plain Text  
2 = Hashed  
  
\*Optional\* Salt
## Formatting
Hashed Arguments without Salt: "13c6cf272b6dc642b9712d5dfccc2e42" 1 1 2  
Plaintext Arguments without Salt: "mariam" 1 1 1  
Hashed Arguments with Salt:  "$2a$10$YqmSH16toVKiutZsCmOgL.S9gqtAdJ6W4DTDsTojEdYg0rq2h5ln." 1 3 2 "$2a$10$YqmSH16toVKiutZsCmOgL."  
Hashed Arguments with Salt:  "mariam" 1 3 2 "$2a$10$YqmSH16toVKiutZsCmOgL."

## Notes
When using Brute Force Attack, the plaintext password must be exactly 5 characters.  
When decrypting BCrypt, the hashed password must include the salt at the beginning.
