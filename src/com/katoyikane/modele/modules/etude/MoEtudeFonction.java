package com.katoyikane.modele.modules.etude;

import com.katoyikane.exception.*;
import com.katoyikane.modele.Exportateur;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import org.matheclipse.core.eval.ExprEvaluator;
import org.matheclipse.core.interfaces.IExpr;
import org.matheclipse.parser.client.SyntaxError;

import java.io.File;
import java.io.IOException;
import java.lang.Math;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by christopher on 07/02/16.
 */
/*
Classe correspondant au traitement des données de l'étude de fonction
Troisième onglet
 */
public class MoEtudeFonction
{
    /**
     * Attributs de classe
     */
    private int                 itv_bas         = 0 ;
    private int                 itv_haut        = 0 ;
    private float pas           = 0f ;
    private String              expression        = "" ;                                  //Stocke la fonction entrée par l'utilisateur si elle est validée
    private String              inconnue ;                                                 //Stocke la lettre de l'inconnu de la fonction
    private String              tempInconnue ;                                             //Stock l'inconnue déterminée lors de la vérification de l'expression
    private ArrayList<Double>   indices         = new ArrayList<>();                     //Contient la liste d'indices entrée par l'utilisateur
    private ArrayList<String>   resultats       = new ArrayList<String>();                     //Contient la liste des résultats selon les indices
    private DecimalFormat       df              = new DecimalFormat() ;                   //Objet restriction des décimales
    private boolean             isInconnue      = false ;

    private final ArrayList<Character> operateurs = new ArrayList<Character>(Arrays.asList('+', '-', '*', '/', '^', '(', ')')) ;     //Liste stockant les opérateurs utiles ici

    //Pour l'export
    private Exportateur exportateur;

    private ExprEvaluator       moteurCalcul    = new ExprEvaluator(false, 1);            //Création d'un objet prenant en compte des expressions de calculs de type IExpr
    private IExpr               resultat ;                                                //Variable stockant le résultat renvoyé par la méthode de calcul de l'objet ExprEvaluator



    /**
     * Constructeur de la classe
     */
    public MoEtudeFonction()
    {
        df.setMaximumFractionDigits(5);
    }

    /**
     * SETTERS & SETTERS
     */
    public int                  getItv_bas() {
        return itv_bas;
    }

    public void                 setItv_bas(int itv_bas) {
        this.itv_bas = itv_bas;
    }

    public int                  getItv_haut() {
        return itv_haut;
    }

    public void                 setItv_haut(int itv_haut) {
        this.itv_haut = itv_haut;
    }

    public String               getExpression() {
        return expression;
    }

    public void                 setExpression(String expression) {
        IExpr iexpr = new ExprEvaluator().evaluate(expression);
        this.expression = iexpr.toString();
    }

    public String               getInconnue() {
        return inconnue;
    }

    public void                 setInconnue(String inconnu) {
        this.inconnue = inconnu;
    }

    public ArrayList<String>    getResultats() {
        return resultats;
    }

    public ArrayList<Double> getIndices() {
        return indices;
    }

    public boolean              isInconnue() {
        return isInconnue;
    }

    public void                 setIsInconnue(boolean inconnu) {
        isInconnue = inconnu;
    }

    public String               getTempInconnue() {
        return tempInconnue;
    }

    public void                 setTempInconnue(String tempInconnu) {
        this.tempInconnue = tempInconnu;
    }

    public float getPas() {
        return pas;
    }

    public void setPas(float pas) {
        this.pas = pas;
    }

    /**
     * Méthodes du modèle d'étude de fonction
     */
    //Méthode invoquée pour vérifier l'expression selon la syntaxe requise de Symja
    public void verifierSyntaxe(String expression) throws SyntaxeFonctionException
    {
        boolean         isOk    = false;                           //Variable indiquant si l'expression est valide ici
        IExpr           iexpr ;                                    //Variable initialisée plus tard si l'expression est valide
        ExprEvaluator   eval    = new ExprEvaluator(false, 1);     //Moteur d'évaluation SYMJA

        try
        {
            iexpr = eval.evaluate("Hold(" + expression + ")");
            //Si tout est bon ici on ajoute l'expression à la variable d'attribut Expression pour la vérification suivante
            this.setExpression(iexpr.toString());
        }
        catch (SyntaxError e)
        {
            throw new SyntaxeFonctionException();
        }
    }

    //Méthode invoquée pour vérifier l'intégrité des données
    public void verifierIntegrite(String fonction, String inconnue, String itv1, String itv2, String pas) throws  InformationMissingException
    {
        if (fonction.isEmpty())
            throw new InformationMissingException(1);
        else if (inconnue.isEmpty())
            throw new InformationMissingException(2);
        else if (itv1.isEmpty() || itv2.isEmpty())
            throw new InformationMissingException(3);
        else if (pas.isEmpty())
            throw new InformationMissingException(4);
    }

