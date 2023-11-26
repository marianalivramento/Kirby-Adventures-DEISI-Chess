package pt.ulusofona.lp2.deisichess;

public class HomerSimpson extends Peca{
    int valor;
    int turnosDormir;

    public int getValor() {
        return valor;
    }

    boolean move(int x0, int y0, int x1, int y1){
        coordenadas.setCoordenadaX(x1);
        coordenadas.setCoordenadaY(y1);
        numeroJogadas++;
        return true;
    }
}
