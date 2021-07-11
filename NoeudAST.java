

/** @author Ahmed Khoumsi */

/** Classe representant une feuille d'AST
 */
public class NoeudAST extends ElemAST {
  // Attributs
  Terminal terminal;
  ElemAST leftChild;
  ElemAST rightChild;

  /** Constructeur pour l'initialisation d'attributs
   */
  public NoeudAST(Terminal terminal, ElemAST leftChild, ElemAST rightChild) { // avec arguments
    this.terminal = terminal;
    this.leftChild = leftChild;
    this.rightChild = rightChild;
  }

 
  /** Evaluation de noeud d'AST
   */
  public int EvalAST( ) {
    if(terminal.Terminal.equals("+")){
      return(leftChild.EvalAST() + rightChild.EvalAST());
    }
    else if(terminal.Terminal.equals("-")){
      return(leftChild.EvalAST() - rightChild.EvalAST());
    }
    else if(terminal.Terminal.equals("*")){
      return(leftChild.EvalAST() * rightChild.EvalAST());
    }
    else if(terminal.Terminal.equals("/")){
      return(leftChild.EvalAST() / rightChild.EvalAST());
    }
    else{
      ErreurEvalAST("(" + leftChild.LectAST() + terminal.Terminal + rightChild.LectAST() + ")");
      return -1;
    }
  }


  /** Lecture de noeud d'AST
   */
  public String LectAST( ) {
    return "("+leftChild.LectAST() + terminal.Terminal + rightChild.LectAST() + ")";
  }

}


