package main.java.com.ubo.tp.twitub.core;

public class JavaFxComposant {

    private Controller controller;
    private JavaFxView javaFxView;

    public JavaFxComposant(Controller controller, JavaFxView javaFxView) {
        this.controller = controller;
        this.javaFxView = javaFxView;
        this.javaFxView.setController(controller);
    }

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public JavaFxView getView() {
        return javaFxView;
    }

    public void setView(JavaFxView javaFxView) {
        this.javaFxView = javaFxView;
    }
}
