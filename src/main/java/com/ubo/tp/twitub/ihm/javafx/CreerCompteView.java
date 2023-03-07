package main.java.com.ubo.tp.twitub.ihm.javafx;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import main.java.com.ubo.tp.twitub.controllers.ConnexionController;
import main.java.com.ubo.tp.twitub.controllers.CreerCompteController;
import main.java.com.ubo.tp.twitub.core.Controller;
import main.java.com.ubo.tp.twitub.core.JavaFxView;

public class CreerCompteView extends JavaFxView {
    private final CreerCompteController monController;

    public CreerCompteView(Controller controller) {
        super(controller);
        monController = ((CreerCompteController)this.getController());
        init();
    }

    @Override
    public void init(){
        Label titre = new Label("Créer un compte");
        setPadding(new Insets(10));
        setHgap(30);
        setVgap(10);
        add(titre, 0, 0, 2, 1);

        Label tagText = new Label("Tag (*):");
        add(tagText, 0, 1);

        TextField tag = new TextField();
        add(tag, 1, 1);

        Label nomText = new Label("Nom (*):");
        add(nomText, 0, 2);

        TextField nom = new TextField();
        add(nom, 1, 2);

        Label mdpText = new Label("Mot de passe (*):");
        add(mdpText, 0, 3);

        PasswordField mdp = new PasswordField();
        add(mdp, 1, 3);

        Label cmdpText = new Label("Confirmation mot de passe (*):");
        add(cmdpText, 0, 4);

        PasswordField cmdp = new PasswordField();
        add(cmdp, 1, 4);


        Label erreur = new Label("");
        erreur.setTextFill(Color.RED);
        add(erreur, 0, 5, 2, 1);


        Button boutonAnnuler = new Button("Annuler");
        EventHandler<ActionEvent> goToConnexion = e -> monController.goToConnexion();
        boutonAnnuler.setOnAction(goToConnexion);
        add(boutonAnnuler,0,6);

        Button boutonValider = new Button("Valider");
        EventHandler<ActionEvent> creerCompte = e -> {
            if(tag.getText().equals("") || nom.getText().equals("") || (mdp.getText()).equals("") ||  (cmdp.getText()).equals("")){
                erreur.setText("Erreur, un ou plusieurs des champs obligatoires est/sont vide(s).");
            }else if(monController.userExist(tag.getText())){
                erreur.setText("Erreur, le tag est déjà pris.");

            }else if (!(mdp.getText()).equals((cmdp.getText()))){
                erreur.setText("Erreur, les mots de passes sont différents.");
            }else{
                monController.createUser(tag.getText(), nom.getText(), (mdp.getText()));
                tag.setText("");
                nom.setText("");
                mdp.setText("");
                cmdp.setText("");
                erreur.setText("");
                monController.goToConnexion();
            }
        };
        boutonValider.setOnAction(creerCompte);
        add(boutonValider,1,6);

    }
}
