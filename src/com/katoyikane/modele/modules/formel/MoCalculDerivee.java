package com.katoyikane.modele.modules.formel;

import com.katoyikane.exception.InconnueException;
import com.katoyikane.exception.InformationMissingException;
import com.katoyikane.exception.SyntaxeFonctionException;
import org.matheclipse.core.eval.ExprEvaluator;
import org.matheclipse.core.interfaces.IExpr;
import org.matheclipse.parser.client.SyntaxError;

import java.util.ArrayList;

/**
 * Created by christopher on 08/02/16.
 */
/*
Classe correspondant au traitement des données du module de dérivation d'expressions
5e onglet
 */
public class MoCalculDerivee
{
	/**
	 * Attributs de classe
	 */
	private String		expression;
	private String		inconnue;
	private String 		tempInconnue ;
	private boolean 	isInconnue      = false;
	private String		derivee;


	private ExprEvaluator moteurCalcul    = new ExprEvaluator(false, 1);
	private IExpr resultat;

	/**
	 * Constructeur
	 */
	public MoCalculDerivee()
	{

	}

	/**
	 * SETTERS & GETTERS
	 */
	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

	public String getInconnue() {
		return inconnue;
	}

	public void setInconnue(String inconnue) {
		this.inconnue = inconnue;
	}

	public String getTempInconnue() {
		return tempInconnue;
	}

	public void setTempInconnue(String tempInconnue) {
		this.tempInconnue = tempInconnue;
	}

	/**
	 * Méthodes du module de dérivation d'expression
	 */

	public void verifierIntegrite(String expression, String inconnue) throws InformationMissingException
	{
		if (expression.isEmpty())
			throw new InformationMissingException(1);
		else if (inconnue.isEmpty())
			throw new InformationMissingException(2);
	}

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

	//Méthode invoquée pour déterminer si l'expression a une inconnue et savoir laquelle
	public void verifierInconnueExpression(String exp) throws InconnueException
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
					//Si jamais l'inconnue deja renseignée et qu'il s'agit d'une autre lettre que celle précédement trouvée
					if (isInconnue)
					{
						if (exp.charAt(i) != this.getTempInconnue().charAt(0))
						{
							throw new InconnueException(5);
						}
					}
					//Sinon il s'agit bien d'une inconnue donc
					isInconnue = true;
					this.setTempInconnue(Character.toString(exp.charAt(i)));
				}
				//Si la lettre courante n'est pas le premier caractère si un opérateur le précède et qu'il s'agit du dernier caractère
				else if (i == (exp.length() - 1) && operateurs.contains(exp.charAt(i - 1)))
				{
					//Si jamais l'inconnue deja renseignée et qu'il s'agit d'une autre lettre que celle précédement trouvée
					if (isInconnue)
					{
						if (exp.charAt(i) != this.getTempInconnue().charAt(0))
						{
							throw new InconnueException(5);
						}
					}
					//Sinon il s'agit bien d'une inconnue donc
					isInconnue = true;
					this.setTempInconnue(Character.toString(exp.charAt(i)));
				}
				//Si le caractère précédent est un opérateur et le suivant un opérateur aussi
				else if (i != 0 && operateurs.contains(exp.charAt(i - 1)) && operateurs.contains(exp.charAt(i + 1)))
				{
					//Si jamais l'inconnue deja renseignée et qu'il s'agit d'une autre lettre que celle précédement trouvée
					if (isInconnue)
					{
						if (exp.charAt(i) != this.getTempInconnue().charAt(0))
						{
							throw new InconnueException(5);
						}
					}
					//Sinon il s'agit bien d'une inconnue donc
					isInconnue = true;
					this.setTempInconnue(Character.toString(exp.charAt(i)));
				}
			}
		}
	}

	//Méthode de vérification de l'inconnue
	public void verifierInconnue(String inconnue, String exp) throws InconnueException
	{
		//Si l'inconnue est supérieure à deux caractères
		if (inconnue.length() > 1)
			throw new InconnueException(1);

			//Si le premier caractère n'est pas une lettre
		else if (!Character.isLetter(inconnue.charAt(0)))
			throw new InconnueException(2);

		//Si l'inconnue n'est pas dans l'expression alors que la vérification précédente en a trouvé une
		if (isInconnue)
		{
			if (!inconnue.equals(tempInconnue))
				throw new InconnueException(3);
		}

		//Si l'inconnue est égale à e
		if (inconnue.charAt(0) == 'e')
			throw new InconnueException(4);

		//Sinon on affecte normalement
		this.setExpression(exp);
		this.setInconnue(this.getTempInconnue());
	}

	//Méthode de calcul de la dérifée
	public String calculDerivee()
	{
		derivee = moteurCalcul.evaluate("D(" + expression + "," + inconnue + ")").toString();
		return derivee;
	}

	public String generationLatex(String expression)
	{
		return moteurCalcul.evaluate("TexForm(Hold(" + expression + "))").toString();
	}



}
