package Graphics.UI;

import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.function.Consumer;

public class TextEntry extends Panel {
    private String title;
    private TextField textField;

    public TextEntry(String title, String text, Consumer<String> func) {
        this.title = title;
        this.textField = new TextField(text);
        textField.addActionListener(actionEvent -> acceptValue(func));
        textField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent focusEvent) {
                super.focusLost(focusEvent);
                acceptValue(func);
            }
        });
        this.setLayout(new GridLayout(1,2));
        this.add(new Label(title, Label.CENTER));
        this.add(textField);
    }

    public TextEntry(String title, Consumer<String> func) {
        this(title, "", func);
    }

    private void acceptValue(Consumer<String> func){
        try {
            func.accept(textField.getText());
            textField.setText(textField.getText());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
