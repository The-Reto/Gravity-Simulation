package Mathematics.Vector;

public class Plane {
    private Vector basePosition;
    private Vector A, B;

    public Plane(Vector basePosition, Vector A, Vector B){
        this.basePosition = basePosition;
        this.A = A.norm();
        this.B = B.norm();
    }

    public Vector getA() {
        return A;
    }

    public Vector getB() {
        return B;
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
}