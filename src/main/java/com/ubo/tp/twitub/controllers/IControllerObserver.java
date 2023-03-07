package main.java.com.ubo.tp.twitub.controllers;

import main.java.com.ubo.tp.twitub.core.Controller;
import main.java.com.ubo.tp.twitub.datamodel.IDatabase;
import main.java.com.ubo.tp.twitub.datamodel.User;

public interface IControllerObserver {

    void notifyGoToConnexion();
    void notifyGoToCreerCompte();
    void notifyGoToAccueil();
    void notifyGoToCreerTwit();
    void notifyGoToProfil();
    void notifyGoToUtilisateurs();
    void notifyGoToRecherche();
}
