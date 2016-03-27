package com.katoyikane.exception;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 * Created by christopher on 13/03/16.
 */
public class SyntaxeFonctionException extends Exception
{
    public SyntaxeFonctionException()
    {
        Alert alert = new Alert(Alert.AlertType.ERROR, "Erreur de syntaxe de l'expression. Vérifiez si vos fonctions sont correctement orthographiées.", ButtonType.CLOSE);
        alert.showAndWait();
    }
}
