package pt.ulusofona.lp2.deisichess;

public class PadreDaVila extends Peca {
    int valor = 3;

    public int getValor() {
        return valor;
    }


    boolean passaPorPeca(int x0, int y0, int x1, int y1, Jogo jogo) {

        for (int i = 1; i <= 3; i++) {
            if (x1 == x0 + i && y1 == y0 + i) {
                return false;
            } else if (x1 == x0 - i && y1 == y0 - i) {
                return false;
            } else if (x1 == x0 + i && y1 == y0 - i) {
                return false;
            } else if (x1 == x0 - i && y1 == y0 + i) {
                return false;
            } else {
                if (x1 > x0 && y1 > y0) {
                    if (jogo.getTabuleiro().retornoQuadrado(x0 + i, y0 + i).isOcupado()) {
                        return true;
                    }
                }
                if (x1 < x0 && y1 < y0) {
                    if (jogo.getTabuleiro().retornoQuadrado(x0 - i, y0 - i).isOcupado()) {
                        return true;
                    }
                }

                if (x1 > x0 && y1 < y0) {
                    if (jogo.getTabuleiro().retornoQuadrado(x0 + i, y0 - i).isOcupado()) {
                        return true;
                    }
                }

                if (x1 < x0 && y1 > y0) {
                    if (jogo.getTabuleiro().retornoQuadrado(x0 - i, y0 + i).isOcupado()) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    @Override
    boolean movesPermitidos(int x0, int y0, int x1, int y1, Jogo jogo) {

        if (passaPorPeca(x0, y0, x1, y1, jogo)) {
            return false;
        }

        if (x0 == x1 && y0 == y1) {
            return false;
        }
        for (int i = 1; i <= 3; i++) {
            if ((x1 == x0 + i && y1 == y0 + i) || (x1 == x0 - i && y1 == y0 - i) || (x1 == x0 + i && y1 == y0 - i) || (x1 == x0 - i && y1 == y0 + i)) {
                if (!pertenceAequipa(jogo, x1, y1)) {
                    return true;
                }

            }
        }
        return false;
    }


    boolean move(int x0, int y0, int x1, int y1, Jogo jogo) {
        Peca pecaQueMove = jogo.getTabuleiro().retornoQuadrado(x0, y0).getPeca();
        Square quadradoOrigem = jogo.getTabuleiro().retornoQuadrado(x0, y0);
        Square quadradoDestino = jogo.getTabuleiro().retornoQuadrado(x1, y1);

        if (movesPermitidos(x0, y0, x1, y1, jogo)) {
            if (quadradoDestino.isOcupado()) {
                quadradoDestino.getPeca().setNaoCapturado(false);
                quadradoDestino.getPeca().setCoordenadas(null);
                pecaQueMove.setCoordenadas(quadradoDestino);
                quadradoDestino.setPeca(pecaQueMove);

                quadradoDestino.setOcupado(true);
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

    public String nomeDoTipo(Jogo jogo) {
        return "Padre da Vila";
    }

}
