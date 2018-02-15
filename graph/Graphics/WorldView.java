package graph.Graphics;

import phys.Essentials.PhysicsEvent;
import phys.PhysicsWorld;

import java.awt.*;

public class WorldView extends Panel {
    private WorldImage worldImage;
    private int size_X;
    private int size_Y;
    private int frameRate;
    private boolean animate;

    public void setSize_X(int x) {size_X = x;}

    public int getSize_X(){return size_X;}

    public int getSize_Y() {
        return size_Y;
    }

    public void setSize_Y(int size_Y) {
        this.size_Y = size_Y;
    }

    public WorldView(DrawableWorld world ){
        worldImage = new WorldImage(world);
        size_X = world.getSize_X();
        size_Y = world.getSize_Y();
        this.setSize(size_X, size_Y);
        frameRate = 25;
        new PhysicsEvent(PhysicsEvent.EventType.READY, "Physics World Display now ready!", PhysicsWorld.timeElapsed);
        animate = false;
    }

    public void toggleCoordinateGrid() {worldImage.toggleCoordinateGrid();}

    public void setFrameRate(int frameRate) {
        this.frameRate = frameRate;
    }

    public int getFrameRate() {
        return this.frameRate;
    }

    public void startAnimation() {
        animate = true;
        new Thread(this::animate).start();
    }

    public void stopAnimation() {
        animate = false;
        worldImage.drawDisabled();
        this.getGraphics().drawImage(worldImage.image,0,0, null);
    }

    public void toggleAnimation() {
        if (animate) stopAnimation();
        else startAnimation();
    }

    private void animate(){
        synchronized (this) {
            try {
                while (animate) {
                    worldImage.drawImage();
                    wait(1000/frameRate);
                    this.getGraphics().drawImage(worldImage.image,0,0, null);
                }
            } catch (InterruptedException ignored) {}
        }
    }
}
