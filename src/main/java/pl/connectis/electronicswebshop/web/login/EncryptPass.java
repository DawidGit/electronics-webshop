package pl.connectis.electronicswebshop.web.login;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Base64.Encoder;

public class EncryptPass {

    static Cipher cipher;


    public static String EncryptPassword (String passw) throws Exception {

        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128); // block size is 128bits
        SecretKey secretKey = keyGenerator.generateKey();

        cipher = Cipher.getInstance("AES");
        byte[] plainTextByte = passw.getBytes();
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedByte = cipher.doFinal(plainTextByte);
        Encoder encoder = Base64.getEncoder();
        return encoder.encodeToString(encryptedByte);
    }
}
