package main.java.com.ubo.tp.twitub.ihm.javafx;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import main.java.com.ubo.tp.twitub.controllers.UtilisateursController;
import main.java.com.ubo.tp.twitub.core.Controller;
import main.java.com.ubo.tp.twitub.core.JavaFxView;
import main.java.com.ubo.tp.twitub.datamodel.IDatabaseObserver;
import main.java.com.ubo.tp.twitub.datamodel.Twit;
import main.java.com.ubo.tp.twitub.datamodel.User;
import main.java.com.ubo.tp.twitub.ihm.javafx.composants.UserComposant;
import main.java.com.ubo.tp.twitub.model.IModelObserver;

import javax.swing.*;
import java.util.List;

import static main.java.com.ubo.tp.twitub.utils.Panel.refreshPane;

public class UtilisateursView extends JavaFxView implements IModelObserver, IDatabaseObserver {

    private final UtilisateursController monController;

    public UtilisateursView(Controller controller) {
        super(controller);
        monController = (UtilisateursController) controller;
        monController.getSession().addObservers(this);
        monController.getDatabase().addObserver(this);
    }

    @Override
    public void init() {
        Label titre = new Label("Utilisateurs");
        add(titre,0,0, 1,1);
        if (monController.getSession().getConnectedUser() != null) {
            List<User> users = monController.getAllUsers();
            GridPane pane = new GridPane();
            pane.setVgap(20);
            for (int i = 0; i < users.size(); i++) {
                User user = users.get(i);
                if (!monController.getSession().getConnectedUser().equals(user)) {
                    UserComposant userComposant = new UserComposant(user);

                    Button boutonAbonnement = new Button("");
                    if (monController.getSession().getConnectedUser().isFollowing(user)) {
                        boutonAbonnement.setText("Se désabonner");
                        boutonAbonnement.setOnAction(e -> monController.unsubscribe(user.getUserTag()));
                    } else {
                        boutonAbonnement.setText("S'abonner");
                        boutonAbonnement.setOnAction(e -> monController.subscribe(user.getUserTag()));
                    }
                    pane.add(userComposant,0, i);
                    pane.add(boutonAbonnement, 1, i);
                }
            }
            ScrollPane sp = new ScrollPane(pane);
            sp.setHbarPolicy(javafx.scene.control.ScrollPane.ScrollBarPolicy.NEVER);
            sp.setVbarPolicy(javafx.scene.control.ScrollPane.ScrollBarPolicy.AS_NEEDED);
            add(sp,0,1);
        }
    }

    @Override
    public void notifyTwitAdded(Twit addedTwit) {

    }

    @Override
    public void notifyTwitDeleted(Twit deletedTwit) {

    }

    @Override
    public void notifyTwitModified(Twit modifiedTwit) {

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
