package main.java.com.ubo.tp.twitub.ihm.swing.composants;

import javax.swing.*;

public class ScrollBarComposant extends JScrollPane {


    private final JPanel parent;

    public ScrollBarComposant(JPanel panel, JPanel parent){
        super(panel);
        this.parent = parent;
        init();
    }

    private void init(){
        setBorder(BorderFactory.createEmptyBorder());
        setPreferredSize(parent.getPreferredSize());
        setMaximumSize(parent.getPreferredSize());
        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

    }
}
