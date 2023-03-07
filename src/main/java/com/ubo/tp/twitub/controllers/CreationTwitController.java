package main.java.com.ubo.tp.twitub.controllers;

import main.java.com.ubo.tp.twitub.core.Controller;
import main.java.com.ubo.tp.twitub.core.EntityManager;
import main.java.com.ubo.tp.twitub.datamodel.IDatabase;
import main.java.com.ubo.tp.twitub.datamodel.Twit;
import main.java.com.ubo.tp.twitub.model.Session;

public class CreationTwitController extends Controller {

    private EntityManager entityManager;

    public CreationTwitController(IDatabase database, IControllerObserver observer, Session session, EntityManager entityManager) {
        super(database, observer, session);
        this.entityManager = entityManager;
    }

    public void goToAccueil() {
        for(IControllerObserver observer: getmObservers()){
            observer.notifyGoToAccueil();
        }
    }

    public void createTwit(String texte) {
        Twit twit = new Twit(getSession().getConnectedUser(),texte);
        entityManager.sendTwit(twit);
    }
}
