import java.io.File;

public class FolderIterator {

    private String directory;
    private EncryptionDecryption aes;

    public FolderIterator(String directory){
        this.directory = directory;
        //this.aes = new EncryptionDecryption();  
        try {
            this.aes = new EncryptionDecryption();     //Initialization of encryption system (only one key will be generated)
        } catch(Exception ex){
            System.out.println("An exception initializing the EncryptionDecryption system occurred");
        }
    }

    public void encryptDirectory(){
            
        File dir = new File(this.directory);
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            for (File fileInFolder : directoryListing) {        //Every file in folder is encrypted

            //File encryptedMessage = new File("/Users/josevictorgarciallorente/Desktop/test.zip");
            try {
                this.aes.encrypt(fileInFolder);
                //this.aes.decrypt(fileInFolder);
            } catch (Exception ex) {
                System.out.println("An exception during the encryption process occurred");
            }
            //System.out.println(fileInFolder.getName());
            
        }
        } else {
            // Handle the case where dir is not really a directory.
            // Checking dir.isDirectory() above would not be sufficient
            // to avoid race conditions with another process that deletes
            // directories.
        }
    }

/*     public void decryptDirectory(){
            
        File dir = new File(this.directory);
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            for (File fileInFolder : directoryListing) {        //Every file in folder is decrypted

            //File encryptedMessage = new File("/Users/josevictorgarciallorente/Desktop/test.zip");
            try {
                this.aes.decrypt(fileInFolder);
            } catch (Exception ex) {
                System.out.println("An exception during the decryption process occurred");
            }
            //System.out.println(fileInFolder.getName());
            
        }
        } else {
            // Handle the case where dir is not really a directory.
            // Checking dir.isDirectory() above would not be sufficient
            // to avoid race conditions with another process that deletes
            // directories.
        }
    }   */
    
}
