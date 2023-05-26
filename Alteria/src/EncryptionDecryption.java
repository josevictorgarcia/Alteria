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

    public EncryptionDecryption() throws Exception{
        KeyGenerator generator = KeyGenerator.getInstance("AES");
        generator.init(KEY_SIZE);
        this.key = generator.generateKey();
    }

    public void encrypt(File file) throws Exception {
        try{
            //FileInputStream fr = new FileInputStream(file.getPath());

            this.encryptionCipher = Cipher.getInstance("AES/GCM/NoPadding");    //Esta linea y la siguiente son CLAVE
            this.encryptionCipher.init(Cipher.ENCRYPT_MODE, key);                              //Inicializar de nuevo el cifrado de encriptacion al acabar el metodo encrypt

            //byte[] messageInBytes = new byte[(int) file.length()];
            byte[] messageInBytes = java.nio.file.Files.readAllBytes(file.toPath());
            //fr.read(messageInBytes);

            //byte[] messageInBytes = ois.getBytes();
            byte[] encryptedBytes = encryptionCipher.doFinal(messageInBytes);

            encode(encryptedBytes, file);
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

            decode(decryptedBytes, file);
        } catch (IOException ex) {
            System.out.println("Decryption error");
        }
    }

    private void encode(byte[] data, File file) {
        try{
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(data);
            fos.close();
        } catch (IOException ex) {
            System.out.println("Encoding error");
        }
    }

    private void decode(byte[] data, File file) {
        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(data);
            fos.close(); 
        } catch (IOException ex) {
            System.out.println("Decoding error");
        }
    }
}

