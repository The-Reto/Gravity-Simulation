package graph.UI;

import java.awt.*;
import java.util.ArrayList;

public class UIview extends Panel {

    public UIview(ArrayList<UIpanel> panels, int size_x, int size_y){
        GridLayout layout = new GridLayout(1,panels.size());
        layout.setHgap(100);
        this.setLayout(layout);
        for(UIpanel p: panels){
            this.add(p);
        }
        this.setSize(size_x,size_y);
        repaint();
    }
}
