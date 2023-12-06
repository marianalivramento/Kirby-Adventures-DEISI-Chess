package pt.ulusofona.lp2.deisichess;

public class PoneiMagico extends Peca {
    int valor = 5;

    public int getValor() {
        return valor;
    }


    boolean passaPorPeca(int x0, int y0, int x1, int y1, Jogo jogo) {

            for (int coordenadaY = -2 ; coordenadaY <= 2; coordenadaY++) {
                Square s = jogo.tabuleiro.retornoQuadrado(x1,coordenadaY);
                if (coordenadaY != y0 && s != null) {
                    if (((x1 == x0 + 2) && (y1 == y0 + 2)) && ((s.getCoordenadaX() == x0) && ((s.getCoordenadaY() == y0 + coordenadaY)))) {
                        if (s.isOcupado()) {
                            return true;
                        }
                    }
                }
            }

            for (int coordenadaX = -1 ; coordenadaX <= 1; coordenadaX++) {
                Square s = jogo.tabuleiro.retornoQuadrado(coordenadaX,y1);
                if (coordenadaX != x0 && s != null) {
                    if (((x1 == x0 + 2) && (y1 == y0 + 2)) && ((s.getCoordenadaX() == x0 + coordenadaX) && ((s.getCoordenadaY() == y0)))) {
                        if (s.isOcupado()) {
                            return true;
                        }
                    }
                }
            }

        return false;
    }

    @Override
    boolean movesPermitidos(int x0, int y0, int x1, int y1, Jogo jogo) {
        if (passaPorPeca(x0,y0,x1,y1,jogo)) {
            return false;
        }

        if (!pertenceAequipa(jogo, x1, y1)) {
            if ((x1 == x0 + 2) && ((y1 == y0 + 2) || (y1 == y0 - 2))) {
                return true;
            } else if ((x1 == x0 - 2) && ((y1 == y0 + 2) || (y1 == y0 - 2))) {
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

        if (movesPermitidos(x0, y0, x1, y1, jogo)) {
            if (quadradoDestino.isOcupado()) {
                quadradoDestino.getPeca().setNaoCapturado(false);
                quadradoDestino.getPeca().setCoordenadas(null);
                pecaQueMove.setCoordenadas(quadradoDestino);
                quadradoDestino.setPeca(pecaQueMove);

                quadradoDestino.setOcupado(true);
                //equipaPeca.aumentarPecasCapturadas();
                jogo.getClassEquipaAtual().aumentarPecasCapturadas();


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

        return false;
    }

}
