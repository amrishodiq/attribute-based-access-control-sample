package amri.shodiq.authorization.util;

import amri.shodiq.authorization.model.Requestor;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.Mac;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author amri.s
 */
public class Crypto {
    private static Crypto instance;
    public static synchronized Crypto getInstance() {
        if (instance == null) {
            instance = new Crypto();
        }
        return instance;
    }
    
    private static final String CIPHER_SPEC = "AES/CBC/PKCS5PADDING";
    
    private String MAC_KEY  = "#AsalBukanJokowi";
    private String KEY      = "DunguLevelMenter";
    private String IV       = "2019gantipreside";
    
    private SecretKeySpec   key;
    private IvParameterSpec iv;
    private Cipher          encryptor;
    private Cipher          decryptor;
    private Mac             mac;
    
    private Crypto() {
        initCrypto();
    }
    
    public void overrideCryptoParams(String newKey, String newIv, String macKey) throws Exception {
        if (newKey == null || newIv == null || macKey == null) throw new Exception("Key, IV, Mac Key cannot be null.");
        if (newKey.length() != 8 || newIv.length() != 8 || macKey.length() != 8) throw new Exception("Key, IV, Mac Key should be 8 characters.");
        
        this.KEY        = newKey;
        this.IV         = newIv;
        this.MAC_KEY    = macKey;
        
        initCrypto();
    }
    
    private void initCrypto() {
        try {
            iv  = new IvParameterSpec(IV.getBytes("UTF-8"));
            key = new SecretKeySpec(KEY.getBytes("UTF-8"), "AES");
            
            encryptor = Cipher.getInstance(CIPHER_SPEC);
            encryptor.init(Cipher.ENCRYPT_MODE, key, iv);
            
            decryptor = Cipher.getInstance(CIPHER_SPEC);
            decryptor.init(Cipher.DECRYPT_MODE, key, iv);
            
            mac = Mac.getInstance("HmacSHA256");
            SecretKeySpec macKey = new SecretKeySpec(MAC_KEY.getBytes("UTF-8"), "HmacSHA256");
            mac.init(macKey);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private String encrypt(String plainText) throws IllegalBlockSizeException, BadPaddingException {
        byte[] encrypted = encryptor.doFinal(plainText.getBytes());
        return Base64.getUrlEncoder().withoutPadding().encodeToString(encrypted);
    }
    
    private String decrypt(String cipherText) throws BadPaddingException, IllegalBlockSizeException {
        byte[] decrypted = decryptor.doFinal(Base64.getUrlDecoder().decode(cipherText));
        return new String(decrypted);
    }
    
    private String hashedMac(String text) throws UnsupportedEncodingException {
        mac.reset();
        mac.update(text.getBytes("UTF-8"));
        return Base64.getUrlEncoder().withoutPadding().encodeToString(mac.doFinal());
    }
    
    public String createToken(int userId, String username) throws BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException {
        String payload = encrypt(String.valueOf(userId) + "|" + username);
        String hMac = hashedMac(payload);
        return payload + "." + hMac;
    }
    
    public boolean isTokenValid(String token) {
        String[] parts = token.split("\\.");
        if (parts.length != 2) return false;
        
        try {
            if (!hashedMac(parts[0]).equals(parts[1])) return false;
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }
    
    public Requestor tokenToRequestor(String token) {
        String[] parts = token.split("\\.");
        try {
            String decrypted = decrypt(parts[0]);
            parts = decrypted.split("\\|");
            
            if (parts.length != 2) {
                System.out.println(parts.length);
                return null;
            }
            
            return new Requestor(Integer.parseInt(parts[0]), parts[1], null);
        } catch (BadPaddingException | IllegalBlockSizeException | NumberFormatException ex) {
        }
        return null;
    }
    
    public static void main(String[] args) {
        try {
//            String plain = "ABCDE FGHIJ KLMNO PQRST UVWXY Z";
//            String cipher = Crypto.getInstance().encrypt(plain);
//            System.out.println("Cipher: "+cipher);
//            String decrypted = Crypto.getInstance().decrypt(cipher);
//            System.out.println("Decrypted: "+decrypted);
//            String hash = Crypto.getInstance().hash(cipher);
//            System.out.println("Hashed cipher: "+hash);
//            hash = Crypto.getInstance().hash(cipher);
//            System.out.println("Hashed cipher: "+hash);
//            
//            String hMac = Crypto.getInstance().hashedMac(plain);
//            System.out.println("Hashed mac: "+hMac);

            int id = 12;
            String username = "amri.shodiq";
            
            String token = Crypto.getInstance().createToken(id, username);
            System.out.println("Token: "+token);
            
            boolean isValid = Crypto.getInstance().isTokenValid(token);
            System.out.println("Valid 1: "+isValid);
            
            isValid = Crypto.getInstance().isTokenValid(token+"=");
            System.out.println("Valid 2: "+isValid);
            
            Requestor requestor = Crypto.getInstance().tokenToRequestor(token);
            System.out.println("Requestor: "+requestor);

        } catch (BadPaddingException | IllegalBlockSizeException | UnsupportedEncodingException ex) {
            ex.printStackTrace();
        }
    }
}
