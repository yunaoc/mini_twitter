package main.java.com.ubo.tp.twitub.controllers;

import main.java.com.ubo.tp.twitub.core.Controller;
import main.java.com.ubo.tp.twitub.core.EntityManager;
import main.java.com.ubo.tp.twitub.datamodel.IDatabase;
import main.java.com.ubo.tp.twitub.datamodel.User;
import main.java.com.ubo.tp.twitub.model.Session;

import java.util.ArrayList;
import java.util.List;

public class UtilisateursController extends Controller {
    private EntityManager entityManager;

    public UtilisateursController(IDatabase database, IControllerObserver observer, Session session, EntityManager entityManager) {
        super(database, observer, session);
        this.entityManager = entityManager;
    }

    public List<User> getAllUsers(){
        return new ArrayList<>(getDatabase().getUsers());
    }

    public void unsubscribe(String userTag) {
        getSession().getConnectedUser().removeFollowing(userTag);
        getDatabase().modifiyUser(getSession().getConnectedUser());
        entityManager.sendUser(getSession().getConnectedUser());

    }

    public void subscribe(String userTag) {
        getSession().getConnectedUser().addFollowing(userTag);
        getDatabase().modifiyUser(getSession().getConnectedUser());
        entityManager.sendUser(getSession().getConnectedUser());
    }
}
