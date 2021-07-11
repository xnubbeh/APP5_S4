
/**
 * @author Ahmed Khoumsi
 */

/**
 * Cette classe effectue l'analyse lexicale
 */
public class AnalLex {

    // Attributs
//  ...
    public String Chain = "";
    public int Length = 0;
    public int State = 0;
    public int ptrReader = 0;
    public boolean leftChars;


    /**
     * Constructeur pour l'initialisation d'attribut(s)
     */
    public AnalLex(String chain) {  // arguments possibles
        this.Chain = chain;
        this.Length = chain.length();
    }


    /**
     * resteTerminal() retourne :
     * false  si tous les terminaux de l'expression arithmetique ont ete retournes
     * true s'il reste encore au moins un terminal qui n'a pas ete retourne
     */
    public boolean resteTerminal() {
        if (ptrReader < Length) {
            leftChars = true;
        }
        if (ptrReader == Length) {
            leftChars = false;
        }
        return leftChars;
    }


    /**
     * prochainTerminal() retourne le prochain terminal
     * Cette methode est une implementation d'un AEF
     */
    public Terminal prochainTerminal() {
        String tempChain = "";
        State = 0;
        char c = 0;
        int tempC = 0;
        while (resteTerminal() == true) {
            c = Chain.charAt(ptrReader);
            ptrReader++;
            if (State == 0) {
                if (c == '+' || c == '-' || c == '*' || c == '/') {
                    return (new Terminal(Character.toString(c),TerminalType.OPERATOR));
                }

                else if (c >= '0' && c <= '9') {
                //else if(Character.getNumericValue(c) >= 0 || Character.getNumericValue(c) <= 9){
                    tempChain += c;
                    State = 1;
                } else {
                    ErreurLex(Character.toString(c));
                }
            }
            else if (State == 1) {
                if (c >= '0' && c <= '9') {
                //if(Character.getNumericValue(c) >= 0 || Character.getNumericValue(c) <= 9){
                    tempChain += c;
                    State = 1;
                } else {
                    ptrReader--;
                    return new Terminal(tempChain,TerminalType.OPERAND);
                }
            }
        }
        return new Terminal(tempChain,TerminalType.OPERAND);
    }


    /**
     * ErreurLex() envoie un message d'erreur lexicale
     */
    public void ErreurLex(String s) {
        System.out.println("Erreur: " + s);
    }


    //Methode principale a lancer pour tester l'analyseur lexical
    public static void main(String[] args) {
        String toWrite = "";
        System.out.println("Debut d'analyse lexicale");
        if (args.length == 0) {
            args = new String[2];
            args[0] = "ExpArith.txt";
            args[1] = "ResultatLexical.txt";
        }
        Reader r = new Reader(args[0]);

        AnalLex lexical = new AnalLex(r.toString()); // Creation de l'analyseur lexical

        // Execution de l'analyseur lexical
        Terminal t = null;
        while (lexical.resteTerminal()) {
            t = lexical.prochainTerminal();
            toWrite += t.Terminal + "\n";  // toWrite contient le resultat
        }                   //    d'analyse lexicale
        System.out.println(toWrite);    // Ecriture de toWrite sur la console
        Writer w = new Writer(args[1], toWrite); // Ecriture de toWrite dans fichier args[1]
        System.out.println("Fin d'analyse lexicale");
    }
}
