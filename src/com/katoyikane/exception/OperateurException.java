package com.katoyikane.exception;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 * Created by christopher on 27/02/16.
 */
//Exception levée lorsqu'un opérateur est seelctionné sans qu'un nombre ait été entré au préalable.
public class OperateurException extends Exception
{
    public OperateurException(String operateur)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR, "Impossible de sélectionner un opérateur sans qu'un nombre ait été entré.", ButtonType.CLOSE);
        alert.showAndWait();
    }
}
