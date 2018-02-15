package Graphics.Windows;

import Graphics.UI.TextEntry;
import Graphics.UI.UIpanel;
import Physics.Essentials.Vector;
import Physics.PhysicsWorld;

import java.awt.*;

public class NewMassSphereWindow extends Window {

    private double mass;
    private double density;
    private double momentum_X, momentum_Y, momentum_Z;
    private double position_X, position_Y, position_Z;
    PhysicsWorld world;

    Panel data, buttons;

    public NewMassSphereWindow(PhysicsWorld world) {
        super(700,250);

        this.world = world;

        this.setLayout(new GridLayout(2,1));
        this.setTitle("New Object");
        this.setAlwaysOnTop(true);
        this.setLocation(500,250);
        setUpData();
        setUpButtons();
        this.add(data);
        this.add(buttons);
        this.setVisible(true);
    }

    void setUpData(){
        data = new Panel();
        data.setLayout(new GridLayout());

        data.add(new TextEntry("Mass", "10", s -> this.mass = Double.parseDouble(s)));

        data.add(new TextEntry("Density","1", s -> this.density = Double.parseDouble(s)));

        UIpanel pos = new UIpanel("Position", 16);
        pos.content.add(new TextEntry("X", "0", s -> this.position_X = Double.parseDouble(s)));
        pos.content.add(new TextEntry("Y","0", s -> this.position_Y = Double.parseDouble(s)));
        pos.content.add(new TextEntry("Z","0", s -> this.position_Z = Double.parseDouble(s)));
        pos.makePanel(3);
        data.add(pos);

        UIpanel mom = new UIpanel("Initial Momentum", 16);
        mom.content.add(new TextEntry("X","0", s -> this.momentum_X = Double.parseDouble(s)));
        mom.content.add(new TextEntry("Y","0", s -> this.momentum_Y = Double.parseDouble(s)));
        mom.content.add(new TextEntry("Z","0", s -> this.momentum_Z = Double.parseDouble(s)));

        mom.makePanel(3);
        data.add(mom);
    }

    void setUpButtons(){
        buttons = new Panel();
        buttons.setLayout(new GridLayout());
        Button cancel = new Button("Cancel");
        cancel.addActionListener(actionEvent -> dispose());
        buttons.add(cancel);
        Button add = new Button("Add");
        add.addActionListener(actionEvent -> {
            world.addObject(mass, new Vector(momentum_X,momentum_Y,momentum_Z), new Vector(position_X,position_Y,position_Z), (density > 0) ? density:1);
            dispose();
        });
        buttons.add(add);
    }
}
