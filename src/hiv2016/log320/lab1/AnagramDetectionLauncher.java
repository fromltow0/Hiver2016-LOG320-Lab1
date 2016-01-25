
package hiv2016.log320.lab1;

/**
 *
 * @author Camx0
 */
public class AnagramDetectionLauncher 
{
    private MappingMotsAnagrammesDictionnaire correspondance;
    
    public AnagramDetectionLauncher()
    {
        correspondance = new MappingMotsAnagrammesDictionnaire();  
    }
    
    public static void main(String args[])
    {
        AnagramDetectionLauncher launcher = new AnagramDetectionLauncher();
        
        launcher.start();
    }
    
    public void start()
    {
        correspondance.pretraiterFichiersTexte();
        correspondance.compterAnagrammesDesMots();
        correspondance.afficherResultats();
    }
    
}
