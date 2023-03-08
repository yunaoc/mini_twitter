package main.java.com.ubo.tp.twitub;

import javafx.application.Application;
import javafx.stage.Stage;
import main.java.com.ubo.tp.twitub.core.Twitub;

import java.applet.Applet;

/**
 * Classe de lancement de l'application.
 * 
 * @author S.Lucas
 */
public class TwitubLauncher extends Application {

	/**
	 * Launcher.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Twitub twitubSwing = new Twitub(true);
		twitubSwing.show();


		Twitub twitubFx = new Twitub(false);
		twitubFx.setPrimaryStage(primaryStage);
		twitubFx.show();
	}
}
