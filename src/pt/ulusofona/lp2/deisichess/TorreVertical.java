package pt.ulusofona.lp2.deisichess;

import java.util.ArrayList;
import java.util.List;

public class TorreVertical extends Peca {
    int valor = 3;

    public int getValor() {
        return valor;
    }

    boolean passaPorPeca(int x0, int y0, int x1, int y1, Jogo jogo) {
        int minY = Math.min(y0, y1);
        int maxY = Math.max(y0, y1);

        for (int y = minY + 1; y < maxY; y++) {
            Square square = jogo.getTabuleiro().retornoQuadrado(x0, y);
            if (square != null && square.isOcupado()) {
                return true;
            }
        }
        return false;
    }

    boolean move(int x0, int y0, int x1, int y1, Jogo jogo) {
        Peca pecaQueMove = jogo.getTabuleiro().retornoQuadrado(x0, y0).getPeca();
        Square quadradoOrigem = jogo.getTabuleiro().retornoQuadrado(x0, y0);
        Square quadradoDestino = jogo.getTabuleiro().retornoQuadrado(x1, y1);

        if (x0 == x1 && y0 == y1) {
            return false;
        }
        if (x0 == x1) {
            if (!passaPorPeca(x0, y0, x1, y1, jogo)) {
                if (!pertenceAequipa(jogo,x1,y1)) {
                    pecaQueMove.setCoordenadas(quadradoDestino);
                    quadradoDestino.setPeca(pecaQueMove);
                    quadradoDestino.setOcupado(true);
                    quadradoOrigem.resetQuadrado();
                    numeroJogadas++;
                    return true;
                }
            }

        }
        return false;
    }
}
