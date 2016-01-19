package hiv2016.log320.lab1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TextFileProcessing 
{
    private static final String MOTS = "\\Ressources\\words.txt";
    private static final String DICTIONNAIRE = "\\Ressources\\dict.txt";
    
    private Path cheminMots;
    private Path cheminDictionnaire;
    
    private BufferedReader bufferMots;
    private BufferedReader bufferDictionnaire;
    private ArrayList<char[]> listeDeMots;
    
    public static void main(String[] args)
    {
        TextFileProcessing traitement = new TextFileProcessing();
        
        ArrayList<char[]> liste = traitement.getListeDeMots();
        
        traitement.creerListeDeMotsEnMemoire(liste);
        
    }
    
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
            
            listeDeMots = new ArrayList<char[]>();
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(TextFileProcessing.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void creerListeDeMotsEnMemoire(ArrayList<char[]> listeDeMots) 
    {
        String ligneActuelle;
        
        try
        {
            while ( (ligneActuelle = bufferMots.readLine() ) != null)
            {
		listeDeMots.add( ligneActuelle.toCharArray() );
                
            }
            System.out.println(listeDeMots.size());
        
        }
        catch(IOException ex)
        {
        
        }   
    }
    
    public ArrayList<char[]>getListeDeMots()
    {
        return listeDeMots;
    }
    
}
