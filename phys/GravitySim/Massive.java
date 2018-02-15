package phys.GravitySim;

import phys.Essentials.Vector;

public interface Massive {
    double getMass();
    void setMass(double mass);

    Vector getMomentum();
    void setMomentum(Vector momentum);

    void addForce(Vector force);
}
