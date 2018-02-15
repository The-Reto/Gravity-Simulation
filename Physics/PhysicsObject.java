package Physics;

import Graphics.Graphics.Drawable;
import Physics.Essentials.Vector;

import java.awt.*;
import java.util.ArrayList;

public class PhysicsObject implements Drawable {
    private Vector pos;         //U
    private Vector velocity;    //M/S
    private Vector[] boundingBox  = new Vector[2];
    private boolean movable;
    public boolean deactivated;

    protected PhysicsObject(Vector pos, boolean movable) {
        this.pos = pos;
        this.velocity = new Vector(0,0);
        this.movable = movable;
        boundingBox[0] = new Vector(pos.getX(), pos.getY());
        boundingBox[1] = new Vector(pos.getX(), pos.getY());
        deactivated = false;
    }

    public void setPos(Vector pos) {
        this.pos = pos;
    }

    public Vector getPos() {
        return pos;
    }

    public Vector[] getBoundingBox() {
        return boundingBox;
    }

    public boolean isInBoundingBox(Vector test) {
        return !(pos.add(boundingBox[0]).getX() > test.getX()) && !(pos.add(boundingBox[0]).getY() > test.getY()) && !(pos.add(boundingBox[1]).getX() < test.getX()) && !(pos.add(boundingBox[1]).getY() < test.getY());
    }

    public Vector getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector velocity) {
        if(this.movable) this.velocity = velocity;
    }

    public void accelerate(Vector acc){
        if(this.movable) this.velocity = this.getVelocity().add(acc);
    }

    public void frameUpdate(double secPerTick, double meterPerUnit, double kgPerMass)
    {
        pos = this.getPos().add(this.getVelocity().scale(secPerTick/meterPerUnit));
    }

    public void frameCalculate(ArrayList<PhysicsObject> physicsObjectList, double secPerTick, double meterPerUnit, double kgPerMass) {}

    public void draw(Graphics g) {if(!deactivated) g.fillOval((int) (pos.getX()-1),(int) (pos.getY()-1),2,2);}
}