package pt.ulusofona.lp2.deisichess;

public class Rainha extends Peca {
    int valor = 8;

    public int getValor() {
        return valor;
    }

    boolean passaPorPeca(int x0, int y0, int x1, int y1, Jogo jogo) {
        int minX = Math.min(x0, x1);
        int maxX = Math.max(x0, x1);
        int minY = Math.min(y0, y1);
        int maxY = Math.max(y0, y1);

        for (int x = minX + 1; x < maxX; x++) {
            for (int y = minY + 1; y < maxY; y++) {
                Square square = jogo.getTabuleiro().retornoQuadrado(x, y);
                if (square != null && square.isOcupado()) {
                    return true;
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
        if (!pertenceAequipa(jogo, x1, y1)) {
            for (int i = 1; i <= 5; i++) {
                if ((x1 == x0 + i || x1 == x0 - i) && y1 == y0) { //direita e esquerda
                    return true;
                } else if (x1 == x0 && (y1 == y0 + i || y1 == y0 - i)) { //frente e trás
                    return true;
                } else if ((y1 == y0 + i || y1 == y0 - i) && (x1 == x0 + i || x1 == x0 - i)) {
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
        return "Rainha";
    }
}


