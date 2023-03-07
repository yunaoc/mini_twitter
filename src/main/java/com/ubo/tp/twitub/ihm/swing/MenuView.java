package main.java.com.ubo.tp.twitub.ihm.swing;

import main.java.com.ubo.tp.twitub.controllers.CreerCompteController;
import main.java.com.ubo.tp.twitub.controllers.MenuController;
import main.java.com.ubo.tp.twitub.core.Controller;
import main.java.com.ubo.tp.twitub.core.SwingView;
import main.java.com.ubo.tp.twitub.datamodel.IDatabaseObserver;
import main.java.com.ubo.tp.twitub.datamodel.Twit;
import main.java.com.ubo.tp.twitub.datamodel.User;

import javax.swing.*;
import java.awt.*;

import static main.java.com.ubo.tp.twitub.utils.Panel.refreshPanel;

public class MenuView extends SwingView implements IDatabaseObserver {

    boolean notif = false;
    public MenuView(Controller controller) {
        super(controller);
        this.getController().getDatabase().addObserver(this);
        init();
    }

    @Override
    public void init(){
        JButton boutonAccueil = new JButton("Accueil");
        if(notif){
            boutonAccueil.setBackground(Color.decode("#594249"));
            notif = false;
        }

        add(boutonAccueil);
        JButton boutonProfil = new JButton("Profil");
        add(boutonProfil);
        JButton boutonPoste = new JButton("Poste");
        add(boutonPoste);
        JButton boutonUtilisateurs = new JButton("Utilisateurs");
        add(boutonUtilisateurs);
        JButton boutonRechercher = new JButton("Rechercher");
        add(boutonRechercher);
        JButton boutonDeconnexion = new JButton("Deconnexion");
        boutonDeconnexion.setBackground(Color.decode("#a82828"));
        add(boutonDeconnexion);

        boutonProfil.addActionListener((event) -> {
            this.getController().goToProfil();
        });
        boutonAccueil.addActionListener((event) -> {
            this.getController().goToAccueil();
            notif = false;
            refreshPanel(this);
        });
        boutonPoste.addActionListener((event) -> {
            this.getController().goToCreerTwit();
        });
        boutonUtilisateurs.addActionListener((event) -> {
            this.getController().goToUtilisateurs();
        });
        boutonRechercher.addActionListener((event) -> {
            this.getController().goToRecherche();
        });

        boutonDeconnexion.addActionListener((event) -> {
            this.getController().getSession().disconnect();
            this.getController().goToConnexion();

        });
    }

    @Override
    public MenuController getController(){
        return ((MenuController)super.getController());
    }

    @Override
    public void notifyTwitAdded(Twit addedTwit) {
        if(this.getController().getSession().getConnectedUser() != null) {
            for (String follower : this.getController().getSession().getConnectedUser().getFollows()) {
                if (addedTwit.getTwiter().getUserTag().equals(follower)) {
                    notif = true;
                    break;
                }
            }
            refreshPanel(this);
        }
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
