package graph.UI;

import java.awt.*;

class InfoBox extends Panel {
    String title, data;

    InfoBox(String title, String data) {
        this.title = title;
        this.data = data;

        this.setLayout(new GridLayout(2,1));
        this.add(new Label(title));
        this.add(new Label(data));
    }
}
