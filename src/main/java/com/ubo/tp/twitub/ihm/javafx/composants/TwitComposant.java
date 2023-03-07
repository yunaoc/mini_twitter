package main.java.com.ubo.tp.twitub.ihm.javafx.composants;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import main.java.com.ubo.tp.twitub.datamodel.Twit;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TwitComposant extends GridPane {

    private Twit twit;

    public TwitComposant(Twit twit){
        this.twit = twit;
        init();
    }

    private void init(){
        setHgap(5);
        setVgap(5);

        Label nomUser = new Label(twit.getTwiter().getName());
        add(nomUser, 0, 0);
        Label tagUser = new Label("@"+twit.getTwiter().getUserTag());
        add(tagUser, 1, 0);

        Date date = new Date(twit.getEmissionDate());

        String pattern = "dd/MM/yyyy hh:mm";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);


        Label dateTwit = new Label(simpleDateFormat.format(date));
        add(dateTwit, 2, 0);

        Text text = new Text(twit.getText());
        text.setWrappingWidth(300);
        add(text, 1, 1,3,1);

    }
}
