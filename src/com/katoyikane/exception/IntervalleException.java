package com.katoyikane.exception;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 * Created by christopher on 13/03/16.
 */
public class IntervalleException extends Exception
{
    public IntervalleException(int type)
    {
        if (type == 1)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Les intervalles doivent être des nombres entiers.", ButtonType.CLOSE);
            alert.showAndWait();
        }
        if (type == 2)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR, "L'intervalle de gauche ne peux pas être plus grand que l'intervalle de droite.", ButtonType.CLOSE);
            alert.showAndWait();
        }
    }

}
