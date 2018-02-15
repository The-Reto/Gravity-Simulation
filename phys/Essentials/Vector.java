package phys.Essentials;

public class Vector
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
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector()
    {
        new Vector(0,0);
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public Vector add(Vector toAdd){
        return new Vector(x + toAdd.getX(), y + toAdd.getY(), z + toAdd.getZ());
    }

    public Vector scale(double factor){
        return new Vector(x * factor, y*factor, z*factor);
    }

    public Vector subtract(Vector toSub){
        return new Vector(x - toSub.getX(), y - toSub.getY(), z - toSub.getZ());
    }

    public double abs(){
        return Math.sqrt(x*x + y*y + z*z);
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
        return new Vector(this.x/abs, this.y/abs, this.z/abs);
    }

    @Override
    public String toString() {
        return String.format("X: %.2f| Y: %.2f| Z: %.2f", this.getX(),this.getY(),this.getZ());
    }
}
