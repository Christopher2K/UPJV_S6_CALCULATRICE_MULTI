package com.katoyikane.controleur.modules.formel;

import com.katoyikane.modele.modules.formel.MoFactorisation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

/**
 * Created by christopher on 08/02/16.
 */
/*
Controleur du sixième onglet de l'application : Calcul de l'expression factorisée
 */
public class CoFactorisation
{
    /**
     * Attributs de classe
     */
    /*
    Liste des composants graphiques présent dans cet onglet
    En cas de modification, consulter : src/com.katoyikane/vue/modules/module5_factorisation.fxml
     */
    @FXML private Button bt_valider ;
    @FXML private Button bt_reset ;
    @FXML private Button bt_factoriser ;
    @FXML private TextField saisie_expression ;
    @FXML private TextField vue_latex ;
    @FXML private TextField vue_latex_resultat ;
    @FXML private TextField affichage_resultat ;

    private MoFactorisation modele = new MoFactorisation() ;            //Instance du modèle de calcul de dérivée

    /* **********
    *************
    ********** */

    /**
     * Méthodes invoquées lors des clics
     * L'attribution des écouteurs à un composant se fait dans : src/com.katoyikane/vue/modules/module5_factorisation.fxml
     */
    //Méthode invoquuée lors du clic sur le bouton de vérification
    @FXML private void btValiderClic(ActionEvent event)
    {

    }

    //Méthodée invoquée lors du clic sur le bouton de réinitialisation
    @FXML private void btResetClic(ActionEvent event)
    {
        //Réinitialisation du modèle
        modele = new MoFactorisation();
        //Réinitilisation des champs et des vues
        reset(saisie_expression);
        reset(vue_latex);
        reset(affichage_resultat);
        reset(vue_latex_resultat);
    }

    //Méthode invoquée lors du clic sur le bouton d'affichage de l'expression factorisée
    @FXML private void btFactoriserClic(ActionEvent event)
    {

    }

    /* **********
    *************
    ********** */

    /**
     * Méthodes propres au controleur
     */
    public void reset(TextField t)
    {
        t.setText("");
    }
}
