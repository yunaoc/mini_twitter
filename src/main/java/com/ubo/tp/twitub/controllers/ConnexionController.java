package main.java.com.ubo.tp.twitub.controllers;

import main.java.com.ubo.tp.twitub.core.Controller;
import main.java.com.ubo.tp.twitub.datamodel.IDatabase;
import main.java.com.ubo.tp.twitub.datamodel.User;
import main.java.com.ubo.tp.twitub.model.Session;

public class ConnexionController extends Controller {
    public ConnexionController(IDatabase database, IControllerObserver controllerObserver, Session session) {
        super(database,controllerObserver, session);
    }

    public void goToAccueil() {
        for(IControllerObserver observer: getmObservers()){
            observer.notifyGoToAccueil();
        }
    }

    public void goToCreerCompte() {
        for(IControllerObserver observer: getmObservers()){
            observer.notifyGoToCreerCompte();
        }
    }

    public boolean identification(String tag, String password) {
        User user = userExist(tag);
        if(user == null || !user.getUserPassword().equals(password)){
            return false;
        }else{
            getSession().connectUser(user);
            return true;
        }
    }

    public User userExist(String tag){
        for(User user : getDatabase().getUsers()){
            if(user.getUserTag().equals(tag)){
                return user;
            }
        }
        return null;
    }
}
