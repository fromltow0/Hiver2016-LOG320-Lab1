package hiv2016.log320.lab1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
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
                  JAVA.Les pretraitements effectues sont les suivants:
                  1-Recuperation des donnees contenues dans les fichiers texte.
                  2-Pour chaque ensemble de lettres present dans le dictionnaire
                  et a partir duquel est forme un ou plusieurs mots du
                  dictionnaire,il ya determination du nombred'anagrammes presents
                  dans le dictionnaire et etablissement d'une correspondance
                  entre cet ensemble de lettres et son nombre d'anagrammes grace
                  a une hashmap.
                  3-Creation d'une structure de donnees contenant les mots pour
                  lesquels on veut trouver le nombre d'anagrammes presents dans le
                  dictionnaire.
                  4-Creation d'une structure de donnees contenant les mots du
                  dictionnaire pour lesquels on a determine le nombre
                  d'anagrammes presents dans le dictionnaire.Cette structure
                  de donnees optimisera le processus de detection du nombre 
                  d'anagrammes pour chaque mot de notre liste de mots dans 
                  laquelle se trouvent les mots pour lesquels on desire
                  connaitre le nombre d'anagrammes presents dans le
                  dictionnaire.
                 
                                   
*Constructors   :
    public TextFileProcessing()

*Methods        :
    public void creerListeDeMotsEnMemoire()
    public void creerListeDeMotsDictionnaire()
    public void determinerAnagrammesDictionnaire()
    public void remplirTableauMotsDictionnaire()
    public ArrayList<char[]>getListeDeMots()
    public HashMap getMotDictionnaire_nombreAnagrammes()
    public ArrayList<char[]> getTableauMotsDictionnaire()
    
*/
public class TextFileProcessing 
{
    private static final String MOTS = "\\Ressources\\words.txt";
    private static final String DICTIONNAIRE = "\\Ressources\\dict.txt";
    private static final char ESPACE = ' ';
    private static final int MOINS_UN = -1;
    private static final int ZERO = 0;
    private static final char CARACTERE_A = 'a';
    
    private Path cheminMots;
    private Path cheminDictionnaire;
    
    private BufferedReader bufferMots;
    private BufferedReader bufferDictionnaire;
    
    //Cette variable contient la liste de mots pour lesquels on veut determiner
    //le nombre d'anagrammes presents dans le dictionnaire.
    private ArrayList<char[]> listeDeMots;
    
    //Cette variable contient tous les mots du dictionnaire.
    private ArrayList<char[]> motsDuDictionnaire;
    
    //Cette variable contient les ensembles de lettres qui constituent des mots
    //qui sont presents dans le dictionnaire et pour lesquels on a determine
    //le nombre d'anarammes presents dans le dictionnaire.
    ArrayList<char[]> tableauMotsDictionnaire;
    
    private HashMap<char[], Integer> motDictionnaire_nombreAnagrammes;
    
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
            
            tableauMotsDictionnaire = new ArrayList<char[]>();    
            
            //Initialisation de la hashmap motDictionnaire_nombreAnagrammes.
            motDictionnaire_nombreAnagrammes = new HashMap<char[], Integer>();
            
