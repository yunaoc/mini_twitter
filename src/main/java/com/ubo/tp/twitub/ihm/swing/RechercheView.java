package main.java.com.ubo.tp.twitub.ihm.swing;

import main.java.com.ubo.tp.twitub.controllers.RechercheController;
import main.java.com.ubo.tp.twitub.core.Controller;
import main.java.com.ubo.tp.twitub.core.SwingView;
import main.java.com.ubo.tp.twitub.datamodel.Twit;
import main.java.com.ubo.tp.twitub.ihm.swing.composants.ListeTwitsComposant;
import main.java.com.ubo.tp.twitub.ihm.swing.composants.ScrollBarComposant;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import static main.java.com.ubo.tp.twitub.utils.Font.setFontTitre;

public class RechercheView extends SwingView {
    private JPanel liste;

    public RechercheView(Controller controller) {
        super(controller);
        liste = new JPanel();
        init();
    }

    @Override
    public void init(){
        setLayout(new GridBagLayout());

        JLabel titre = new JLabel("Recherche");
        setFontTitre(titre);
        add(titre, new GridBagConstraints(0, 0, 1, 1, 1, 0, GridBagConstraints.NORTH,
                GridBagConstraints.NONE, new Insets(0, 0, 30, 0), 0, 0));

        JTextField barRecherche = new JTextField();
        barRecherche.setBorder(BorderFactory.createEmptyBorder());
        add(barRecherche, new GridBagConstraints(0, 1, 1, 1, 1, 0, GridBagConstraints.NORTH,
                GridBagConstraints.BOTH, new Insets(0, 0, 30, 10), 0, 0));

        JButton boutonRechercher = new JButton("Rechercher");
        boutonRechercher.setBackground(Color.decode("#594249"));
        add(boutonRechercher, new GridBagConstraints(1, 1, 1, 1, 0, 0, GridBagConstraints.NORTH,
                GridBagConstraints.NONE, new Insets(0, 0, 30, 0), 0, 0));

        add(new ScrollBarComposant(liste, this), new GridBagConstraints(0, 3, 2, 1, 1, 1, GridBagConstraints.NORTH,
                GridBagConstraints.BOTH, new Insets(0, 0, 10, 0), 0, 0));

        boutonRechercher.addActionListener((event) -> {
            List<Twit> twits = this.getController().getTwitsRecherche(barRecherche.getText());
            liste = new ListeTwitsComposant(twits);

            removeAll();
            init();
            validate();
        });
    }

    @Override
    public RechercheController getController(){
        return ((RechercheController)super.getController());
    }
}
