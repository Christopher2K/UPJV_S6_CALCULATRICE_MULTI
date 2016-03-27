package com.katoyikane.exception;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
//TODO REDIGER LES EXCEPTIONS PAR RAPPORT À CETTE SYNTAXE
/**
 * Created by christopher on 13/03/16.
 */
/*
Exceptions générées par MoEtudefonction.verifierInconnueExpression
 */
public class InconnueException extends Exception
{
    Alert alert ;
    public InconnueException(int type)
    {
        switch (type)
        {
            /*
            Exception générée par MoEtudeFonction.validerInconnue
             */
            case 0 :
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur : inconnue");
                alert.setHeaderText("Inconnue manquante.");
                alert.setContentText("L'inconnue doit être impérativement renseignée.");
                alert.showAndWait();
                break;
            case 1 :
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur : inconnue");
                alert.setHeaderText("Trop de caractères.");
                alert.setContentText("L'inconnue doit être constituée d'une unique lettre.");
                alert.showAndWait();
                break;
            case 2 :
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur : inconnue");
                alert.setHeaderText("Caractère incorrect.");
                alert.setContentText("L'inconnue ne peut pas être autre chose qu'une lettre.");
                alert.showAndWait();
                break;
            case 3 :
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur : inconnue");
                alert.setHeaderText("Inconnue incorrecte.");
                alert.setContentText("L'inconnue renseignée n'est pas la même que celle trouvée dans l'expression");
                alert.showAndWait();
                break;
            case 4 :
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur : inconnue");
                alert.setHeaderText("Inconnue illégale.");
                alert.setContentText("L'inconnue ne peut pas être constituée de la lettre e.");
                alert.showAndWait();
                break;
            case 5:
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur : inconnue");
                alert.setHeaderText("Plusieurs inconnues.");
                alert.setContentText("Il ne peut pas y avoir plus d'une inconnue dans l'expression.");
                alert.showAndWait();
                break;
            case 6:
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur : inconnue");
                alert.setHeaderText("Inconnne inexistante");
                alert.setContentText("L'inconnue entrée ne figure pas dans la liste des inconnues des expression entrées précedement.");
                alert.showAndWait();
                break;
        }
    }
}
