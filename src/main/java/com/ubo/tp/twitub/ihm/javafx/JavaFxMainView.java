package main.java.com.ubo.tp.twitub.ihm.javafx;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import main.java.com.ubo.tp.twitub.core.JavaFxView;
import main.java.com.ubo.tp.twitub.model.IModelObserver;
import main.java.com.ubo.tp.twitub.model.Session;

import java.awt.*;
import java.io.File;

public class JavaFxMainView implements IModelObserver {

    /**
     * Fenetre principale
     */
    protected Stage mStage;
    /**
     * Contenu de la fenêtre
     */
    protected BorderPane root;
    protected Scene mScene;

    protected Pane menu;


    public JavaFxMainView(Stage stage) {
        this.mStage = stage;
    }

    public void abonnerSession(Session session) {
        session.addObservers(this);
    }

    public void initGUI(JavaFxView menu) {
        // Création de la fenetre principale
        this.menu = menu;
        mStage.setTitle("Mini Twitter");
        BorderPane mainRoot = new BorderPane();
        root = new BorderPane();
        mainRoot.setTop(menu);
        BorderPane.setAlignment(menu, Pos.CENTER);
        mainRoot.setCenter(root);
        BorderPane.setAlignment(root, Pos.CENTER);
        this.menu.setVisible(false);
        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        mScene = new Scene(mainRoot, screenSize.width/3.0, screenSize.height/2.0);
        root.setPrefSize(mScene.getWidth(),mScene.getHeight());
        mStage.setScene(mScene);
        mStage.show();
    }

    public String setExchangeRepository() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setInitialDirectory(new File("echanges"));
        directoryChooser.setTitle("Choix du repertoire d'échange");
        File selectedFile = directoryChooser.showDialog(mStage);

        if( selectedFile.exists() && selectedFile.isDirectory() && selectedFile.canWrite()){
            return selectedFile.getAbsolutePath();
        }else{
            System.out.println("Erreur, le dossier sélectionné n'est pas valide, veuillez réessayer.");
            setExchangeRepository();
        }
        return null;
    }

    public void goToView(JavaFxView view) {
        root.setTop(view);
        BorderPane.setAlignment(view, Pos.CENTER);
    }

    @Override
    public void notifyUserConnected() {
        this.menu.setVisible(true);
    }

    @Override
    public void notifyDisconnect() {
        this.menu.setVisible(false);
    }
}
