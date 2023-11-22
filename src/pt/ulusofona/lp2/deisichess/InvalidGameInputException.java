package pt.ulusofona.lp2.deisichess;

public class InvalidGameInputException extends Exception {

    String str;
    int linha;

    public InvalidGameInputException(int linha, String message) {
        super();
        this.linha = linha;
        this.str = message;
    }


    public int getLineWithError() {
        return linha;
    }

    public String getProblemDescription() {
        return str;
    }
}
