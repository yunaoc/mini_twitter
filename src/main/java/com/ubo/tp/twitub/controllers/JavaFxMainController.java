package main.java.com.ubo.tp.twitub.controllers;

import main.java.com.ubo.tp.twitub.core.*;
import main.java.com.ubo.tp.twitub.datamodel.IDatabase;
import main.java.com.ubo.tp.twitub.events.file.WatchableDirectory;
import main.java.com.ubo.tp.twitub.ihm.javafx.AccueilView;
import main.java.com.ubo.tp.twitub.ihm.javafx.ConnexionView;
import main.java.com.ubo.tp.twitub.ihm.javafx.CreationTwitView;
import main.java.com.ubo.tp.twitub.ihm.javafx.CreerCompteView;
import main.java.com.ubo.tp.twitub.ihm.javafx.JavaFxMainView;
import main.java.com.ubo.tp.twitub.ihm.javafx.MenuView;
import main.java.com.ubo.tp.twitub.ihm.javafx.ProfilView;
import main.java.com.ubo.tp.twitub.ihm.javafx.RechercheView;
import main.java.com.ubo.tp.twitub.ihm.javafx.UtilisateursView;
import main.java.com.ubo.tp.twitub.ihm.swing.*;
import main.java.com.ubo.tp.twitub.model.Session;

public class JavaFxMainController implements IControllerObserver{
    /**
     * Base de donénes de l'application.
     */
    protected IDatabase mDatabase;

    /**
     * Gestionnaire de bdd et de fichier.
     */
    protected EntityManager mEntityManager;

    private JavaFxMainView mView;

    private JavaFxComposant connexion;
    private JavaFxComposant creerCompte;
    private JavaFxComposant creerTwit;
    private JavaFxComposant accueil;
    private JavaFxComposant profil;
    private JavaFxComposant utilisateurs;
    private JavaFxComposant menu;
    private JavaFxComposant recherche;

    public JavaFxMainController(IDatabase database, EntityManager entityManager, JavaFxMainView view){
        this.mDatabase = database;
        this.mEntityManager = entityManager;
        this.mView = view;
        initComposants();

        lancement();
    }

    private void initComposants() {
        Session session = new Session();
        mView.abonnerSession(session);
        Controller cMenu = new MenuController(mDatabase,this, session);
        menu = new JavaFxComposant(cMenu, new MenuView(cMenu));
        Controller cConnexion = new ConnexionController(mDatabase,this, session);
        connexion = new JavaFxComposant(cConnexion, new ConnexionView(cConnexion));
        Controller cCreerCompte = new CreerCompteController(mDatabase,this, session, mEntityManager);
        creerCompte = new JavaFxComposant(cCreerCompte, new CreerCompteView(cCreerCompte));
        AccueilController cAccueil = new AccueilController(mDatabase,this, session);
        accueil = new JavaFxComposant(cAccueil, new AccueilView(cAccueil));
        Controller cCreerTwit = new CreationTwitController(mDatabase,this, session,mEntityManager);
        creerTwit = new JavaFxComposant(cCreerTwit, new CreationTwitView(cCreerTwit));
        Controller cProfil = new ProfilController(mDatabase,this, session);
        profil = new JavaFxComposant(cProfil, new ProfilView(cProfil));
        Controller cUtilisateurs = new UtilisateursController(mDatabase,this, session, mEntityManager);
        utilisateurs = new JavaFxComposant(cUtilisateurs, new UtilisateursView(cUtilisateurs));
        Controller cRecherche = new RechercheController(mDatabase,this, session);
        recherche = new JavaFxComposant(cRecherche, new RechercheView(cRecherche));
    }

    private void lancement() {
        mView.initGUI(menu.getView());
        String dir = mView.setExchangeRepository();
        initDirectory(dir);
        mView.goToView(connexion.getView());
    }

    /**
     * Initialisation du répertoire d'échange.
     *
     * @param directoryPath
     */
    public void initDirectory(String directoryPath) {
        WatchableDirectory mWatchableDirectory = new WatchableDirectory(directoryPath);
        mEntityManager.setExchangeDirectory(directoryPath);
        mWatchableDirectory.initWatching();
        mWatchableDirectory.addObserver(mEntityManager);
    }

    @Override
    public void notifyGoToConnexion() {
        mView.goToView(connexion.getView());
    }

    @Override
    public void notifyGoToCreerCompte() {
        mView.goToView(creerCompte.getView());
    }

    @Override
    public void notifyGoToAccueil() {
        mView.goToView(accueil.getView());
    }

    @Override
    public void notifyGoToCreerTwit() {
        mView.goToView(creerTwit.getView());
    }

    @Override
    public void notifyGoToProfil() { mView.goToView(profil.getView());
    }

    @Override
    public void notifyGoToUtilisateurs() {
        mView.goToView(utilisateurs.getView());
    }

    @Override
    public void notifyGoToRecherche() {
        mView.goToView(recherche.getView());
    }
}
