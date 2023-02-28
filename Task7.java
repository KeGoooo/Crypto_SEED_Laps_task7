//the task talking about if I have a plain text and cipher text and I know the cipher was AES-CBC ,also i have a wordlist ,finally You have to find the key 
//so the text was "This is a top secret." and the cipher was "764aa26b55a4da654df6b19e4bce00f4ed05e09346fb0e762583cb7da2ac93a2" and the iv was "aabbccddeeff00998877665544332211"
//My refernces that helped me doing this task 
//https://stackoverflow.com/questions/13185727/reading-a-txt-file-using-scanner-class-in-java
//https://techieshouts.com/home/aes-encryption-and-decryption-in-java/
//https://www.baeldung.com/java-aes-encryption-decryption
//https://www.baeldung.com/java-byte-arrays-hex-strings
//https://www.programiz.com/java-programming/examples/convert-byte-array-hexadecimal
//https://stackoverflow.com/questions/23561104/how-to-encrypt-and-decrypt-string-with-my-passphrase-in-java-pc-not-mobile-plat
import java.util.*;
import java.io.File;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.IvParameterSpec;


public class Task7 {

    public static void main(String [] args){
        try {
            Scanner sc = new Scanner(new File("D:\\words.txt"));// the path will be diffrent in ubuntu 
            while (sc.hasNextLine()) {
                String k = sc.nextLine();
                k = k.replace("\n", "");
                if (k.length() < 16) {

                    for (int i = k.length(); i < 16; i++) // if the word less then 16 letter add #
                        k += "#"; 
                String plaintext = "This is a top secret.";                        
                String IV="aabbccddeeff00998877665544332211";
                byte[] iv = hexdecemilToArray(IV);
                byte[] encr = encrypt(plaintext, iv, k);

                if (encr == null) {
                    System.out.println("No Key Found!");

                               } else {
                                String encryptedHexadecmal = byteToHexadecemal(encr);
                                if (encryptedHexadecmal.toLowerCase().equals("764aa26b55a4da654df6b19e4bce00f4ed05e09346fb0e762583cb7da2ac93a2")) {
                                   System.out.println("Looking for the key...");
                                   String plainText = decrypt(encr, iv, k);
                                     if (plainText != null) {
                                       System.out.println("The Plain Text Is: " + plainText);
                                       System.out.println("The Cipher Text Is: " + encryptedHexadecmal);
                                       System.out.println("The Key Is: " + k);
                                }                                                                        
                                       }
                                       }
                                   } 
                                }
            }

        catch (Exception ex) {
            ex.printStackTrace();
                              }            

      }

//Simple method that converts bytes to hex
    public static String byteToHexadecemal(byte[] iv) {
        char[] hex_char = new char[iv.length * 2];
        char[] hex_arr = "0123456789ABCDEF".toCharArray();
        for (int j = 0; j < iv.length; j++) {
            int v = iv[j] & 0xFF;
            hex_char[j * 2] = hex_arr[v >>> 4];
            hex_char[j * 2 + 1] = hex_arr[v & 0x0F];
                                               }

        return new String(hex_char); 
                }


      
// Simple method that converts hex to an array
    public static byte[] hexdecemilToArray(String hex) {
        int len = hex.length();
        byte[] value = new byte[len / 2];

        for (int i = 0; i < len; i += 2) 
            value[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4) + Character.digit(hex.charAt(i + 1), 16));
        return value;                          
      }


        public static byte[] encrypt(String plaintext, byte[] IV, String k) {

        try {
            IvParameterSpec iv = new IvParameterSpec(IV);
            SecretKeySpec key = new SecretKeySpec(k.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key, iv);
            byte[] cipherText = cipher.doFinal(plaintext.getBytes());
            return cipherText;

        } catch (Exception ex) {

                               } 
                   return null;                                              }



        public static String decrypt(byte[] encr, byte[] IV, String k) {

        try {
            IvParameterSpec iv = new IvParameterSpec(IV);
            SecretKeySpec key = new SecretKeySpec(k.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, key, iv);
            byte[] original = cipher.doFinal(encr);
                 return new String(original);
            } catch (Exception ex) {
                                   } 

            return null;                                         
   }

    

    

        

    

    

    

}