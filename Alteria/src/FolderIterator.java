import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FolderIterator implements Serializable {

    private String directory;
    //private List<EncryptionDecryption> aes;
    EncryptionDecryption aes;
    private List<byte[]> byteList;

    public FolderIterator(String directory) throws Exception{
        this.directory = directory;
        //this.aes = new ArrayList<EncryptionDecryption>();
        this.aes = new EncryptionDecryption();
        this.byteList = new ArrayList<byte[]>();
    }

    public void encryptDirectory() {
        

        File dir = new File(this.directory);
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            for (File fileInFolder : directoryListing) {        //Every file in folder is encrypted
//                try {
//                    this.aes.add(new EncryptionDecryption());     //Initialization of encryption system (only one key will be generated)
//                } catch(Exception ex){
//                   System.out.println("An exception initializing the EncryptionDecryption system occurred");
//                }
                try {
//                    this.aes.get(this.aes.size()-1).encrypt(fileInFolder);
                    //this.aes.decrypt(fileInFolder);
                    this.byteList.add(aes.encrypt(fileInFolder));
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

    public void decryptDirectory() throws Exception{

        int index = 0;

        File dir = new File(this.directory);
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            for (File fileInFolder : directoryListing) {        //Every file in folder is decrypted
                aes.decrypt(this.byteList.get(index), fileInFolder);
//            try {
//                this.aes.get(index).decrypt(fileInFolder);
//            } catch (Exception ex) {
//               System.out.println("An exception during the decryption process occurred");
//            }
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

    public void reset(){
        //this.aes.clear();
        this.byteList.clear();
    }

    public boolean isEncrypted(){
        //return !this.aes.isEmpty();
        return !this.byteList.isEmpty();
    }
    
}
