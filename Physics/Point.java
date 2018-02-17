package Physics;

import Graphics.Graphics.AlwaysDraw;
import Graphics.Graphics.Drawable;
import Mathematics.LinAlg.Vector.Vector;

import java.awt.*;
import java.util.ArrayList;

public abstract class Point extends PhysicsObject implements Drawable, AlwaysDraw{

    private Color myColor;
    private int size;

    Point(Color col){
        super(new Vector(0,0), true);
        myColor = col;
        size = 3;
    }

    public void frameCalculate(ArrayList<PhysicsObject> physicsObjectList, double secPerTick, double meterPerUnit, double kgPerMass) {
        setPos(physicsObjectList);
    }

    abstract void setPos(ArrayList<PhysicsObject> physicsObjectList);

    public void draw(Graphics g) {
        if(!deactivated){
            g.setColor(myColor);
            g.drawLine((int) (this.getPos().getX()/PhysicsWorld.meterPerUnit) - size, (int) (this.getPos().getY()/PhysicsWorld.meterPerUnit), (int) (this.getPos().getX()/PhysicsWorld.meterPerUnit) + size, (int) (this.getPos().getY()/PhysicsWorld.meterPerUnit));
            g.drawLine((int) (this.getPos().getX()/PhysicsWorld.meterPerUnit), (int) (this.getPos().getY()/PhysicsWorld.meterPerUnit) - size, (int) (this.getPos().getX()/PhysicsWorld.meterPerUnit), (int) (this.getPos().getY()/PhysicsWorld.meterPerUnit) + size);
        }
    }
}
