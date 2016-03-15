package com.katoyikane.exception;

import javafx.scene.control.Alert;

/**
 * Created by christopher on 15/03/16.
 */
public class PasException extends Exception
{
    Alert alert ;

    public PasException(int type)
    {
        switch (type)
        {
            case 1 :
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur : pas");
                alert.setHeaderText("Pas manquant.");
                alert.setContentText("Le pas doit être impérativement renseigné.");
                alert.showAndWait();
                break;
            case 2 :
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur : pas");
                alert.setHeaderText("Mauvaise entrée.");
                alert.setContentText("Le pas doit être impérativement un nombre entier ou décimal.");
                alert.showAndWait();
                break;
            case 3 :
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur : pas");
                alert.setHeaderText("Trop grand.");
                alert.setContentText("Le pas est plus grand que la différence des intervalles.");
                alert.showAndWait();
                break;
            case 4 :
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur : pas");
                alert.setHeaderText("Pas illégal.");
                alert.setContentText("Le pas doit être strictement positif.");
                alert.showAndWait();
                break;
        }
    }
}
