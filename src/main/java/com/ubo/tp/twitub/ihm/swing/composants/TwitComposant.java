package main.java.com.ubo.tp.twitub.ihm.swing.composants;

import main.java.com.ubo.tp.twitub.datamodel.Twit;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import static main.java.com.ubo.tp.twitub.utils.Font.gras;

public class TwitComposant extends JPanel {

    private Twit twit;

    public TwitComposant(Twit twit){
        this.twit = twit;
        init();
    }

    private void init() {
        setLayout(new GridBagLayout());

        JLabel nomUser = new JLabel(twit.getTwiter().getName());
        gras(nomUser);
        JLabel tagUser = new JLabel("@"+twit.getTwiter().getUserTag());

        Date date = new Date(twit.getEmissionDate());

        String pattern = "dd/MM/yyyy hh:mm";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        JLabel dateTwit = new JLabel(simpleDateFormat.format(date));

        JTextArea texteTwit = new JTextArea(twit.getText());
        texteTwit.setBackground(Color.decode("#594249"));

        texteTwit.setEditable(false);
        texteTwit.setLineWrap(true);


        add(nomUser, new GridBagConstraints(0, 0, 1, 1, 0, 1, GridBagConstraints.NORTHWEST,
                GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 2, 0));
        add(tagUser, new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.NORTHWEST,
                GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 150), 0, 0));
        add(dateTwit, new GridBagConstraints(2, 0, 1, 1, 0, 1, GridBagConstraints.NORTHWEST,
                GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
        add(texteTwit, new GridBagConstraints(0, 1, 3, 0, 1, 1, GridBagConstraints.NORTHWEST,
                GridBagConstraints.BOTH, new Insets(5, 0, 0, 0), 0, 0));
    }
}
