package pt.ulusofona.lp2.deisichess;

import java.util.ArrayList;
import java.util.List;

public class Rei extends Peca {
    int valor = 1000;

    public int getValor() {
        return valor;
    }

    boolean move(int x0, int y0, int x1, int y1, Jogo jogo) {
        if (x0 == x1 && y0 == y1) {
            return false;
        }

        if (((x1 == x0 + 1 || x1 == x0 - 1) && y1 == y0) || (x1 == x0 && (y1 == y0 + 1 || y1 == y0 - 1)) || ((y1 == y0 + 1 || y1 == y0 - 1) && (x1 == x0 + 1 || x1 == x0 - 1))) {
            //coordenadas.setCoordenadaX(x1);
            //coordenadas.setCoordenadaY(y1);
            numeroJogadas++;
            return true;
        }

        return false;
    }

    List<Comparable> jogadasPermitidas(Tabuleiro tabuleiro) {
        List<Comparable> permittedMoves = new ArrayList<>();
        for (Square s : tabuleiro.getQuadrados()) {
            if (move(coordenadas.getCoordenadaX(), coordenadas.getCoordenadaY(), s.getCoordenadaX(), s.getCoordenadaY(), )) {
                if (s.getPeca() == null) {
                    permittedMoves.add("(" + s.getCoordenadaX() + ", " + s.getCoordenadaY() + ")->0");
                } else {
                    permittedMoves.add("(" + s.getCoordenadaX() + ", " + s.getCoordenadaY() + ")->" + s.getPeca().getValor());
                }
            }
        }
        return permittedMoves;
    }
}
