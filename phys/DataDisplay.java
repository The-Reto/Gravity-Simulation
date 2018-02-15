package phys;

import graph.Graphics.Drawable;
import phys.Essentials.Vector;

import java.awt.*;

public class DataDisplay<t> extends PhysicsObject implements Drawable {
    public String title;
    public t data;

    DataDisplay(String title, Vector pos, t data){
        super(pos, false);
        this.title = title;
        this.data = data;
    }

    public void draw(Graphics g){
        if(!deactivated) g.drawString(title + ": " + data.toString(), (int) this.getPos().getX(), (int) this.getPos().getY());
    }

    @Override
    public String toString() {
        return data.toString();
    }
}
