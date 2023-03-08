package main.java.com.ubo.tp.twitub.core;

import java.io.File;

import com.formdev.flatlaf.FlatDarculaLaf;
import javafx.stage.Stage;
import main.java.com.ubo.tp.twitub.controllers.JavaFxMainController;
import main.java.com.ubo.tp.twitub.controllers.SwingMainController;
import main.java.com.ubo.tp.twitub.datamodel.Database;
import main.java.com.ubo.tp.twitub.datamodel.DatabaseObserverImpl;
import main.java.com.ubo.tp.twitub.datamodel.IDatabase;
import main.java.com.ubo.tp.twitub.events.file.IWatchableDirectory;
import main.java.com.ubo.tp.twitub.events.file.WatchableDirectory;
import main.java.com.ubo.tp.twitub.ihm.javafx.JavaFxMainView;
import main.java.com.ubo.tp.twitub.ihm.swing.SwingMainView;
import main.java.com.ubo.tp.twitub.ihm.swing.TwitubMock;

import javax.swing.*;

/**
 * Classe principale l'application.
 * 
 * @author S.Lucas
 */
public class Twitub {
	/**
	 * Base de données.
	 */
	protected IDatabase mDatabase;

	/**
	 * Gestionnaire des entités contenu de la base de données.
	 */
	protected EntityManager mEntityManager;

	/**
	 * Vue principale de l'application.
	 */
	protected SwingMainView mMainView;
	protected JavaFxMainView mJavaFxMainView;

	/**
	 * Classe de surveillance de répertoire
	 */
	protected IWatchableDirectory mWatchableDirectory;

	/**
	 * Répertoire d'échange de l'application.
	 */
	protected String mExchangeDirectoryPath;

	/**
	 * Idnique si le mode bouchoné est activé.
	 */
	protected boolean mIsMockEnabled = false;

	/**
	 * Idnique si le mode bouchoné est activé.
	 */
	protected boolean isSwing;

	/**
	 * Nom de la classe de l'UI.
	 */
	protected String mUiClassName;

	protected Stage primaryStage;

	/**
	 * Constructeur.
	 */
	public Twitub(Boolean isSwing) {
		this.isSwing = isSwing;
	}

	/**
	 * Initialisation du look and feel de l'application.
	 */
	protected void initLookAndFeel() {
		try {
			UIManager.setLookAndFeel( new FlatDarculaLaf() );
		} catch( Exception ex ) {
			System.err.println( "Failed to initialize LaF" );
		}
	}

	/**
	 * Initialisation de l'interface graphique.
	 */
	protected void initGui() {
		// this.mMainView...
	}

	/**
	 * Initialisation du répertoire d'échange (depuis la conf ou depuis un file
	 * chooser). <br/>
	 * <b>Le chemin doit obligatoirement avoir été saisi et être valide avant de
	 * pouvoir utiliser l'application</b>
	 */
	protected void initDirectory() {
	}

	/**
	 * Indique si le fichier donné est valide pour servire de répertoire
	 * d'échange
	 * 
	 * @param directory
	 *            , Répertoire à tester.
	 */
	protected boolean isValideExchangeDirectory(File directory) {
		// Valide si répertoire disponible en lecture et écriture
		return directory != null && directory.exists() && directory.isDirectory() && directory.canRead()
				&& directory.canWrite();
	}

	/**
	 * Initialisation du mode bouchoné de l'application
	 */
	protected void initMock() {
		TwitubMock mock = new TwitubMock(this.mDatabase, this.mEntityManager);
		mock.showGUI();
	}

	/**
	 * Initialisation l'application
	 */
	protected void initApp() {
		if(isSwing){
			mMainView = new SwingMainView();
			new SwingMainController(this.mDatabase, this.mEntityManager, mMainView);
		}else{
			mJavaFxMainView = new JavaFxMainView(getPrimaryStage());
			new JavaFxMainController(this.mDatabase, this.mEntityManager, mJavaFxMainView);
		}
	}

	/**
	 * Initialisation de la base de données
	 */
	protected void initDatabase() {
		mDatabase = new Database();
		mEntityManager = new EntityManager(mDatabase);
		mDatabase.addObserver(new DatabaseObserverImpl());
	}

	/**
	 * Initialisation du répertoire d'échange.
	 * 
	 * @param directoryPath
	 */
	public void initDirectory(String directoryPath) {
		mExchangeDirectoryPath = directoryPath;
		mWatchableDirectory = new WatchableDirectory(directoryPath);
		mEntityManager.setExchangeDirectory(directoryPath);

		mWatchableDirectory.initWatching();
		mWatchableDirectory.addObserver(mEntityManager);
	}

	public void show() {
		// Init du look and feel de l'application
		this.initLookAndFeel();

		// Initialisation de la base de données
		this.initDatabase();

		if (this.mIsMockEnabled) {
			// Initialisation du bouchon de travail
			this.initMock();
		}
		this.initApp();


		// Initialisation de l'IHM
		this.initGui();

		// Initialisation du répertoire d'échange
		this.initDirectory();
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
}