            algorithme2 = new DeuxiemeAlgorithme();
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(TextFileProcessing.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /*************************************
      FUNCTION  : creerListeDeMotsEnMemoire
     *************************************
    *Description    : Cette fonction lit les mots contenus dans le fichier
                      texte contenant les mots à analyser, élimine les espaces
                      contenus dans ces mots,convertit ces mots en tableaux de
                      caracteres et place ces tableaux de caractères dans la
                      structure de données représentant notre liste de mots.
                      
    @param  aucun : 
     
    @return       : 
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
                    
                    int i = 0;
                    
                    /*
                    Cette variable prend la valeur "true" lorsque un mot
                    qu'on veut inserer dans la liste de mots du dictionnaire
                    contient un caractere en majuscule.Elle prend la valeur
                    "false" dans le cas contraire.
                    */
                    boolean majuscule = false;
                    while( i < motDeLaListeDeMots.length )
                    {
                        int difference = 0;
                        
                        /*
                        On recupere le code ASCII de chaque caractere et on 
                        soustrait ce code ASCII au code ASCII du caractere
                        'a'.
                        */
                        difference = motDeLaListeDeMots[i] - CARACTERE_A;
                        
                        /*
                        Si le resultat de cette soustraction est un
                        nombre negatif, alors on est en presence d'un 
                        caractere en majuscule.La variable "majuscule" prend
                        alors la valeur "true".
                        */
                        if( difference < ZERO )
                        {
                            majuscule = true;
                        }
                        
                        i += 1;
                    }
                    
                    //S'il n'y a pas de majuscule, on ajoute le mot à la liste
                    //de mots pour lesquels on veut determiner le nombre
                    //d'anagrammes presents dans le dictionnaire.
                    if( !majuscule )
                    {
                        listeDeMots.add(motDeLaListeDeMots);
                    }    
                }
                else
                {
                    motDeLaListeDeMots = ligneActuelle.toCharArray();
                    
                    int i = 0;
                    
                    /*
                    Cette variable prend la valeur "true" lorsque un mot
                    qu'on veut inserer dans la liste de mots contient un
                    caractere en majuscule.Elle prend la valeur "false" dans
                    le cas contraire.
                    */
                    boolean majuscule = false;
                    while( i < motDeLaListeDeMots.length )
                    {
                        int difference = 0;
                        
                        /*
                        On recupere le code ASCII de chaque caractere et on 
                        soustrait ce code ASCII au code ASCII du caractere
                        'a'.
                        */
                        difference = motDeLaListeDeMots[i] - CARACTERE_A;
                        
                        /*
                        Si le resultat de cette soustraction est un
                        nombre negatif, alors on est en presence d'un 
                        caractere en majuscule.La variable "majuscule" prend
                        alors la valeur "true".
                        */
                        if( difference < ZERO )
                        {
                            majuscule = true;
                        }
                        
                        i += 1;
                    }
                    
                    //S'il n'y a pas de majuscule, on ajoute le mot à la liste
                    //de mots.
                    if( !majuscule )
                    {
                       listeDeMots.add(motDeLaListeDeMots);
                    }    
                }
            }
        }
        catch(IOException ex)
        {
        
        }   
    }
    
    /*************************************
      FUNCTION  : creerListeDeMotsDictionnaire
     *************************************
    *Description    : Cette fonction lit les mots contenus dans le fichier
                      texte représentant le dictionnaire,élimine les espaces
                      contenus dans ces mots,convertit ces mots en tableaux de
                      caracteres et place ces tableaux de caractères dans la
                      structure de données chargée de contenir les mots du 
                      dictionnaire.
                      
    @param  aucun : 
     
    @return       : 
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
                        int difference = 0;
                        
                        /*
                        On recupere le code ASCII de chaque caractere et on 
                        soustrait ce code ASCII au code ASCII du caractere
                        'a'.
                        */
                        difference = motDuDictionnaire[i] - CARACTERE_A;
                        
                        /*
                        Si le resultat de cette soustraction est un
                        nombre negatif, alors on est en presence d'un 
                        caractere en majuscule.La variable "majuscule" prend
                        alors la valeur "true".
                        */
                        if( difference < ZERO )
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
                        int difference = 0;
                        
                        /*
                        On recupere le code ASCII de chaque caractere et on 
                        soustrait ce code ASCII au code ASCII du caractere
                        'a'.
                        */
                        difference = motDuDictionnaire[i] - CARACTERE_A;
                        
                        /*
                        Si le resultat de cette soustraction est un
                        nombre negatif, alors on est en presence d'un 
                        caractere en majuscule.La variable "majuscule" prend
                        alors la valeur "true".
                        */
                        if( difference < ZERO )
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
      FUNCTION  : determinerAnagrammesDictionnaire
     *************************************
    *Description : Cette fonction determine pour chaque ensemble de lettres 
                   constituant un mot du dictionnaire le nombre d'anagrammes
                   presents dans le dictionnaire.
                      
    *Parameters  :
     - aucun
     
    @return      : 
     - void.
       
    */
    public void determinerAnagrammesDictionnaire()
    {
        /*
        On verifie d'abord si pour un ensemble de lettres qui constitue un mot
        du dictionnaire, le nombre d'anagrammes présents dans le dictionnaire
        a déja été trouvé.Si le nombre d'anagrammes a déja été trouvé on ne 
        le calcule plus et on passe au mot suivant.Exemple:Dans le 
        dictionnaire, nous avons les mots "aze", "zea", "eza", "qsd", "aze".
        Nous commencons par chercher le nombre d'anagrammes du mot "aze".Nous
        trouvons 4 anagrammes.Ensuite, nous cherchons le nombre d'anagrammes
        pour le mot "zea".A ce moment, nous nous rendons compte que "aze" et
        "zea" représentent le meme ensemble de lettres et possèdent donc le 
        meme nombre d'anagrammes.Vu que nous avons déja déterminé le nombre
        d'anagrammes pour l'ensemble constitué des lettres 'a', 'z', 'e',
        il est inutile de chercher le nombre d'anagrammes pour "zea".Si
        l'ensemble de lettres constituant le mot pour lequel on cherche le 
        nombre d'anagrammes était différent de l'ensemble contenant les
        lettres 'a', 'z', 'e', nous aurions constaté que le nombre
        d'anagrammes de cet ensemble n'a pas encore été déterminé et nous
        l'aurions calculé.Après avoir travaillé avec "zea", nous passons au
        mot suivant qui est "eza" et nous repetons le meme processus.
        */
        for(int i = 0; i < motsDuDictionnaire.size(); i++)
        {
            //Cette variable represente le nombre d'anagrammes d'un mot
            //du dictionnaire.
            int nombreAnagrammes = 0;
            
            //Recuperer chaque mot du dictionnaire.
            char[] mot1 = motsDuDictionnaire.get(i);
            
            
            int j = i - 1;
            
            //Si pour un ensemble de mots, le nombre d'anagrammes a déja été
            //déterminé, cette variable prend la valeur "true", sinon elle
            //prend la valeur "false".
            boolean anagrammeDejaEvalue = false;
            
            while( j >= 0 && (!anagrammeDejaEvalue) )
            {
                //Recuperer chaque mot precedant le mot pour lequel on 
                //veut determiner le nombre d'anagrammes.
                char[] mot2 = motsDuDictionnaire.get(j);
                
                //Determiner si chaque mot qui précède le mot pour lequel
                //on veut determiner le nombre d'anagrammes est un anagramme
                //du mot pour lequel on veut determiner le nombre d'anagramme.
                anagrammeDejaEvalue = algorithme2.estUnAnagramme(mot2, mot1);
                
                j -= 1;
            }
            
            //Si on n'a pas encore trouve le nombre d'anagrammes du mot pour
            //lequel on veut determiner le nombre d'anagrammes
            if( !anagrammeDejaEvalue )
            {
                /*
                On est certain que les mots précédant le mot pour lequel on
                veut determiner le nombre d'anagrammes ne sont pas des
                anagrammes du mot pour lequel on veut determiner le nombre
                d'anagrammes.On debute donc la recherche d'anagrammes dans la
                structure contenant les mots du dictionnaire à partir de
                l'indice d'acces au mot  pour lequel on veut determiner le
                nombre d'anagrammes.
                */
                for(int k = i; k < motsDuDictionnaire.size(); k++)
                {
                    char[] mot3 = motsDuDictionnaire.get(k);
                    
                    boolean anagramme = algorithme2.estUnAnagramme(mot3, mot1);
                    
                    if( anagramme )
                    {
                        nombreAnagrammes += 1;
                    }   
                }
            } 
            
            //On utilise un hashmap pour établir une correspondance entre
            //un mot du dictionnaire et son nombre d'anagrammes dans le
            //dictionnaire.
            if(nombreAnagrammes > ZERO)
            {
                String motDico = String.valueOf(mot1);
                
                Integer leNombreAnagrammes = new Integer(nombreAnagrammes );
                
                char[] unMotDuDictionnaire = motDico.toCharArray();
                motDictionnaire_nombreAnagrammes.put( unMotDuDictionnaire, leNombreAnagrammes );
            }
        } 
    }
    
    /*************************************
      FUNCTION  : remplirTableauMotsDictionnaire
     *************************************
    *Description : Cette fonction crée un tableau contenant les mots du dictionnaire
                   qui figurent dans la hashmap qui etablit la correspondance entre
                   un mot du dictionnaire et le nombre d'anagrammes de ce mot dans 
                   le dictionnaire.La hashmap qui etablit la correspondance entre 
                   un mot du dictionnaire et le nombre d'anagrammes de ce mot dans
                   le dictionnaire doit etre au prealable etablie avant de pouvoir 
                   appeler cette fonction.Ce tableau sera utilise pour reduire
                   le temps de detection du nombre d'anagrammes de chaque mot de la
                   la liste de mots.
                      
    *Parameters  :
     - aucun
     
    @return      : 
     - void.
       
    */
    public void remplirTableauMotsDictionnaire()
    {
        Set ensemble = motDictionnaire_nombreAnagrammes.entrySet();
        
        Iterator i = ensemble.iterator();
    
        while( i.hasNext() )
        {
            //Recuperer une entree de la hashmap.
            Map.Entry entree = (Map.Entry)i.next();
            
            //Recuperer la cle de l'entree que l'on a recuperee precedemment
            //et convertir cette cle en tableau de caracteres.
            char[] tableauCaracteres = (char[]) entree.getKey();
            
            //Convertir le tableau de caracteres en chaines de caracteres.
            String motDico = String.valueOf(tableauCaracteres);
            
            //Convertir la chaine de caracteres en tableau de caracteres.
            char[] motDuDictionnaire = motDico.toCharArray();
            
            //Ajouter le tableau de caracteres  au tableau.
            tableauMotsDictionnaire.add(motDuDictionnaire);
        }
    }
    
    /*************************************
      FUNCTION  : getListeDeMots
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
      FUNCTION  : getMotDictionnaire_nombreAnagrammes
     *************************************
    *Description : Cette fonction retourne la hashmap qui fait la correspondance
                   entre chaque mot du dictionnaire et le nombre d'anagrammes
                   de chaque mot du dictionnaire. 
                      
    *Parameters  :
     - aucun
     
    @return      : 
     - HashMap.
       
    */
    public HashMap getMotDictionnaire_nombreAnagrammes()
    {
        return motDictionnaire_nombreAnagrammes;
    }
    
    /*************************************
      FUNCTION  : getTableauMotsDictionnaire
     *************************************
    *Description : Cette fonction retourne le tableau contenant les mots du
                   dictionnaire presents dans la hashmap "motDictionnaire_
                   nombreAnagrammes".
                      
    *Parameters  :
     - aucun
     
    @return      : 
     - ArrayList<char[]>.
       
    */
    public ArrayList<char[]> getTableauMotsDictionnaire()
    {
        return tableauMotsDictionnaire;
    }
}
