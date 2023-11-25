package pt.ulusofona.lp2.deisichess;

public class Rainha extends Peca {
    int valor = 8;

    public int getValor() {
        return valor;
    }

    boolean move(int x0, int y0, int x1, int y1) {
        for (int i = 1; i <= 5; i++) {
            if ((x1 == x0 + i && y1 == y0 + i) || (x1 == x0 - i && y1 == y0 - i)) {
                return true;
            } else if (x0 == x1 && (y1 == y0 + i || y1 == y0 - i)) {
                return true;
            } else if (y0 == y1 && (x1 == x0 + i || x1 == x0 - i)) {
                return true;
            }
        }

        //FALTA A CONDIÇÃO DE UMA RAINHA NAO CAPTURA UMA RAINHA
        return false;
    }
}