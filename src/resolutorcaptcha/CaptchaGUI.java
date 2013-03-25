package resolutorcaptcha;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Enrique Carvajal 25/03/2013
 */
public class CaptchaGUI extends JFrame {
   Resolutor resolutor = new Resolutor(this); // vinculamos con la clase Resolutor
    // separacion top
//    JPanel filaTop = new JPanel();
    // configurar fila 1
    JPanel fila1 = new JPanel();
    JLabel etiqueta_Imagen = new JLabel();//Etiqueta donde se mostrara la imagen
    // configurar fila 2
    JPanel fila2 = new JPanel();
    JButton enviar = new JButton("enviar");    
    JTextField respuesta = new JTextField(15);
    // configurar fila 3
    JPanel fila3 = new JPanel();
    JButton iniciar = new JButton("Iniciar");
    JButton parar = new JButton("Parar");
    // configurar fila 4
        JPanel fila4 = new JPanel();
    // constructor por defecto
    public CaptchaGUI(){
        super("Resolutor Captcha Version Beta.");
        setSize(750,370); // tamaño inicial de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4,1,10,10)); // mas simple
//        add(filaTop);
        // hasta aqui la confi del frame principal
        
        // añadimos los listener para los eventos de los botones
        iniciar.addActionListener(resolutor);
        parar.addActionListener(resolutor);
        enviar.addActionListener(resolutor);
        
        // configuracion de los JPanel
        fila1.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
        fila1.add(etiqueta_Imagen);
        etiqueta_Imagen.setBounds(0, 0, 400, 400);
        add(fila1);
        
        fila2.setLayout(new FlowLayout(FlowLayout.CENTER,20,20));
        fila2.add(enviar);
        enviar.setEnabled(false);
        fila2.add(respuesta);
        add(fila2);
        
        fila3.setLayout(new FlowLayout(FlowLayout.CENTER,20,20));
        fila3.add(iniciar);
        fila3.add(parar);
        parar.setEnabled(false);
        add(fila3);
        
        fila4.setLayout(new FlowLayout(FlowLayout.CENTER,20,20));
        add(fila4);
        
        
        setVisible(true);
    }
}
