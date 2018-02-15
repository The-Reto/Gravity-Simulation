package Physics;

import Graphics.Graphics.AlwaysDraw;
import Graphics.Graphics.Drawable;
import Mathematics.Vector.Vector;

import java.awt.*;

public class DataDisplay<t> extends PhysicsObject implements Drawable, AlwaysDraw {
    public String title;
    public t data;

    DataDisplay(String title, Vector pos, t data){
        super(new Vector(pos.getX(), pos.getY(), Double.POSITIVE_INFINITY), false);
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
