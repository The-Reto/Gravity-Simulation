package graph.Graphics;

import java.awt.*;

public interface Drawable {
    default void draw(Graphics g) {
        g.drawString("Implement drawing", 50,50);
    }
}
