package pt.ulusofona.lp2.deisichess;

public class TorreVertical extends Peca{
    int valor = 3;

    public int getValor() {
        return valor;
    }

    boolean move(int x0, int y0, int x1, int y1){
        if (y0 == y1){
            return true;
        }
        return false;
    }
}
