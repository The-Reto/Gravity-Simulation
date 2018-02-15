package graph.Graphics;

import java.awt.*;
import java.util.ArrayList;

public abstract class DrawableWorld<E extends Drawable> {

    protected int size_X, size_Y;
    protected boolean running = false;
    public boolean coordinateGrid = false;

    public ArrayList<E> objects;

    protected abstract void animate();

    private void startAnimation() {
        running = true;
        new Thread(this::animate).start();
    }

    public void stopAnimation() {running = false;}

    public void toggleAnimation() {
        if (running) stopAnimation();
        else startAnimation();
    }

    public int getSize_X() {
        return size_X;
    }

    public void setSize_X(int size_X) {
        this.size_X = size_X;
    }

    public int getSize_Y() {
        return size_Y;
    }

    public void setSize_Y(int size_Y) {
        this.size_Y = size_Y;
    }

    public void setSize(int size_X, int size_Y){
        this.setSize_X(size_X);
        this.setSize_Y(size_Y);
    }

    public void draw(Graphics g) {
        if (coordinateGrid) {
            g.setColor(Color.GRAY);
            for (int x = 0; x <= this.getSize_X(); x += 50) {
                g.drawLine(x,0,x,this.getSize_Y());
            }
            for (int y = 0; y <= this.getSize_Y(); y += 50) {
                g.drawLine(0,y,this.getSize_X(),y);
            }
        }
    }
}
