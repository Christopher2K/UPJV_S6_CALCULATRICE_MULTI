package com.katoyikane.exception;

import javafx.scene.control.Alert;

/**
 * Created by christopher on 27/03/16.
 */
public class InformationMissingException extends Exception
{
    Alert alert;

    public InformationMissingException(int type)
    {
        switch (type)
        {
            case 1 :
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Information manquante");
                alert.setHeaderText("Fonction non renseignée");
                alert.setContentText("Le fonction doit être impérativement renseignée.");
                alert.showAndWait();
                break;
            case 2 :
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Information manquante");
                alert.setHeaderText("Inconnue non renseignée");
                alert.setContentText("L'inconnue doit être impérativement renseignée.");
                alert.showAndWait();
                break;
            case 3 :
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Information manquante");
                alert.setHeaderText("Invervalles non renseignées");
                alert.setContentText("Les intervalles doivent être impérativement renseignées.");
                alert.showAndWait();
                break;
            case 4 :
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Information manquante");
                alert.setHeaderText("Pas non renseigné");
                alert.setContentText("Le pas doit être impérativement renseigné.");
                alert.showAndWait();
                break;
            case 5 :
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Information manquante");
                alert.setHeaderText("Membres de l'équation non renseignés");
                alert.setContentText("Les deux membres de l'équations doivent être renseignée même si l'un est nul.");
                alert.showAndWait();
        }
    }
}
