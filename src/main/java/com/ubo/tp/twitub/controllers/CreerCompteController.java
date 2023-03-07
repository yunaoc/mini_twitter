package main.java.com.ubo.tp.twitub.controllers;

import main.java.com.ubo.tp.twitub.core.Controller;
import main.java.com.ubo.tp.twitub.core.EntityManager;
import main.java.com.ubo.tp.twitub.datamodel.IDatabase;
import main.java.com.ubo.tp.twitub.datamodel.User;
import main.java.com.ubo.tp.twitub.model.Session;

import java.util.HashSet;
import java.util.UUID;

public class CreerCompteController extends Controller {

    private EntityManager entityManager;

    public CreerCompteController(IDatabase database, IControllerObserver controllerObserver, Session session, EntityManager entityManager) {
        super(database,controllerObserver, session);
        this.entityManager = entityManager;
    }

    public Boolean userExist(String tag){
        for(User user : getDatabase().getUsers()){
            if(user.getUserTag().equals(tag)){
                return true;
            }
        }
        return false;
    }

    public void goToConnexion() {
        for(IControllerObserver observer: getmObservers()){
            observer.notifyGoToConnexion();
        }
    }

    public void createUser(String tag, String nom, String mdp) {
        User user = new User(UUID.randomUUID(), tag, mdp, nom, new HashSet<>(), "");
        entityManager.sendUser(user);
    }
}
