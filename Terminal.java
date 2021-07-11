

/** @author Ahmed Khoumsi */

import javax.management.Attribute;

/** Cette classe identifie les terminaux reconnus et retournes par
 *  l'analyseur lexical
 */
public class Terminal {

// Constantes et attributs
//  ....
public String Terminal;
public TerminalType Type;

/** Un ou deux constructeurs (ou plus, si vous voulez)
  *   pour l'initalisation d'attributs 
 */	
  public Terminal(String terminal, TerminalType Type) {   // arguments possibles

    this.Terminal = terminal;
    this.Type = Type;

  }

}
