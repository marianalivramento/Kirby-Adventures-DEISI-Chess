package pt.ulusofona.lp2.deisichess;

public class HomerSimpson extends Peca {
    int valor;

    public int getValor() {
        return valor;
    }

    @Override
    boolean movesPermitidos(int x0, int y0, int x1, int y1, Jogo jogo) {
        if ((x1 == x0 + 1 && y1 == y0 + 1) || (x1 == x0 - 1 && y1 == y0 - 1) || (x1 == x0 + 1 && y1 == y0 - 1) || (x1 == x0 - 1 && y1 == y0 + 1)) {
            if (!pertenceAequipa(jogo, x1, y1)) {
                return true;
            }
        }

        return false;
    }

    boolean move(int x0, int y0, int x1, int y1, Jogo jogo) {
        Peca pecaQueMove = jogo.getTabuleiro().retornoQuadrado(x0, y0).getPeca();
        Square quadradoOrigem = jogo.getTabuleiro().retornoQuadrado(x0, y0);
        Square quadradoDestino = jogo.getTabuleiro().retornoQuadrado(x1, y1);
        Equipa equipaPeca;
        if (jogo.getEquipaBranca() == equipa) {
            equipaPeca = jogo.getEquipaBranca();
        } else {
            equipaPeca = jogo.getEquipaPreta();
        }
// se o o turno for multiplo de 3 ele pode se mexer else he sleeps e quanto o movimento n sei se é msm assim mas era o q estava aqui antes

        //if (equipa.numeroDoTurno % 3 != 0) {
        if (movesPermitidos(x0, y0, x1, y1, jogo)) {
            if (quadradoDestino.isOcupado()) {
                quadradoDestino.getPeca().setNaoCapturado(false);
                quadradoDestino.getPeca().setCoordenadas(null);
                pecaQueMove.setCoordenadas(quadradoDestino);
                quadradoDestino.setPeca(pecaQueMove);

                quadradoDestino.setOcupado(true);
                equipaPeca.aumentarPecasCapturadas();

                quadradoOrigem.resetQuadrado();
                return true;
            } else {
                pecaQueMove.setCoordenadas(quadradoDestino);
                quadradoDestino.setPeca(pecaQueMove);
                quadradoDestino.setOcupado(true);
                quadradoOrigem.resetQuadrado();

                return true;
            }

        }
        //}

        return false;
    }

}
