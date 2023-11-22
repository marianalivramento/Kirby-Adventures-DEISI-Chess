package pt.ulusofona.lp2.deisichess;

public class InvalidGameInputException extends Exception {

    String str;
    int linha;

    public InvalidGameInputException(int linha, String message) {
        super();
        this.linha = linha;
        this.str = message;
    }


    int getLineWithError() {
        return linha;
    }

    String getProblemDescription() {
        return str;
    }
}
