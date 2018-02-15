package Physics;

import Graphics.Graphics.DrawableWorld;
import Physics.Essentials.PhysicsEvent;
import Physics.Essentials.Time;
import Mathematics.Vector.Vector;
import Physics.GravitySim.MassSphere;
import Physics.GravitySim.Massive;

import java.awt.*;
import java.util.ArrayList;

public class PhysicsWorld extends DrawableWorld<PhysicsObject> {

    public static Time timeElapsed;
    private static Point massCenter;
    private static DataDisplay<Time> timeDataDisplay;
    private static DataDisplay<Double> massDataDisplay;
    private static DataDisplay<Vector> momentumDataDisplay;
    private static DataDisplay<Vector> massCenterDataDisplay;


    public static double secPerTick;
    public static double meterPerUnit;
    public static double kgPerMass;
    private boolean realTime = false;

    public void toggleRealTime() {realTime = !realTime;}

    public PhysicsWorld(int size_x, int size_y){
        this(size_x,size_y,new ArrayList<>());
    }

    public PhysicsWorld(int size_x, int size_y, ArrayList<PhysicsObject> objects) {
        size_X = size_x;
        size_Y = size_y;
        this.objects = objects;
        this.setSize(size_X, size_Y);

        massCenter = new Point(Color.red) {
            @Override
            void setPos(ArrayList<PhysicsObject> physicsObjectList) {
                double totalMass = 0;
                Vector newPos = new Vector(0,0);
                for(PhysicsObject b : physicsObjectList){
                    if (!b.deactivated && b instanceof Massive){
                        totalMass += ((Massive) b).getMass();
                        newPos = newPos.add(b.getPos().scale(((Massive) b).getMass()));
                    }
                }
                this.setPos(newPos.scale(1d/totalMass));
            }
        };

        timeDataDisplay = new DataDisplay<>("Time elapsed", new Vector(0, 15), timeElapsed);
        massDataDisplay = new DataDisplay<>("Total Mass", new Vector(0,30), 0d);
        momentumDataDisplay = new DataDisplay<>("Total Momentum", new Vector(0,45), new Vector(0,0,0));
        massCenterDataDisplay = new DataDisplay<>("Center of Mass", new Vector(0,60), massCenter.getPos());

        reset();
        new PhysicsEvent(PhysicsEvent.EventType.READY, "Physics World is now ready!", timeElapsed);
    }

    public void addObject(double mass, Vector momentum, Vector pos , double density){
        objects.add(new MassSphere(mass, momentum, pos, density));
        massDataDisplay.data += mass;
        momentumDataDisplay.data = momentumDataDisplay.data.add(momentum);
        new PhysicsEvent(PhysicsEvent.EventType.NEW_OBJECT, String.format("Mass: %f", mass),timeElapsed);
    }

    public void deleteAllObjects(){
        massDataDisplay.data = 0d;
        momentumDataDisplay.data = new Vector(0,0,0);
        objects = new ArrayList<>();
        objects.add(timeDataDisplay);
        objects.add(massDataDisplay);
        objects.add(momentumDataDisplay);
        objects.add(massCenterDataDisplay);
        objects.add(massCenter);
        new PhysicsEvent(PhysicsEvent.EventType.RESET, "All objects deleted!", timeElapsed);
    }

    public void resetTime(){
        PhysicsWorld.timeElapsed = new Time(0);
        timeDataDisplay.data = PhysicsWorld.timeElapsed;
        new PhysicsEvent(PhysicsEvent.EventType.RESET, "The time has been reset!", timeElapsed);
    }

    public void reset(){
        PhysicsWorld.secPerTick = 1;
        PhysicsWorld.meterPerUnit = 1;
        PhysicsWorld.kgPerMass = 1;
        resetTime();
        deleteAllObjects();
        new PhysicsEvent(PhysicsEvent.EventType.RESET, "The world has been reset!", timeElapsed);
    }

    protected void animate() {
        synchronized (this) {
            while (running) {
                if (!realTime) {
                    for (PhysicsObject b : objects) {
                        if (!b.deactivated) b.frameCalculate(objects, secPerTick, meterPerUnit, kgPerMass);
                    }
                    for (PhysicsObject b : objects) {
                        if (! b.deactivated)  b.frameUpdate(secPerTick, meterPerUnit, kgPerMass);
                    }
                    timeElapsed.addSeconds(secPerTick);
                }
                else {
                    try {
                        for (PhysicsObject b : objects) {
                            if (!b.deactivated) b.frameCalculate(objects, secPerTick, meterPerUnit, kgPerMass);
                        }
                        for (PhysicsObject b : objects) {
                            if (! b.deactivated)  b.frameUpdate(secPerTick, meterPerUnit, kgPerMass);
                        }
                        wait((long) (secPerTick*1000));
                        timeElapsed.addSeconds(secPerTick);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                massCenterDataDisplay.data = massCenter.getPos();
            }
        }
    }
}