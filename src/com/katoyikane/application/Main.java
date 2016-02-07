package com.katoyikane.application;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

/*
Chargement de l'application via l'appel de la méthode main
 */
public class Main extends Application
{
    public static void main(String args[]){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Stage fenetrePrincipale = primaryStage;

        try
        {
            //Chemin vers le fichier FXML
            final URL url = getClass().getResource("../vue/FenetrePrincipale.fxml");
            //Creation du loader
            final FXMLLoader fxmlLoader = new FXMLLoader(url);
            //Chargement du fichier FXML
            final Parent root = fxmlLoader.load();

            //Création de la scene
            final Scene scene = new Scene(root);
            fenetrePrincipale.setResizable(false);
            fenetrePrincipale.setTitle("PROJET UPJV 2016 KATOYI & ADAMA : Calculatrice multi-fonctions");
            fenetrePrincipale.setScene(scene);
            fenetrePrincipale.show();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
