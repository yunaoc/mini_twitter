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
	private static Twitub twitub;

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
		twitub = new Twitub();
		twitub.setPrimaryStage(primaryStage);
		twitub.show();
	}
}
