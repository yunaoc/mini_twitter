package main.java.com.ubo.tp.twitub.controllers;

import main.java.com.ubo.tp.twitub.core.Controller;
import main.java.com.ubo.tp.twitub.datamodel.IDatabase;
import main.java.com.ubo.tp.twitub.datamodel.Twit;
import main.java.com.ubo.tp.twitub.model.Session;

import java.util.ArrayList;
import java.util.List;

public class RechercheController extends Controller {

    public RechercheController(IDatabase database, IControllerObserver observer, Session session) {
        super(database, observer, session);
    }


    public List<Twit> getTwitsRecherche(String text) {
        List<Twit> twits = new ArrayList<>();
        if(null != getSession().getConnectedUser()){
            if(text.charAt(0) == '@'){
                for(Twit twit:getDatabase().getTwits()){
                    if(twit.getTwiter().getUserTag().equals(text.substring(1)) || twit.getText().contains(text)){
                        twits.add(twit);
                    }
                }
            } else if(text.charAt(0) == '#'){
                for(Twit twit:getDatabase().getTwits()){
                    if(twit.getText().contains(text)){
                        twits.add(twit);
                    }
                }
            } else {
                for(Twit twit:getDatabase().getTwits()){
                    if(twit.getTwiter().getUserTag().contains(text) || twit.getText().contains(text)){
                        twits.add(twit);
                    }
                }
            }
        }
        return twits;
    }
}
