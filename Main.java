import graph.Windows.MainWindow;
import phys.*;

public class Main {

    public static void main(String[] args){
        PhysicsWorld gravitySimWorld =  new PhysicsWorld(Data.size, Data.size);

        new MainWindow("GravitySim", gravitySimWorld);
    }
}