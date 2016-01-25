
package hiv2016.log320.lab1;

/*************************************
  CLASS         : TestAlgorithmes
 *************************************
@author Louis Wilfried MBOG
    
*Description    : Cette classe effectue une comparaison entre le temps 
                  d'execution de l'algorithme de l'enonce du laboratoire et le
                  temps d'execution de l'algorithme que nous avons implemente.
                                   
*Constructors   :
    aucun

*Methods        :
    public static void main(String[] args)
    
*/
public class TestAlgorithmes 
{
    private static final String CHAINE_1 = "Chaine 1: ";
    private static final String CHAINE_2 = "Chaine 2: ";
    private static final String RESULTAT_ALGORITHME_DE_BASE = "Resultat algorithme de base: ";
    private static final String RESULTAT_DEUXIEME_ALGORITHME = "Resultat de notre algorithme: ";
    private static final String TEMPS_EXECUTION_ALGORITHME_DE_BASE = "Temps execution algorithme de base: ";
    private static final String TEMPS_EXECUTION_DEUXIEME_ALGORITHME = "Temps execution de notre algorithme: ";
    
    public static void main(String[] args)
    {
        AlgorithmeDeBase algorithme = new AlgorithmeDeBase();
        DeuxiemeAlgorithme algorithme2 = new DeuxiemeAlgorithme();
        
        boolean finDeTraitement = false;
        ChronometreThread chronometre2 = new ChronometreThread(finDeTraitement);
        
        StringBuilder chaine = new StringBuilder("azerty");
        StringBuilder anagramme = new StringBuilder("azerty");
        
        String chaine_ = chaine.toString();
        String anagramme_ = anagramme.toString();
        
        char[] chaineArray = algorithme2.getCharArrayFromString( chaine_ );
        char[] anagrammeArray = algorithme2.getCharArrayFromString( anagramme_ );
        
        Boolean trouve = algorithme.estUnAnagramme(anagramme, chaine);
        
        chronometre2.start();
        Boolean trouve2 = algorithme2.estUnAnagramme(anagrammeArray, chaineArray);
        finDeTraitement = true;
        
        chronometre2.setFinDeTraitement(finDeTraitement);
        
        System.out.println(CHAINE_1 + chaine_);
        System.out.println(CHAINE_2 + anagramme_);
        
        System.out.print("\n");
        
        System.out.println(RESULTAT_ALGORITHME_DE_BASE + trouve);
        System.out.println(RESULTAT_DEUXIEME_ALGORITHME + trouve2);
        
        System.out.print("\n");
        
        ChronometreThread chronometre = algorithme.getChronometreThread();
        System.out.print(TEMPS_EXECUTION_ALGORITHME_DE_BASE );
        chronometre.afficherTempsExecution();
        System.out.print(TEMPS_EXECUTION_DEUXIEME_ALGORITHME );
        chronometre2.afficherTempsExecution();   
    }
    
}
