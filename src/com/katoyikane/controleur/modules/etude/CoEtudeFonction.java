package com.katoyikane.controleur.modules.etude;

import com.katoyikane.exception.*;
import com.katoyikane.modele.modules.etude.MoEtudeFonction;
import com.katoyikane.vue.popup.PopUpCourbe;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;

import java.util.Optional;

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
    @FXML private Button    bt_valider;
    @FXML private Button    bt_reset ;
    @FXML private Button    bt_courbe ;
    @FXML private Button    bt_export;
    @FXML private TextField saisie_fonction ;
    @FXML private TextField saisie_intervalle_inf ;
    @FXML private TextField saisie_intervalle_sup ;
    @FXML private TextField saisie_pas ;
    @FXML private TextField saisie_inconnue ;
    @FXML private TextField vue_latex ;

    private MoEtudeFonction modele      = new MoEtudeFonction();   //Instance du modèle d'étude de fonction
    private String          inconnue    = "";
    private Alert           alert;

    /* **********
    *************
    ********** */

    /**
     * Méthodes invoquées lors des clics
     * L'attribution des écouteurs à un composant se fait dans : src/com.katoyikane/vue/modules/module3_etude.fxml
     */
    //Méthode invoquée lors d'un clic sur le bouton de vérification
    @FXML private void btValiderClic(ActionEvent event) throws InformationMissingException, IntervalleException, InconnueException, SyntaxeFonctionException, PasException
    {
        //On réinitialise le modèle
        modele = new MoEtudeFonction();
        try
        {
            //Vérification de l'intégrité des données
            modele.verifierIntegrite(
                    saisie_fonction.getText(),
                    saisie_inconnue.getText(),
                    saisie_intervalle_inf.getText(),
                    saisie_intervalle_sup.getText(),
                    saisie_pas.getText());

            //Vérification de l'expression
            modele.verifierSyntaxe(saisie_fonction.getText());
            modele.verifierInconnueExpression();

            //Vérification de l'inconnue
            modele.verifierInconnue(saisie_inconnue.getText(), saisie_fonction.getText());

            //Vérification des intervalles et du pas
            modele.verifierIntervalles(saisie_intervalle_inf.getText(), saisie_intervalle_sup.getText());
            modele.verifierPas(saisie_pas.getText());


            //On demande au modèle de traiter les données
            modele.calcul();

            //On affiche le code laTex correspondant à l'expression
            vue_latex.setText(modele.generationLatex());

            //On rend les boutons d'options accesibles
            bt_courbe.setDisable(false);
            bt_export.setDisable(false);
        }
        catch (InconnueException e)          {modele.reset();}
        catch (SyntaxeFonctionException e)   {modele.reset();}
        catch (IntervalleException e)        {modele.reset();}
        catch (PasException e)               {modele.reset();}
        catch (InformationMissingException e){modele.reset();}
    }

    //Méthode invoquée lors d'un clic sur le bouton de réinitialisation
    @FXML private void btResetClic(ActionEvent event)
    {
        //On remet tous les champs à zéro.
        vue_latex.setText(null);
        saisie_intervalle_sup.setText("");
        saisie_intervalle_inf.setText("");
        saisie_fonction.setText("");
        saisie_pas.setText("");
        saisie_inconnue.setText("");
        bt_courbe.setDisable(true);
        bt_export.setDisable(true);
        modele.reset();
    }

    //Méthode invoquée lors d'un clic sur le bouton de génération de courbe
    @FXML private void btCourbeClic(ActionEvent event) throws Exception
    {
        //Affiche le pop-Up contenant la courbe
        PopUpCourbe.afficher();
    }

    //Méthode invoquée lors d'un clic sur le bouton de génération de tableau
    @FXML private void btExportClic(ActionEvent event) throws Exception
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



}
