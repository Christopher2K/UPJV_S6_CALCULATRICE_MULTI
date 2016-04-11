package com.katoyikane.vue.popup;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

/**
 * Created by christopher on 07/02/16.
 */
/**
 * Fenêtre qui apparait lors d'une demande d'affichage de courbe lors d'une étude de fonction
 * GUI via src/com.katoyikane/vue/modules/module3_courbe.fxml
 */
public class PopUpCourbe
{
    //Méthode d'affichage de la fenêtre
    public static void afficher() throws Exception
    {
        //Création d'une fenêtre vide
        Stage fenetreCourbe = new Stage();
        Parent root;
        //Ajout du layout FXML
        root = FXMLLoader.load(PopUpCourbe.class.getResource("../modules/module3_courbe.fxml"));

        //Création de la scene
        final Scene scene = new Scene(root);
        fenetreCourbe.setResizable(false);
        fenetreCourbe.setTitle("Courbe de la fonction");
        fenetreCourbe.setScene(scene);
        fenetreCourbe.showAndWait();
    }

    private void tracer()
    {

    }
}
