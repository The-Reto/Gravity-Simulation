package Graphics;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class WhileKeyPressed extends KeyAdapter {
    private boolean isPressed;
    private int keyCode;
    private Runnable func;

    public WhileKeyPressed(int keyCode, Runnable func) {
        this.keyCode = keyCode;
        this.func = func;
        this.isPressed = false;
    }

    @Override
    public synchronized void keyPressed(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == keyCode && !isPressed) {
            isPressed = true;
        }
    }

    @Override
    public synchronized void keyReleased(KeyEvent keyEvent){
        if (keyEvent.getKeyCode() == keyCode && isPressed) {
            isPressed = false;
        }
    }

    public void apply() {
        if (isPressed) func.run();
    }
}
