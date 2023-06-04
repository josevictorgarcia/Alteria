import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.ObjectInputStream;
//import java.io.ObjectOutputStream;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Gui implements ActionListener, Serializable{

private FolderIterator fi;

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

    //FolderIterator fi = new FolderIterator("/Users/josevictorgarciallorente/Desktop/test");

    button1.addActionListener(new Gui() {
        public void actionPerformed(ActionEvent e){
            try{ 
                fi = loadData();
            } 
            catch(Exception ex) {
                System.out.println("Error Class Gui button1.addActionListener");
            }
            if (!fi.isEncrypted()){
                fi.encryptDirectory();
            }
            saveData(fi);
        }
    });

    button2.addActionListener(new Gui() {
        public void actionPerformed(ActionEvent e){
            try{ 
                fi = loadData();
                fi.decryptDirectory();
                fi.reset();
                saveData(fi);
            } 
            catch(Exception ex) {
                System.out.println("Error Class Gui button2.addActionListener");
            }
            
        }
    });

    }

    @Override                                   //Este metodo es inutil, pero lo pongo sin implementar para que no de error
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }

    public FolderIterator loadData() throws Exception {
        try {
            FileInputStream archivo = new FileInputStream("aes.ser");
            ObjectInputStream ois = new ObjectInputStream(archivo);
            FolderIterator aes = (FolderIterator) ois.readObject();
            ois.close();
            return aes;
        }
        catch(IOException | ClassNotFoundException e) {
           //lo creamos pues no hay archivo
            return new FolderIterator("/Users/josevictorgarciallorente/Desktop/test");
        }
    }
    
    //guarda en memoria la estructura equipos
    public void saveData(FolderIterator fi) {
        try {
            FileOutputStream archivo = new FileOutputStream("aes.ser");
            ObjectOutputStream oos = new ObjectOutputStream(archivo);
            oos.writeObject(fi);
            oos.close();
        }
        catch (Exception ex) {
        System.out.println("Error clase App method saveEquipos");}
    }

    /*public void saveData(){
        String file =  Gui.CONFIGURATIONFOLDER + File.separatorChar + "ConfigFiles";
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
        String file = Gui.CONFIGURATIONFOLDER + File.separatorChar + "ConfigFiles";
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
    }*/
}
