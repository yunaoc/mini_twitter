package main.java.com.ubo.tp.twitub.core;

public class SwingComposant {

    private Controller controller;
    private SwingView swingView;

    public SwingComposant(Controller controller, SwingView swingView) {
        this.controller = controller;
        this.swingView = swingView;
        this.swingView.setController(controller);
    }

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public SwingView getView() {
        return swingView;
    }

    public void setView(SwingView swingView) {
        this.swingView = swingView;
    }
}
