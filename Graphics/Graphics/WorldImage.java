package Graphics.Graphics;

import Physics.PhysicsObject;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class WorldImage {

	BufferedImage image;
	private DrawableWorld<Drawable> world;

	public WorldImage(DrawableWorld<Drawable> gravitySimWorld){
		image = new BufferedImage(gravitySimWorld.getSize_X(), gravitySimWorld.getSize_Y(), BufferedImage.TYPE_INT_ARGB);
		this.world = gravitySimWorld;
		image.getGraphics().clearRect(0,0, gravitySimWorld.getSize_X(), gravitySimWorld.getSize_Y());
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
		list.sort(new Comparator<Drawable>() {
			@Override
			public int compare(Drawable drawable, Drawable t1) {
				return (int) (((PhysicsObject) drawable).getPos().getZ() - ((PhysicsObject) t1).getPos().getZ());
			}
		});
	    return  list;
    }
}
