package Graphics.UI;

import Physics.Essentials.PhysicsEvent;

import java.awt.*;

public class Feed extends UIpanel {

    static public TextArea feedDisplay = new TextArea(3,10);

    public Feed(String title) {
        super(title, 16);
        content.add(feedDisplay);
        feedDisplay.setEditable(false);
        makePanel(1);
    }

    public static void insert(String s) {
        feedDisplay.selectAll();
        feedDisplay.insert(s, feedDisplay.getSelectionEnd());
        feedDisplay.selectAll();
        feedDisplay.insert("\n", feedDisplay.getSelectionEnd());
    }

    public static void insert(PhysicsEvent e) {
        Feed.insert(e.toString());
    }
}
