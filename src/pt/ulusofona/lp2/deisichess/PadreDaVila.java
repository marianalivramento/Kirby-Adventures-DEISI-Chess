package pt.ulusofona.lp2.deisichess;

public class PadreDaVila extends Peca{
    int valor = 3;

    public int getValor() {
        return valor;
    }

    boolean move(int x0, int y0, int x1, int y1){
        for (int i = 1; i <= 3; i++) {
            if ((x1 == x0 + i && y1 == y0 + i) || (x1 == x0 - i && y1 == y0 - i)) {
                coordenadas.setCoordenadaX(x1);
                coordenadas.setCoordenadaY(y1);
                return true;
            }
        }
        return false;
    }
}
