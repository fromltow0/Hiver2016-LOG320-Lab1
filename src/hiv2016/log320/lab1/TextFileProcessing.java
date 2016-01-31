package hiv2016.log320.lab1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/*************************************
  CLASS         : TextFileProcessing
 *************************************
@author Louis Wilfried MBOG
    
*Description    : Cette classe effectue le traitement des fichiers texte.Elle
                  transforme les données contenues dans les fichiers texte de
                  façon à les rendre directement exploitables par un programme
                  JAVA.               
                                   
*Constructors   :
    public TextFileProcessing()

*Methods        :
    public void creerListeDeMotsEnMemoire()
    public void creerListeDeMotsDictionnaire()
    public ArrayList<char[]>getListeDeMots()
    public ArrayList<char[]> getMotsDuDictionnaire()
    
*/
public class TextFileProcessing 
{
    private static final String MOTS = "\\Ressources\\words.txt";
    private static final String DICTIONNAIRE = "\\Ressources\\dict.txt";
    private static final char ESPACE = ' ';
    private static final int MOINS_UN = -1;
    private static final int ZERO = 0;
    private static final char A_MAJUSCULE = 'A';
    private static final char Z_MAJUSCULE = 'Z';
    
    private Path cheminMots;
    private Path cheminDictionnaire;
    
    private BufferedReader bufferMots;
    private BufferedReader bufferDictionnaire;
    
    //Cette variable contient la liste de mots pour lesquels on veut determiner
    //le nombre d'anagrammes presents dans le dictionnaire.
    private ArrayList<char[]> listeDeMots;
    
    //Cette variable contient tous les mots du dictionnaire.
    private ArrayList<char[]> motsDuDictionnaire;
   
    
    //Objet contenant l'algorithme de detection d'anagrammes.
    private DeuxiemeAlgorithme algorithme2;
    
