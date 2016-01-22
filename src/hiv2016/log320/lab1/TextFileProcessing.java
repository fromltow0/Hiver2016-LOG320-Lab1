package hiv2016.log320.lab1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
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
    
    private ArrayList<char[]> listeDeMots;
    private ArrayList<char[]> motsDuDictionnaire;
    
    private HashMap mot_nombreAnagrammes;
    
    private HashMap motDictionnaire_nombreAnagrammes;
    
    private DeuxiemeAlgorithme algorithme2;
    
    public static void main(String[] args)
    {
        TextFileProcessing traitement = new TextFileProcessing();
        
        traitement.creerListeDeMotsEnMemoire();
        traitement.creerListeDeMotsDictionnaire();
        traitement.determinerAnagrammesDictionnaire();
        traitement.compterAnagrammesDeUnMot();
    }
    
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
                     lire les chaines de caractères contenus dans le
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
            
            //Initialisation de la hashmap mot-nombreAnagrammes.
            mot_nombreAnagrammes = new HashMap();    
            
            //Initialisation de la hashmap motDictionnaire_nombreAnagrammes.
            motDictionnaire_nombreAnagrammes = new HashMap();
            
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
                      contenus dans ces mots, mets ces mots en minuscules,
                      convertit ces mots en tableaux de caracteres et place ces
                      tableaux de caractères dans la structure de données 
                      représentant notre liste de mots.
                      
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
                    qu'on veut inserer dans la liste e mots du dictionnaire
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
                    //de mots du dictionnaire.
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
                    qu'on veut inserer dans la liste e mots du dictionnaire
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
                    //de mots du dictionnaire.
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
                      contenus dans ces mots, mets ces mots en minuscules,
                      convertit ces mots en tableaux de caracteres et place ces
                      tableaux de caractères dans la structure de données 
                      chargée de contenir les mots du dictionnaire.
                      
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
                    Cette variable prend la valeur "true" lorsque un mot
                    qu'on veut inserer dans la liste e mots du dictionnaire
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
                    Cette variable prend la valeur "true" lorsque un mot
                    qu'on veut inserer dans la liste e mots du dictionnaire
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
      FUNCTION  : compterAnagrammesDeUnMot
     *************************************
    *Description    : Cette fonction détermine pour chaque mot de la liste de 
                      mots, le nombre d'anagrammes présents dans le
                      dictionnaire.On détermine ici pour chaque ensemble
                      de lettres qui constituent un mot, le nombre d'anagrammes
                      présents dans le dictionnaire.Exemple: Si dans la liste de
                      mots, nous avons "aze" et dans le dictionnaire nous avons
                      "aze", "zea", "eza", alors la fonction détermine que pour
                      l'ensemble constitué des lettres 'a', 'z', 'e' il y a dans
                      le dictionnaire 3 anagrammes qui sont "aze", "zea" et 
                      "eza".Le mot "aze" est aussi compté comme un anagramme du
                      mot "aze".
                      
    @param  aucun : 
     
    @return       : 
     - void.
       
    */
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
            for(int j = 0; j < motsDuDictionnaire.size(); j++)
            {
                char[] mot2 = motsDuDictionnaire.get(j);
                
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
        
        System.out.println(motsDuDictionnaire.size());
    }
    
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
        d'anagrammes pour l'ensemble consititué des lettres 'a', 'z', 'e',
        il est inutile de chercher le nombre d'anagrammes pour "zea".Si
        l'ensemble de lettres constituant le mot pour lequel on cherche le 
        nombre d'anagrammes était différent de l'ensemble contenant les
        lettres 'a', 'z', 'e', nous aurions constaté que le nombre
        d'anagrammes de cet ensemble n'a pas encore été déterminé et nous
        l'aurions calculé.Après avoir travaillé avec "zea", nous passons au
        mot suivant qui est "eza" et nous repetons le meme processus.
        passons donc au mot suivant et repetons le meme processus.
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
                motDictionnaire_nombreAnagrammes.put( mot1, nombreAnagrammes );
            }
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
    
}
