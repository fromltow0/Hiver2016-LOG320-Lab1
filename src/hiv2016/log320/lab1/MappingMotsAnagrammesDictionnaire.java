
package hiv2016.log320.lab1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/*************************************
  CLASS         : MappingMotsAnagrammesDictionnaire
 *************************************
@author Louis Wilfried MBOG
    
*Description    : Cette classe determine pour chaque mot de la liste de mots le 
                  nombre d'anagrammes présents dans le dictionnaire.Elle utilise
                  pour cela la liste de mots pour lesquels on doit determiner
                  le nombre d'anagrammes presents dans le dictionnaire, la 
                  hashmap qui etablit la correspondance entre les ensembles de 
                  lettres presents dans le dictionnaire et le nombre 
                  d'anagrammes trouves dans le dictionnaire pour ces ensembles
                  de lettres, la liste de ces ensembles de lettres presents dans
                  le dictionnaire et pour lesquels on a determine le nombre
                  d'anagrammes presents dans le dictionnaire et l'algorithme
                  de detection d'anagrammes implemente dans la classe 
                  "deuxiemeAlgorithme".
                  
                      
*Constructors   :
    public MappingMotsAnagrammesDictionnaire(ArrayList<char[]> listeDeMots, 
                                             ArrayList<char[]> tableauMotsDictionnaire,
                                             HashMap motDictionnaire_nombreAnagrammes)
    

*Methods        :
    public void pretraiterFichiersTexte()
    public void compterAnagrammesDesMots()
    public void afficherResultats()
*/
public class MappingMotsAnagrammesDictionnaire 
{
    private static final String MSG_RESULTAT_1 = "Il y a ";
    private static final String MSG_RESULTAT_2 = " anagrammes du mot ";
    private static final String MSG_RESULTAT_3 = "Il y a un total de  ";
    private static final String MSG_RESULTAT_4 = " anagrammes ";
    private static final String TEMPS_EXECUTION = " Temps execution : ";
    
    //Liste contenant les mots pour lesquels on veut determiner le nombre
    //d'anagrammes dans le dictionnaire.
    private ArrayList<char[]> listeDeMots;
    
    //Structure de donnees contenant les mots du dictionnaire.
    private ArrayList<char[]> motsDuDictionnaire;
    
    
    //Hashmap etablissant la correspondance entre chaque mot de la liste de mots
    //et le nombre d'anagrammes presents dans le dictionnaire pour chacun de ces
    //mots.
    private HashMap mot_nombreAnagrammes;
    
    //Objet contenant l'algorithme de detection d'anagrammes.
    private DeuxiemeAlgorithme algorithme2;
    
    //Objet contenant les operations de pretraitement des fichiers texte.
    private TextFileProcessing traitementFichiersTexte;
    
    //Chronometre charge d'evaluer le temps d'execution de la fonction
    //"compterAngagrammesDesMots".
    private ChronometreThread chronometre;
    
    /*************************************
      CONSTRUCTOR  : MappingMotsAnagrammesDictionnaire
     *************************************
    */
    public MappingMotsAnagrammesDictionnaire()
    {
        //Initialisation du module de traitement des fichiers texte.
        traitementFichiersTexte = new TextFileProcessing();
        
        //Initialisation de la hashmap qui etablit la correspondance
        //entre chaque mot de laliste de mots et le nombres d'anagrammes
        //de ce mot presents dans le dictionnaire.
        mot_nombreAnagrammes = new HashMap<char[], Integer>();
        
        //Initialisation du module contenant l'algorithme de detection 
        //d'anagrammes.
        algorithme2 = new DeuxiemeAlgorithme();
        
        boolean finDeTraitement = false;
        chronometre = new ChronometreThread(finDeTraitement);
    }
    
     /*************************************
      FUNCTION      : pretraiterFichiersTexte
     *************************************
    *Description    : Cette fonction prepare les donnees a etre utilisees par
                      la fonction "compterAnagrammesDesMots".
                      
    @param  aucun   : 
     
    @return         : 
     - void.
       
    */
    public void pretraiterFichiersTexte()
    {
        traitementFichiersTexte.creerListeDeMotsEnMemoire();
        traitementFichiersTexte.creerListeDeMotsDictionnaire();
       
        
        listeDeMots = traitementFichiersTexte.getListeDeMots();
        motsDuDictionnaire = traitementFichiersTexte.getMotsDuDictionnaire();
        
    }
    
    /*************************************
      FUNCTION      : compterAnagrammesDesMots()
     *************************************
    *Description    : Cette fonction determine pour chaque mot de la liste de
                      mots le nombre d'anagrammes present dans le dictionnaire.
                      Elle utilise la hashmap qui etablit la correspondance
                      entre les ensembles de lettres qui constituent un mot 
                      present dans le dictionnaire et le nombre d'anagrammes 
                      de ce mot dans le dictionnaire.
                      
    @param  aucun   : 
     
    @return         : 
     - void.
       
    */
    public void compterAnagrammesDesMots()
    {
        //Demarrer le chronometre.
        chronometre.start();
        
        for(int i = 0; i < listeDeMots.size(); i++)
        {
            //Récupérer chaque mot de la liste de mots
            char[] mot1 = listeDeMots.get(i);
            
            int nombreAnagrammes = 0;
            
            for(int j = 0; j < motsDuDictionnaire.size(); j++)
            {
                //Récupérer chaque mot du dictionnaire
                char[] mot2 = motsDuDictionnaire.get(j);
                
                boolean anagramme;
                
                //Verifier que chaque mot du dictionnaire est un anagramme du 
                //mot de la liste de mots.
                anagramme = algorithme2.estUnAnagramme(mot2, mot1);
                
                if(anagramme == true)
                {
                    nombreAnagrammes += 1;
                }
            }
            
            mot_nombreAnagrammes.put(mot1, nombreAnagrammes);  
        }  
        
        boolean finDeTraitement = true;
        chronometre.setFinDeTraitement(finDeTraitement);
    }
    
     /*************************************
      FUNCTION      : afficherResultats()
     *************************************
    *Description    : Cette fonction affiche pour chaque mot de la liste de
                      mot, le nombre d'anagrammes presents dans le dictionnaire
                      ainsi que le nombre total d'anagrammes trouves.
                      
    @param  aucun   : 
     
    @return         : 
     - void.
       
    */
    public void afficherResultats()
    {
        Set ensemble = mot_nombreAnagrammes.entrySet();
        int nbTotalAnagrammes = 0;
        
        Iterator iterateur = ensemble.iterator();
        
        while( iterateur.hasNext() )
        {
            Map.Entry entree = (Map.Entry) iterateur.next();
            
            //Conversion de la cle en tableau de caracteres.
            char[] mot = (char[])entree.getKey();
            
            //Conversion du tableau de caracteres en chaine de caracteres.
            String motDeLaListe = String.valueOf(mot);
            
            System.out.println( MSG_RESULTAT_1 + entree.getValue() + MSG_RESULTAT_2 + motDeLaListe );
            nbTotalAnagrammes += (int)entree.getValue();
        }
        
        System.out.println(MSG_RESULTAT_3 + nbTotalAnagrammes + MSG_RESULTAT_4);
        System.out.print(TEMPS_EXECUTION);
        chronometre.afficherTempsExecution();
    }
    
}
