package com.katoyikane.controleur.modules.solveur;

import com.katoyikane.exception.*;
import com.katoyikane.modele.modules.solveur.MoSolveurEquation;
import com.katoyikane.vue.popup.PopUpEqSecondDegre;
import com.katoyikane.vue.popup.PopUpEqSimple;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

/**
 * Created by christopher on 07/02/16.
 */
/*
Controleur du quatrième onglet de l'application : Solveur d'équations
 */
public class CoSolveurEquation
{
    /**
     * Attributs de classe
     */
    /*
    Liste des composants graphiques présent dans cet onglet
    En cas de modification, consulter : src/com.katoyikane/vue/modules/module4_solveur.fxml
     */
    @FXML private Button bt_valider ;
    @FXML private Button bt_reset ;
    @FXML private Button bt_resolution;
    @FXML private Button bt_img ;
    @FXML private Button bt_export_fichier ;
    @FXML private TextField saisie_pt_gauche ;
    @FXML private TextField saisie_pt_droite ;
    @FXML private TextField saisie_inconnue ;
    @FXML private TextField vue_latex ;

    private MoSolveurEquation modele = new MoSolveurEquation();

    /* **********
    *************
    ********** */


    /**
     * Méthodes invoquées lors des clics
     * L'attribution des écouteurs à un composant se fait dans : src/com.katoyikane/vue/modules/module4_solveur.fxml
     */
    //Méthode invoquée lors du clic sur le bouton de vérification de l'ensemble de l'équation
    @FXML private void btValiderClic(ActionEvent event) throws SyntaxeFonctionException, InformationMissingException, InconnueException
    {
        try
        {
            //Vérification de l'intégrité de des données
            modele.verifierIntegrite(
                    saisie_pt_gauche.getText(),
                    saisie_pt_droite.getText(),
                    saisie_inconnue.getText()
            );

            //Vérification des membres de l'équations
            modele.verifierSyntaxe(saisie_pt_gauche.getText());
            modele.verifierSyntaxe(saisie_pt_droite.getText());
            modele.getInconnueExpression(saisie_pt_gauche.getText());
            modele.getInconnueExpression(saisie_pt_droite.getText());

            //Vérification de l'inconnue
            modele.verifierEntreeInconnue(saisie_inconnue.getText());

            //Ajout de l'équation au modele
            modele.setEquation(saisie_pt_gauche.getText() + "==" + saisie_pt_droite.getText());
            modele.setInconnue(saisie_inconnue.getText());

            //Affichage Latex
            vue_latex.setText(modele.generationLatex());

            //Résolution de l'équation
            System.out.println(modele.resolution());


            //Activation des boutons
            bt_export_fichier.setDisable(false);
            bt_img.setDisable(false);
            bt_resolution.setDisable(false);
        }
        catch (SyntaxeFonctionException e)      {reset();}
        catch (InformationMissingException e)   {reset();}
        catch (InconnueException e)             {reset();}

    }

    //Méthode invoquée lors d'un clic sur le bouton de réinitialisation
    @FXML private void btResetClic(ActionEvent event)
    {
        modele = new MoSolveurEquation();
        reset(saisie_pt_gauche);
        reset(saisie_pt_droite);
        reset(saisie_inconnue);
        reset(vue_latex);

        bt_export_fichier.setDisable(true);
        bt_img.setDisable(true);
        bt_resolution.setDisable(true);
    }

    //Méthode invoquée lors d'un clic sur un des deux bouton de résolution
    @FXML private void btResolutionClic(ActionEvent event) throws Exception
    {

    }

    //Méthode invoquée lors d'un clic sur le bouton permettant d'afficher une image LaTex
    @FXML private void btLatexImgClic(ActionEvent event)
    {

    }

    //Méthode invoquées lors d'un clic sur le bouton permettant l'export des données dans un fichier
    @FXML private void btExportClic(ActionEvent event)
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
    public void reset()
    {
        modele.reset();
        bt_export_fichier.setDisable(true);
        bt_img.setDisable(true);
        bt_resolution.setDisable(true);
    }

}
