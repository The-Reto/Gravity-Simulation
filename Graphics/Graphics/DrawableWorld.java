package Graphics.Graphics;

import Mathematics.LinAlg.Vector.Vector;

import java.awt.*;
import java.util.ArrayList;

public abstract class DrawableWorld<E extends Drawable> {

    protected int size_X, size_Y;
    protected boolean running = false;
    public boolean displayOrigin = false;

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
        if (displayOrigin) {
            double size = 50;
            g.setColor(Color.RED);
            g.drawLine(GraphicsSettings3d.getImagePositionX(new Vector(size,0,0)), GraphicsSettings3d.getImagePositionY(new Vector(size,0,0)),GraphicsSettings3d.getImagePositionX(new Vector(-size,0,0)), GraphicsSettings3d.getImagePositionY(new Vector(-size,0,0)));
            g.setColor(Color.GREEN);
            g.drawLine(GraphicsSettings3d.getImagePositionX(new Vector(0,size,0)), GraphicsSettings3d.getImagePositionY(new Vector(0,size,0)),GraphicsSettings3d.getImagePositionX(new Vector(0,-size,0)), GraphicsSettings3d.getImagePositionY(new Vector(0,-size,0)));
            g.setColor(Color.BLUE);
            g.drawLine(GraphicsSettings3d.getImagePositionX(new Vector(0,0,size)), GraphicsSettings3d.getImagePositionY(new Vector(0,0,size)),GraphicsSettings3d.getImagePositionX(new Vector(0,0,-size)), GraphicsSettings3d.getImagePositionY(new Vector(0,0,-size)));
        }
    }
}
