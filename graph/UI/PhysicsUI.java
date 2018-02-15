package graph.UI;

import graph.Windows.NewMassSphereWindow;
import phys.Essentials.PhysicsConstant;
import phys.PhysicsWorld;

import java.awt.*;

public class PhysicsUI extends UIpanel {

    PhysicsWorld world;

    UIpanel worldPanel, constantsPanel, actionsPanel;

    public PhysicsUI(PhysicsWorld world) {
        super("Physics");
        this.world = world;

        setUpWorldSettings();
        setUpConstantsSettings();
        setUpActionsSettings();

        this.content.add(worldPanel);
        this.content.add(constantsPanel);
        this.content.add(actionsPanel);

        this.makePanel(3);
    }

    private void setUpWorldSettings(){
        worldPanel = new UIpanel("World Settings", 16);
        worldPanel.content.add(new TextEntry("Sec per Tick", Double.toString(PhysicsWorld.secPerTick), s -> PhysicsWorld.secPerTick = Double.parseDouble(s)));
        worldPanel.content.add(new TextEntry("Meter per Unit", Double.toString(PhysicsWorld.meterPerUnit), s -> PhysicsWorld.meterPerUnit = Double.parseDouble(s)));
        worldPanel.content.add(new TextEntry("Kg per Mass", Double.toString(PhysicsWorld.kgPerMass), s -> PhysicsWorld.kgPerMass = Double.parseDouble(s)));
        Checkbox c1 = new Checkbox("Realtime", false);
        c1.addItemListener(itemEvent -> world.toggleRealTime());
        Checkbox c2 = new Checkbox("Animate", false);
        c2.addItemListener(itemEvent -> world.toggleAnimation());
        worldPanel.content.add(c1);
        worldPanel.content.add(c2);
        worldPanel.makePanel(6);
    }

    private void setUpConstantsSettings(){
        constantsPanel = new UIpanel("Physics Constants", 16);
        constantsPanel.content.add(new TextEntry("Gravitational Constant", Double.toString(PhysicsConstant.G), d -> PhysicsConstant.G = Double.parseDouble(d)));
        constantsPanel.makePanel(6);
    }

    private void setUpActionsSettings(){
        actionsPanel = new UIpanel("Actions", 16);
        Button newObject = new Button("new Object");
        newObject.addActionListener(actionEvent -> {
            world.stopAnimation();
            NewMassSphereWindow w = new NewMassSphereWindow(world);
        });
        actionsPanel.content.add(newObject);
        Button deleteObjects = new Button("delete all Objects");
        deleteObjects.addActionListener(actionEvent -> world.deleteAllObjects());
        actionsPanel.content.add(deleteObjects);
        Button resetTime = new Button("RESET time");
        resetTime.addActionListener(actionEvent -> world.resetTime());
        actionsPanel.content.add(resetTime);
        Button reset = new Button("RESET All");
        reset.addActionListener(actionEvent -> world.reset());
        actionsPanel.content.add(reset);
        actionsPanel.makePanel(5);
    }
}
