package com.katoyikane.vue.popup;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by christopher on 07/02/16.
 */
/**
 * Fenêtre qui apparait lors d'une demande d'affichage du tableau de valeur dans l'étude de fonction
 * GUI via src/com.katoyikane/vue/modules/module3_tableau.fxml
 */
public class PopUpTableau
{
    //Méthode d'affichage de la fenêtre
    public static void afficher() throws Exception
    {
        //Création d'une fenêtre vide
        Stage fenetreTableau = new Stage();
        Parent root;
        //Ajout du layout FXML
        root = FXMLLoader.load(PopUpTableau.class.getResource("../modules/module3_tableau.fxml"));

        //Création de la scene
        Scene scene = new Scene(root);
        fenetreTableau.setResizable(false);
        fenetreTableau.setTitle("Tableau de valeur de la fonction");
        fenetreTableau.setScene(scene);
        fenetreTableau.showAndWait();
    }

    private void tracer()
    {

    }
}
