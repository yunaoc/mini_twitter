package main.java.com.ubo.tp.twitub.controllers;

import main.java.com.ubo.tp.twitub.core.Controller;
import main.java.com.ubo.tp.twitub.datamodel.IDatabase;
import main.java.com.ubo.tp.twitub.model.Session;

public class MenuController extends Controller {
    public MenuController(IDatabase database, IControllerObserver observer, Session session) {
        super(database, observer, session);
    }

    public void goToCreerTwit() {
        for(IControllerObserver observer: getmObservers()){
            observer.notifyGoToCreerTwit();
        }
    }

    public void goToAccueil() {
        for(IControllerObserver observer: getmObservers()){
            observer.notifyGoToAccueil();
        }
    }

    public void goToProfil() {
        for(IControllerObserver observer: getmObservers()){
            observer.notifyGoToProfil();
        }
    }

    public void goToConnexion() {
        for(IControllerObserver observer: getmObservers()){
            observer.notifyGoToConnexion();
        }
    }

    public void goToUtilisateurs() {
        for(IControllerObserver observer: getmObservers()){
            observer.notifyGoToUtilisateurs();
        }
    }

    public void goToRecherche() {
        for(IControllerObserver observer: getmObservers()){
            observer.notifyGoToRecherche();
        }
    }
}
