import java.awt.*;

public class Mario {
    public String name;
    public int xpos;
    public int ypos;
    public int dx;
    public int dy;
    public int width;
    public int height;
    public boolean isAlive;
    public Rectangle rect;
    public boolean isCrashing;
    public boolean isNorth;
    public boolean isSouth;
    public boolean isWest;
    public boolean isEast;
    public boolean isControlled;


    public Mario(int pXpos, int pYpos){
        xpos = pXpos;
        ypos = pYpos;
        dx = 5;
        dy = 1;
        width = 100;
        height = 100;
        isAlive = true;
        rect = new Rectangle(xpos, ypos, width, height);
        isCrashing = false;
        isNorth = false;
        isSouth = false;
        isWest = false;
        isEast = false;
        isControlled = false;
    }

    public void move(){
        xpos = xpos + dx;
        ypos = ypos + dy;
        rect = new Rectangle(xpos, ypos, width, height);
    }

    public void bounce(){
        if (isControlled) {
            if (isNorth == false && isSouth == false){
                dy = 0;
            } else if (isNorth) {
                dy = -4;
            } else {
                dy = 4;
            }
            if (isWest == false && isEast == false) {
                dx = 0;
            } else if (isWest) {
                dx = -4;
            } else {
                dx = 4;
            }
        }
        if(xpos < 0){
            dx = -dx;
        }
        if (xpos > 1000-width){ //bounce off west wall
            dx = -dx;
        }

        if(ypos < 0){
            dy = -dy;
        }

        if(ypos > 700-height){
            dy = -dy;
        }

        xpos = xpos + dx;
        ypos = ypos + dy;
        rect = new Rectangle(xpos, ypos, width, height);
    }

    public void wrap(){
        if(isControlled) {
            if (isNorth == false && isSouth == false) {
                dy = 0;
            } else if (isNorth) {
                dy = -4;
            } else {
                dy = 4;
            }
            if (isWest == false && isEast == false) {
                dx = 0;
            } else if (isWest) {
                dx = -4;
            } else {
                dx = 4;
            }
        }

        if (xpos < 0){
            xpos = 1000-width;
        }
        if (xpos > 1000-width){ //bounce off west wall
            xpos = 0;
        }

        if(ypos < 0){
            ypos = 700-height;
        }

        if(ypos > 700-height){
            ypos = 0;
        }

        xpos = xpos + dx;
        ypos = ypos + dy;
        rect = new Rectangle(xpos, ypos, width, height);
    }



}