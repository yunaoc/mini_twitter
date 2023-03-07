package main.java.com.ubo.tp.twitub.ihm.javafx;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import main.java.com.ubo.tp.twitub.controllers.RechercheController;
import main.java.com.ubo.tp.twitub.core.Controller;
import main.java.com.ubo.tp.twitub.core.JavaFxView;
import main.java.com.ubo.tp.twitub.datamodel.Twit;
import main.java.com.ubo.tp.twitub.ihm.javafx.composants.ListeTwitsComposant;

import java.util.List;

import static main.java.com.ubo.tp.twitub.utils.Panel.refreshPane;


public class RechercheView extends JavaFxView {
    private final RechercheController monController;
    private ScrollPane liste;

    public RechercheView(Controller controller) {
        super(controller);
        monController = (RechercheController) controller;
        liste = new ScrollPane();
        init();
    }

    @Override
    public void init() {

        Label titre = new Label("Recherche");
        add(titre,0,0,2,1);

        TextField barRecherche = new TextField();
        add(barRecherche,0,1);

        Button boutonRechercher = new Button("Rechercher");
        add(boutonRechercher,1,1);

        EventHandler<ActionEvent> rechercher = e -> {
            List<Twit> twits = monController.getTwitsRecherche(barRecherche.getText());
            liste = new ListeTwitsComposant(twits);
            refreshPane(this);
        };
        boutonRechercher.setOnAction(rechercher);
        add(liste,0,2,2,1);
    }
}