    //Méthode invoquée pour déterminer si l'expression a une inconnue et savoir laquelle
    public void verifierInconnueExpression() throws InconnueException
    {
        String exp      = this.getExpression();

        //Boucle itérative parcourant l'expression donnée en argument
        for (int i = 0; i < exp.length(); i++)
        {
            //SI on rencontre une lettre
            if (Character.isLetter(exp.charAt(i)) && exp.charAt(i) != 'e')
            {

                //Si il s'agit du premier caractère de l'expression et si le caractère qui le suit est un operateur
                if (i == 0 && operateurs.contains(exp.charAt(i+1)))
                {
                    //Si jamais l'inconnue deja renseignée et qu'il s'agit d'une autre lettre que celle précédement trouvée
                    if (isInconnue)
                    {
                        if (exp.charAt(i) != this.getTempInconnue().charAt(0))
                        {
                            throw new InconnueException(5);
                        }
                    }
                    //Sinon on affecte cette inconnue
                    this.setIsInconnue(true);
                    this.setTempInconnue(Character.toString(exp.charAt(i)));
                }
                //Si la lettre courante n'est pas le premier caractère si un opérateur le précède et qu'il s'agit du dernier caractère
                else if (i == (exp.length() -1) && operateurs.contains(exp.charAt(i-1)))
                {
                    //Si jamais l'inconnue deja renseignée et qu'il s'agit d'une autre lettre que celle précédement trouvée
                    if (isInconnue)
                    {
                        if (exp.charAt(i) != this.getTempInconnue().charAt(0))
                        {
                            throw new InconnueException(5);
                        }
                    }

                    //Il s'agit bien d'une inconnue donc
                    this.setIsInconnue(true);
                    this.setTempInconnue(Character.toString(exp.charAt(i)));
                }
                //Si le caractère précédent est un opérateur et le suivant un opérateur aussi
                else if (i != 0 && operateurs.contains(exp.charAt(i-1)) && operateurs.contains(exp.charAt(i+1)))
                {
                    //Si jamais l'inconnue deja renseignée et qu'il s'agit d'une autre lettre que celle précédement trouvée
                    if (isInconnue)
                    {
                        if (exp.charAt(i) != this.getTempInconnue().charAt(0))
                        {
                            throw new InconnueException(5);
                        }
                    }

                    //Il s'agit bien d'une inconnue donc
                    this.setIsInconnue(true);
                    this.setTempInconnue(Character.toString(exp.charAt(i)));
                }
            }
        }

    }

    //Méthode de vérification de l'inconnue
    public void verifierInconnue(String inconnue, String exp) throws InconnueException
    {
        //Si l'inconnue est vide
        if (inconnue.isEmpty())
            throw new InconnueException(0);

        //Si l'inconnue est supérieure à deux caractères
        if (inconnue.length() > 1)
            throw new InconnueException(1);

            //Si le premier caractère n'est pas une lettre
        else if (!Character.isLetter(inconnue.charAt(0)))
            throw new InconnueException(2);

        //Si l'inconnue n'est pas dans l'expression alors que la vérification précédente en a trouvé une
        if (isInconnue)
        {
            if (!this.getExpression().contains(inconnue))
                throw new InconnueException(3);
        }

        //Si l'inconnue est égale à e
        if (inconnue.charAt(0) == 'e')
            throw new InconnueException(4);

        //Sinon on affecte normalement
        this.setInconnue(this.getTempInconnue());
    }

    //Méthodée invoquée afin de vérifier si les intervalles sont bien des nombres
    public void verifierIntervalles(String inf, String sup) throws IntervalleException
    {
        //Si c'est un nombre négatif avec un "-" devant :
        if (sup.isEmpty() || inf.isEmpty() || (!Character.isDigit(sup.charAt(0)) && !(sup.charAt(0) == '-')) || (!Character.isDigit(inf.charAt(0)) && !(inf.charAt(0) == '-')))
        {
            throw new IntervalleException(1);
        }

        //Vérification du reste des caractères
        for(int i = 1; i < sup.length(); i++)
        {
            if(!Character.isDigit(sup.charAt(i)))
                throw new IntervalleException(1);
        }
        for(int i = 1; i < inf.length(); i++)
        {
            if(!Character.isDigit(inf.charAt(i)))
                throw new IntervalleException(1);
        }

        //Vérification de l'ordre des intervalles
        if (Integer.decode(sup) < Integer.decode(inf))
            throw new IntervalleException(2);

        //Si tout va bien on affecte les valeurs
        this.setItv_bas(Integer.decode(inf));
        this.setItv_haut(Integer.decode(sup));
    }

    //Méthode de vérificas du pas
    public void verifierPas(String pas) throws PasException
    {
        //Si le pas n'est pas renseigné
        if (pas.isEmpty())
            throw new PasException(1);

        //Si le pas n'est pas un nombre entier ou decimal
        try { Float.parseFloat(pas); }
        catch (NumberFormatException e) { throw new PasException(2); }

        //Si le pas est plus grand que la différence entre les intervalles
        if ((this.getItv_haut() - this.getItv_bas()) < Double.parseDouble(pas))
        {
            throw new PasException(3);
        }

        if (Double.parseDouble(pas) < 0)
            throw new PasException(4);

        //Sinon on affecte la variable
        this.setPas(Float.parseFloat(pas));
    }

