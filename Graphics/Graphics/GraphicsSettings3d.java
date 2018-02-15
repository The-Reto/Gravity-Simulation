package Graphics.Graphics;

import Mathematics.Vector.Vector;
import Physics.PhysicsObject;
import Physics.PhysicsWorld;

public abstract class GraphicsSettings3d {

    public static int sizeX;
    public static int sizeY;
    public static CoordinateAxis myAxis = CoordinateAxis.Z;
    public static Orientation myOrientation = Orientation.FRONT;

    public enum CoordinateAxis{
        X,Y,Z
    }

    public enum Orientation{
        FRONT, BACK
    }

    public static int getImagePositionX(Vector worldPosition) {
        int toReturn;
        switch (GraphicsSettings3d.myAxis){
            case X: toReturn = (int) (-worldPosition.getY() + PhysicsWorld.meterPerUnit * GraphicsSettings3d.sizeX/2); break;
            case Y: toReturn = (int) (worldPosition.getX() + PhysicsWorld.meterPerUnit * GraphicsSettings3d.sizeX/2); break;
            case Z: toReturn = (int) (worldPosition.getX() + PhysicsWorld.meterPerUnit * GraphicsSettings3d.sizeX/2); break;
            default: toReturn = 0;
        }
        return toReturn;
    }

    public static int getImagePositionY(Vector worldPosition) {
        int toReturn;
        switch (GraphicsSettings3d.myAxis){
            case X: toReturn = (int) (worldPosition.getZ() + PhysicsWorld.meterPerUnit * GraphicsSettings3d.sizeY/2); break;
            case Y: toReturn = (int) (-worldPosition.getZ() + PhysicsWorld.meterPerUnit * GraphicsSettings3d.sizeY/2); break;
            case Z: toReturn = (int) (worldPosition.getY() + PhysicsWorld.meterPerUnit * GraphicsSettings3d.sizeY/2); break;
            default: toReturn = 0;
        }
        return toReturn;
    }
}
