import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FolderIterator {

    private String directory;
    private List<EncryptionDecryption> aes;

    public FolderIterator(String directory){
        this.directory = directory;
        this.aes = new ArrayList<EncryptionDecryption>();
    }

    public void encryptDirectory(){
        
        File dir = new File(this.directory);
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            for (File fileInFolder : directoryListing) {        //Every file in folder is encrypted
                try {
                    this.aes.add(new EncryptionDecryption());     //Initialization of encryption system (only one key will be generated)
                } catch(Exception ex){
                    System.out.println("An exception initializing the EncryptionDecryption system occurred");
                }
                try {
                    this.aes.get(this.aes.size()-1).encrypt(fileInFolder);
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

    public void decryptDirectory(){

        int index = 0;

        File dir = new File(this.directory);
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            for (File fileInFolder : directoryListing) {        //Every file in folder is decrypted

            try {
                this.aes.get(index).decrypt(fileInFolder);
            } catch (Exception ex) {
                System.out.println("An exception during the decryption process occurred");
            }
            //System.out.println(fileInFolder.getName());
            index++;
        }
        } else {
            // Handle the case where dir is not really a directory.
            // Checking dir.isDirectory() above would not be sufficient
            // to avoid race conditions with another process that deletes
            // directories.
        }
    }
    
}
