
package hiv2016.log320.lab1;

/*************************************
  CLASS         : AnagramDetectionLauncher
 *************************************
@author Louis Wilfried MBOG
    
*Description    : Cette classe lance le programme de detection d'anagrammes.
                                    
*Constructors   :
    public AnagramDetectionLauncher()

*Methods        :
    public static void main(String args[])
    public start()
    
*/
public class AnagramDetectionLauncher 
{
    private MappingMotsAnagrammesDictionnaire correspondance;
    
    /*************************************
      CONSTRUCTOR  : AnagramDetectionLauncher
     *************************************
    */
    public AnagramDetectionLauncher()
    {
        correspondance = new MappingMotsAnagrammesDictionnaire();  
    }
    
    public static void main(String args[])
    {
        AnagramDetectionLauncher launcher = new AnagramDetectionLauncher();
        
        launcher.start();
    }
    
    /*************************************
      FUNCTION  : start
     *************************************
    *Description    : Cette fonction lance d'abord les operations de traitement
                      du fichier texte qui represente le dictionnaire et du
                      fichier texte qui contient les mots pour lesquels on veut
                      determiner le nombre d'anagrammmes presents dans le
                      dictionnaire,ensuite elle lance les operations de 
                      determination du nombre d'anagrammes de chaque mot de la
                      liste de mots et enfin elle lance les operations
                      d'affichage des resultats du processus de determination
                      du nombre d'anagrammes ainsi que le temps d'execution du
                      processus de determination du nombre d'anagrammes.
                      
                                      
    *Parameters     :
    @param  aucun   : 
     
    @return         : 
     - void.
       
    */
    public void start()
    {
        correspondance.pretraiterFichiersTexte();
        correspondance.compterAnagrammesDesMots();
        correspondance.afficherResultats();
    }
    
}
