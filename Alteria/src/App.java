import java.io.File;

public class App {
    public static void main(String[] args) throws Exception {
        try {
            EncryptionDecryption aes = new EncryptionDecryption();
            aes.initialize();
            File encryptedMessage = new File("/Users/josevictorgarciallorente/Desktop/test.txt");
            aes.encrypt(encryptedMessage);
            aes.decrypt(encryptedMessage);
        } catch (Exception ex){
            System.out.println("An exception occurred");
        }
    }
}
