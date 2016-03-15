package com.katoyikane.vue.popup;

import com.katoyikane.controleur.popup.CoPopUpTableau;
import com.sun.tools.javac.util.Name;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.util.ArrayList;

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
    public static void afficher(ArrayList<Integer> indices ,ArrayList<String> resultats) throws Exception
    {
        //Création d'une fenêtre vide
        Stage fenetreTableau = new Stage();
        Parent root;
        //Ajout du layout FXML
        FXMLLoader fxmlLoader = new FXMLLoader(PopUpTableau.class.getResource("../modules/module3_tableau.fxml"));
        root = (Parent) fxmlLoader.load();

        //Récupération du controleur
        CoPopUpTableau controller  = fxmlLoader.getController();
        controller.setIndices(indices);
        controller.setResultats(resultats);

        //Invoquation des méthodes d'affichage des données du controller



        //Création de la scene
        Scene scene = new Scene(root);
        fenetreTableau.setResizable(false);
        fenetreTableau.setTitle("Tableau de valeur de la fonction");
        fenetreTableau.setScene(scene);

        fenetreTableau.showAndWait();
    }
}
