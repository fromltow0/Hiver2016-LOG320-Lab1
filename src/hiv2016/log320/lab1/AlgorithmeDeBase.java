package hiv2016.log320.lab1;

/*************************************
  CLASS         : AlgorithmeDeBase
 *************************************
@author Louis Wilfried MBOG
    
*Description    : Cette classe implement un algorithme de base qui verifie qu'un
                  mot est un anagramme d'un autre mot.Il s'agit de l'algorithme
                  de la section "Algorithme de base" de l'enonce du laboratoire
                  1.
                      
*Constructors   :
    public AlgorithmeDeBase()

*Methods        :
    public static void main(String[] args)
    public boolean estUnAnagramme(StringBuilder chaine1, StringBuilder chaine2)
    public ChronometreThread getChronometreThread()
    
*/
public class AlgorithmeDeBase 
{
    //Constante représentant une longueur nulle pour une chaine de caractères.
    public static final int NUL = 0;
     
    private volatile boolean finDeTraitement = false;
    private ChronometreThread chronometre;

    /*************************************
      CONSTRUCTOR : AlgorithmeDeBase
     *************************************
    */
    public AlgorithmeDeBase()
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
            TYPE    : StringBuilder
            
        DESCRIPTION : chaine de caractères à comparer avec la chaine de
                      caractères contenue dans la variable "chaine2" afin de 
                      savoir si la chaine de caracteres contenue dans la 
                      variable "chaine1" est un anagramme de la chaine de
                      caractères contenue dans la variable "chaine2".
                  
    @param  chaine2 : 
            TYPE    : StringBuilder
         
        DESCRIPTION : chaine de caracteres qui sera comparée avec la chaine de
                      caracteres contenue dans la varaible "chaine1" afin de
                      déterminer si la chaine de caractères contenue dans la
                      variable "chaine1" est un anagramme de la chaine de
                      caractères contenue dans la variable "chaine2".
    
    
    @return         : 
     - true si "chaine1" est un anagramme de "chaine2".
     - false si "chaine1" n'est pas un anagramme de "chaine2".
       
    */
    public boolean estUnAnagramme(StringBuilder chaine1, StringBuilder chaine2)
    {
        //Appel à la méthode "run" de la classe "ChronometreThread"
        //a travers l'invocation de la méthode "start" de la classe
        //"ChronometreThread".En faisant cela, nous demarrons le chronometre.
        chronometre.start();
     
        for( int indice1 = 0; indice1 < chaine1.length(); indice1++ )
        {        
            //Récupérer un caractere de "chaine1".
            char caractere1 = chaine1.charAt(indice1);
            
            boolean trouve = false;
            
            for( int indice2 = 0;
                    indice2 < chaine2.length() && (!trouve); indice2++)
            {
                //Récupérer un caractère de "chaine2".
                char caractere2 = chaine2.charAt(indice2);
                
                if( caractere1 == caractere2 )
                {
                    chaine2.deleteCharAt(indice2);
                    trouve = true;
                }
            }
                
            //Si après avoir comparé un caractère de "chaine1" avec tous les
            //caractères de "chaine2", on s'apercoit que ce caractère de
            //"chaine1" est absent de "chaine2",alors, il est inutile de
            //vérifier si les autres caractères de "chaine1" sont inclus
            //dans "chaine2".On retourne alors "false".
            if(!trouve)
            {
                finDeTraitement = true;
                chronometre.setFinDeTraitement(finDeTraitement);
                return false;
            }
        
        }
        //Si après avoir retiré de "chaine2" les caractères présents à la
        //fois dans "chaine1" et "chaine2", la longueur de "chaine2" n'est pas
        //nulle, alors "chaine2" n'est pas égale à "chaine1".
        //Il faut donc retourner "false".
        if( chaine2.length() != NUL )
        {
            finDeTraitement = true;
            chronometre.setFinDeTraitement(finDeTraitement);
            return false;
        }
        
        finDeTraitement = true;
        chronometre.setFinDeTraitement(finDeTraitement);
        return true;
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
