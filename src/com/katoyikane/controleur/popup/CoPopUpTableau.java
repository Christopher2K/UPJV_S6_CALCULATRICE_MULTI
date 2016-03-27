package com.katoyikane.controleur.popup;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.ArrayList;

/**
 * Created by christopher on 14/03/16.
 */
/*
Controleur de la fenêtre tableau de valeur du troisième onglet de l'application : Etude de fonction
 */
public class CoPopUpTableau
{
    /*
    Liste des composants graphiques présent dans cet onglet
    En cas de modification, consulter : src/com.katoyikane/vue/modules/module3_tableau.fxml
     */
    @FXML private Label         label;
    @FXML private TableView     table_view;
    @FXML private TableColumn   colonne_inconnus;
    @FXML private TableColumn   colonne_valeurs;

    //Données à afficher
    private ArrayList<Double> indices         = new ArrayList<>();                     //Contient la liste d'indices entrée par l'utilisateur
    private ArrayList<String>   resultats       = new ArrayList<String>();                      //Contient la liste des résultats selon les indices
    /* **********
    *************
    ********** */

    /**
     * GETTERS & SETTERS UTILES
     */
    public ArrayList<Double> getIndices() {
        return indices;
    }

    public void setIndices(ArrayList<Double> indices) {
        this.indices = indices;
    }

    public ArrayList<String> getResultats() {
        return resultats;
    }

    public void setResultats(ArrayList<String> resultats) {
        this.resultats = resultats;
    }

}
