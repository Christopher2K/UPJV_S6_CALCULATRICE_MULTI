package com.katoyikane.vue.popup;

import com.katoyikane.controleur.modules.solveur.CoPopUp;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * Created by christopher on 11/04/16.
 */
public class PopUpSolveur
{
    //Méthode d'affichage de la fenêtre
    public static void afficher(String equation, String inconnue, ArrayList<String> solutions) throws Exception
    {
        //Création d'une fenêtre vide
        Stage popUp = new Stage();
        //Ajout du layout FXML
        FXMLLoader fxmlLoader = new FXMLLoader(PopUpSolveur.class.getResource("../modules/module4_resolution.fxml"));
        Parent root = (Parent) fxmlLoader.load();


        //Chargement du controleur
        CoPopUp controller = fxmlLoader.getController();

        //Affichage des résultats
        controller.getAffichage_equation().setText(equation);
        controller.getAffichage_inconnue().setText("Inconue : " + inconnue);
        String temp = "";
        int cpt = 0;
        for (String s : solutions)
        {
            cpt++;
            temp += "Solution " + cpt + ": " + s + "\n";
        }
        controller.getAffichage_solutions().setText(temp);

        //Création de la scene
        final Scene scene = new Scene(root);
        popUp.setResizable(false);
        popUp.setTitle("Module : Solveur d'équation");
        popUp.setScene(scene);
        popUp.showAndWait();
    }
}
