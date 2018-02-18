package Mathematics.LinAlg.Vector;

import Mathematics.LinAlg.Matix.Matrix;

public class Vector extends Matrix
{
    private double x;
    private double y;
    private double z;

    public Vector(double x) {
        this(x,0,0);
    }

    public Vector(double x, double y) {
        this(x,y,0);
    }

    public Vector(double x, double y, double z) {
        super(1,3);
        this.setValue(0,0, x);
        this.setValue(0,1, y);
        this.setValue(0,2, z);
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector()
    {
        this(0,0);
    }

    public double getY() {
        return this.y;
    }

    public void setY(double y) {
        this.setValue(0,1, y);
    }

    public double getX() {
        return this.x;
    }

    public void setX(double x) {
        this.setValue(0,0, x);
    }

    public double getZ() {
        return this.z;
    }

    public void setZ(double z) {
        this.setValue(0,2, z);
    }

    public Vector add(Vector toAdd){
        return new Vector(this.getX() + toAdd.getX(), this.getY() + toAdd.getY(), this.getZ() + toAdd.getZ());
    }

    public Vector scale(double factor){
        return new Vector(this.getX() * factor, this.getY()*factor, this.getZ()*factor);
    }

    public Vector subtract(Vector toSub){
        return new Vector(this.getX() - toSub.getX(), this.getY() - toSub.getY(), this.getZ() - toSub.getZ());
    }

    public double abs(){
        return Math.sqrt(this.getX()*this.getX() + this.getY()*this.getY() + this.getZ()*this.getZ());
    }

    public Vector crossMultiplication(Vector toMulti){
        return new Vector(this.getY()*toMulti.getZ() - this.getZ()*toMulti.getY(), this.getZ()*toMulti.getX() - this.getX()*toMulti.getZ(), this.getX()*toMulti.getY() - this.getY()*toMulti.getX());
    }

    public double scalarMultiplication(Vector toMulti) {
        return this.getX()*toMulti.getX()+this.getY()*toMulti.getY()+this.getZ()*toMulti.getZ();
    }

    public double angle(Vector other) {
        return Math.acos(this.scalarMultiplication(other) / (this.abs() * other.abs()));
    }

    public Vector norm(){
        double abs = this.abs();
        return new Vector(this.getX()/abs, this.getY()/abs, this.getZ()/abs);
    }

    @Override
    public String toString() {
        return String.format("X: %.2f| Y: %.2f| Z: %.2f", this.getX(),this.getY(),this.getZ());
    }
}
