package pt.ulusofona.lp2.deisichess;

import java.util.ArrayList;
import java.util.List;

public class GetHints implements Comparable<GetHints> {
    Square square;

    @Override
    public int compareTo(GetHints other) {
        if (this.square.getPeca() != null && other.square.getPeca() != null) {
            int value1 = this.square.getPeca().getValor();
            int value2 = other.square.getPeca().getValor();

            if (value1 < value2) {
                return 1;
            } else if (value1 == value2) {
                return 0;
            }
            return -1;

        } else {
            if (this.square.getPeca() != null && other.square.getPeca() == null) {
                return -1;
            }
            if (this.square.getPeca() == null && other.square.getPeca() != null) {
                return 1;
            }
            return 0;
        }

    }

    List<GetHints> jogadasPermitidas(Jogo jogo, List<Comparable> hints) {
        Tabuleiro tabuleiro = jogo.getTabuleiro();
        Peca piece = square.getPeca();
        List<GetHints> ordenar = new ArrayList<>();
        if (piece != null) {
            for (Square s : tabuleiro.getQuadrados()) {
                if (piece.movesPermitidos(square.getCoordenadaX(), square.getCoordenadaY(), s.getCoordenadaX(), s.getCoordenadaY(), jogo)) {
                    if (!piece.passaPorPeca(square.getCoordenadaX(), square.getCoordenadaY(), s.getCoordenadaX(), s.getCoordenadaY(), jogo)) {
                        GetHints result = new GetHints();
                        result.square = s;
                        ordenar.add(result);
                    }
                }
            }

        }
        //Collections.sort(ordenar);
        /*

        for (getHints h: ordenar){
            hints.add(h);
        }

         */

        return ordenar;
    }


    @Override
    public String toString() {
        if (square.getPeca() != null) {
            return "(" + square.getCoordenadaX() + "," + square.getCoordenadaY() + ") -> " + square.getPeca().getValor();
        }

        return "(" + square.getCoordenadaX() + "," + square.getCoordenadaY() + ") -> 0";
    }

    //get hints apenas usando o x e o y do quadrado w instanciae o valoe  tipo int pontos = 0;
}
