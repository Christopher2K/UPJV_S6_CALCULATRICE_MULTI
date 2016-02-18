package com.katoyikane.modele.modules.calculatrice;

//Importation de Symja
import org.matheclipse.core.basic.Config ;
import org.matheclipse.core.eval.ExprEvaluator;
import org.matheclipse.core.interfaces.IExpr;

/**
 * Created by christopher on 07/02/16.
 */

/*
Classe correspondant au traitement des données de la calculatrice scientifique
Deuxième onglet
On utilisera ici une première implémentation des libraires SYMJA afin de gérer le calcul avec priorités (parenthèses)
 */
public class MoCalculatriceScientifique
{
    /**
     * Attributs de classe
     */
    private ExprEvaluator moteurCalcul = new ExprEvaluator(false, 1);       //Création d'un objet prenant en compte des expressions de calculs de type IExpr
    private IExpr resultat ;                                                //Variable stockant le résultat renvoyé par la méthode de calcul de l'objet ExprEvaluator
    private String calcul = "N(" ;                                          //Stocke sous forme de string le calcul que l'on enverra au moteur de calcul
    private boolean egalIsClicked = false ;                                 //Indique si le bouton égal a été cliqué au moins une fois afin d'eviter une exception si l'utilisateur clic égal deux fois de suite

    /**
     * Constructeur de la classe
     */
    public MoCalculatriceScientifique()
    {
        //Option permettant d'ignorer la casse lors des calculs
        Config.PARSER_USE_LOWERCASE_SYMBOLS = true ;
    }

    /**
     * Méthode du modèle de calcul
     */
    //Méthode renvoyant le résultat de l'expression passée en argument
    public String getResultat()
    {
        //On ferme la parenthèse pour former une expression valide
        this.calcul += ")" ;
        //Evaluation de l'expression entrée par l'utilisateur
        resultat = moteurCalcul.evaluate(this.calcul);
        //Retour du résultat sous forme de String
        return resultat.toString();
    }

    //Méthode renvoyant l'inverse de l'expression passée en argument
    public String getInverse()
    {
        //On ferme la parenthèse pour former une expression valide
        this.calcul += ")" ;

        //On remplace l'expression courant par celle de son inverse
        String calc = "N(1/(" + calcul + "))" ;

        //Evaluation de l'inverse de l'expression entrée par l'utilisateur
        resultat = moteurCalcul.evaluate(calc) ;

        //Retour du résultat sous forme de string
        return resultat.toString();
    }

    //Méthode renvoyant l'opposé de l'expression passée en argument
    public String getOppose()
    {
        //On ferme la parenthèse pour former une expression valide
        this.calcul += ")" ;

        //On remplace l'expression courant par celle de son opposé
        String calc = "N(-1*(" + calcul + "))" ;

        //Evaluation de l'opposé de l'expression entrée par l'utilisateur
        resultat = moteurCalcul.evaluate(calc) ;

        //Retour du résultat sous forme de string
        return resultat.toString() ;
    }

    //Méthode réinitialisation du modèle
    public void reset()
    {
        this.calcul = "N(" ;
    }

    /**
     * Getters et setters
     */
    public String getCalcul() {
        return calcul;
    }

    public void setCalcul(String calcul)
    {
        this.calcul += calcul;
    }

    public boolean getEgalIsClicked()
    {
        return this.egalIsClicked ;
    }

    public void setEgalIsClicked(Boolean isClicked)
    {
        this.egalIsClicked = isClicked ;
    }
}
