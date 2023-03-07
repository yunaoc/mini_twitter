package main.java.com.ubo.tp.twitub.core;

import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;

public class JavaFxView extends GridPane {
        protected Controller controller;

        public JavaFxView(Controller controller) {
            this.controller = controller;
            setAlignment(Pos.CENTER);
        }

        public void setController(Controller controller){
            this.controller = controller;
        }

        public Controller getController() {
            return controller;
        }

        public void init(){}
}
