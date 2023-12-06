package pt.ulusofona.lp2.deisichess;

public class TorreHorizontal extends Peca {
    int valor = 3;

    public int getValor() {
        return valor;
    }


    //verifico se quando faz o movimento se passa por cima de uma peca
    // as condicoes sao para n declara q passa por cima de outra peca quando o move é possivel
    boolean passaPorPeca(int x0, int y0, int x1, int y1, Jogo jogo) {

        int minX = Math.min(x0, x1);
        int maxX = Math.max(x0, x1);

        for (int x = minX + 1; x < maxX; x++) {
            Square square = jogo.getTabuleiro().retornoQuadrado(x, y0);
            if (square != null && square.isOcupado()) {
                return true;
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
        if (y0 == y1) {
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
}
