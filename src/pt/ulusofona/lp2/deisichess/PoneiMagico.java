package pt.ulusofona.lp2.deisichess;

import java.util.ArrayList;
import java.util.List;

public class PoneiMagico extends Peca {
    int valor = 5;

    public int getValor() {
        return valor;
    }

    boolean move(int x0, int y0, int x1, int y1) {

        for (int i = 1; i <= 2; i++) {
            if (x0 == x1 && y0 == y1) {
                return false;
            }else if ((x1 == x0 + i && y1 == y0 + i) || (x1 == x0 - i && y1 == y0 - i)) {
                //coordenadas.setCoordenadaX(x1);
                //coordenadas.setCoordenadaY(y1);
                numeroJogadas++;
                return true;
            } else if (x0 == x1 && (y1 == y0 + i || y1 == y0 - i)) {
                //coordenadas.setCoordenadaX(x1);
                //coordenadas.setCoordenadaY(y1);
                numeroJogadas++;
                return true;
            } else if (y0 == y1 && (x1 == x0 + i || x1 == x0 - i)) {
                //coordenadas.setCoordenadaX(x1);
                //coordenadas.setCoordenadaY(y1);
                numeroJogadas++;
                return true;
            }
        }

        return false;
    }

    List<Comparable> jogadasPermitidas(Tabuleiro tabuleiro) {
        List<Comparable> permittedMoves = new ArrayList<>();
        for (Square s : tabuleiro.getQuadrados()) {
            if (move(coordenadas.getCoordenadaX(), coordenadas.getCoordenadaY(), s.getCoordenadaX(), s.getCoordenadaY())) {
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

