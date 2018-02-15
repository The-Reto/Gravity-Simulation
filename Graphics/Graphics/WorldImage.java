package Graphics.Graphics;

import Physics.PhysicsObject;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Comparator;

public class WorldImage {

	Comparator<Drawable> axisX = (d1, d2) -> (int) (((PhysicsObject) d1).getPos().getX() - ((PhysicsObject) d2).getPos().getX());
	Comparator<Drawable> axisY = (d1, d2) -> (int) (((PhysicsObject) d1).getPos().getY() - ((PhysicsObject) d2).getPos().getY());
	Comparator<Drawable> axisZ = (d1, d2) -> (int) (((PhysicsObject) d1).getPos().getZ() - ((PhysicsObject) d2).getPos().getZ());

	BufferedImage image;
	private DrawableWorld<Drawable> world;

	public WorldImage(DrawableWorld<Drawable> gravitySimWorld){
		image = new BufferedImage(gravitySimWorld.getSize_X(), gravitySimWorld.getSize_Y(), BufferedImage.TYPE_INT_ARGB);
		this.world = gravitySimWorld;
		image.getGraphics().clearRect(0,0, gravitySimWorld.getSize_X(), gravitySimWorld.getSize_Y());
		GraphicsSettings3d.sizeX = image.getHeight();
		GraphicsSettings3d.sizeY = image.getWidth();
	}

	public void toggleCoordinateGrid() {world.coordinateGrid = !world.coordinateGrid;}

	public void drawDisabled(){
		image.getGraphics().clearRect(0,0, world.getSize_X(), world.getSize_Y());
		image.getGraphics().drawString("DISPLAY DISABLED", image.getWidth()/2-100, image.getHeight()/2);
	}

	public void drawImage(){
		image.getGraphics().clearRect(0,0, world.getSize_X(), world.getSize_Y());
		world.draw(image.getGraphics());
		for(Drawable d: sortObjects()){
		    if(!(d instanceof AlwaysDraw)) d.draw(image.getGraphics());
        }
		for(Drawable d: sortObjects()){
			if(d instanceof AlwaysDraw) d.draw(image.getGraphics());
		}
	}

    private ArrayList<Drawable> sortObjects() {
		ArrayList<Drawable> list = new ArrayList<>();
		list = (ArrayList<Drawable>) world.objects.clone();
		switch (GraphicsSettings3d.myAxis) {
			case X: list.sort(axisX);
			case Y: list.sort(axisY);
			case Z: list.sort(axisZ);
		}
	    return  list;
    }
}
