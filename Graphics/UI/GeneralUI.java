package Graphics.UI;

import Graphics.Graphics.GraphicsSettings3d;
import Graphics.Graphics.WorldView;

import java.awt.*;


public class GeneralUI extends UIpanel {
    private WorldView world;

    private UIpanel animationPanel, eventPanel, axisPanel;

    public GeneralUI(WorldView world) {
        super("General");
        this.world = world;

        setUpGraphics();
        setUpEvent();
        setUpaxis();

        content.add(animationPanel);
        content.add(eventPanel);
        content.add(axisPanel);

        makePanel(3);
    }

    private void setUpaxis(){
        axisPanel = new UIpanel("Axis", 16);
        CheckboxGroup chooseAxis = new CheckboxGroup();
        Checkbox axisX = new Checkbox("X", chooseAxis, false);
        Checkbox axisY = new Checkbox("Y", chooseAxis, false);
        Checkbox axisZ = new Checkbox("Z", chooseAxis, true);
        axisX.addItemListener(itemEvent -> GraphicsSettings3d.myAxis = GraphicsSettings3d.CoordinateAxis.X);
        axisY.addItemListener(itemEvent -> GraphicsSettings3d.myAxis = GraphicsSettings3d.CoordinateAxis.Y);
        axisZ.addItemListener(itemEvent -> GraphicsSettings3d.myAxis = GraphicsSettings3d.CoordinateAxis.Z);

        axisPanel.content.add(axisX);
        axisPanel.content.add(axisY);
        axisPanel.content.add(axisZ);

        CheckboxGroup chooseOrinetation = new CheckboxGroup();
        Checkbox orientationFront = new Checkbox("Front", chooseOrinetation, true);
        Checkbox orientationBack = new Checkbox("Back", chooseOrinetation, false);
        orientationFront.addItemListener(itemEvent -> GraphicsSettings3d.myOrientation = GraphicsSettings3d.Orientation.FRONT);
        orientationBack.addItemListener(itemEvent -> GraphicsSettings3d.myOrientation = GraphicsSettings3d.Orientation.BACK);
        axisPanel.content.add(orientationFront);
        axisPanel.content.add(orientationBack);

        axisPanel.makePanel(6);
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