    /*************************************
      CONSTRUCTOR  : TextFileProcessing
     *************************************
    *Description   : Ce constructeur crée un objet correspondant au
                     chemin d'accès du fichier qui contient les mots à
                     analyser afin de déterminer s'ils ont des 
                     anagrammes dans le fichier texte qui représente le
                     dictionnaire.Le constructeur crée aussi un objet
                     correspondant au chemin d'accès du fichier texte
                     qui représente le dictionnaire.
                     Ensuite, des tampons de lecture sont créés afin de
                     lire les chaines de caractères contenues dans le
                     fichier texte qui contient les mots à analyser
                     et dans le fichier texte qui represente notre
                     dictionnaire.
                                      
    *Parameters    :
    @param  aucun  : 
 
    */
    public TextFileProcessing()
    {
        try 
        {
            File repertoireActuel = new File (".");
            
            //Creation du chemin absolu d'acces au fichier qui contient les
            //mots pour lesquels on doit déterminer le nombre d'anagrammes
            //contenus dans le dictionnaire.
            cheminMots = Paths.get( repertoireActuel.getCanonicalPath() + MOTS);
            
            //Creation du chemin absolu d'acces au fichier qui represente notre
            //dictionnaire.
            cheminDictionnaire = Paths.get( repertoireActuel.getCanonicalPath() + DICTIONNAIRE);
            
            //Creation des bufferedReader permettant d'extraire des chaines de
            //caracteres d'un fichier texte.
            bufferMots = new BufferedReader( new FileReader( cheminMots.toString() ) );
            bufferDictionnaire = new BufferedReader( new FileReader( cheminDictionnaire.toString() ) );
            
            //Initialisation de la liste de mots.
            listeDeMots = new ArrayList<char[]>();
            
            //Initialisation de la liste de mots du dictionnaire.
            motsDuDictionnaire = new ArrayList<char[]>();
            
            algorithme2 = new DeuxiemeAlgorithme();
            
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(TextFileProcessing.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /*************************************
      FUNCTION      : creerListeDeMotsEnMemoire
     *************************************
    *Description    : Cette fonction lit les mots contenus dans le fichier
                      texte contenant les mots à analyser, élimine les espaces
                      contenus dans ces mots,convertit ces mots en tableaux de
                      caracteres et place ces tableaux de caractères dans la
                      structure de données représentant notre liste de mots.
                      
    @param  aucun   : 
     
    @return         : 
     - void.
       
    */
    public void creerListeDeMotsEnMemoire() 
    {
        String ligneActuelle;
        char[] motDeLaListeDeMots;
        
        try
        {
            while ( (ligneActuelle = bufferMots.readLine() ) != null)
            { 
                //Si la chaine de caractères contient un espace, alors
                //supprimer cet espace de la chaine.
                if( ligneActuelle.indexOf(ESPACE) != MOINS_UN )
                {
                    int indexOfEspace = ligneActuelle.indexOf(ESPACE);
                    
                    String nouvelleLignePartie1 = ligneActuelle.substring(ZERO , indexOfEspace);
                    String nouvelleLignePartie2 = ligneActuelle.substring(indexOfEspace + 1);
                    String nouvelleLigne = nouvelleLignePartie1.concat(nouvelleLignePartie2);
                    
                    motDeLaListeDeMots = nouvelleLigne.toCharArray();   
                }  
                else
                {
                    motDeLaListeDeMots = ligneActuelle.toCharArray();
                }
                
                listeDeMots.add(motDeLaListeDeMots);
                    
            }
        }
        catch(IOException ex)
        {
        
        }   
    }
    
    /*************************************
      FUNCTION      : creerListeDeMotsDictionnaire
     *************************************
    *Description    : Cette fonction lit les mots contenus dans le fichier
                      texte représentant le dictionnaire,élimine les espaces
                      contenus dans ces mots,convertit ces mots en tableaux de
                      caracteres et place ces tableaux de caractères dans la
                      structure de données chargée de contenir les mots du 
                      dictionnaire.
                      
    @param  aucun   : 
     
    @return         : 
     - void.
       
    */
     public void creerListeDeMotsDictionnaire() 
    {
        String ligneActuelle;
        char[] motDuDictionnaire;
        
        try
        {
            while ( (ligneActuelle = bufferDictionnaire.readLine() ) != null)
            {
                //Si la chaine de caractères contient un espace, alors
                //supprimer cet espace de la chaine.
                if( ligneActuelle.indexOf(ESPACE) != MOINS_UN )
                {
                    int indexOfEspace = ligneActuelle.indexOf(ESPACE);
                    
                    String nouvelleLignePartie1 = ligneActuelle.substring(ZERO , indexOfEspace);
                    String nouvelleLignePartie2 = ligneActuelle.substring(indexOfEspace + 1);
                    String nouvelleLigne = nouvelleLignePartie1.concat(nouvelleLignePartie2);
                    
                    motDuDictionnaire = nouvelleLigne.toCharArray();
                    
                    int i = 0;
                    
                    /*
                    Cette variable prend la valeur "true" lorsqu'un mot
                    qu'on veut inserer dans la liste de mots du dictionnaire
                    contient un caractere en majuscule.Elle prend la valeur
                    "false" dans le cas contraire.
                    */
                    boolean majuscule = false;
                    while( i < motDuDictionnaire.length )
                    {
                       
                        if( (motDuDictionnaire[i] >= A_MAJUSCULE) 
                                &&  (motDuDictionnaire[i] <= Z_MAJUSCULE) )
                        {
                            majuscule = true;
                        }
                        
                        i += 1;
                    }
                    
                    //S'il n'y a pas de majuscule, on ajoute le mot à la liste
                    //de mots du dictionnaire.
                    if( !majuscule )
                    {
                        motsDuDictionnaire.add(motDuDictionnaire);
                    }
                }
                else
                {
                    motDuDictionnaire = ligneActuelle.toCharArray();
                    
                    int i = 0;
                    
                    /*
                    Cette variable prend la valeur "true" lorsqu'un mot
                    qu'on veut inserer dans la liste de mots du dictionnaire
                    contient un caractere en majuscule.Elle prend la valeur
                    "false" dans le cas contraire.
                    */
                    boolean majuscule = false;
                    while( i < motDuDictionnaire.length )
                    {
                       
                        if( (motDuDictionnaire[i] >= A_MAJUSCULE)
                                &&  (motDuDictionnaire[i] <= Z_MAJUSCULE)  )
                        {
                            majuscule = true;
                        }
                        
                        i += 1;
                    }
                    
                    //S'il n'y a pas de majuscule, on ajoute le mot à la liste
                    //de mots du dictionnaire.
                    if( !majuscule )
                    {
                        motsDuDictionnaire.add(motDuDictionnaire);
                    }
                }
            }
        }
        catch(IOException ex)
        {
        
        }   
    }
    
    /*************************************
      FUNCTION   : getListeDeMots
     *************************************
    *Description : Cette fonction retourne la liste de mots à analyser. 
                      
    *Parameters  :
     - aucun
     
    @return      : 
     - ArrayList<char[]>.
       
    */
    public ArrayList<char[]>getListeDeMots()
    {
        return listeDeMots;
    }
    
    
    /*************************************
      FUNCTION   : getTableauMotsDictionnaire
     *************************************
    *Description : Cette fonction retourne le tableau contenant les mots du
                   dictionnaire presents dans la hashmap "motDictionnaire_
                   nombreAnagrammes".
                      
    *Parameters  :
     - aucun
     
    @return      : 
     - ArrayList<char[]>.
       
    */
    public ArrayList<char[]> getMotsDuDictionnaire()
    {
        return motsDuDictionnaire;
    }
    
}
