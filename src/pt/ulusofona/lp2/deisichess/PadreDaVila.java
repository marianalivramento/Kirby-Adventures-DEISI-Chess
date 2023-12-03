package pt.ulusofona.lp2.deisichess;

public class PadreDaVila extends Peca {
    int valor = 3;

    public int getValor() {
        return valor;
    }

    @Override
    boolean movesPermitidos(int x0, int y0, int x1, int y1, Jogo jogo) {
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

    boolean passaPorPeca(int x0, int y0, int x1, int y1, Jogo jogo) {
        for (Square s : jogo.getTabuleiro().getQuadrados()) {
            if (s.getCoordenadaX() != x1 && s.getCoordenadaY() != y1) {
                // compara x1 com x0 e se x1 for maior devolve 1 se n devolve -1 same thing for y
                int sinalX = Integer.compare(x1, x0);
                int sinalY = Integer.compare(y1, y0);

                for (int i = 1; i <= 2; i++) {
                    //com o -1 ou 1 de antes vemos se vamos adicionar ou subtruir em x e y
                    int possivelX = x0 + (i * sinalX);
                    int possivelY = y0 + (i * sinalY);

                    if (s.getCoordenadaX() == possivelX && s.getCoordenadaY() == possivelY && s.isOcupado()) {
                        return true;
                    }
                }
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
            if (!passaPorPeca(x0, y0, x1, y1, jogo)) {
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
        }


        return false;
    }

}
