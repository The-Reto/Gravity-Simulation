package Mathematics.LinAlg.Vector;

import Mathematics.LinAlg.Matix.Matrix;

public class Plane {
    private Vector basePosition;
    private Vector A, B;

    public Plane(Vector basePosition, Vector A, Vector B){
        this.basePosition = basePosition;
        this.A = A.norm();
        this.B = B.norm();
    }

    public Vector getA() {
        return A.norm();
    }

    public Vector getB() {
        return B.norm();
    }

    public Vector getBasePosition() {
        return basePosition;
    }

    public void setA(Vector a) {
        A = a;
    }

    public void setB(Vector b) {
        B = b;
    }

    public void setBasePosition(Vector basePosition) {
        this.basePosition = basePosition;
    }

    public Vector normal() {
        return this.A.crossMultiplication(this.B);
    }

    public double distance(Vector point) {
        Vector norm = this.normal();
        double a = norm.getX()*basePosition.getX() + norm.getY()*basePosition.getY() + norm.getZ()*basePosition.getZ();
        return (norm.getX()*point.getX() + norm.getY()*point.getY() + norm.getZ()*point.getZ() - a)/norm.abs();
    }

    public enum Axis{
        X,Y,Z
    }

    public void rotateLocal(Axis rot, double degrees) {
        Matrix rotationMatrix;
        switch (rot) {
            case X: rotationMatrix = new Matrix(new double[] {1d,0d,0d}, new double[] {0d,Math.cos(degrees),-Math.sin(degrees)}, new double[] {0d,Math.sin(degrees),Math.cos(degrees)}); break;
            case Y: rotationMatrix = new Matrix(new double[] {Math.cos(degrees),0d,Math.sin(degrees)}, new double[] {0d,1d,0d}, new double[] {-Math.sin(degrees),0d,Math.cos(degrees)}); break;
            case Z: rotationMatrix = new Matrix(new double[] {Math.cos(degrees),-Math.sin(degrees),0d}, new double[] {Math.sin(degrees),Math.cos(degrees),0d}, new double[] {0d,0d,1d}); break;
            default: rotationMatrix = new Matrix(new double[] {1d,0d,0d}, new double[] {0d,1d,0d}, new double[] {0d,0d,1d}); break;
        }
        Matrix replaceA = this.getA().multiply(rotationMatrix);
        Matrix replaceB = this.getB().multiply(rotationMatrix);
        A = new Vector(replaceA.getValue(0,0), replaceA.getValue(0,1),replaceA.getValue(0,2));
        B = new Vector(replaceB.getValue(0,0), replaceB.getValue(0,1),replaceB.getValue(0,2));
    }

    public void rotateGlobal(Axis rot, double degrees) {
        Vector axis = (rot == Axis.X) ? this.getA() : (rot == Axis.Y) ? this.getB() : this.normal().norm();
        Matrix rotationMatrix = new Matrix( new double[]{axis.getX()*axis.getX()*(1 - Math.cos(degrees)) + Math.cos(degrees), axis.getX()*axis.getY()*(1 - Math.cos(degrees)) - axis.getZ()*Math.sin(degrees), axis.getX()*axis.getZ()*(1 - Math.cos(degrees)) +  axis.getY()*Math.sin(degrees)},
                                            new double[]{axis.getY()*axis.getX()*(1 - Math.cos(degrees)) + axis.getZ()*Math.sin(degrees), axis.getY()*axis.getY()*(1 - Math.cos(degrees)) + Math.cos(degrees), axis.getY()*axis.getZ()*(1 - Math.cos(degrees)) - axis.getX()*Math.sin(degrees)},
                                            new double[]{axis.getZ()*axis.getX()*(1 - Math.cos(degrees)) - axis.getY()*Math.sin(degrees), axis.getZ()*axis.getY()*(1 - Math.cos(degrees)) + axis.getX()*Math.sin(degrees), axis.getZ()*axis.getZ()*(1 - Math.cos(degrees)) + Math.cos(degrees)}
                                            );
        Matrix replaceA = this.getA().multiply(rotationMatrix);
        Matrix replaceB = this.getB().multiply(rotationMatrix);
        A = new Vector(replaceA.getValue(0,0), replaceA.getValue(0,1),replaceA.getValue(0,2));
        B = new Vector(replaceB.getValue(0,0), replaceB.getValue(0,1),replaceB.getValue(0,2));
    }
}