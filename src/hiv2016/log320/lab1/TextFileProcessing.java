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
    
    private Path cheminMots;
    private Path cheminDictionnaire;
    
    private BufferedReader bufferMots;
    private BufferedReader bufferDictionnaire;
    
    private ArrayList<char[]> listeDeMots;
    private ArrayList<char[]> motsDuDictionnaire;
    
    private HashMap mot_nombreAnagrammes;
    
    private DeuxiemeAlgorithme algorithme2;
    
    public static void main(String[] args)
    {
        TextFileProcessing traitement = new TextFileProcessing();
        
        traitement.creerListeDeMotsEnMemoire();
        traitement.creerListeDeMotsDictionnaire();
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
            
            //Initialisation de la hashmap mots-nombreAnagramme.
            mot_nombreAnagrammes = new HashMap();    
            
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
                      texte contenant les mots à analyser, convertit ces mots
                      en tableaux de caracteres et place ces tableaux de
                      caractères dans la structure de données représentant notre
                      liste de mots.
                      
    @param  aucun : 
     
    @return       : 
     - void.
       
    */
    public void creerListeDeMotsEnMemoire() 
    {
        String ligneActuelle;
        
        try
        {
            while ( (ligneActuelle = bufferMots.readLine() ) != null)
            {
		listeDeMots.add( ligneActuelle.toCharArray() );   
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
                      contenus dans ces mots, convertit ces mots
                      en tableaux de caracteres et place ces tableaux de
                      caractères dans la structure de données chargée de
                      contenir les mots du dictionnaire.
                      
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
                    String nouvelleLigne2 = nouvelleLigne.toLowerCase();
                    
                    motDuDictionnaire = nouvelleLigne2.toCharArray();
                    
                    motsDuDictionnaire.add(motDuDictionnaire);
                }
                else
                {
                    String ligneActuelle2 = ligneActuelle.toLowerCase();
                    motDuDictionnaire = ligneActuelle2.toCharArray();
                    
                    motsDuDictionnaire.add(motDuDictionnaire);
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
