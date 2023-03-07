package main.java.com.ubo.tp.twitub.ihm.javafx;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import main.java.com.ubo.tp.twitub.controllers.AccueilController;
import main.java.com.ubo.tp.twitub.controllers.MenuController;
import main.java.com.ubo.tp.twitub.core.Controller;
import main.java.com.ubo.tp.twitub.core.JavaFxView;
import main.java.com.ubo.tp.twitub.datamodel.IDatabaseObserver;
import main.java.com.ubo.tp.twitub.datamodel.Twit;
import main.java.com.ubo.tp.twitub.datamodel.User;

public class MenuView extends JavaFxView implements IDatabaseObserver {

    private final MenuController monController;

    public MenuView(Controller controller) {
        super(controller);
        monController = (MenuController) controller;
        monController.getDatabase().addObserver(this);
        init();
    }

    @Override
    public void init(){
        Button boutonAccueil = new Button("Accueil");
        add(boutonAccueil, 0,0);
        Button boutonProfil = new Button("Profil");
        add(boutonProfil, 1,0);
        Button boutonPoste = new Button("Poste");
        add(boutonPoste, 2,0);
        Button boutonUtilisateurs = new Button("Utilisateurs");
        add(boutonUtilisateurs, 3,0);
        Button boutonRecherche = new Button("Recherche");
        add(boutonRecherche, 4,0);
        Button boutonDeconnexion = new Button("Déconnexion");
        add(boutonDeconnexion, 5,0);

        EventHandler<ActionEvent> goToAccueil = e -> monController.goToAccueil();
        boutonAccueil.setOnAction(goToAccueil);
        EventHandler<ActionEvent> goToProfil = e -> monController.goToProfil();
        boutonProfil.setOnAction(goToProfil);
        EventHandler<ActionEvent> goToCreerTwit = e -> monController.goToCreerTwit();
        boutonPoste.setOnAction(goToCreerTwit);
        EventHandler<ActionEvent> goToUtilisateurs = e -> monController.goToUtilisateurs();
        boutonUtilisateurs.setOnAction(goToUtilisateurs);
        EventHandler<ActionEvent> goToRecherche = e -> monController.goToRecherche();
        boutonRecherche.setOnAction(goToRecherche);
        EventHandler<ActionEvent> disconnect = e -> {
            monController.getSession().disconnect();
            monController.goToConnexion();
        };
        boutonDeconnexion.setOnAction(disconnect);
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

    }

    @Override
    public void notifyUserDeleted(User deletedUser) {

    }

    @Override
    public void notifyUserModified(User modifiedUser) {

    }
}
