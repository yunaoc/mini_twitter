package main.java.com.ubo.tp.twitub.ihm.swing;

import main.java.com.ubo.tp.twitub.controllers.AccueilController;
import main.java.com.ubo.tp.twitub.controllers.ConnexionController;
import main.java.com.ubo.tp.twitub.core.Controller;
import main.java.com.ubo.tp.twitub.core.SwingView;

import javax.swing.*;
import java.awt.*;

import static main.java.com.ubo.tp.twitub.utils.Font.setFontTitre;

public class ConnexionView extends SwingView {

    public ConnexionView(Controller controller){
        super(controller);
        init();
    }

    @Override
    public void init(){
        setLayout(new GridBagLayout());
        JLabel titre = new JLabel("Se Connecter");
        setFontTitre(titre);
        add(titre, new GridBagConstraints(0, 0, 0, 1, 1, 0, GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(0, 0, 30, 0), 0, 0));

        JLabel tagTexte = new JLabel("Tag (*) : ");
        add(tagTexte, new GridBagConstraints(0, 1, 1, 1, 1, 0, GridBagConstraints.WEST,
                GridBagConstraints.NONE, new Insets(0, 100, 10, 0), 0, 0));

        JTextField tag = new JTextField();
        tag.setBorder(BorderFactory.createEmptyBorder());
        add(tag, new GridBagConstraints(1, 1, 1, 1, 1, 0, GridBagConstraints.WEST,
                GridBagConstraints.BOTH, new Insets(0, 0, 10, 100), 0, 0));

        JLabel mdpTexte = new JLabel("Mot de passe (*) : ");
        add(mdpTexte, new GridBagConstraints(0, 2, 1, 1, 1, 0, GridBagConstraints.WEST,
                GridBagConstraints.NONE, new Insets(0, 100, 10, 0), 0, 0));

        JPasswordField mdp = new JPasswordField();
        mdp.setBorder(BorderFactory.createEmptyBorder());
        add(mdp, new GridBagConstraints(1, 2, 1, 1, 1, 0, GridBagConstraints.WEST,
                GridBagConstraints.BOTH, new Insets(0, 0, 10, 100), 0, 0));

        JLabel erreur = new JLabel("");
        erreur.setForeground(Color.RED);
        add(erreur, new GridBagConstraints(0, 3, 0, 1, 0, 0, GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(0, 0, 10, 0), 0, 0));

        JButton boutonCreer = new JButton("Créer un compte");
        boutonCreer.addActionListener(event ->{
            this.getController().goToCreerCompte();
        });
        add(boutonCreer, new GridBagConstraints(0, 4, 1, 1, 0, 0, GridBagConstraints.EAST,
                GridBagConstraints.NONE, new Insets(0, 10, 0,0 ), 0, 0));

        JButton boutonConnexion = new JButton("Connexion");
        boutonConnexion.addActionListener(event ->{
            if(connection(tag, mdp)){
                this.getController().goToAccueil();
                tag.setText("");
                mdp.setText("");
                erreur.setText("");
            }else{
                erreur.setText("Erreur, les informations sont incorrectes.");
            }
        });
        add(boutonConnexion, new GridBagConstraints(1, 4, 1, 1, 0, 0, GridBagConstraints.WEST,
                GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    }

    private boolean connection(JTextField tag, JPasswordField mdp) {
        if(tag.getText().equals("") || mdp.getPassword().length == 0){
            return false;
        }else{
            return this.getController().identification(tag.getText(), String.valueOf((mdp.getPassword())));
        }
    }


    @Override
    public ConnexionController getController(){
        return ((ConnexionController)super.getController());
    }
}
