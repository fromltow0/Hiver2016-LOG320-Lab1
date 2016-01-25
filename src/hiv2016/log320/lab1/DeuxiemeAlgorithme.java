
package hiv2016.log320.lab1;

/*************************************
  CLASS         : DeuxiemeAlgorithme
 *************************************
@author Louis Wilfried MBOG
    
*Description    : Cette classe implemente un algorithme de cout 'n' qui verifie
                  si un mot est un anagramme d'un autre mot.
                                    
*Constructors   :
    public DeuxiemeAlgorithme()

*Methods        :
    public boolean estUnAnagramme(char[] chaine1, char[] chaine2)
    public char[] getCharArrayFromString(String chaine)
    
*/
public class DeuxiemeAlgorithme 
{
    private static final int ZERO = 0;
    
    /*************************************
      CONSTRUCTOR : DeuxiemeAlgorithme
     *************************************
    */
    public DeuxiemeAlgorithme()
    {
        
    }
    
    /*************************************
      FUNCTION : estUnAnagramme
     *************************************
    *Description    : Cette fonction vérifie que la chaine de caractères
                      contenue dans la variable "chaine1" et la chaine de
                      caractères contenue dans la variable "chaine2" ont les
                      memes caractères.
                      Si les variables "chaine1" et "chaine2" ont les memes
                      caracteres alors, "chaine1" est un anagramme de "chaine2"
                      et la fonction retourne la valeur "true".
                      Si les variables "chaine1" et "chaine2" ont les memes
                      caracteres alors, "chaine1" est un anagramme de "chaine2"
                      et la fonction retourne la valeur "true".
                      Si les variables "chaine1" et "chaine2" n'ont pas les
                      memes caracteres alors, "chaine1" n'est pas un anagramme
                      de "chaine2" et la fonction retourne la valeur "false".
                      Le principe utilise par la fonction est le suivant:
                      Soit le mot "manoir" et son anagramme "romain".Il y a
                      autant de 'm', 'a', 'n', 'o', 'i', 'r' dans "manoir" que
                      dans "romain".Considerons le code ASCII de chaque
                      caractere du mot "manoir".Pour 'm' le code ASCII est 109,
                      pour 'a' le code ASCII est 97, pour 'n' le code ASCII est
                      '110', pour 'o' le code ASCII est 111, pour 'i' le code
                      ASCII est '105' et pour 'r' le code ASCII est 114.
                      Additionnons ces codes ASCII.109 + 97 + 110 +111 +105 +114
                      .Nous obtenons 646 que nous nommons "sommeA".Si nous 
                      determinons le code ASCII de chaque caractere du mot 
                      "romain" et que nous additionnons ces codes ASCII, nous
                      obtenons 646 que nous nommons "sommeB".Faisons maintenant
                      la difference de "sommeA" et "sommeB"."sommeA" - "sommeB"=
                      646 - 646 = 0.Nous constatons donc que lorsqu'on a un mot
                      A qui est un anagramme d'un mot B, si on effectue la
                      difference de la **somme des codes ASCII des caracteres
                      du mot A** et de la **somme des codes ASCII des caracteres
                      du mot B**, le resultat de cette difference est zero.
                                   
    *Parameters     :
    @param  chaine1 : 
            TYPE    : char[]
            
        DESCRIPTION : chaine de caractères à comparer avec la chaine de
                      caractères contenue dans la variable "chaine2" afin de 
                      savoir si la chaine de caracteres contenue dans la 
                      variable "chaine1" est un anagramme de la chaine de
                      caractères contenue dans la variable "chaine2".
                  
    @param  chaine2 : 
            TYPE    : char[]
         
        DESCRIPTION : chaine de caracteres qui sera comparée avec la chaine de
                      caracteres contenue dans la varaible "chaine1" afin de
                      déterminer si la chaine de caractères contenue dans la
                      variable "chaine1" est un anagramme de la chaine de
                      caractères contenue dans la variable "chaine2".
    
    
    @return         : 
     - true si "chaine1" est un anagramme de "chaine2".
     - false si "chaine1" n'est pas un anagramme de "chaine2".
       
    */
    public boolean estUnAnagramme(char[] chaine1, char[] chaine2)
    {
        
        //Si les chaines sont de longueurs differentes
        //alors retourner "false".
        if(chaine1.length != chaine2.length)
        {
            //finDeTraitement = true;
            //chronometre.setFinDeTraitement(finDeTraitement);
            return false;
        }
        
        int valeur = 0;
        
        for(int i = 0; i < chaine1.length; i++)
        {
            valeur += chaine1[i] - chaine2[i];
        }
        
        return ( valeur == ZERO );
    }
    
    /*************************************
      FUNCTION  : getCharArrayFromString
     *************************************
    *Description    : Cette fonction retourne le tableau de caracteres
                      correspondant a une chaine de caracteres passee
                      en parametres.
                                      
    *Parameters     :
    @param  chaine  : 
            TYPE    : String
            
        DESCRIPTION : chaine de caractères pour laquelle on desire obtenir le
                      tableau de caracteres.
     
    @return         : 
     - char[].
       
    */
    public char[] getCharArrayFromString(String chaine)
    {
        return chaine.toCharArray();
    }
    
}