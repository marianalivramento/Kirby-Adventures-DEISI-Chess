package pt.ulusofona.lp2.deisichess;

public class Rei extends Peca {
    int valor = 1000;

    public int getValor() {
        return valor;
    }

    @Override
    boolean movesPermitidos(int x0, int y0, int x1, int y1, Jogo jogo) {
        if (x0 == x1 && y0 == y1) {
            return false;
        }
        if (((x1 == x0 + 1 || x1 == x0 - 1) && y1 == y0) || (x1 == x0 && (y1 == y0 + 1 || y1 == y0 - 1)) || ((y1 == y0 + 1 || y1 == y0 - 1) && (x1 == x0 + 1 || x1 == x0 - 1))) {
            if (!pertenceAequipa(jogo, x1, y1)) {
                return true;
            }
        }
        return false;
    }

    public String nomeDoTipo(Jogo jogo) {
        return "Rei";
    }
}
