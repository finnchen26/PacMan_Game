import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.tools.Tool;

public class BasicGameApp implements Runnable ,KeyListener {
    final int WIDTH = 1000;
    final int HEIGHT = 700;
    public JFrame frame;
    public Canvas canvas;
    public JPanel panel;
    public BufferStrategy bufferStrategy;

    public Image background;
    public Image toadcool;
    public Image marioPic;
    public Image toadPic;
    int x = 0;
    int x2 = 999;


    private Mario mario;
    private Mario mariotoad;
    private Mario mariospawn;

    public static void main(String[] args) {
        BasicGameApp ex = new BasicGameApp();
        new Thread(ex).start();
    }

    public BasicGameApp() {
        setUpGraphics();
        background = Toolkit.getDefaultToolkit().getImage("background.jpg");
        toadcool = Toolkit.getDefaultToolkit().getImage("toadcool .png");
        marioPic = Toolkit.getDefaultToolkit().getImage("mario.png");
        toadPic = Toolkit.getDefaultToolkit().getImage("toad.png");
       // toadPic
        mario = new Mario((int)(Math.random()*940), (int)(Math.random()*620));
        mariotoad = new Mario((int)(Math.random()*940), (int)(Math.random()*620));
        mariospawn = new Mario((int)(Math.random()*940), (int)(Math.random()*620));

        mario.isControlled = true;
        mariotoad.isControlled = true;
        mariospawn.isAlive = false;
    }

    public void run() {
        while (true) {
            moveThings();
            render();
            pause(20);
        }
    }

    public void moveThings(){
        mario.bounce();
        mariotoad.bounce();

        if(mario.rect.intersects(mariotoad.rect) && !mario.isCrashing && !mariotoad.isCrashing){
            System.out.println("Crash!");
//            mario.height = mario.height - 30;
//            mariotoad.height = mariotoad.height + 30;
            mario.isCrashing = true;
            mariotoad.isCrashing = true;
            mariospawn.isAlive = true;
        }

        if (mario.rect.intersects(mariotoad.rect) == false){
            mario.isCrashing = false;
            mariotoad.isCrashing = false;
        }
    }

    public void pause(int time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e){}
    }

    public void setUpGraphics(){
        frame = frame = new JFrame("Application Template");   //Create the program window or frame.  Names it.

        panel = (JPanel) frame.getContentPane();  //sets up a JPanel which is what goes in the frame
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));  //sizes the JPanel
        panel.setLayout(null);   //set the layout

        // creates a canvas which is a blank rectangular area of the screen onto which the application can draw
        // and trap input events (Mouse and Keyboard events)
        canvas = new Canvas();
        canvas.setBounds(0, 0, WIDTH, HEIGHT);
        canvas.setIgnoreRepaint(true);
        canvas.addKeyListener(this);

        panel.add(canvas);  // adds the canvas to the panel.

        // frame operations
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //makes the frame close and exit nicely
        frame.pack();  //adjusts the frame and its contents so the sizes are at their default or larger
        frame.setResizable(false);   //makes it so the frame cannot be resized
        frame.setVisible(true);      //IMPORTANT!!!  if the frame is not set to visible it will not appear on the screen!

        // sets up things so the screen displays images nicely.
        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();
        canvas.requestFocus();
        System.out.println("DONE graphic setup");

    }

    public void render(){
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.clearRect(0, 0, WIDTH, HEIGHT);

        if (x < -1000) {
            x = 1000;
        }
        if (x2 < -1000) {
            x2 = 1000;
        }
        x = x - 2;
        x2 = x2 - 2;
        g.drawImage(background, x, 0, WIDTH, HEIGHT, null);
        g.drawImage(background, x2, 0, WIDTH, HEIGHT, null);
        g.drawRect(mario.rect.x,mario.rect.y,mario.rect.width,mario.rect.height);
        g.drawRect(mariotoad.rect.x,mariotoad.rect.y,mariotoad.rect.width,mariotoad.rect.height);
        g.drawImage(marioPic, mario.xpos, mario.ypos, mario.width, mario.height, null);
        g.drawImage(toadPic, mariotoad.xpos, mariotoad.ypos, mariotoad.width, mariotoad.height, null);

        if (mariospawn.isAlive){
            g.drawImage(toadcool, mariospawn.xpos, mariospawn.ypos, mariospawn.width, mariospawn.height, null);
        }

        g.dispose();
        bufferStrategy.show();


    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(e.getKeyCode());
        if (e.getKeyCode() == 38){
            System.out.println("going up");
            mario.isNorth = true;
        }
        else if (e.getKeyCode() == 40){
            System.out.println("going down");
            mario.isSouth = true;


        }
        else if (e.getKeyCode() == 37){
            System.out.println("going left");
            mario.isWest = true;

        }
        else if (e.getKeyCode() == 39){
            System.out.println("going right");
            mario.isEast = true;

        }



        //mario taod
        System.out.println(e.getKeyCode());
        if (e.getKeyCode() == 87){
            System.out.println("going up toad");
            mariotoad.isNorth = true;
        }
        else if (e.getKeyCode() == 83){
            System.out.println("going down toad");
            mariotoad.isSouth = true;


        }
        else if (e.getKeyCode() == 65){
            System.out.println("going left toad");
            mariotoad.isWest = true;

        }
        else if (e.getKeyCode() == 68){
            System.out.println("going right toad");
            mariotoad.isEast = true;

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == 38){
            System.out.println("going up");
            mario.isNorth = false;
        }

        if (e.getKeyCode() == 40){
            System.out.println("going down");
            mario.isSouth = false;
        }

        if (e.getKeyCode() == 37){
            System.out.println("going left");
            mario.isWest = false;
        }
        if (e.getKeyCode() == 39){
            System.out.println("going right");
            mario.isEast = false;
        }

        //toad

        System.out.println(e.getKeyCode());
        if (e.getKeyCode() == 87){
            System.out.println("going up toead");
            mariotoad.isNorth = false;
        }
        else if (e.getKeyCode() == 83){
            System.out.println("going down toad");
            mariotoad.isSouth = false;


        }
        else if (e.getKeyCode() == 65){
            System.out.println("going left taod");
            mariotoad.isWest = false;

        }
        else if (e.getKeyCode() == 68){
            System.out.println("going right toad");
            mariotoad.isEast = false;

        }

    }

}