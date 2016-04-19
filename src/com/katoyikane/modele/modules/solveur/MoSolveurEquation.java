package com.katoyikane.modele.modules.solveur;

import com.katoyikane.exception.EquationException;
import com.katoyikane.exception.InconnueException;
import com.katoyikane.exception.InformationMissingException;
import com.katoyikane.exception.SyntaxeFonctionException;
import com.katoyikane.modele.Exportateur;
import javafx.scene.control.Alert;
import javafx.stage.Window;
import org.matheclipse.core.eval.ExprEvaluator;
import org.matheclipse.core.eval.exception.WrongArgumentType;
import org.matheclipse.core.interfaces.IExpr;
import org.matheclipse.core.reflection.system.CharacteristicPolynomial;
import org.matheclipse.parser.client.SyntaxError;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by christopher on 07/02/16.
 */
/*
Classe correspondant au traitement des données du solveur d'équations
Quatrième onglet
 */
public class MoSolveurEquation
{
    /**
     * Attributs de classe
     */
    private String equation;
    private String inconnue;
    private String solution;
    private ArrayList<String> listeInconnues = new ArrayList<String>();
    private ArrayList<String> listeSolutions = new ArrayList<String>();


    private DecimalFormat df = new DecimalFormat();

    private Exportateur exportateur;

    private ExprEvaluator moteurCalcul    = new ExprEvaluator(false, 1);            //Création d'un objet prenant en compte des expressions de calculs de type IExpr
    private IExpr resultat;

    /**
     * Constructeur
     */
    public MoSolveurEquation()
    {

    }

    /**
     * SETTERS & GETTERS
     */
    public String getEquation() {
        return equation;
    }

    public void setEquation(String equation) {
        this.equation = equation;
    }

    public String getInconnue() {
        return inconnue;
    }

