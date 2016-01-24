
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
    public static void main(String[] args)
    {
        AlgorithmeDeBase algorithme = new AlgorithmeDeBase();
        DeuxiemeAlgorithme algorithme2 = new DeuxiemeAlgorithme();
        
        StringBuilder chaine = new StringBuilder("azertyuiopmlkjnhbgfvcdsxqw123654789874563210mpolkiujhytgfdcqazsedrtryfhcnvkjoilpm78451239220vnvbhjderu  784qdfghjjlmpotieytetcgbv12369754123658tpelfyeaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaqqqqqqqqqqqqqqqqqq111111111111111111111111112222222222222222222222225555555555555555555555555555555555555566666666666666666666666666666ccccccccpmlouhjnhsfs");
        StringBuilder anagramme = new StringBuilder("azertyuiopmlkjnhbgfvcdsxqw123654789874563210mpolkiujhytgfdcqazsedrtryfhcnvkjoilpm78451239220vnvbhjderu  784qdfghjjlmpotieytetcgbv12369754123658tpelfyeaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaqqqqqqqqqqqqqqqqqq111111111111111111111111112222222222222222222222225555555555555555555555555555555555555566666666666666666666666666666ccccccccpmlouhjnhsfs");
        
        
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
