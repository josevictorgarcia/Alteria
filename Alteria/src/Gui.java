import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Gui implements ActionListener{

    public Gui(){
        
    }

    public void main(){
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

        button1.addActionListener(new Gui() {
            public void actionPerformed(ActionEvent e){
                if (!fi.isEncrypted()){
                    fi.encryptDirectory();
                }
            }
        });

        button2.addActionListener(new Gui() {
            public void actionPerformed(ActionEvent e){
                fi.decryptDirectory();
                fi.reset();
            }
        });

        }

        @Override                                   //Este metodo es inutil, pero lo pongo sin implementar para que no de error
        public void actionPerformed(ActionEvent e) {
            throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
        }


    public void saveData(){
        String file = /* Gui.CONFIGURATIONFOLDER + File.separatorChar + */ "ConfigFiles";
        try{
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(this);
            oos.flush();
            oos.close();
        } 
        catch(IOException ex) {
            System.out.println("Data not saved or corrupted file");
        }
    }

    public Gui loadData(){
        Gui gui = new Gui();
        String file = /* Gui.CONFIGURATIONFOLDER + File.separatorChar + */ "ConfigFiles";
        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);

            gui = (Gui)ois.readObject();
            ois.close();
            return gui;
        }
        catch (IOException | ClassNotFoundException ex){
            System.out.println("Error loading configuration files");
        }
        return gui;
    }
}
