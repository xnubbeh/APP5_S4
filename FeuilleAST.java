

/**
 * @author Ahmed Khoumsi
 */

/** Classe representant une feuille d'AST
 */
public class FeuilleAST extends ElemAST {

    // Attribut(s)
    public Terminal t;

    /**Constructeur pour l'initialisation d'attribut(s)
     */
    public FeuilleAST(Terminal t) {  // avec arguments
        this.t = t;
    }


    /** Evaluation de feuille d'AST
     */
    public int EvalAST() {
        return Integer.parseInt(this.t.Terminal);
    }


    /** Lecture de chaine de caracteres correspondant a la feuille d'AST
     */
    public String LectAST() {
        return this.t.Terminal;
    }

}
