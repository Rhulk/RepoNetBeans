/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
 * @author Administrador
 */
public class CaptchaGUI extends JFrame {
    // separacion top
    JPanel filaTop = new JPanel();
    // configurar fila 1
    JPanel fila1 = new JPanel();
        // pendiente de saber donde mostraremos la imagen
        JLabel imagen = new JLabel();
    // configurar fila 2
    JPanel fila2 = new JPanel();
    JButton iniciar = new JButton("Iniciar");
    JButton parar = new JButton("Parar");
    JTextField respuesta = new JTextField(15);
    
    // configurar fila 3
        JPanel fila3 = new JPanel();
    // constructor por defecto
    public CaptchaGUI(){
        super("Resolutor Captcha.");
        setSize(750,270); // tamaño inicial de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4,1,10,10)); // mas simple
        add(filaTop);
        // hasta aqui la confi del frame principal
        fila1.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
        fila1.add(imagen);
        add(fila1);
        
        fila2.setLayout(new FlowLayout(FlowLayout.CENTER,20,20));
        fila2.add(iniciar);
        parar.setEnabled(false);
        fila2.add(respuesta);
        fila2.add(parar);
        add(fila2);
        
        add(fila3);
        
        
        setVisible(true);
    }
}
