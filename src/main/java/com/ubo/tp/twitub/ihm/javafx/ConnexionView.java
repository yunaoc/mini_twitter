package main.java.com.ubo.tp.twitub.ihm.javafx;

import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import main.java.com.ubo.tp.twitub.controllers.ConnexionController;
import main.java.com.ubo.tp.twitub.core.Controller;
import main.java.com.ubo.tp.twitub.core.JavaFxView;

public class ConnexionView extends JavaFxView {

    private final ConnexionController monController;

    public ConnexionView(Controller controller) {
        super(controller);
        monController = ((ConnexionController)this.getController());
        init();
    }

    @Override
    public void init(){
        setHgap(30);
        setVgap(10);

        Label titre = new Label("Se connecter");
        setPadding(new Insets(10));
        add(titre, 0, 0, 2, 1);

        Label tagText = new Label("Tag :");
        add(tagText, 0, 1);

        TextField tag = new TextField();
        add(tag, 1, 1);

        Label mdpText = new Label("Mot de passe :");
        add(mdpText, 0, 2);

        PasswordField mdp = new PasswordField();
        add(mdp, 1, 2);


        Label erreur = new Label("");
        erreur.setTextFill(Color.RED);
        add(erreur, 0, 3, 2, 1);


        Button boutonCreerCompte = new Button("Créer un compte");
        EventHandler<ActionEvent> goToCreerCompte = e -> monController.goToCreerCompte();

        boutonCreerCompte.setOnAction(goToCreerCompte);
        add(boutonCreerCompte,0,4);


        Button boutonConnexion = new Button("Connexion");
        EventHandler<ActionEvent> connexion = e -> {
            if(connection(tag.getText(), mdp.getText())){
                monController.goToAccueil();
                tag.setText("");
                mdp.setText("");
                erreur.setText("");
            }else{
                erreur.setText("Erreur, les informations sont incorrectes.");
            }
        };
        boutonConnexion.setOnAction(connexion);
        add(boutonConnexion,1,4);

    }

    private boolean connection(String tag, String mdp) {
        if(tag.equals("") || mdp.length() == 0){
            return false;
        }else{
            return monController.identification(tag,mdp);
        }
    }

}
