package Graphics.Graphics;

import Mathematics.LinAlg.Vector.Plane;
import Mathematics.LinAlg.Vector.Vector;
import Physics.PhysicsWorld;

public abstract class GraphicsSettings3d {

    public static int sizeX;
    public static int sizeY;
    public static Plane viewPlane = new Plane(new Vector(0,0,0),new Vector(1,0,0),new Vector(0,1,0));


    public static int getImagePositionX(Vector worldPosition) {
        return (int) (getProjectionX(worldPosition) + PhysicsWorld.meterPerUnit * GraphicsSettings3d.sizeX/2d);
    }

    public static int getImagePositionY(Vector worldPosition) {
        return (int) (getProjectionY(worldPosition)+ PhysicsWorld.meterPerUnit * GraphicsSettings3d.sizeY/2d);
    }

    private static double getProjectionX(Vector worldPosition) {
        return worldPosition.scalarMultiplication(viewPlane.getA());
    }

    private static double getProjectionY(Vector worldPosition) {
        return worldPosition.scalarMultiplication(viewPlane.getB());
    }
}
