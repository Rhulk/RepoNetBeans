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
     
    }
    void conectar() throws IOException{

    }
    void desconectar() throws IOException{

    }
    void enviarRespuesta() throws IOException{

        
    }
    void obtenerMostrarImagen() throws IOException{
    
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
