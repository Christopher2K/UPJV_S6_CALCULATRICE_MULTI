package com.katoyikane.vue.popup;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by christopher on 07/02/16.
 */
/**
 * Fenêtre qui apparait lors d'une résolution d'équation du second degré
 * GUI via src/com.katoyikane/vue/modules/module4_eq_degre2.fxml
 */
public class PopUpEqSecondDegre
{
    //Méthode d'affichage de la feneêtre
    public static void afficher() throws Exception
    {
        //Création d'une fenêtre vide
        Stage fenetreCourbe = new Stage();
        Parent root;
        //Ajout du layout FXML
        root = FXMLLoader.load(PopUpTableau.class.getResource("../modules/module4_eq_degre2.fxml"));

        //Création de la scene
        final Scene scene = new Scene(root);
        fenetreCourbe.setResizable(false);
        fenetreCourbe.setTitle("Solution de l'équation");
        fenetreCourbe.setScene(scene);
        fenetreCourbe.showAndWait();
    }
}
