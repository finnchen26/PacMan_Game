import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class BasicGameApp implements Runnable, KeyListener {
    final int WIDTH = 1000;
    final int HEIGHT = 700;
    public JFrame frame;
    public Canvas canvas;
    public JPanel panel;
    public BufferStrategy bufferStrategy;

    public Image background;
    public Image toad;
    public Image toadcool;
    public Image mario;

    public static void main(String[] args) {
        BasicGameApp ex = new BasicGameApp();
        new Thread(ex).start();
    }

    public BasicGameApp() {
        setUpGraphics();
        background = Toolkit.getDefaultToolkit().getImage("background.jpg");
    }
}