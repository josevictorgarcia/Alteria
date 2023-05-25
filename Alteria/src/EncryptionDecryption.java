import javax.crypto.*;
import javax.crypto.spec.GCMParameterSpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class EncryptionDecryption {

    private SecretKey key;
    private static final int KEY_SIZE = 128;
    private final int T_LEN = 128;
    private Cipher encryptionCipher;

    public void initialize() throws Exception{
        KeyGenerator generator = KeyGenerator.getInstance("AES");
        generator.init(KEY_SIZE);
        this.key = generator.generateKey();
    }

    public void encrypt(File file) throws Exception {
        try{
            FileInputStream fr = new FileInputStream(file.getPath());
        
            byte[] messageInBytes = new byte[(int) file.length()];
            fr.read(messageInBytes);

            //byte[] messageInBytes = ois.getBytes();
            this.encryptionCipher = Cipher.getInstance("AES/GCM/NoPadding");
            this.encryptionCipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encryptedBytes = encryptionCipher.doFinal(messageInBytes);
            encode(encryptedBytes);
        } catch (IOException ex) {
            System.out.println("Encryption error");
        }
    }


    public void decrypt(File file) throws Exception{
        try {
            FileInputStream fr = new FileInputStream(file.getPath());

            byte[] messageInBytes = new byte[(int) file.length()];
            fr.read(messageInBytes);
            
            Cipher decryptionCipher = Cipher.getInstance("AES/GCM/NoPadding");
            GCMParameterSpec spec = new GCMParameterSpec(T_LEN, encryptionCipher.getIV());
            decryptionCipher.init(Cipher.DECRYPT_MODE, key, spec);
            byte[] decryptedBytes = decryptionCipher.doFinal(messageInBytes);

            decode(decryptedBytes);
        } catch (IOException ex) {
            System.out.println("Decryption error");
        }
    }

    private void encode(byte[] data) {
        try{
            FileOutputStream fos = new FileOutputStream("/Users/josevictorgarciallorente/Desktop/test.txt");
            fos.write(data);
            fos.close();
        } catch (IOException ex) {
            System.out.println("Encoding error");
        }
    }

    private void decode(byte[] data) {
        try {
            FileOutputStream fos = new FileOutputStream("/Users/josevictorgarciallorente/Desktop/test.txt");
            fos.write(data);
            fos.close(); 
        } catch (IOException ex) {
            System.out.println("Decoding error");
        }
    }
}

