package com.katoyikane.exception;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 * Created by christopher on 07/03/16.
 */
public class LimiteException extends Exception
{
    public LimiteException(String calcul)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR, "Impossible d'effectuer le calcul : " + calcul + ". La limite est dépassée.", ButtonType.CLOSE);
        alert.showAndWait();
    }

}
