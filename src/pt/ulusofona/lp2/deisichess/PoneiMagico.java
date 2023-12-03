package pt.ulusofona.lp2.deisichess;

public class PoneiMagico extends Peca {
    int valor = 5;

    public int getValor() {
        return valor;
    }


    boolean passaPorPeca(int x0, int y0, int x1, int y1, Jogo jogo) {
        for (Square s : jogo.getTabuleiro().getQuadrados()) {
            if (s.getCoordenadaX() != x1 && s.getCoordenadaY() != y1) {
                if (((x1 == x0 + 2) && (y1 == y0 + 2)) && ((s.getCoordenadaX() == x0 + 1) && ((s.getCoordenadaY() == y0 + 1)))) {
                    if (s.isOcupado()) {
                        return true;
                    }
                } else if (((x1 == x0 - 2) && (y1 == y0 - 2)) && ((s.getCoordenadaX() == x0 - 1) && ((s.getCoordenadaY() == y0 - 1)))) {
                    if (s.isOcupado()) {
                        return true;
                    }
                } else if (((x1 == x0 - 2) && (y1 == y0 + 2)) && ((s.getCoordenadaX() == x0 - 1) && ((s.getCoordenadaY() == y0 + 1)))) {
                    if (s.isOcupado()) {
                        return true;
                    }
                } else if (((x1 == x0 + 2) && (y1 == y0 - 2)) && ((s.getCoordenadaX() == x0 + 1) && ((s.getCoordenadaY() == y0 - 1)))) {
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
                if (!passaPorPeca(x0, y0, x1, y1, jogo)) {

                    quadradoDestino.getPeca().setNaoCapturado(false);
                    quadradoDestino.getPeca().setCoordenadas(null);
                    pecaQueMove.setCoordenadas(quadradoDestino);
                    quadradoDestino.setPeca(pecaQueMove);

                    quadradoDestino.setOcupado(true);
                    equipaPeca.aumentarPecasCapturadas();

                    quadradoOrigem.resetQuadrado();
                    return true;
                }

            } else {
                if (!passaPorPeca(x0, y0, x1, y1, jogo)) {

                    pecaQueMove.setCoordenadas(quadradoDestino);
                    quadradoDestino.setPeca(pecaQueMove);

                    quadradoDestino.setOcupado(true);
                    quadradoOrigem.resetQuadrado();
                    return true;

                }
            }
        }
/*
        if (quadradoDestino.isOcupado()) {
            if (!passaPorPeca(x0, y0, x1, y1, jogo)) {
                if (!pertenceAequipa(jogo, x1, y1)) {
                    pecaQueMove.setCoordenadas(quadradoDestino);
                    quadradoDestino.setPeca(pecaQueMove);

                    quadradoDestino.setOcupado(true);
                    equipaPeca.aumentarPecasCapturadas();

                    quadradoOrigem.resetQuadrado();
                    //equipaPeca.aumentarJogadasValidas();
                    //equipaPeca.numeroDoTurno++;
                    return true;
                }
            }
        } else {
            if (!passaPorPeca(x0, y0, x1, y1, jogo)) {

                pecaQueMove.setCoordenadas(quadradoDestino);
                quadradoDestino.setPeca(pecaQueMove);
                quadradoOrigem.resetQuadrado();

                //equipaPeca.aumentarJogadasValidas();
                //equipaPeca.numeroDoTurno++;
                return true;

            }
        }

 */

        //equipaPeca.numeroDoTurno++;
        //equipaPeca.aumentarTenativasInvalidas();
        return false;
    }

}
