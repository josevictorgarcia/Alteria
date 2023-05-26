//import java.io.File;

public class App {
    public static void main(String[] args) {
        FolderIterator fi = new FolderIterator("/Users/josevictorgarciallorente/Desktop/test");
        fi.encryptDirectory();
        //fi.decryptDirectory();
        //try {
            //EncryptionDecryption aes = new EncryptionDecryption();
            //aes.initialize();
            //File encryptedMessage = new File("/Users/josevictorgarciallorente/Desktop/test.zip");
            //aes.encrypt(encryptedMessage);
            //aes.decrypt(encryptedMessage);

            

        //} catch (Exception ex){
        //    System.out.println("An exception occurred");
        //}
    }
}
