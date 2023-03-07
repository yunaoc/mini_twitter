package main.java.com.ubo.tp.twitub.ihm.javafx;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import main.java.com.ubo.tp.twitub.controllers.CreationTwitController;
import main.java.com.ubo.tp.twitub.core.Controller;
import main.java.com.ubo.tp.twitub.core.JavaFxView;

public class CreationTwitView extends JavaFxView {

    public CreationTwitView(Controller controller) {
        super(controller);
        init();
    }

    @Override
    public void init(){
        setHgap(30);
        setVgap(10);

        Label titre = new Label("Poster un twit");
        add(titre, 0, 0);

        TextArea twit = new TextArea();
        twit.setWrapText(true);
        add(twit, 0, 1);

        Label erreur = new Label("");
        erreur.setTextFill(Color.RED);
        add(erreur, 0, 2);

        Button poster = new Button("Poster");
        EventHandler<ActionEvent> posterAction = e -> {
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
        };
        poster.setOnAction(posterAction);
        add(poster, 0, 3);
    }

    @Override
    public CreationTwitController getController(){
        return ((CreationTwitController)super.getController());
    }
}
