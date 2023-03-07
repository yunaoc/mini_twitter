package main.java.com.ubo.tp.twitub.core;

import javax.swing.*;

public abstract class SwingView extends JPanel {
    public Controller controller;

    public SwingView(Controller controller) {
        this.controller = controller;
    }

    public void setController(Controller controller){
        this.controller = controller;
    }

    public Controller getController() {
        return controller;
    }

    public void init(){}
}
