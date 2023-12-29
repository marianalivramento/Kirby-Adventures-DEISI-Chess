package pt.ulusofona.lp2.deisichess;

<<<<<<< HEAD
public class Kirby extends Peca{
    int valor = 10;

    public int getValor() {
        return valor;
    }

    boolean passaPorPeca(int x0, int y0, int x1, int y1, Jogo jogo) {
        return false;
    }

    boolean movesPermitidos(int x0, int y0, int x1, int y1, Jogo jogo) {
        return true;
=======
public class Kirby extends Peca {

    private Peca pecaComida;
    int valor = 20;

    @Override
    int getValor() {
        return valor;
    }

    @Override
    boolean movesPermitidos(int x0, int y0, int x1, int y1, Jogo jogo) {
        return pecaComida.movesPermitidos(x0, y0, x1, y1, jogo);
>>>>>>> origin/main
    }

    boolean move(int x0, int y0, int x1, int y1, Jogo jogo) {
        Peca pecaQueMove = jogo.getTabuleiro().retornoQuadrado(x0, y0).getPeca();
        Square quadradoOrigem = jogo.getTabuleiro().retornoQuadrado(x0, y0);
        Square quadradoDestino = jogo.getTabuleiro().retornoQuadrado(x1, y1);

        if (movesPermitidos(x0, y0, x1, y1, jogo)) {
            if (quadradoDestino.isOcupado()) {

                quadradoDestino.getPeca().setNaoCapturado(false);
<<<<<<< HEAD
=======
                pecaComida = quadradoDestino.getPeca();
>>>>>>> origin/main
                quadradoDestino.getPeca().setCoordenadas(null);
                pecaQueMove.setCoordenadas(quadradoDestino);
                quadradoDestino.setPeca(pecaQueMove);

                quadradoDestino.setOcupado(true);
                jogo.getClassEquipaAtual().aumentarPecasCapturadas();

                quadradoOrigem.resetQuadrado();
                return true;

<<<<<<< HEAD

            } else {
                pecaQueMove.setCoordenadas(quadradoDestino);
                quadradoDestino.setPeca(pecaQueMove);

                quadradoDestino.setOcupado(true);
                quadradoOrigem.resetQuadrado();
                return true;
            }

=======
            } else {
                pecaQueMove.setCoordenadas(quadradoDestino);
                quadradoDestino.setPeca(pecaQueMove);
                quadradoDestino.setOcupado(true);
                quadradoOrigem.resetQuadrado();

                return true;
            }
>>>>>>> origin/main
        }
        return false;
    }

<<<<<<< HEAD
    public String nomeDoTipo(Jogo jogo) {
        return "Kirby";
    }

=======
    @Override
    String nomeDoTipo(Jogo jogo) {
        return "Kirby/" + pecaComida;
    }
>>>>>>> origin/main
}
