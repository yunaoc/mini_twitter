package main.java.com.ubo.tp.twitub.core;

import main.java.com.ubo.tp.twitub.controllers.IControllerObserver;
import main.java.com.ubo.tp.twitub.datamodel.IDatabase;
import main.java.com.ubo.tp.twitub.datamodel.User;
import main.java.com.ubo.tp.twitub.model.Session;

import java.util.HashSet;
import java.util.Set;

public abstract class Controller {

    /**
     * Base de données de l'application.
     */
    private IDatabase database;

    private Set<IControllerObserver> mObservers;

    private Session session;

    public Controller(IDatabase database, IControllerObserver observer, Session session) {
        this.session = session;
        this.database = database;
        mObservers = new HashSet<>();
        mObservers.add(observer);
    }

    public Set<IControllerObserver> getmObservers() {
        return mObservers;
    }

    public IDatabase getDatabase() {
        return database;
    }

    public void setDatabase(IDatabase database) {
        this.database = database;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}
