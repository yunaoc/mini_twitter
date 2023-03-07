package main.java.com.ubo.tp.twitub.ihm.javafx.composants;

import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.GridPane;
import main.java.com.ubo.tp.twitub.datamodel.Twit;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ListeTwitsComposant extends ScrollPane {
    private final List<Twit> twits;

    public ListeTwitsComposant(List<Twit> twits){
        this.twits = twits;
        init();
    }

    private void init(){
        setHbarPolicy(javafx.scene.control.ScrollPane.ScrollBarPolicy.NEVER);
        setVbarPolicy(javafx.scene.control.ScrollPane.ScrollBarPolicy.AS_NEEDED);
        setPrefSize(400, 400);
        setStyle("-fx-background-color:transparent;");

        GridPane content = new GridPane();
        content.setAlignment(Pos.CENTER);
        content.setHgap(5);
        content.setVgap(15);

        Comparator<Twit> comparator = Comparator.comparingLong(Twit::getEmissionDate);

        twits.sort(comparator);
        Collections.reverse(twits);

        for(int i=0;i<twits.size(); i++){
            content.add(new TwitComposant(twits.get(i)), 0,i);
        }

        this.setContent(content);
    }
}
