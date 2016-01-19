/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hiv2016.log320.lab1;

/**
 *
 * @author Camx0
 */
public class TestAlgorithmes 
{
    public static void main(String[] args)
    {
        AlgorithmeDeBase algorithme = new AlgorithmeDeBase();
        DeuxiemeAlgorithme algorithme2 = new DeuxiemeAlgorithme();
        
        StringBuilder chaine = new StringBuilder("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        StringBuilder anagramme = new StringBuilder("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        
        
        char[] chaineArray = algorithme2.getCharArrayFromString( chaine.toString() );
        char[] anagrammeArray = algorithme2.getCharArrayFromString( anagramme.toString() );
        
        Boolean trouve = algorithme.estUnAnagramme(anagramme, chaine);
        Boolean trouve2 = algorithme2.estUnAnagramme(anagrammeArray, chaineArray);
        
        System.out.println(trouve);
        System.out.println(trouve2);
        
        ChronometreThread chronometre = algorithme.getChronometreThread();
        ChronometreThread chronometre2 = algorithme2.getChronometreThread(); 
        
        chronometre.afficherTempsExecution();
        chronometre2.afficherTempsExecution();
    }
    
}
