
package hiv2016.log320.lab1;

/*************************************
  CLASS         : DeuxiemeAlgorithme
 *************************************
@author Louis Wilfried MBOG
    
*Description    : This class implements an algorithm of cost "n" which verifies
                  if a word is an anagram of an another word.This algorithm only
                  works with lower case characters.
                  
                      
*Constructors   :
    public DeuxiemeAlgorithme()

*Methods        :
    public boolean estUnAnagramme(char[] chaine1, char[] chaine2)
    public ChronometreThread getChronometreThread()
    
*/
public class DeuxiemeAlgorithme 
{
    private static final char CARACTERE_A = 'a';
    
    private volatile boolean finDeTraitement = false;
    private ChronometreThread chronometre;
    
    /*************************************
      CONSTRUCTOR : DeuxiemeAlgorithme
     *************************************
    */
    public DeuxiemeAlgorithme()
    {
        chronometre = new ChronometreThread(finDeTraitement);
    }
    
    /*************************************
      FUNCTION : estUnAnagramme
     *************************************
    *Description    : Cette fonction vérifie que la chaine de caractères
                      contenue dans la variable "chaine1" et la chaine de
                      caractères contenue dans la variable "chaine2" ont les
                      memes caractères.
                      Si les variables "chaine1" et "chaine2" ont les memes
                      caracteres alors, "chaine1" est un anagramme de "chaine2"
                      et la fonction retourne la valeur "true".
                      Si les variables "chaine1" et "chaine2" ont les memes
                      caracteres alors, "chaine1" est un anagramme de "chaine2"
                      et la fonction retourne la valeur "true".
                      Si les variables "chaine1" et "chaine2" n'ont pas les
                      memes caracteres alors, "chaine1" n'est pas un anagramme
                      de "chaine2" et la fonction retourne la valeur "false".
                      
                      
    *Parameters     :
    @param  chaine1 : 
            TYPE    : char[]
            
        DESCRIPTION : chaine de caractères à comparer avec la chaine de
                      caractères contenue dans la variable "chaine2" afin de 
                      savoir si la chaine de caracteres contenue dans la 
                      variable "chaine1" est un anagramme de la chaine de
                      caractères contenue dans la variable "chaine2".
                  
    @param  chaine2 : 
            TYPE    : char[]
         
        DESCRIPTION : chaine de caracteres qui sera comparée avec la chaine de
                      caracteres contenue dans la varaible "chaine1" afin de
                      déterminer si la chaine de caractères contenue dans la
                      variable "chaine1" est un anagramme de la chaine de
                      caractères contenue dans la variable "chaine2".
    
    
    @return         : 
     - true si "chaine1" est un anagramme de "chaine2".
     - false si "chaine1" n'est pas un anagramme de "chaine2".
       
    */
    public boolean estUnAnagramme(char[] chaine1, char[] chaine2)
    {
        //chronometre.start();
        
        if(chaine1.length != chaine2.length)
        {
            //finDeTraitement = true;
            //chronometre.setFinDeTraitement(finDeTraitement);
            return false;
        }
        else
        {
            int[] tableau1 = new int[123];
            int[] tableau2 = new int[123];
            
            int i = 0;
            
            while(i < chaine1.length)
            {
                tableau1[ chaine1[i] ] += 1;
                tableau2[ chaine2[i] ] += 1;
                i++;
            }
            
            for(int j = 0; j < tableau1.length; j++)
            {
                if( tableau1[j] != tableau2[j] )
                {
                    //finDeTraitement = true;
                    //chronometre.setFinDeTraitement(finDeTraitement);
                    return false;
                }
            }
            //finDeTraitement = true;
            //chronometre.setFinDeTraitement(finDeTraitement);
            return true;
        }    
    }
    
    public char[] getCharArrayFromString(String chaine)
    {
        return chaine.toCharArray();
    }
    
    /*************************************
      FUNCTION OF TYPE "GETTER"  : getChronometreThread
     *************************************
    *Description    : Cette fonction retourne l'objet représentant le 
                      chronomètre associé à la classe "AlgorithmeDeBase" et
                      chargé de calculer le temps d'exécution des fonctions
                      de la classe "AlgorithmeDeBase"
                                      
    *Parameters     :
     - aucun
     
    @return         : 
     - objet de type "ChronometreThread"représentant le chronomètre associé
       à la classe "AlgorithmeDeBase".
       
    */
    public ChronometreThread getChronometreThread()
    {
       return chronometre;
    }
    
}
