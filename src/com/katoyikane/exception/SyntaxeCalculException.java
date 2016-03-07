package com.katoyikane.exception;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 * Created by christopher on 29/02/16.
 */
//Exception levée lorsqu'un opérateur est sélectionné et que le bouton = est cliqué juste après
public class SyntaxeCalculException extends Exception
{
    public SyntaxeCalculException(String operateur)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR, "Impossible d'effectuer le calcul, il se termine par un " + operateur + ".", ButtonType.CLOSE);
        alert.showAndWait();
    }
}
