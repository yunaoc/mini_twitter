package main.java.com.ubo.tp.twitub.ihm.swing;

import main.java.com.ubo.tp.twitub.controllers.CreerCompteController;
import main.java.com.ubo.tp.twitub.controllers.ProfilController;
import main.java.com.ubo.tp.twitub.core.Controller;
import main.java.com.ubo.tp.twitub.core.SwingView;
import main.java.com.ubo.tp.twitub.datamodel.IDatabaseObserver;
import main.java.com.ubo.tp.twitub.datamodel.Twit;
import main.java.com.ubo.tp.twitub.datamodel.User;
import main.java.com.ubo.tp.twitub.ihm.swing.composants.ListeTwitsComposant;
import main.java.com.ubo.tp.twitub.ihm.swing.composants.ScrollBarComposant;
import main.java.com.ubo.tp.twitub.ihm.swing.composants.UserComposant;
import main.java.com.ubo.tp.twitub.model.IModelObserver;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import static main.java.com.ubo.tp.twitub.utils.Font.setFontTitre;
import static main.java.com.ubo.tp.twitub.utils.Panel.refreshPanel;

public class ProfilView extends SwingView implements IModelObserver, IDatabaseObserver {

    public ProfilView(Controller controller) {
        super(controller);
        this.getController().getSession().addObservers(this);
        this.getController().getDatabase().addObserver(this);
    }

    @Override
    public void init(){
        setLayout(new GridBagLayout());

        JLabel titre = new JLabel("Profil");
        setFontTitre(titre);
        add(titre, new GridBagConstraints(0, 0, 1, 1, 1, 0, GridBagConstraints.CENTER,
                GridBagConstraints.HORIZONTAL, new Insets(0, 0, 30, 0), 0, 0));

        if(this.getController().getSession().getConnectedUser() != null){

            UserComposant userComposant = new UserComposant(getController().getSession().getConnectedUser());
            add(userComposant, new GridBagConstraints(0, 1, 1, 1, 1, 0, GridBagConstraints.CENTER,
                GridBagConstraints.HORIZONTAL, new Insets(0, 0, 10, 0), 0, 0));


            JLabel nbAbonnement = new JLabel(getController().getSession().getConnectedUser().getFollows().size()+" abonnements");
            add(nbAbonnement, new GridBagConstraints(0, 2, 1, 1, 1, 0, GridBagConstraints.CENTER,
                    GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 2, 2));

            JLabel titreListe = new JLabel("Twits");
            setFontTitre(titreListe);
            add(titreListe, new GridBagConstraints(0, 3, 1, 1, 1, 0, GridBagConstraints.CENTER,
                    GridBagConstraints.HORIZONTAL, new Insets(0, 0, 2, 0), 0, 0));

            List<Twit> twits = this.getController().getTwitsProfil();
            JPanel panelTwits = new ListeTwitsComposant(twits);

            add(new ScrollBarComposant(panelTwits, this), new GridBagConstraints(0, 4, 1, 1, 1, 1, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(0, 0, 10, 0), 0, 0));
        }
    }

    @Override
    public ProfilController getController(){
        return ((ProfilController)super.getController());
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

        refreshPanel(this);
    }
}
