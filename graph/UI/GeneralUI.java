package graph.UI;

import graph.Graphics.WorldView;

import java.awt.*;


public class GeneralUI extends UIpanel {
    private WorldView world;

    private UIpanel animationPanel, eventPanel;

    public GeneralUI(WorldView world) {
        super("General");
        this.world = world;

        setUpGraphics();
        setUpEvent();

        content.add(animationPanel);
        content.add(eventPanel);

        makePanel(3);
    }

    private void setUpGraphics() {
        animationPanel = new UIpanel("Graphics", 16);

        Checkbox updateAnimation = new Checkbox("Update Animation", true);
        updateAnimation.addItemListener(itemEvent -> world.toggleAnimation());

        Checkbox coordinateGrid = new Checkbox("Display Grid", false);
        TextEntry frameRate = new TextEntry("Frame Rate",Integer.toString(world.getFrameRate()), s -> world.setFrameRate(Integer.parseInt(s)));
        coordinateGrid.addItemListener(itemEvent -> world.toggleCoordinateGrid());
        InfoBox scaleInfo = new InfoBox("Info:", "One Grid square has a sidelength of 50 units");

        animationPanel.content.add(updateAnimation);
        animationPanel.content.add(frameRate);
        animationPanel.content.add(coordinateGrid);
        animationPanel.content.add(scaleInfo);

        animationPanel.makePanel(6);
    }

    private void setUpEvent() {
        eventPanel = new Feed("Events");
    }
}
