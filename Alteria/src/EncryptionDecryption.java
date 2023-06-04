import javax.crypto.*;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.util.ArrayList;

public class EncryptionDecryption implements Serializable{

    private final int T_LEN = 128;
    private final int GCM_IV_LENGTH = 12;

    private SecretKey secretKey;
    private final SecureRandom secureRandom;

    public EncryptionDecryption() throws Exception{
        byte[] key = new byte[16];
        secureRandom = new SecureRandom();
        secureRandom.nextBytes(key);
        secretKey = new SecretKeySpec(key, "AES");
    }

    public byte[] encrypt(File file) throws Exception {
        try{
            //FileInputStream fr = new FileInputStream(file.getPath());

            byte[] iv = new byte[GCM_IV_LENGTH]; //NEVER REUSE THIS IV WITH SAME KEY
            secureRandom.nextBytes(iv);
            final Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
            GCMParameterSpec parameterSpec = new GCMParameterSpec(T_LEN, iv); //128 bit auth tag length
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, parameterSpec);

            //Cipher encryptionCipher = Cipher.getInstance("AES/GCM/NoPadding");    //Esta linea y la siguiente son CLAVE
            //encryptionCipher.init(Cipher.ENCRYPT_MODE, key);                              //Inicializar de nuevo el cifrado de encriptacion al acabar el metodo encrypt

            //byte[] messageInBytes = new byte[(int) file.length()];
            byte[] messageInBytes = java.nio.file.Files.readAllBytes(file.toPath());
            //fr.read(messageInBytes);

            //byte[] messageInBytes = ois.getBytes();
            byte[] encryptedBytes = cipher.doFinal(messageInBytes);

            encode(encryptedBytes, file);

            ByteBuffer byteBuffer = ByteBuffer.allocate(iv.length + encryptedBytes.length);
            byteBuffer.put(iv);
            byteBuffer.put(encryptedBytes);
            return byteBuffer.array();

        } catch (IOException ex) {
            System.out.println("Encryption error");
        }
        return null;
    }


    public void decrypt(byte[] cipherMessage, File file) throws Exception{
        //try {
            //FileInputStream fr = new FileInputStream(file.getPath());

            //byte[] messageInBytes = new byte[(int) file.length()];
            //fr.read(messageInBytes);
            
            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
            AlgorithmParameterSpec gcmIv = new GCMParameterSpec(128, cipherMessage, 0, GCM_IV_LENGTH);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, gcmIv);
    
            //GCMParameterSpec spec = new GCMParameterSpec(T_LEN, encryptionCipher.getIV());
            //decryptionCipher.init(Cipher.DECRYPT_MODE, key, spec);
            byte[] decryptedBytes = cipher.doFinal(cipherMessage, GCM_IV_LENGTH, cipherMessage.length - GCM_IV_LENGTH);

            decode(decryptedBytes, file);
        //} catch (IOException ex) {
        //    System.out.println("Decryption error");
        //}
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

