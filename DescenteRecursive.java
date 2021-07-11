
/**
 * @author Ahmed Khoumsi
 */

import javax.swing.plaf.basic.BasicTreeUI;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Cette classe effectue l'analyse syntaxique
 */
public class DescenteRecursive {

    // Attributs
    AnalLex Lex;

    /**
     * Constructeur de DescenteRecursive :
     * - recoit en argument le nom du fichier contenant l'expression a analyser
     * - pour l'initalisation d'attribut(s)
     */
    public DescenteRecursive(String in) {
        Lex = new AnalLex(new Reader(in).toString()); //Create Lexic
    }


    /**
     * AnalSynt() effectue l'analyse syntaxique et construit l'AST.
     * Elle retourne une reference sur la racine de l'AST construit
     */
    public ElemAST AnalSynt() {
        return E();
    }


// Methode pour chaque symbole non-terminal de la grammaire retenue

    public ElemAST E() {
        ElemAST n1 = T();
        Terminal lastRead = Lex.prochainTerminal();

        if (lastRead.Type == TerminalType.OPERATOR) {

            ElemAST n2 = E();
            n1 = new NoeudAST(lastRead, n1, n2);
        }
        return n1;
    }

    public ElemAST T() {
        Terminal lastRead = Lex.prochainTerminal();

        ElemAST elem = null;
        if (lastRead.Type == TerminalType.OPERAND) {

            elem = new FeuilleAST(lastRead);
        } else {
            ErreurSynt(lastRead.Terminal);
        }
        return elem;
    }


    /**
     * ErreurSynt() envoie un message d'erreur syntaxique
     */
    public void ErreurSynt(String s) {
        System.out.println("Erreur: " + s);
    }


    //Methode principale a lancer pour tester l'analyseur syntaxique
    public static void main(String[] args) {
        String toWriteLect = "";
        String toWriteEval = "";

        System.out.println("Debut d'analyse syntaxique");
        if (args.length == 0) {
            args = new String[2];
            args[0] = "ExpArith.txt";
            args[1] = "ResultatSyntaxique.txt";
        }
        DescenteRecursive dr = new DescenteRecursive(args[0]);
        try {
            ElemAST RacineAST = dr.AnalSynt();
            toWriteLect += "Lecture de l'AST trouve : " + RacineAST.LectAST() + "\n";
            System.out.println(toWriteLect);
            toWriteEval += "Evaluation de l'AST trouve : " + RacineAST.EvalAST() + "\n";
            System.out.println(toWriteEval);
            Writer w = new Writer(args[1], toWriteLect + toWriteEval); // Ecriture de toWrite
            // dans fichier args[1]
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
            System.exit(51);
        }
        System.out.println("Analyse syntaxique terminee");
    }

}