    //Méthode invoquée afin que l'expression entrée soit traitée par le module, les calculs sont effectués et stockés
    public void calcul()
    {
        String tempResult;

        //Calcul du nombre de calcul que l'on va effectuer
        Float t = (this.getItv_haut() - this.getItv_bas()) / this.getPas();
        int nombreDeCalcul = Math.round(t);

        //Génération des indices
        double ind = 0.0;
        for (int i = 0 ; i <= nombreDeCalcul ; i++)
        {
            //On arrondi car la représentation des nombres décimaux en java donnerais de faux résultats
            indices.add((double) Math.round((this.getItv_bas()+ind) * 100) / 100);
            //On incrémente ce que l'on ajout à l'intervalle du bas afin d'avoir l'indice suivant
            ind += pas;
        }

        //Génération des résultats
        for (Double x : indices)
        {
            tempResult = moteurCalcul.evaluate("N(" + remplacerInconnueExpression(this.getExpression(), getInconnue(), x) +")").toString();
            if (Character.isLetter(tempResult.charAt(0)))
            {
                resultats.add("NaN");
            }
            else
            {
                if(tempResult.contains("e"))
                    resultats.add(tempResult);
                else
                    resultats.add(df.format(Double.parseDouble(tempResult)));
            }
        }
    }

    //Méthode servant à remplacer dans une expression une inconnue par un indice
    public String remplacerInconnueExpression(String exp, String inc, double ind)
    {
        String temp = exp, t1, t2 ;
        String indice = Double.toString(ind);

        for (int i = 0; i < temp.length(); i++)
        {
            //S'il s'agit d'une lettre différente de e
            if (Character.isLetter(temp.charAt(i)) && temp.charAt(i) != 'e')
            {
                //Si il s'agit du premier caractère de l'expression et si le caractère qui le suit est un operateur
                if (i == 0 && operateurs.contains(exp.charAt(i + 1))) {
                    //Processus de remplacement
                    t1 = temp.substring(i+1, temp.length());
                    temp = indice + t1 ;
                }
                //Si la lettre courante n'est pas le premier caractère si un opérateur le précède et qu'il s'agit du dernier caractère
                else if (i == (temp.length() - 1) && operateurs.contains(temp.charAt(i - 1))) {
                    //Processus de remplacement
                    t1 = temp.substring(0, i);
                    temp = t1 + indice ;
                }
                //Si le caractère précédent est un opérateur et le suivant un opérateur aussi
                else if (i != 0 && operateurs.contains(temp.charAt(i - 1)) && operateurs.contains(temp.charAt(i + 1))) {
                    //Processus de remplacement
                    t1 = temp.substring(0, i);
                    t2 = temp.substring(i+1, temp.length());
                    temp = t1 + indice + t2;
                }
                //Si il s'agit du premier et dernier caractère
                else if (i == 0 && temp.length() == 1)
                {
                    //Processus de remplacement
                    temp = indice;
                }
            }
        }
        return temp;
    }

    //Méthode invoquée pour générer le code LaTex de l'expréssion rentrée.
    public String generationLatex()
    {
        return moteurCalcul.evaluate("TexForm(" + this.getExpression() + ")").toString();
    }

    //Méthode d'exportation des nombres calculés
    public void export() throws IOException
    {
        //Génération des lignes de mprésentation
        String module =
                "MODULE : ETUDE DE FONCTION\n" +
                "DATE : " + this.exportateur.getAujourdhui()  + "\n" +
                "OBJET : Restitution des résultats\n\n" +
                "FONCTION : " + this.getExpression() + "\n" +
                "LATEX : " + moteurCalcul.evaluate("TexForm(" + this.getExpression() + ")").toString() + "\n\n" +
                "INCONNUE:\t\tRESULTATS:\n" ;

        //Génération de l'affichage des résultats
        String affichage = "";
        for (int i = 0 ; i < indices.size(); i++)
        {
            affichage = affichage + Double.toString(indices.get(i)) + "\t\t" + resultats.get(i) + "\n";
        }
        exportateur.ecriture(module);
        exportateur.ecriture(affichage);
        exportateur.separer();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Boite de confirmation");
        alert.setHeaderText("Données enregistrées");
        alert.setContentText("Les données ont bien écrites dans \"" + exportateur.getF().getAbsolutePath() + "\".");
        alert.showAndWait();
    }

    //Méthode de création d'un fichier
    public void creationExport(Window w) throws IOException
    {
        this.exportateur = new Exportateur(w);
        this.exportateur.creerExport();
    }

    //Méthode d'overture d'un fichier
    public void ouvertureExport(Window w) throws IOException
    {
        this.exportateur = new Exportateur(w);
        this.exportateur.ouvrirExport();
    }


    //Méthode de réinitialisation
    public void reset()
    {
        this.expression = "";
        this.setItv_bas(0);
        this.setItv_haut(0);
        this.setInconnue("");
        this.setTempInconnue("");
        this.setIsInconnue(false);
        this.exportateur = null;
    }

}
