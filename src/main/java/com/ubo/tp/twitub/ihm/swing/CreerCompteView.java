package main.java.com.ubo.tp.twitub.ihm.swing;

import main.java.com.ubo.tp.twitub.controllers.CreationTwitController;
import main.java.com.ubo.tp.twitub.controllers.CreerCompteController;
import main.java.com.ubo.tp.twitub.core.Controller;
import main.java.com.ubo.tp.twitub.core.SwingView;

import javax.swing.*;
import java.awt.*;

import static main.java.com.ubo.tp.twitub.utils.Font.setFontTitre;

public class CreerCompteView extends SwingView {
    
    public CreerCompteView(Controller controller){
        super(controller);
        setLayout(new GridBagLayout());
        init();
    }

    @Override
    public void init() {

        JPanel contentPane = new JPanel(new GridBagLayout());


        JPanel titrePane = new JPanel(new GridBagLayout());
        JLabel titre = new JLabel("Créer un compte");
        setFontTitre(titre);
        titrePane.add(titre, new GridBagConstraints(0, 0, 0, 1, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

        contentPane.add(titrePane, new GridBagConstraints(0, 0, 0, 1, 1, 0, GridBagConstraints.CENTER,
                        GridBagConstraints.NONE, new Insets(0, 0, 30, 0), 0, 0));



        JPanel formPane = new JPanel(new GridBagLayout());

        JLabel tagTexte = new JLabel("Tag (*) : ");
        formPane.add(tagTexte, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH, new Insets(0, 0, 10, 0), 0, 0));

        JTextField tag = new JTextField();
        tag.setBorder(BorderFactory.createEmptyBorder());
        formPane.add(tag, new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH, new Insets(0, 0, 10, 0), 0, 0));

        JLabel nomTexte = new JLabel("Nom (*) : ");
        formPane.add(nomTexte, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH, new Insets(0, 0, 10, 0), 0, 0));

        JTextField nom = new JTextField();
        nom.setBorder(BorderFactory.createEmptyBorder());
        formPane.add(nom, new GridBagConstraints(1, 1, 1, 1, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH, new Insets(0, 0, 10, 0), 0, 0));

        JLabel mdpTexte = new JLabel("Mot de passe (*) : ");
        formPane. add(mdpTexte, new GridBagConstraints(0, 2, 1, 1, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH, new Insets(0, 0, 10, 0), 0, 0));

        JPasswordField mdp = new JPasswordField();
        mdp.setBorder(BorderFactory.createEmptyBorder());
        formPane.add(mdp, new GridBagConstraints(1, 2, 1, 1, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH, new Insets(0, 0, 10, 0), 0, 0));

        JLabel cmdpTexte = new JLabel("Confirmation mot de passe (*) : ");
        formPane.add(cmdpTexte, new GridBagConstraints(0, 3, 1, 1, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH, new Insets(0, 0, 10, 0), 0, 0));

        JPasswordField cmdp = new JPasswordField();
        cmdp.setBorder(BorderFactory.createEmptyBorder());
        formPane.add(cmdp, new GridBagConstraints(1, 3, 1, 1, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH, new Insets(0, 0, 10, 0), 0, 0));

        contentPane.add(formPane, new GridBagConstraints(0, 1, 1, 1, 1, 0, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH, new Insets(0, 90, 30, 90), 0, 0));



        JPanel erreurPane = new JPanel(new GridBagLayout());

        JLabel erreur = new JLabel("");
        erreur.setForeground(Color.RED);
        erreurPane.add(erreur, new GridBagConstraints(0, 0, 0, 1, 0, 0, GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));

        contentPane.add(erreurPane, new GridBagConstraints(0, 2, 0, 1, 1, 0, GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(0, 0, 30, 0), 0, 0));



        JPanel buttonPanel = new JPanel(new GridBagLayout());

        JButton boutonAnnuler = new JButton("Annuler");
        boutonAnnuler.addActionListener(event ->{
            this.getController().goToConnexion();
        });
        buttonPanel.add(boutonAnnuler, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.EAST,
                GridBagConstraints.NONE, new Insets(0, 0, 0,0 ), 0, 0));

        JButton boutonValider = new JButton("Valider");
        boutonValider.addActionListener(event ->{
            if(tag.getText().equals("") || nom.getText().equals("") || String.valueOf((mdp.getPassword())).equals("") ||  String.valueOf((cmdp.getPassword())).equals("")){
                erreur.setText("Erreur, un ou plusieurs des champs obligatoires est/sont vide(s).");
            }else if(this.getController().userExist(tag.getText())){
                erreur.setText("Erreur, le tag est déjà pris.");

            }else if (!String.valueOf((mdp.getPassword())).equals(String.valueOf((cmdp.getPassword())))){
                erreur.setText("Erreur, les mots de passes sont différents.");
            }else{
                this.getController().createUser(tag.getText(), nom.getText(), String.valueOf((mdp.getPassword())));
                tag.setText("");
                nom.setText("");
                mdp.setText("");
                cmdp.setText("");
                erreur.setText("");
                this.getController().goToConnexion();
            }
        });
        buttonPanel.add(boutonValider, new GridBagConstraints(1, 0, 1, 1, 0, 0, GridBagConstraints.WEST,
                GridBagConstraints.NONE, new Insets(0, 0, 0,0 ), 0, 0));

        contentPane.add(buttonPanel, new GridBagConstraints(0, 3, 0, 1, 0, 0, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH, new Insets(0, 0, 0,0 ), 0, 0));


        this.add(contentPane, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH, new Insets(0, 0, 0,0 ), 0, 0));
    }

    @Override
    public CreerCompteController getController(){
        return ((CreerCompteController)super.getController());
    }
}
