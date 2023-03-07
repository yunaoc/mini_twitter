package main.java.com.ubo.tp.twitub.ihm.swing.composants;

import main.java.com.ubo.tp.twitub.datamodel.User;

import javax.swing.*;
import java.awt.*;

import static main.java.com.ubo.tp.twitub.utils.Font.gras;

public class UserComposant extends JPanel {
    User user;
    public UserComposant(User user){
        this.user = user;
        init();
    }

    private void init(){
        setLayout(new GridBagLayout());

        ImageIcon image = new ImageIcon("src/main/resources/images/logo_50.jpg");
        JLabel avatar = new JLabel(image);

        JLabel nom = new JLabel(user.getName()+"@"+user.getUserTag());
        gras(nom);

        add(avatar, new GridBagConstraints(0, 0, 1, 1, 0, 1, GridBagConstraints.NORTHWEST,
                GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 2, 2));

        add(nom, new GridBagConstraints(0, 1, 1, 1, 0, 1, GridBagConstraints.NORTHWEST,
                GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 2, 2));


    }
}
