package main.java.com.ubo.tp.twitub.ihm.swing;

import main.java.com.ubo.tp.twitub.controllers.RechercheController;
import main.java.com.ubo.tp.twitub.controllers.UtilisateursController;
import main.java.com.ubo.tp.twitub.core.Controller;
import main.java.com.ubo.tp.twitub.core.SwingView;
import main.java.com.ubo.tp.twitub.datamodel.IDatabaseObserver;
import main.java.com.ubo.tp.twitub.datamodel.Twit;
import main.java.com.ubo.tp.twitub.datamodel.User;
import main.java.com.ubo.tp.twitub.ihm.swing.composants.ScrollBarComposant;
import main.java.com.ubo.tp.twitub.ihm.swing.composants.UserComposant;
import main.java.com.ubo.tp.twitub.model.IModelObserver;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import static main.java.com.ubo.tp.twitub.utils.Font.setFontTitre;
import static main.java.com.ubo.tp.twitub.utils.Panel.refreshPanel;

public class UtilisateursView extends SwingView implements IModelObserver, IDatabaseObserver {

    public UtilisateursView(Controller controller) {
        super(controller);
        this.getController().getSession().addObservers(this);
        this.getController().getDatabase().addObserver(this);
    }

    @Override
    public void init() {
        setLayout(new GridBagLayout());

        JLabel titre = new JLabel("Utilisateurs");
        setFontTitre(titre);
        add(titre, new GridBagConstraints(0, 0, 1, 1, 1, 0, GridBagConstraints.NORTHWEST,
                GridBagConstraints.HORIZONTAL, new Insets(0, 0, 30, 0), 0, 0));
        if (this.getController().getSession().getConnectedUser() != null) {
            List<User> users = this.getController().getAllUsers();
            JPanel panelUsers = new JPanel(new GridBagLayout());

            for (int i = 0; i < users.size(); i++) {
                User user = users.get(i);
                if (!this.getController().getSession().getConnectedUser().equals(user)) {
                    UserComposant userComposant = new UserComposant(user);

                    JButton boutonAbonnement = new JButton("");
                    if (this.getController().getSession().getConnectedUser().isFollowing(user)) {
                        boutonAbonnement.setText("Se désabonner");
                        boutonAbonnement.addActionListener((event) -> {
                            this.getController().unsubscribe(user.getUserTag());
                        });
                    } else {
                        boutonAbonnement.setText("S'abonner");
                        boutonAbonnement.addActionListener((event) -> {
                            this.getController().subscribe(user.getUserTag());

                        });
                    }

                    panelUsers.add(userComposant, new GridBagConstraints(0, i, 1, 1, 0, 1, GridBagConstraints.CENTER,
                            GridBagConstraints.NONE, new Insets(0, 00, 0, 0), 0, 0)
                    );
                    panelUsers.add(boutonAbonnement, new GridBagConstraints(1, i, 1, 1  , 0, 0, GridBagConstraints.CENTER,
                            GridBagConstraints.NONE, new Insets(0,
                            0, 0, 0), 0, 0));


                }
            }

            add(new ScrollBarComposant(panelUsers, this), new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(0, 0, 10, 0), 0, 0));

        }
    }

    @Override
    public UtilisateursController getController(){
        return ((UtilisateursController)super.getController());
    }

    @Override
    public void notifyTwitAdded(Twit addedTwit) {

    }

    @Override
    public void notifyTwitDeleted(Twit deletedTwit) {

    }

    @Override
    public void notifyTwitModified(Twit modifiedTwit) {

    }

    @Override
    public void notifyUserAdded(User addedUser) {
        refreshPanel(this);
    }

    @Override
    public void notifyUserDeleted(User deletedUser) {
        refreshPanel(this);
    }

    @Override
    public void notifyUserModified(User modifiedUser) {
        refreshPanel(this);
    }

    @Override
    public void notifyUserConnected() {
        refreshPanel(this);
    }

    @Override
    public void notifyDisconnect() {
        refreshPanel(this);
    }
}
