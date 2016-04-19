package com.katoyikane.exception;

import javafx.scene.control.Alert;

/**
 * Created by christopher on 10/04/16.
 */
/*
Exception généré par CoSolveurEquation.btValiderClic dans le cas ou l'équation ne serait pas solvable
 */
public class EquationException extends Exception
{
    Alert alert;
    public EquationException(int type)
    {
        switch (type)
        {
            case 0 :
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur : equation");
                alert.setHeaderText("Equation insolvable");
                alert.setContentText("L'algorithme de ce solveur ne permet pas la résolution de cette équation");
                alert.showAndWait();
                break;
            case 1 :
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur : equation");
                alert.setHeaderText("Equation impossible");
                alert.setContentText("Cette equation est impossible à résoudre.");
                alert.showAndWait();
                break;
        }
    }
}
