package com.katoyikane.controleur.modules.formel;

import com.katoyikane.exception.InconnueException;
import com.katoyikane.exception.InformationMissingException;
import com.katoyikane.exception.SyntaxeFonctionException;
import com.katoyikane.modele.modules.formel.MoCalculDerivee;
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
Controleur du cinquième onglet de l'application : Calcul de fonction dérivée
 */
public class CoCalculDerivee
{
    /**
     * Attributs de classe
     */
    /*
    Liste des composants graphiques présent dans cet onglet
    En cas de modification, consulter : src/com.katoyikane/vue/modules/module5_derivee.fxml
     */
    @FXML private Button bt_valider ;
    @FXML private Button bt_reset ;
    @FXML private Button bt_deriver ;
    @FXML private TextField saisie_expression ;
    @FXML private TextField saisie_inconnue ;
    @FXML private TextField vue_latex ;
    @FXML private TextField vue_latex_resultat ;
    @FXML private TextField affichage_resultat;

    private MoCalculDerivee modele = new MoCalculDerivee() ;            //Instance du modèle de calcul de dérivée

    /* **********
    *************
    ********** */

    /**
     * Méthodes invoquées lors des clics
     * L'attribution des écouteurs à un composant se fait dans : src/com.katoyikane/vue/modules/module5_derivee.fxml
     */
    //Méthode invoquuée lors du clic sur le bouton de vérification
    @FXML private void btValiderClic(ActionEvent event) throws InformationMissingException, InconnueException,
            SyntaxeFonctionException
    {
		try
		{
			//Verification et affectation des valeurs
			modele.verifierIntegrite(saisie_expression.getText(), saisie_inconnue.getText());
			modele.verifierSyntaxe(saisie_expression.getText());
			modele.verifierInconnueExpression(saisie_expression.getText());
			modele.verifierInconnue(saisie_inconnue.getText(), saisie_expression.getText());
			vue_latex.setText(modele.generationLatex(saisie_expression.getText()));
			bt_deriver.setDisable(false);
		}
		catch (InformationMissingException e) { modele = new MoCalculDerivee(); }
		catch (InconnueException e) { modele = new MoCalculDerivee(); }
		catch (SyntaxeFonctionException e) { modele = new MoCalculDerivee(); }


    }

    //Méthodée invoquée lors du clic sur le bouton de réinitialisation
    @FXML private void btResetClic(ActionEvent event)
    {
        //Réinitialisation du modèle
        modele = new MoCalculDerivee();
        //Réinitilisation des champs et des vues
        reset(saisie_expression);
        reset(saisie_inconnue);
        reset(affichage_resultat);
        reset(vue_latex);
        reset(vue_latex_resultat);
		bt_deriver.setDisable(true);
    }

    //Méthode invoquée lors du clic sur le bouton d'affichage de la dérivée
    @FXML private void btDeriverClic(ActionEvent event)
    {
		affichage_resultat.setText(modele.calculDerivee());
		vue_latex_resultat.setText(modele.generationLatex(modele.calculDerivee()));
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

