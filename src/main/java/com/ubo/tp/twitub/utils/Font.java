package main.java.com.ubo.tp.twitub.utils;

import javax.swing.*;

public class Font {

    public static  void setFontTitre(JLabel titre) {
        java.awt.Font f = titre.getFont();
        titre.setFont(f.deriveFont(f.getStyle() | java.awt.Font.BOLD,18));
    }

    public static  void gras(JLabel texte) {
        java.awt.Font f = texte.getFont();
        texte.setFont(f.deriveFont(f.getStyle() | java.awt.Font.BOLD));
    }
}
