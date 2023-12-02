package pt.ulusofona.lp2.deisichess;

import java.util.ArrayList;
import java.util.List;

public class Rei extends Peca {
    int valor = 1000;

    public int getValor() {
        return valor;
    }

    boolean move(int x0, int y0, int x1, int y1, Jogo jogo) {
        Peca pecaQueMove = jogo.getTabuleiro().retornoQuadrado(x0, y0).getPeca();
        Square quadradoOrigem = jogo.getTabuleiro().retornoQuadrado(x0, y0);
        Square quadradoDestino = jogo.getTabuleiro().retornoQuadrado(x1, y1);
        Equipa equipaPeca;
        if (jogo.getEquipaBranca() == equipa){
            equipaPeca = jogo.getEquipaBranca();
        }else{
            equipaPeca = jogo.getEquipaPreta();
        }


        if (x0 == x1 && y0 == y1) {
            return false;
        }

        if (((x1 == x0 + 1 || x1 == x0 - 1) && y1 == y0) || (x1 == x0 && (y1 == y0 + 1 || y1 == y0 - 1)) || ((y1 == y0 + 1 || y1 == y0 - 1) && (x1 == x0 + 1 || x1 == x0 - 1))) {
            if (quadradoDestino.isOcupado()) {
                if (!pertenceAequipa(jogo, x1, y1)) {
                    pecaQueMove.setCoordenadas(quadradoDestino);
                    quadradoDestino.setPeca(pecaQueMove);
                    quadradoDestino.setOcupado(true);
                    quadradoOrigem.resetQuadrado();
                    equipaPeca.aumentarPecasCapturadas();

                    //equipaPeca.aumentarJogadasValidas();
                    //equipaPeca.numeroDoTurno++;
                    return true;
                }

            } else {
                pecaQueMove.setCoordenadas(quadradoDestino);
                quadradoDestino.setPeca(pecaQueMove);
                quadradoOrigem.resetQuadrado();

                //equipaPeca.aumentarJogadasValidas();
                //equipaPeca.numeroDoTurno++;
                return true;
            }

        }
        //equipaPeca.aumentarTenativasInvalidas();
        //equipaPeca.numeroDoTurno++;
        return false;
    }

}
