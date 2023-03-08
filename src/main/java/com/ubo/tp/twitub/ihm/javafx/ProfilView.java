package main.java.com.ubo.tp.twitub.ihm.javafx;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import main.java.com.ubo.tp.twitub.controllers.ProfilController;
import main.java.com.ubo.tp.twitub.core.Controller;
import main.java.com.ubo.tp.twitub.core.JavaFxView;
import main.java.com.ubo.tp.twitub.datamodel.IDatabaseObserver;
import main.java.com.ubo.tp.twitub.datamodel.Twit;
import main.java.com.ubo.tp.twitub.datamodel.User;
import main.java.com.ubo.tp.twitub.ihm.javafx.composants.ListeTwitsComposant;
import main.java.com.ubo.tp.twitub.ihm.javafx.composants.UserComposant;
import main.java.com.ubo.tp.twitub.model.IModelObserver;

import javax.swing.*;
import java.util.List;

import static main.java.com.ubo.tp.twitub.utils.Panel.refreshPane;

public class ProfilView extends JavaFxView implements IModelObserver, IDatabaseObserver {

    private final ProfilController monController;
    public ProfilView(Controller controller) {
        super(controller);
        this.monController = (ProfilController)controller;
        monController.getSession().addObservers(this);
        monController.getDatabase().addObserver(this);
    }

    @Override
    public void init(){
        Label titre = new Label("Profil");
        setPadding(new Insets(10));
        add(titre,0,0);

        if(monController.getSession().getConnectedUser() != null) {
            UserComposant userComposant = new UserComposant(getController().getSession().getConnectedUser());
            add(userComposant,0,0);

            Label nbAbonnement = new Label(getController().getSession().getConnectedUser().getFollows().size()+" abonnements");
            add(nbAbonnement,0,1);

            Label titreListe = new Label("Twits");
            add(titreListe, 0,2);

            List<Twit> twits = monController.getTwitsProfil();
            add(new ListeTwitsComposant(twits), 0, 3);
        }
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
