
package hiv2016.log320.lab1;

/*************************************
  CLASS         : ChronometreThread
 *************************************
@author Louis Wilfried MBOG
    
*Description    : This class implements the timer which computes the running
                  time of a function.
                      
*Constructors   :
    public ChronometreThread(boolean finDeTraitement)

*Methods        :
    public void run()
    public void setFinDeTraitement(boolean finDeTraitement)
    public long getTempsExecution()
    public void afficherTempsExecution()
    
*/
public class ChronometreThread extends Thread
{
    private static final String MSG_TPS_EXEC = "Temps d\'execution : ";
    private static final String MSG_NANOSEC = " nanosecondes.";
    
    //Variable mise à "true" pour indiquer à la classe "ChronometreThread"
    //que l'exécution d'une série d'instructions s'est achevée.
    //Elle est mise à "false" pour indiquer à la classe "ChronometreThread"
    //que l'exécution d'une série d'instructions n'est pas achevée.
    private volatile boolean finDeTraitement = false;
    
    //Temps auquel l'execution des instructions debute.
    private long tempsDebut = 0l;
    
    //Temps auquel l'execution des instructions s'acheve.
    private long tempsFin = 0l;
    
    /*************************************
      CONSTRUCTOR  : ChronometreThread
     *************************************
    *Description    : 
                                      
    *Parameters             :
    @param  finDeTraitement : 
            TYPE            : boolean
            
        DESCRIPTION         : cette variable est mise à "true" pour indiquer la
                              fin d'exécution d'une suite d'instructions.Elle
                              est mise à "false" pour indiquer que ce n'est pas
                              encore la fin d'exécution d'une suite
                              d'instructions.
 
    */
    public ChronometreThread(boolean finDeTraitement)
    {
        this.finDeTraitement = finDeTraitement;
    }
    
    @Override
    public void run()
    {
        tempsDebut = System.nanoTime();
        
        while(!finDeTraitement)
        {
            
        
        }
        
        tempsFin = System.nanoTime();       
    }
    
    /*************************************
      PROCEDURE OF TYPE "SETTER"  : setFinDeTraitement
     *************************************
    *Description    : Cette procédure met la variable "finDeTraitement" à 
                      "true" pour indiquer la fin d'exécution d'un ensemble
                      d'instructions.Cette procédure est appelée au sein d'une
                      fonction d'une classe qui utilise la classe "Chronometre
                      Thread".
                      La fonction "run" de la classe "ChronometreThread"
                      s'exécute tant que la variable "finDeTraitement" est à 
                      "false".Lorsqu'une classe utilise la classe "Chronometre
                      Thread" pour estimer la durée d'exécution d'une de ses 
                      fonctions, la fonction dont on veut connaitre la durée 
                      d'exécution fait appel au tout début de son bloc à la
                      méthode "start" de la classe "ChronometreThread" pour
                      démarrer l'enregistrement de son temps d'exécution.Avant
                      un appel à l'instruction "return", cette meme fonction 
                      appelle la méthode "setFinDetraitement" à laquelle elle
                      passe en paramètre la valeur "true" pour indiquer à la 
                      classe "ChronometreThread" que son exécution s'achève.
                      Une fois que la variable "finDeTraitement" est mise à
                      "true", la classe "ChronometreThread" cesse d'exécuter
                      la boucle "while" incluse dans sa méthode "run".
                                      
    *Parameters             :
    @param  finDeTraitement : 
            TYPE            : boolean
            
        DESCRIPTION         : cette variable est mise à "true" pour indiquer la
                              fin d'exécution d'une suite d'instructions.Elle
                              est mise à "false" pour indiquer que ce n'est pas
                              encore la fin d'exécution d'une suite
                              d'instructions.
     
    
    
    @return                 : 
     - void.
       
    */
    public void setFinDeTraitement(boolean finDeTraitement)
    {
        this.finDeTraitement = finDeTraitement;
    }
    
    /*************************************
      FUNCTION  : getTempsExecution
     *************************************
    *Description    : Cette fonction retourne en nanosecondes le temps 
                      d'execution d'une serie d'instructions.
    *Parameters     :
     - aucun
     
    @return         : 
     - long.
       
    */
    public long getTempsExecution()
    {
        long tempsExecution = tempsFin - tempsDebut;
        
        return tempsExecution;
    }
    
    /*************************************
      PROCEDURE  : afficherTempsExecution
    *************************************
    *Description : Cette procedure affiche le temps 
                   d'execution d'une fonction.
    *Parameters  :
     - aucun
     
    @return      : 
     - void.
       
   */
   public void afficherTempsExecution()
   {
       long tempsExecution = this.getTempsExecution();
       
       System.out.println( MSG_TPS_EXEC + tempsExecution + MSG_NANOSEC);
   }
    
}
