package pt.ulusofona.lp2.deisichess;

public class Rei extends Peca {
    int valor = 1000;

    public int getValor() {
        return valor;
    }

    boolean move(int x0, int y0, int x1, int y1) {
        if (((x1 == x0 + 1 || x1 == x0 - 1) && y1 == y0) || (x1 == x0 && (y1 == y0 + 1 || y1 == y0 - 1)) || ((y1 == y0 + 1 || y1 == y0 - 1) && (x1 == x0 + 1 || x1 == x0 - 1))) {
            numeroJogadas++;
            return true;
        }
        return false;
    }
}
