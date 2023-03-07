package main.java.com.ubo.tp.twitub.utils;

import javafx.application.Platform;
import main.java.com.ubo.tp.twitub.core.JavaFxView;
import main.java.com.ubo.tp.twitub.core.SwingView;


public class Panel {

    public static void refreshPanel(SwingView panel){
        panel.removeAll();
        panel.init();
        panel.validate();
    }
    public static void refreshPane(JavaFxView pane){
        Platform.runLater(new Runnable(){
            @Override
            public void run() {
                pane.getChildren().clear();
                pane.init();
            }
        });
    }
}
