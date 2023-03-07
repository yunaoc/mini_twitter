package main.java.com.ubo.tp.twitub.ihm.swing;

import main.java.com.ubo.tp.twitub.controllers.CreationTwitController;
import main.java.com.ubo.tp.twitub.core.Controller;
import main.java.com.ubo.tp.twitub.core.SwingView;

import javax.swing.*;
import java.awt.*;

import static main.java.com.ubo.tp.twitub.utils.Font.setFontTitre;

public class CreationTwitView extends SwingView {

    public CreationTwitView(Controller controller) {
        super(controller);
        init();
    }

    @Override
    public void init(){
        setLayout(new GridBagLayout());
        JLabel titre = new JLabel("Poster un twit");
        setFontTitre(titre);
        add(titre, new GridBagConstraints(0, 0, 1, 1, 1, 0, GridBagConstraints.NORTH,
                GridBagConstraints.VERTICAL, new Insets(0, 0, 10, 0), 0, 0));

        JTextArea twit = new JTextArea();
        twit.setLineWrap(true);
        add(twit, new GridBagConstraints(0, 1, 2, 1, 2, 0, GridBagConstraints.NORTH,
                GridBagConstraints.BOTH, new Insets(0, 100, 10, 100), 0, 0));

        JLabel erreur = new JLabel("");
        erreur.setForeground(Color.RED);
        add(erreur, new GridBagConstraints(0, 2, 1, 1, 1, 0, GridBagConstraints.NORTH,
                GridBagConstraints.VERTICAL, new Insets(0, 0, 10, 0), 0, 0));

        JButton publier = new JButton("Publier");
        add(publier, new GridBagConstraints(0, 3, 1, 1, 1, 0, GridBagConstraints.NORTH,
                GridBagConstraints.VERTICAL, new Insets(0, 0, 10, 0), 0, 0));
        publier.addActionListener(event -> {
            String texte = twit.getText();
            if(texte.equals("")){
                erreur.setText("Erreur, le twit est vide.");
            }else if(texte.length() > 250 ){
                erreur.setText("Erreur, le twit est trop long de "+ (texte.length()-250)+" caractères.");
            }else{
                this.getController().createTwit(texte);
                twit.setText("");
                erreur.setText("");
                this.getController().goToAccueil();
            }
        });
    }

    @Override
    public CreationTwitController getController(){
        return ((CreationTwitController)super.getController());
    }
}
