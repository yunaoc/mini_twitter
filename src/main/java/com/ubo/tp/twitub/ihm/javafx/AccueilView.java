package main.java.com.ubo.tp.twitub.ihm.javafx;

import javafx.animation.RotateTransition;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import main.java.com.ubo.tp.twitub.controllers.AccueilController;
import main.java.com.ubo.tp.twitub.core.Controller;
import main.java.com.ubo.tp.twitub.core.JavaFxView;
import main.java.com.ubo.tp.twitub.datamodel.IDatabaseObserver;
import main.java.com.ubo.tp.twitub.datamodel.Twit;
import main.java.com.ubo.tp.twitub.datamodel.User;
import main.java.com.ubo.tp.twitub.ihm.javafx.composants.ListeTwitsComposant;
import main.java.com.ubo.tp.twitub.model.IModelObserver;

import java.util.List;

import static main.java.com.ubo.tp.twitub.utils.Panel.refreshPane;

public class AccueilView extends JavaFxView implements IModelObserver, IDatabaseObserver {

    public AccueilView(AccueilController controller) {
        super(controller);
        this.getController().getDatabase().addObserver(this);
        this.getController().getSession().addObservers(this);
    }

    @Override
    public void init(){
        Label titre = new Label("Accueil");
        setPadding(new Insets(10));
        add(titre,0,0);

        List<Twit> twits = this.getController().getTwitsAbonnements();
        add(new ListeTwitsComposant(twits), 0,1);

        Button rotate = new Button("Tourne !!!!");
        rotate.setOnAction(e->rotation(titre));
        add(rotate,0,2);
    }

    private void rotation(Node node){
        RotateTransition rotate = new RotateTransition();

        //Setting Axis of rotation
        rotate.setAxis(Rotate.Z_AXIS);

        // setting the angle of rotation
        rotate.setByAngle(360);

        //setting cycle count of the rotation
        rotate.setCycleCount(1);

        //Setting duration of the transition
        rotate.setDuration(Duration.millis(1000));

        //setting Rectangle as the node onto which the
// transition will be applied
        rotate.setNode(node);

        //playing the transition
        rotate.play();
    }

    @Override
    public AccueilController getController(){
        return ((AccueilController)super.getController());
    }

    @Override
    public void notifyTwitAdded(Twit addedTwit) {
        refreshPane(this);
    }

    @Override
    public void notifyTwitDeleted(Twit deletedTwit) {
        refreshPane(this);
    }

    @Override
    public void notifyTwitModified(Twit modifiedTwit) {
        refreshPane(this);
    }

    @Override
    public void notifyUserAdded(User addedUser) {
        refreshPane(this);
    }

    @Override
    public void notifyUserDeleted(User deletedUser) {
        refreshPane(this);
    }

    @Override
    public void notifyUserModified(User modifiedUser) {
        refreshPane(this);
    }

    @Override
    public void notifyUserConnected() {
        refreshPane(this);
    }

    @Override
    public void notifyDisconnect() {
        refreshPane(this);
    }
}
