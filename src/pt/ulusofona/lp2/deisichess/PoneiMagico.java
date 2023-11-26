package pt.ulusofona.lp2.deisichess;

public class PoneiMagico extends Peca{
    int valor = 5;

    public int getValor() {
        return valor;
    }

    boolean move(int x0, int y0, int x1, int y1) {
        for (int i = 1; i <= 2; i++) {
            if ((x1 == x0 + i && y1 == y0 + i) || (x1 == x0 - i && y1 == y0 - i)) {
                coordenadas.setCoordenadaX(x1);
                coordenadas.setCoordenadaY(y1);
                return true;
            } else if (x0 == x1 && (y1 == y0 + i || y1 == y0 - i)) {
                coordenadas.setCoordenadaX(x1);
                coordenadas.setCoordenadaY(y1);
                return true;
            } else if (y0 == y1 && (x1 == x0 + i || x1 == x0 - i)) {
                coordenadas.setCoordenadaX(x1);
                coordenadas.setCoordenadaY(y1);
                return true;
            }
        }

        return true;
    }
}
