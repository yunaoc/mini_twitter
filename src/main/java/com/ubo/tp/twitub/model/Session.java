package main.java.com.ubo.tp.twitub.model;

import main.java.com.ubo.tp.twitub.datamodel.User;

import java.util.HashSet;
import java.util.Set;

public class Session {
    private User connectedUser;

    private Set<IModelObserver> myObservers;

    public Session(){
        myObservers = new HashSet<>();
    }

    public User getConnectedUser() {
        return connectedUser;
    }

    public void connectUser(User connectedUser) {
        this.connectedUser = connectedUser;
        for(IModelObserver observer : myObservers){
            observer.notifyUserConnected();
        }
    }

    public void disconnect() {
        this.connectedUser = null;
        for(IModelObserver observer : myObservers){
            observer.notifyDisconnect();
        }
    }

    public void addObservers(IModelObserver observer){
        this.myObservers.add(observer);
    }
}