    public void setInconnue(String inconnue) {
        this.inconnue = inconnue;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public ArrayList<String> getListeSolutions() {
        return listeSolutions;
    }

    public void setListeSolutions(ArrayList<String> listeSolutions) {
        this.listeSolutions = listeSolutions;
    }

    /**
     * Méthodes du module de résolution d'équations
     */
    //Méthode invoquée afin de vérifier l'intégrité des données entrés par l'utilisateur
    public void verifierIntegrite(String p_gauche, String p_droite, String inconnue) throws InformationMissingException
     {
        if (p_gauche.isEmpty() || p_droite.isEmpty())
        {
            throw new InformationMissingException(5);
        }
        else if (inconnue.isEmpty())
        {
            throw new InformationMissingException(2);
        }
    }

    //Méthode invoquée pour vérifier l'expression selon la syntaxe requise de Symja
    public boolean verifierSyntaxe(String expression) throws SyntaxeFonctionException
    {
        boolean         isOk    = false;                           //Variable indiquant si l'expression est valide ici
        IExpr           iexpr ;                                    //Variable initialisée plus tard si l'expression est valide
        ExprEvaluator   eval    = new ExprEvaluator(false, 1);     //Moteur d'évaluation SYMJA

        try
        {
            iexpr = eval.evaluate("Hold(" + expression + ")");
            return true;
        }
        catch (SyntaxError e)
        {
            throw new SyntaxeFonctionException();
        }
    }

    //Méthode invoqyée pour valider les inconnues dans les deux expressions
    public void getInconnueExpression(String exp)
    {
        exp = moteurCalcul.evaluate("Hold(" + exp + ")").toString();

        //Liste stockant les opérateurs
        ArrayList<Character> operateurs = new ArrayList<Character>() ;
        operateurs.add('+');
        operateurs.add('-');
        operateurs.add('*');
        operateurs.add('/');
        operateurs.add('^');
        operateurs.add('(');
        operateurs.add(')');

        //Boucle cherchant a déterminer les inconnues dans la fonction
        for (int i = 0; i < exp.length(); i++)
        {
            //S'il s'agit d'une lettre différente de e
            if (Character.isLetter(exp.charAt(i)) && exp.charAt(i) != 'e')
            {
                //Si il s'agit du premier caractère de l'expression et si le caractère qui le suit est un operateur
                if (i == 0 && operateurs.contains(exp.charAt(i + 1)))
                {
                    //On l'ajoute dans la liste des inconnus seulement si cette inconnue n'est pas déjà apparue
                    if (!listeInconnues.contains(Character.toString(exp.charAt(i))))
                    {
                        listeInconnues.add(Character.toString(exp.charAt(i)));
                    }
                }
                //Si la lettre courante n'est pas le premier caractère si un opérateur le précède et qu'il s'agit du dernier caractère
                else if (i == (exp.length() - 1) && operateurs.contains(exp.charAt(i - 1)))
                {
                    if (!listeInconnues.contains(Character.toString(exp.charAt(i))))
                    {
                        listeInconnues.add(Character.toString(exp.charAt(i)));
                    }
                }
                //Si le caractère précédent est un opérateur et le suivant un opérateur aussi
                else if (i != 0 && operateurs.contains(exp.charAt(i - 1)) && operateurs.contains(exp.charAt(i + 1)))
                {
                    if (!listeInconnues.contains(Character.toString(exp.charAt(i))))
                    {
                        listeInconnues.add(Character.toString(exp.charAt(i)));
                    }
                }
            }
        }
    }

    //Méthode invoquée pour voir si l'inconnue renseignée par l'utilisateur est bien présente dans le tableau des inconnues trouvée
    public void verifierEntreeInconnue(String inc) throws InconnueException
    {
        if (inc.length() > 1)
        {
            throw new InconnueException(1);
        }
        else if (!Character.isLetter(inc.charAt(0)))
        {
            throw new InconnueException(3);
        }
        else if (!listeInconnues.contains(inc))
        {
            throw new InconnueException(6);
        }
    }

    public String generationLatex()
    {
        resultat = moteurCalcul.evaluate("TexForm(" + this.getEquation() + ")");
        return resultat.toString();
    }

    public String resolution() throws EquationException,WrongArgumentType
    {
        try
        {
            resultat = moteurCalcul.evaluate("Solve(" + this.getEquation() + "," + this.getInconnue() +")");
            String temp = resultat.toString();
            if (temp.charAt(0) != '{')
            {
                throw new EquationException(0);
            }
            else
            {
                return temp;
            }
        }
        catch (WrongArgumentType e) { throw  new EquationException(1); }

    }

    public void genererListeSolutions()
    {
        String temp = this.getSolution();

        //Parcours du output de Symja
        for (int i = 0; i < temp.length(); i++)
        {
            //Si on rencontre un ">" il s'agit du début d'une solution
            if (temp.charAt(i) == '>')
            {
                int beg, end;
                beg = i+1;  //On stocke l'indice du début de la solution

                //On parcours le reste de la chaine de manière a trouver le caractère "}" qui indique la fin d'une solution
                for (int z = i; z < temp.length(); z++)
                {
                    if (temp.charAt(z) == '}')
                    {
                        end = z;
                        this.getListeSolutions().add(temp.substring(beg, end));
                        break;
                    }
                }
            }
        }
    }


    public void export()
    {
        //Génération des lignes de mprésentation
        String module =
                "MODULE : SOLVEUR D'EQUATION POLYNOMIALE\n" +
                        "DATE : " + this.exportateur.getAujourdhui()  + "\n" +
                        "OBJET : EXPORT DE LA SOLUTION\n\n" +
                        "EQUATION : " + this.getEquation() + "\t" + "INCONNUE : " + this.getInconnue() + "\n" +
                        "LATEX : " + moteurCalcul.evaluate("TexForm(" + this.getEquation() + ")").toString() + "\n\n" +
                        "SOLUTION :\n" ;

        //Génération de l'affichage de la solution
        String affichage = "";
        for(String s : this.getListeSolutions())
        {
            affichage += "\t\t" + s + "\n";
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

    public void reset()
    {
        this.setSolution("");
        this.setInconnue("");
        this.setEquation("");
        this.listeInconnues = new ArrayList<String>();
        this.exportateur = null;
    }

}
