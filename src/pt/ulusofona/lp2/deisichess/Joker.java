package pt.ulusofona.lp2.deisichess;

public class Joker extends Peca{
    int valor = 4;
    int pecaImita;
    public int getValor() {
        return valor;
    }

    boolean move(int x0, int y0, int x1, int y1){
        return true;
    }
}
