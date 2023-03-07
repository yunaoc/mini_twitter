package main.java.com.ubo.tp.twitub.ihm.swing.composants;

import main.java.com.ubo.tp.twitub.datamodel.Twit;

import javax.swing.*;
import java.awt.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ListeTwitsComposant extends JPanel {
    private final List<Twit> twits;

    public ListeTwitsComposant(List<Twit> twits){
        this.twits = twits;
        init();
    }

    private void init(){
        setLayout(new GridBagLayout());
        Comparator<Twit> comparator = Comparator.comparingLong(Twit::getEmissionDate);

        twits.sort(comparator);
        Collections.reverse(twits);

        for(int i=0;i<twits.size(); i++){
            add(new TwitComposant(twits.get(i)), new GridBagConstraints(0, i, 1, 1, 1, 0, GridBagConstraints.NORTH,
                    GridBagConstraints.BOTH, new Insets(0, 0, 20, 0), 0, 0));
        }
    }
}
