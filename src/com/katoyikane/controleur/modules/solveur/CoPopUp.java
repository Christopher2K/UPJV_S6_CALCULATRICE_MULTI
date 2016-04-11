package com.katoyikane.controleur.modules.solveur;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;

/*
 * Controleur du pop UP d'affichage de la solution de l'équation
 */
public class CoPopUp
{
    /**
     * Attributs de classe
     */
    /*
    Liste des composants graphiques présent dans ce pop-up
    En cas de modification, consulter : src/com.katoyikane/vue/modules/module4_resolution.fxml
     */
    @FXML private TextField affichage_equation;
    @FXML private Label affichage_inconnue;
    @FXML private TextArea affichage_solutions;
    @FXML private Button bt_close;


    /* **********
    *************
    ********** */

    /**
     * GETTERS & SETTERS
     */
    public TextField getAffichage_equation() {
        return affichage_equation;
    }

    public Label getAffichage_inconnue() {
        return affichage_inconnue;
    }

    public TextArea getAffichage_solutions() {
        return affichage_solutions;
    }

    /**
     * Méthodes invoquées lors des clics
     * L'attribution des écouteurs à un composant se fait dans : src/com.katoyikane/vue/modules/module4_resolution.fxml
     */
    @FXML public void btCloseClic()
    {
        Stage stage = (Stage) bt_close.getScene().getWindow();
        stage.close();
    }
}
