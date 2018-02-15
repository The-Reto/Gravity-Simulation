import Graphics.Windows.MainWindow;
import Physics.*;

public class Main {

    public static void main(String[] args){
        PhysicsWorld gravitySimWorld =  new PhysicsWorld(Data.size, Data.size);

        new MainWindow("GravitySim", gravitySimWorld);
    }
}