package main.java.com.ubo.tp.twitub.ihm.swing;

import main.java.com.ubo.tp.twitub.core.SwingView;
import main.java.com.ubo.tp.twitub.model.IModelObserver;
import main.java.com.ubo.tp.twitub.model.Session;

import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * Classe de la vue principale de l'application.
 */
public class SwingMainView implements IModelObserver {

    /**
     * Fenetre principale
     */
    protected JFrame mFrame;

    private Dimension frameSize;
    /**
     * Contenu de la fenêtre
     */
    protected JPanel mPanel;

    protected JPanel menu;

    public SwingMainView() {
    }

    /**
     * Lance l'affichage de l'IHM.
     */
    public void showGUI(SwingView menu) {
        // Init auto de l'IHM au cas ou ;)
        if (mFrame == null) {
            this.initGUI(menu);
        }

        // Affichage dans l'EDT
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Custom de l'affichage
                JFrame frame = SwingMainView.this.mFrame;
                Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
                frameSize = new Dimension(screenSize.width/3,screenSize.height/2);
                frame.setSize(frameSize);
                frame.setPreferredSize(frameSize);
                frame.setLocationRelativeTo(null);
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

                // Affichage
                frame.setVisible(true);

                frame.pack();
            }
        });
    }

    /**
     * Initialisation de l'IHM
     */
    protected void initGUI(SwingView menu) {
        // Création de la fenetre principale
        mFrame = new JFrame("Mini Twitter");
        mFrame.setLayout(new GridBagLayout());
        setMenuFrame();

        this.menu = menu;
        mFrame.getContentPane().add(this.menu, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.NORTH,
                GridBagConstraints.BOTH, new Insets(0, 0, 10, 0), 0, 0));
        this.menu.setVisible(false);

        mPanel = new JPanel(new GridBagLayout());

        mFrame.getContentPane().add(mPanel, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.NORTH,
                GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
    }



    public void goToView(SwingView swingView){
        reset();
        print(swingView);
    }

    private void print(SwingView swingView) {
        swingView.setPreferredSize(new Dimension((frameSize.width/6)*5,(frameSize.height/8)*6));
        mPanel.add(swingView,  new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        mFrame.revalidate();
        mFrame.repaint();
    }

    private void reset() {
        mPanel.removeAll();
        mFrame.revalidate();
        mFrame.repaint();
    }

    private void setMenuFrame() {
        String messageAPropos = "UBO TIIL-A 2 Master Informatique, MiniTwit par Yuna et Loreleï <3";
        ImageIcon iconUbo = new ImageIcon("src/main/resources/images/logo_50.jpg");
        ImageIcon iconExit = new ImageIcon("src/main/resources/images/exitIcon_20.png");
        ImageIcon iconEdit = new ImageIcon("src/main/resources/images/editIcon_20.png");
        JMenuBar menuBar = new JMenuBar();
        mFrame.setJMenuBar(menuBar);

        JMenu menuFichier = new JMenu("Fichier");
        menuBar.add(menuFichier);

        JMenuItem exit = new JMenuItem("Quitter", iconExit);
        exit.addActionListener(arg0 -> {
            mFrame.setVisible(false);
            mFrame.dispose();
        });
        exit.setToolTipText("Quitter l'application");
        menuFichier.add(exit);

        JMenu menuAbout = new JMenu("?");
        JMenuItem menuAPropos = new JMenuItem("A propos");
        menuBar.add(menuAbout);
        menuAbout.add(menuAPropos);
        menuAPropos.addActionListener(arg0 -> {
            JLabel label = new JLabel(messageAPropos);
            label.setHorizontalAlignment(SwingConstants.CENTER);
            JOptionPane.showMessageDialog(mFrame, label, "À propos", JOptionPane.PLAIN_MESSAGE, iconUbo);
        });
    }

    public String setExchangeRepository() {
        JFileChooser jfc = new JFileChooser("echanges");
        jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int returnValue = jfc.showOpenDialog(mFrame);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = jfc.getSelectedFile();
            if( selectedFile.exists() && selectedFile.isDirectory() && selectedFile.canWrite()){
                return selectedFile.getAbsolutePath();
            }else{
                System.out.println("Erreur, le dossier sélectionné n'est pas valide, veuillez réessayer.");
                setExchangeRepository();
            }
        }else if (returnValue == JFileChooser.CANCEL_OPTION )  {
            System.exit(0);
        }
        return null;
    }

    public void abonnerSession(Session session){
        session.addObservers(this);
    }

    @Override
    public void notifyUserConnected() {
        this.menu.setVisible(true);
        this.menu.validate();
    }

    @Override
    public void notifyDisconnect() {
        this.menu.setVisible(false);
        this.menu.validate();
    }
}