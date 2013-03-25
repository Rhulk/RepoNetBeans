/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resolutorcaptcha;

import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
/**
 *
 * @author Enrique Carvajal 25/03/2013
 */
public class Resolutor  implements ItemListener, ActionListener, Runnable{
    CaptchaGUI gui;
    Thread conectando;
    private Socket sk;
    private int puerto=5000;
    private DataOutputStream dos;
    private DataInputStream dis;
    private ObjectInputStream entrada_imagen;
    
    
    
    public Resolutor(CaptchaGUI in){
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
//            obtenerMostrarImagen(); // leemos la imagen.
            System.out.println("recibida");            
        } catch (UnknownHostException ex) {
            Logger.getLogger(Resolutor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Resolutor.class.getName()).log(Level.SEVERE, null, ex);
        }          
    }
    void conectar(){
        conectando = new Thread(this);
        gui.iniciar.setEnabled(false);
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
    }
    void obtenerMostrarImagen() throws IOException{
               try {
                entrada_imagen = new ObjectInputStream(sk.getInputStream());
                byte[] bytesImagen = (byte[]) entrada_imagen.readObject();
                ByteArrayInputStream entradaImagen = new ByteArrayInputStream(bytesImagen);
                BufferedImage bufferedImage = ImageIO.read(entradaImagen);
 
                String nombreFichero="C:\\Documents and Settings\\Administrador\\Escritorio\\CaptchaIn.jpg";
                System.out.println("Generando el fichero: "+nombreFichero );
                FileOutputStream out = new FileOutputStream(nombreFichero);
                // esbribe la imagen a fichero
                ImageIO.write(bufferedImage, "jpg", out);
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
    
}
