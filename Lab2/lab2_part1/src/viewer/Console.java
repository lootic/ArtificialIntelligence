/******************************************************
 *
 * Project: javaRL
 * File: Console.java
 * @version 1.0
 * Date: 11.05.2003
 * 
 * @author  Alexander Kleiner,
 * 		    University of Freiburg
 * 		    kleiner@informatik.uni-freiburg.de
 * 
 * Description:
 * 
 *  
 ******************************************************/
package viewer;

import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JPanel;
//import java.awt.event.*;

public class Console {
    // Create a title string from the class name:
    public static String title(Object o) {
        String t = o.getClass().toString();
        // Remove the word "class":
        if (t.indexOf("class") != -1)
            t = t.substring(6);
        return t;
    }
    public static void run(JFrame frame, int width, int height) {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, height);
        frame.setVisible(true);
    }
    public static void run(JApplet applet, int width, int height) {
        JFrame frame = new JFrame(title(applet));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(applet);
        frame.setSize(width, height);
        applet.init();
        applet.start();
        frame.setVisible(true);
    }
    public static void run(JPanel panel, int width, int height) {
        JFrame frame = new JFrame(title(panel));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(panel);
        frame.setSize(width, height);
        frame.setVisible(true);
    }
} ///:~
