package com.katoyikane.controleur.modules.solveur;

import com.katoyikane.exception.*;
import com.katoyikane.modele.modules.solveur.MoSolveurEquation;
import com.katoyikane.vue.popup.PopUpSolveur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.Optional;

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
    @FXML private Button bt_export_fichier ;
    @FXML private TextField saisie_pt_gauche ;
    @FXML private TextField saisie_pt_droite ;
    @FXML private TextField saisie_inconnue ;
    @FXML private TextField vue_latex ;

    private MoSolveurEquation modele = new MoSolveurEquation();
    private Alert alert;

    /* **********
    *************
    ********** */


    /**
     * Méthodes invoquées lors des clics
     * L'attribution des écouteurs à un composant se fait dans : src/com.katoyikane/vue/modules/module4_solveur.fxml
     */
    //Méthode invoquée lors du clic sur le bouton de vérification de l'ensemble de l'équation
    @FXML private void btValiderClic(ActionEvent event) throws SyntaxeFonctionException, InformationMissingException, InconnueException, EquationException
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
            modele.setSolution(modele.resolution());
            modele.genererListeSolutions();

            //Activation des boutons
            bt_export_fichier.setDisable(false);
            bt_resolution.setDisable(false);
        }
        catch (SyntaxeFonctionException e)      {reset();}
        catch (InformationMissingException e)   {reset();}
        catch (InconnueException e)             {reset();}
        catch (EquationException e)             {reset();}


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
        bt_resolution.setDisable(true);
    }

    //Méthode invoquée lors d'un clic sur un des deux bouton de résolution
    @FXML private void btResolutionClic(ActionEvent event) throws Exception
    {
        //Invocation de la méthode d'affichage du popUp
        PopUpSolveur.afficher(modele.getEquation(), modele.getInconnue(), modele.getListeSolutions());
    }

    //Méthode invoquées lors d'un clic sur le bouton permettant l'export des données dans un fichier
    @FXML private void btExportClic(ActionEvent event) throws IOException
    {
        //Ouverture de l'alerte pour savoir s'il s'agit d'une création ou d'un ajout à un fichier
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Choix de la méthode d'export");
        alert.setHeaderText("Nouveau ou ajouter à fichier existant ?");
        alert.setContentText("Voulez vous ouvrir un fichier existant pour l'export ou utiliser un fichier existant ?");
        ButtonType creer = new ButtonType("Créer");
        ButtonType ouvrir = new ButtonType("Ouvrir");
        ButtonType annuler = new ButtonType("Annuler", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(creer, ouvrir, annuler);
        Optional<ButtonType> resultat = alert.showAndWait();

        if (resultat.get() == creer)
        {
            alert = new Alert(Alert.AlertType.INFORMATION);
            modele.creationExport(((Node)event.getTarget()).getScene().getWindow());
            alert.setTitle("Boite de confirmation");
            alert.setHeaderText("Fichier créé.");
            alert.setContentText("Le fichier a bien été créé.");
            alert.showAndWait();
        }
        else
        {
            alert = new Alert(Alert.AlertType.INFORMATION);
            modele.ouvertureExport(((Node)event.getTarget()).getScene().getWindow());
            alert.setTitle("Boite de confirmation");
            alert.setHeaderText("Fichier ouvert.");
            alert.setContentText("Le fichier a bien été ouvert.");
            alert.showAndWait();
        }

        //Formatage et export
        modele.export();
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
        bt_resolution.setDisable(true);
    }

}
