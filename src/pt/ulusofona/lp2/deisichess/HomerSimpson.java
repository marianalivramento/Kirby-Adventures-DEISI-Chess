package pt.ulusofona.lp2.deisichess;

import java.util.ArrayList;
import java.util.List;

public class HomerSimpson extends Peca {
    int valor;

    public int getValor() {
        return valor;
    }

    boolean move(int x0, int y0, int x1, int y1, Jogo jogo) {
        Peca pecaQueMove = jogo.getTabuleiro().retornoQuadrado(x0, y0).getPeca();
        Square quadradoOrigem = jogo.getTabuleiro().retornoQuadrado(x0, y0);
        Square quadradoDestino = jogo.getTabuleiro().retornoQuadrado(x1, y1);
// se o o turno for multiplo de 3 ele pode se mexer else he sleeps e quanto o movimento n sei se é msm assim mas era o q estava aqui antes

        //if (equipa.numeroDoTurno % 3 != 0) {
            if ((x1 == x0 + 1 && y1 == y0 + 1) || (x1 == x0 - 1 && y1 == y0 - 1) || (x1 == x0 + 1 && y1 == y0 - 1) || (x1 == x0 - 1 && y1 == y0 + 1)) {
                pecaQueMove.setCoordenadas(quadradoDestino);
                quadradoDestino.setPeca(pecaQueMove);
                quadradoDestino.setOcupado(true);
                quadradoOrigem.resetQuadrado();
                numeroJogadas++;
                return true;
            //}
        }

        return false;
    }

}
