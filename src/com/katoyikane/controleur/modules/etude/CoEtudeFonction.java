package com.katoyikane.controleur.modules.etude;

import com.katoyikane.exception.*;
import com.katoyikane.modele.modules.etude.MoEtudeFonction;
import com.katoyikane.vue.popup.PopUpCourbe;
import com.katoyikane.vue.popup.PopUpTableau;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import org.matheclipse.core.eval.ExprEvaluator;
import org.matheclipse.core.interfaces.IExpr;
import org.matheclipse.parser.client.SyntaxError;

/**
 * Created by christopher on 07/02/16.
 */
/*
Controleur du troisième onglet de l'application : Etude de fonction
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
    @FXML private Button    bt_verifier ;
    @FXML private Button    bt_reset ;
    @FXML private Button    bt_courbe ;
    @FXML private Button    bt_tableau ;
    @FXML private TextField saisie_fonction ;
    @FXML private TextField saisie_intervalle_inf ;
    @FXML private TextField saisie_intervalle_sup ;
    @FXML private TextField saisie_pas ;
    @FXML private TextField saisie_inconnue ;
    @FXML private TextField vue_latex ;

    private MoEtudeFonction modele      = new MoEtudeFonction();   //Instance du modèle d'étude de fonction
    private String          inconnue    = "";

    /* **********
    *************
    ********** */

    /**
     * Méthodes invoquées lors des clics
     * L'attribution des écouteurs à un composant se fait dans : src/com.katoyikane/vue/modules/module3_etude.fxml
     */
    //Méthode invoquée lors d'un clic sur le bouton de vérification
    @FXML private void btVerifierClic(ActionEvent event) throws IntervalleException, InconnueException, SyntaxeFonctionException, PasException
    {
        //On réinitialise le modèle
        modele = new MoEtudeFonction();
        try
        {
            //Vérification de l'expression
            modele.verifierSyntaxe(saisie_fonction.getText());
            modele.verifierInconnueExpression();

            //Vérification de l'inconnue
            modele.verifierInconnue(saisie_inconnue.getText(), saisie_fonction.getText());

            //Vérification des intervalles et du pas
            modele.verifierIntervalles(saisie_intervalle_inf.getText(), saisie_intervalle_sup.getText());
            modele.verifierPas(saisie_pas.getText());


            //On demande au modèle de traiter les données
            //modele.calcul();

            //On affiche le code laTex correspondant à l'expression
            vue_latex.setText(modele.generationLatex());
        }
        catch (InconnueException e)         {modele.reset();}
        catch (SyntaxeFonctionException e)  {modele.reset();}
        catch (IntervalleException e)       {modele.reset();}
        catch (PasException e)              {modele.reset();}

    }

    //Méthode invoquée lors d'un clic sur le bouton de réinitialisation
    @FXML private void btResetClic(ActionEvent event)
    {
        //On remet tous les champs à zéro.
        vue_latex.setText(null);
        saisie_intervalle_sup.setText("");
        saisie_intervalle_inf.setText("");
        saisie_fonction.setText("");
        modele.reset();
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
        PopUpTableau.afficher(modele.getIndices(), modele.getResultats());
    }

    /* **********
    *************
    ********** */

    /**
     * Méthodes propres au controleur
     */



}
