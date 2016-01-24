/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hiv2016.log320.lab1;

import java.util.ArrayList;
import java.util.HashMap;

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
    public void creerListeDeMotsEnMemoire()
    public void creerListeDeMotsDictionnaire()
    public ArrayList<char[]>getListeDeMots()
    
*/
public class MappingMotsAnagrammesDictionnaire 
{
    private ArrayList<char[]> listeDeMots;
    private ArrayList<char[]> tableauMotsDictionnaire;
    
    private HashMap motDictionnaire_nombreAnagrammes;
    private HashMap mot_nombreAnagrammes;
    
    private DeuxiemeAlgorithme algorithme2;
    
    public MappingMotsAnagrammesDictionnaire(ArrayList<char[]> listeDeMots, 
                                             ArrayList<char[]> tableauMotsDictionnaire,
                                             HashMap motDictionnaire_nombreAnagrammes)
    {
        this.listeDeMots = listeDeMots;
        this.tableauMotsDictionnaire = tableauMotsDictionnaire;
        this.motDictionnaire_nombreAnagrammes = motDictionnaire_nombreAnagrammes;
        
        mot_nombreAnagrammes = new HashMap();
        algorithme2 = new DeuxiemeAlgorithme();
    }
    
    public void compterAnagrammesDeUnMot()
    {
        for(int i = 0; i < listeDeMots.size(); i++)
        {
            //Récupérer chaque mot de la liste de mots
            char[] mot1 = listeDeMots.get(i);
            
            //Cette variable représente le nombre d'anagrammes
            //de chaque mot de la liste de mots.
            int nombreAnagrammes = 0;
            
            System.out.println("------------" + String.valueOf(mot1));
            
            //Déterminer si chaque mot du dictionnaire est un anagramme
            //du mot stocké dans la variable "mot1".
            for(int j = 0; j < tableauMotsDictionnaire.size(); j++)
            {
                char[] mot2 = tableauMotsDictionnaire.get(j);
                
                boolean anagramme = algorithme2.estUnAnagramme(mot2, mot1);
                
                //Si un mot du dictionnaire est un anagramme du mot stocké
                //dans la variable "mot1",
                if(anagramme)
                {
                    //alors incrémenter le nombre d'anagrammes de 1.
                    nombreAnagrammes += 1;
                    System.out.println(anagramme);
                }
                
                  
            }
            /*
            Après avoir déterminé le nombre d'anagrammes d'un mot de la liste
            de mots, on établit une correspondance entre un mot de la liste de
            mots et le nombre d'anagrammes trouvés pour ce mot.Pour cela on
            utilise une hashmap qui fait correspondre à chaque mot de la liste
            de mots un nombre qui représente le nombre d'anagrammes qui
            correspondent à ce mot et qui sont présents dans le dictionnaire.
            */
            mot_nombreAnagrammes.put(mot1, nombreAnagrammes);
            System.out.println("mot: " + String.valueOf(mot1) + "----" + "nombre anagrammes: " + nombreAnagrammes);
        }  
        
        System.out.println(tableauMotsDictionnaire.size());
    }
    
}
