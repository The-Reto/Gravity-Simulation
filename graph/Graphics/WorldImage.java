package graph.Graphics;

import java.awt.image.BufferedImage;

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
		for(Drawable b: world.objects) {
			b.draw(image.getGraphics());
		}
	}
}
