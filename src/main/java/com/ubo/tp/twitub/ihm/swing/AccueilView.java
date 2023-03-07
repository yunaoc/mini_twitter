package main.java.com.ubo.tp.twitub.ihm.swing;

import main.java.com.ubo.tp.twitub.controllers.AccueilController;
import main.java.com.ubo.tp.twitub.core.Controller;
import main.java.com.ubo.tp.twitub.core.SwingView;
import main.java.com.ubo.tp.twitub.datamodel.IDatabaseObserver;
import main.java.com.ubo.tp.twitub.datamodel.Twit;
import main.java.com.ubo.tp.twitub.datamodel.User;
import main.java.com.ubo.tp.twitub.ihm.swing.composants.ListeTwitsComposant;
import main.java.com.ubo.tp.twitub.ihm.swing.composants.ScrollBarComposant;
import main.java.com.ubo.tp.twitub.model.IModelObserver;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import static main.java.com.ubo.tp.twitub.utils.Font.setFontTitre;
import static main.java.com.ubo.tp.twitub.utils.Panel.refreshPanel;

public class AccueilView extends SwingView implements IModelObserver, IDatabaseObserver {

    public AccueilView(AccueilController controller) {
        super(controller);
        this.getController().getSession().addObservers(this);
        this.getController().getDatabase().addObserver(this);
    }

    @Override
    public void init() {
        setLayout(new GridBagLayout());

        JLabel titre = new JLabel("Accueil");
        setFontTitre(titre);
        add(titre, new GridBagConstraints(0, 0, 1, 1, 1, 0, GridBagConstraints.NORTH,
                GridBagConstraints.NONE, new Insets(0, 0, 30, 0), 0, 0));

        List<Twit> twits = this.getController().getTwitsAbonnements();
        JPanel panelTwits = new ListeTwitsComposant(twits);

        add(new ScrollBarComposant(panelTwits,this), new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.NORTH,
                GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
    }


    @Override
    public AccueilController getController(){
        return ((AccueilController)super.getController());
    }

    @Override
    public void notifyUserConnected() {
        refreshPanel(this);
    }

    @Override
    public void notifyDisconnect() {
        refreshPanel(this);
    }

    @Override
    public void notifyTwitAdded(Twit addedTwit) {
        refreshPanel(this);
    }

    @Override
    public void notifyTwitDeleted(Twit deletedTwit) {
        refreshPanel(this);
    }

    @Override
    public void notifyTwitModified(Twit modifiedTwit) {
        refreshPanel(this);
    }

    @Override
    public void notifyUserAdded(User addedUser) {

    }

    @Override
    public void notifyUserDeleted(User deletedUser) {

    }

    @Override
    public void notifyUserModified(User modifiedUser) {

    }
}
