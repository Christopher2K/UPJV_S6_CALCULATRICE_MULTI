package com.katoyikane.modele;

import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Objet permettant l'exportation des calculs dans un fichier
 */
public class Exportateur
{
    //Attributs
    private FileChooser fc = new FileChooser();
    private File f ;
    private Date date;
    private DateFormat df;
    private BufferedWriter bw;
    private Window w;

    public Exportateur(Window w)
    {
        this.w = w;
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Fichiers texte","*.txt"));
    }

    /**
     * Getters & Setters
     */
    public FileChooser getFc() {
        return fc;
    }

    public void setFc(FileChooser fc) {
        this.fc = fc;
    }

    public File getF() {
        return f;
    }

    public void setF(File f) {
        this.f = f;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public DateFormat getDf() {
        return df;
    }

    public void setDf(DateFormat df) {
        this.df = df;
    }

    public BufferedWriter getBw() {
        return bw;
    }

    public void setBw(BufferedWriter bw) {
        this.bw = bw;
    }

    public Window getW() {
        return w;
    }

    public void setW(Window w) {
        this.w = w;
    }

    /**
     * Méthodes du module
     */
    //Méthode de création du fichier
    public void creerExport() throws IOException
    {
        this.setF(this.getFc().showSaveDialog(this.getW()));
        this.getF().createNewFile();
    }

    //Méthode d'ouverture du fichier
    public void ouvrirExport()
    {
        this.setF(this.getFc().showOpenDialog(this.getW()));

    }

    //Génération de la date
    public String getAujourdhui()
    {
        date = new Date();
        df = DateFormat.getDateTimeInstance(
                DateFormat.MEDIUM,
                DateFormat.MEDIUM,
                new Locale("FR", "fr")
        );

        return df.format(date);
    }

    //Ecrire dans le fichier
    public void ecriture(String chaine)
    {
        try
        {
            this.setBw(new BufferedWriter(new FileWriter(this.getF(), true)));
            try
            {
                bw.write(chaine);
            }
            finally
            {
                bw.close();
            }
        }catch (IOException e){
            //TODO a gerer l'erreur ici
        }
    }

    //Permet de séparer les instances et les expressions entres-elles
    public void separer()
    {
        try
        {
            this.setBw(new BufferedWriter(new FileWriter(this.getF(), true)));
            try
            {
                bw.write("\n");
                for (int y = 1 ; y <= 3 ; y++)
                {
                    for (int i = 1 ; i <= 50 ; i++)
                    {
                        bw.write("x");
                    }
                    bw.write("\n");
                }
                bw.write("\n");
            }
            finally
            {
                bw.close();
            }
        }catch (IOException e){
            //TODO a gerer l'erreur ici
        }
    }

}
