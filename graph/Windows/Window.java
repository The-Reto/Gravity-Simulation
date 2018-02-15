package graph.Windows;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Window extends Frame {

    protected int size_Y;
    protected int size_X;
    protected boolean main = false;

    public int getSize_Y() {
        return size_Y;
    }

    public void setSize_Y(int size_Y) {
        this.size_Y = size_Y;
    }

    public int getSize_X() {
        return size_X;
    }

    public void setSize_X(int size_X) {
        this.size_X = size_X;
    }

    public Window(int size_X, int size_Y) {
        this.size_X = size_X;
        this.size_Y = size_Y;

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                e.getWindow().dispose();
                if(main) {
                    System.exit(0);
                }
            }
        });

        this.setSize(this.size_X, size_Y);
    }
}
