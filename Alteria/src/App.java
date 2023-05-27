//import java.io.File;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class App implements ActionListener{
    //public static void main(String[] args) {
      //  FolderIterator fi = new FolderIterator("/Users/josevictorgarciallorente/Desktop/test");
      //  fi.encryptDirectory();
      //  fi.decryptDirectory();

        public static void main(String args[]){
 
            JFrame frame = new JFrame();
            frame.setVisible(true);

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);

            JButton button1 = new JButton("Encrypt");
            JButton button2 = new JButton("Decrypt"); 
            JButton buttonAux = new JButton("");
            frame.add(button1);
            frame.add(button2);
            frame.add(buttonAux);
            frame.setSize(400,200);
            button1.setBounds(0 , 50 , 200 , 100);
            button2.setBounds(200 , 50 , 200 , 100);

            FolderIterator fi = new FolderIterator("/Users/josevictorgarciallorente/Desktop/test");

            button1.addActionListener(new App() {
                public void actionPerformed(ActionEvent e){
                    if (!fi.isEncrypted()){
                        fi.encryptDirectory();
                    }
                }
            });

            button2.addActionListener(new App() {
                public void actionPerformed(ActionEvent e){
                    fi.decryptDirectory();
                    fi.reset();
                }
            });

         }

        @Override                                   //Este metodo es inutil, pero lo pongo sin implementar para que no de error
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
        }

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
//}
