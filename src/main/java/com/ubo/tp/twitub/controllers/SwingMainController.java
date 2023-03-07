package main.java.com.ubo.tp.twitub.controllers;

import main.java.com.ubo.tp.twitub.core.SwingComposant;
import main.java.com.ubo.tp.twitub.core.Controller;
import main.java.com.ubo.tp.twitub.core.EntityManager;
import main.java.com.ubo.tp.twitub.datamodel.IDatabase;
import main.java.com.ubo.tp.twitub.events.file.WatchableDirectory;
import main.java.com.ubo.tp.twitub.ihm.swing.*;
import main.java.com.ubo.tp.twitub.model.Session;

public class SwingMainController implements IControllerObserver{
    /**
     * Base de donénes de l'application.
     */
    protected IDatabase mDatabase;

    /**
     * Gestionnaire de bdd et de fichier.
     */
    protected EntityManager mEntityManager;

    private SwingMainView mView;

    private SwingComposant connexion;
    private SwingComposant creerCompte;
    private SwingComposant creerTwit;
    private SwingComposant accueil;
    private SwingComposant profil;
    private SwingComposant utilisateurs;
    private SwingComposant menu;
    private SwingComposant recherche;

    public SwingMainController(IDatabase database, EntityManager entityManager, SwingMainView view){
        this.mDatabase = database;
        this.mEntityManager = entityManager;
        this.mView = view;
        initComposants();

        lancement();
    }

    private void initComposants() {
        Session session = new Session();
        mView.abonnerSession(session);
        ConnexionController cConnexion = new ConnexionController(mDatabase,this, session);
        connexion = new SwingComposant(cConnexion, new ConnexionView(cConnexion));
        CreerCompteController cCreerCompte = new CreerCompteController(mDatabase,this, session, mEntityManager);
        creerCompte = new SwingComposant(cCreerCompte, new CreerCompteView(cCreerCompte));
        CreationTwitController cCreerTwit = new CreationTwitController(mDatabase,this, session, mEntityManager);
        creerTwit = new SwingComposant(cCreerTwit, new CreationTwitView(cCreerTwit));
        AccueilController cAccueil = new AccueilController(mDatabase,this, session);
        accueil = new SwingComposant(cAccueil, new AccueilView(cAccueil));
        UtilisateursController cUtilisateur = new UtilisateursController(mDatabase,this, session, mEntityManager);
        utilisateurs = new SwingComposant(cUtilisateur, new UtilisateursView(cUtilisateur));
        ProfilController cProfil = new ProfilController(mDatabase,this, session);
        profil = new SwingComposant(cProfil, new ProfilView(cProfil));
        RechercheController cRecherche = new RechercheController(mDatabase,this, session);
        recherche = new SwingComposant(cRecherche, new RechercheView(cRecherche));

        MenuController cMenu = new MenuController(mDatabase,this, session);
        menu = new SwingComposant(cMenu, new MenuView(cMenu));
    }

    private void lancement() {
        mView.showGUI(menu.getView());
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
