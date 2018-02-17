package Physics.GravitySim;

import Physics.Essentials.PhysicsConstant;
import Physics.Essentials.PhysicsEvent;
import Physics.PhysicsObject;
import Mathematics.LinAlg.Vector.Vector;
import Physics.PhysicsWorld;

import java.awt.*;
import java.util.ArrayList;

public class MassSphere extends PhysicsObject implements Massive {
    private double mass;
    private double radius;
    private double density;
    private Vector momentum;

    public MassSphere(double mass, Vector momentum, Vector pos, double density) {
        super(pos, true);
        this.density = density;
        this.mass = mass;
        this.momentum = momentum;
        this.setRadius();
    }

    public void addForce(Vector force) {
        momentum = this.getMomentum().add(force);
    }

    public Vector getMomentum() {
        return momentum;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public void setMomentum(Vector momentum) {
        this.momentum = momentum;
    }

    @Override
    public void frameCalculate(ArrayList<PhysicsObject> physicsObjectList, double secPerTick, double meterPerUnit, double kgPerMass) {
        for (PhysicsObject b : physicsObjectList) {
            if (!b.deactivated) {
                if (b instanceof Massive && b != this) {
                    if (b.getPos().subtract(this.getPos()).abs() > this.getRadius() + ((MassSphere) b).getRadius()) {
                        Vector force = b.getPos().subtract(this.getPos()).norm();
                        double factor = (secPerTick * secPerTick) * (kgPerMass * kgPerMass) * PhysicsConstant.G * this.getMass() * ((Massive) b).getMass() / Math.pow(meterPerUnit * b.getPos().subtract(this.getPos()).abs(), 2);
                        force = force.scale(factor);
                        this.addForce(force);
                    } else {
                        double totalMass = this.getMass() + ((Massive) b).getMass();
                        this.setPos(this.getPos().scale(this.getMass()).add(b.getPos().scale(((MassSphere) b).getMass())).scale(1d / totalMass));
                        new PhysicsEvent(PhysicsEvent.EventType.COLLISION, String.format("at: %s", this.getPos().toString()), PhysicsWorld.timeElapsed);
                        this.setMass(totalMass);
                        this.setMomentum(this.getMomentum().add(((Massive) b).getMomentum()));
                        b.deactivated = true;
                        this.setRadius();
                    }
                }
            }
        }
        this.setVelocity(momentum.scale(1d / (mass * kgPerMass)));
        super.frameCalculate(physicsObjectList, secPerTick, meterPerUnit, kgPerMass);
    }

    public double getRadius() {
        return radius;
    }

    private void setRadius() {
        this.radius = Math.pow(3d * this.mass / (4d * this.density * Math.PI), 1d / 3d);
    }

    public double getDensity() {
        return density;
    }

    public void setDensity(double density) {
        this.density = density;
    }

    protected void drawingOperation(Graphics g, int x, int y){
        g.setColor(Color.BLACK);
        g.fillOval((int) ((x - radius) / PhysicsWorld.meterPerUnit), (int) ((y - radius) / PhysicsWorld.meterPerUnit), (int) (2 * radius / PhysicsWorld.meterPerUnit), (int) (2 * radius / PhysicsWorld.meterPerUnit));
        g.setColor(Color.WHITE);
        g.drawOval((int) ((x - radius) / PhysicsWorld.meterPerUnit), (int) ((y - radius) / PhysicsWorld.meterPerUnit), (int) (2 * radius / PhysicsWorld.meterPerUnit), (int) (2 * radius / PhysicsWorld.meterPerUnit));
    }
}