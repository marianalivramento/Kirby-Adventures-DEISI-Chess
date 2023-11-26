package pt.ulusofona.lp2.deisichess;

import java.util.ArrayList;
import java.util.List;

public class Rainha extends Peca {
    int valor = 8;

    public int getValor() {
        return valor;
    }

    boolean move(int x0, int y0, int x1, int y1) {
        for (int i = 1; i <= 5; i++) {
            if ((x1 == x0 + i && y1 == y0 + i) || (x1 == x0 - i && y1 == y0 - i)) {
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

        //FALTA A CONDIÇÃO DE UMA RAINHA NAO CAPTURA UMA RAINHA
        return false;
    }

    List<Comparable> jogadasPermitidas(Tabuleiro tabuleiro) {
        List<Comparable> permittedMoves = new ArrayList<>();
        for (Square s : tabuleiro.getQuadrados()) {
            if (move(coordenadas.getCoordenadaX(), coordenadas.getCoordenadaY(), s.getCoordenadaX(), s.getCoordenadaY())) {
                if (s.getPeca() == null){
                    permittedMoves.add("(" + s.getCoordenadaX() + ", " + s.getCoordenadaY() + ")->0" );
                }else {
                    permittedMoves.add("(" + s.getCoordenadaX() + ", " + s.getCoordenadaY() + ")->" + s.getPeca().getValor());
                }
            }
        }
        return permittedMoves;
    }
}


