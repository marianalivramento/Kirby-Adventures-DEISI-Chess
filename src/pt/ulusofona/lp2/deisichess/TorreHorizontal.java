package pt.ulusofona.lp2.deisichess;

public class TorreHorizontal extends Peca {
    int valor = 3;

    public int getValor() {
        return valor;
    }

    //As torres estão trocadas?
    boolean move(int x0, int y0, int x1, int y1) {
        if (x0 == x1){
            //coordenadas.setCoordenadaX(x1);
            //coordenadas.setCoordenadaY(y1);
            numeroJogadas++;
            return true;
        }
        return false;
    }
}
