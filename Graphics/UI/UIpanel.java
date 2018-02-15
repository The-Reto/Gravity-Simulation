package Graphics.UI;

import java.awt.*;

public class UIpanel extends Panel {
    public Panel content;
    private String titleText;
    private int titleSize;
    private GridLayout contentLayout;

    UIpanel(){
        this("TITLE", 20);
    }

    UIpanel(String titleText){
        this(titleText, 20);
    }

    public UIpanel(int titleSize){
        this("TITLE", titleSize);
    }

    public UIpanel(String titleText, int titleSize){
        content = new Panel();
        contentLayout = new GridLayout();
        content.setLayout(contentLayout);
        this.titleText = titleText;
        this.titleSize = titleSize;
    }

    public void makePanel(int rows) {
        Label title = new Label(titleText, Label.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, titleSize));

        contentLayout.setColumns(1);
        contentLayout.setRows(rows);

        this.setLayout(new BorderLayout());

        this.add(title, BorderLayout.NORTH);
        this.add(content, BorderLayout.CENTER);
    }
}
