package com.katoyikane.controleur.modules.etude;

import com.katoyikane.modele.modules.etude.MoEtudeFonction;
import com.katoyikane.vue.popup.PopUpCourbe;
import com.katoyikane.vue.popup.PopUpTableau;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

/**
 * Created by christopher on 07/02/16.
 */
public class CoEtudeFonction
{
    /**
     * Attributs de classe
     */
    /*
    Liste des composants graphiques présent dans cet onglet
    En cas de modification, consulter : src/com.katoyikane/vue/modules/module3_etude.fxml
     */
    @FXML private Button bt_verifier ;
    @FXML private Button bt_reset ;
    @FXML private Button bt_courbe ;
    @FXML private Button bt_tableau ;
    @FXML private TextField saisie_fonction ;
    @FXML private TextField saisie_intervalle_inf ;
    @FXML private TextField saisie_intervalle_sup ;
    @FXML private ImageView vue_latex ;

    private MoEtudeFonction modele = new MoEtudeFonction();   //Instance du modèle d'étude de fonction

    /* **********
    *************
    ********** */

    /**
     * Méthodes invoquées lors des clics
     * L'attribution des écouteurs à un composant se fait dans : src/com.katoyikane/vue/modules/module3_etude.fxml
     */
    //Méthode invoquée lors d'un clic sur le bouton de vérification
    @FXML private void btVerifierClic(ActionEvent event)
    {

    }

    //Méthode invoquée lors d'un clic sur le bouton de réinitialisation
    @FXML private void btResetClic(ActionEvent event)
    {
        //On remet tous les champs à zéro.
        vue_latex.setImage(null);
        saisie_intervalle_sup.setText("");
        saisie_intervalle_inf.setText("");
        saisie_fonction.setText("");
    }

    //Méthode invoquée lors d'un clic sur le bouton de génération de courbe
    @FXML private void btCourbeClic(ActionEvent event) throws Exception
    {
        //Affiche le pop-Up contenant la courbe
        PopUpCourbe.afficher();
    }

    //Méthode invoquée lors d'un clic sur le bouton de génération de tableau
    @FXML private void btTableauClic(ActionEvent event) throws Exception
    {
        //Affiche le pop-up contenant le tableau de valeurs
        PopUpTableau.afficher();
    }

    /* **********
    *************
    ********** */

    /**
     * Méthodes propres au controleur
     */


}
