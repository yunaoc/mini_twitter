package main.java.com.ubo.tp.twitub.ihm.javafx.composants;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import main.java.com.ubo.tp.twitub.datamodel.User;

import javax.swing.*;

public class UserComposant extends GridPane {
    User user;
    public UserComposant(User user){
        this.user = user;
        init();
    }

    private void init(){
        Image image = new Image("File:src/main/resources/images/logo_50.jpg");
        add(new ImageView(image), 0,0);
        Label nom = new Label(user.getName()+"@"+user.getUserTag());
        add(nom,0,1);
    }
}
