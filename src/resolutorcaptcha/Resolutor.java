package resolutorcaptcha;

import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
/**
 *
 * @author Enrique Carvajal 25/03/2013
 */
public class Resolutor  implements ItemListener, ActionListener, Runnable{
//    CaptchaGUI gui;
    GuiCaptcha gui;
    Thread conectando;
    private Socket sk;
    private int puerto=5000;
    private DataOutputStream dos;
    private DataInputStream dis;
    private ObjectInputStream entrada_imagen;
    
//    private JLabel etiqueta_Imagen_Original;//Etiqueta donde se mostrara la imagen

    public Resolutor(GuiCaptcha in){
        gui =in;        
    }

    @Override
    public void run(){
        try { 
            sk = new Socket("127.0.0.1", puerto);
            dos = new DataOutputStream(sk.getOutputStream());
            dis = new DataInputStream(sk.getInputStream());
            dos.writeUTF("resolucion");
//            System.out.println("esperando respuesta");
            System.out.println(dis.readUTF());  // El server nos devuelve el foco. Para recibir la imagen.
            obtenerMostrarImagen(); // leemos la imagen.
            gui.enviar.setEnabled(true);
            gui.respuesta.setEnabled(true);
            gui.respuesta.setEditable(true);
            
            System.out.println("recibida");            
        } catch (UnknownHostException ex) {
            Logger.getLogger(Resolutor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Resolutor.class.getName()).log(Level.SEVERE, null, ex);
        }       
    }
    void conectar() throws IOException{
        esperarCaptcha();
        System.out.println("conectar");
        conectando = new Thread(this);
        gui.iniciar.setEnabled(false);
        gui.enviar.setEnabled(false);
        gui.respuesta.setEditable(false);
        gui.parar.setEnabled(true);
        conectando.start();
    }
    void desconectar() throws IOException{
        sk.close();
        gui.parar.setEnabled(false);
        gui.iniciar.setEnabled(true);
        conectando = null;
    }
    void enviarRespuesta() throws IOException{
        dos.writeUTF(gui.respuesta.getText());
        gui.enviar.setEnabled(false);
        gui.respuesta.setText("");
        conectar(); // continuar a la espera de captchas
        
    }
    void obtenerMostrarImagen() throws IOException{
//        System.out.println("obtener imagen");
               try {
                entrada_imagen = new ObjectInputStream(sk.getInputStream());
                byte[] bytesImagen = (byte[]) entrada_imagen.readObject();
                ByteArrayInputStream entradaImagen = new ByteArrayInputStream(bytesImagen);
                BufferedImage bufferedImage = ImageIO.read(entradaImagen);
                // cargar la imagen en un JLabel.
                ImageIcon icono=new ImageIcon(bufferedImage);
                gui.etiqueta_Imagen.setIcon(icono);
                gui.enviar.setEnabled(true);// para poder enviar la respuesta
                // Guardar la imagen en fichero.
//                String nombreFichero="C:\\Documents and Settings\\Administrador\\Escritorio\\CaptchaIn.jpg";
//                System.out.println("Generando el fichero: "+nombreFichero );
//                FileOutputStream out = new FileOutputStream(nombreFichero);
//                // esbribe la imagen a fichero
//                ImageIO.write(bufferedImage, "jpg", out);
            }
 
            // atrapar problemas que pueden ocurrir al tratar de leer del cliente
            catch ( ClassNotFoundException excepcionClaseNoEncontrada ) {
                System.out.println( "\nSe recibió un tipo de objeto desconocido" );
            }         
    }
    /*
     * Recoge el boton pulsado
     */
    @Override
    public void actionPerformed(ActionEvent event){
        String comando = event.getActionCommand();
         try {
            if (comando.equalsIgnoreCase("iniciar")){
                conectar();
            }
            if (comando.equalsIgnoreCase("parar")){
                    desconectar();
            }
            if (comando.equalsIgnoreCase("enviar")){
                    enviarRespuesta();
            }
        } catch (IOException ex) {
                Logger.getLogger(Resolutor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public void esperarCaptcha() throws IOException{
        BufferedImage esperar= ImageIO.read(new File("C:\\Documents and Settings\\Administrador\\Mis documentos\\NetBeansProjects\\ResolutorCaptcha\\src\\resolutorcaptcha\\esperandoCaptcha.jpg"));
        ImageIcon icono=new ImageIcon(esperar);
        gui.etiqueta_Imagen.setIcon(icono);     
    }
    
}
