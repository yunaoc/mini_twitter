package main.java.com.ubo.tp.twitub.controllers;

import main.java.com.ubo.tp.twitub.core.Controller;
import main.java.com.ubo.tp.twitub.datamodel.IDatabase;
import main.java.com.ubo.tp.twitub.datamodel.Twit;
import main.java.com.ubo.tp.twitub.datamodel.User;
import main.java.com.ubo.tp.twitub.model.IModelObserver;
import main.java.com.ubo.tp.twitub.model.Session;

import java.util.ArrayList;
import java.util.List;

public class AccueilController extends Controller{

    public AccueilController(IDatabase database, IControllerObserver observer, Session session) {
        super(database, observer, session);
    }

    public List<Twit> getTwitsAbonnements() {
        List<Twit> twits = new ArrayList<>();
        if(null != getSession().getConnectedUser()){
            for(Twit twit:getDatabase().getTwits()){
                if(getSession().getConnectedUser().isFollowing(twit.getTwiter())){
                    twits.add(twit);
                }
            }
        }
        return twits;
    }
}