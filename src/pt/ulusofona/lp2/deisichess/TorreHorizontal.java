package pt.ulusofona.lp2.deisichess;

import java.util.ArrayList;
import java.util.List;

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

    //As torres estão trocadas?
    boolean move(int x0, int y0, int x1, int y1, Jogo jogo) {
        Peca pecaQueMove = jogo.getTabuleiro().retornoQuadrado(x0, y0).getPeca();
        Square quadradoOrigem = jogo.getTabuleiro().retornoQuadrado(x0, y0);
        Square quadradoDestino = jogo.getTabuleiro().retornoQuadrado(x1, y1);

        if (x0 == x1 && y0 == y1) {
            return false;
        }
        if (y0 == y1) {
            if (!passaPorPeca(x0, y0, x1, y1, jogo)) {
                if (!pertenceAequipa(jogo,x1,y1)) {
                    pecaQueMove.setCoordenadas(quadradoDestino);
                    quadradoDestino.setPeca(pecaQueMove);
                    quadradoDestino.setOcupado(true);
                    quadradoOrigem.resetQuadrado();
                    numeroJogadas++;
                    return true;
                }
            }
        }
        return false;
    }

    List<Comparable> jogadasPermitidas(Jogo jogo) {
        List<Comparable> permittedMoves = new ArrayList<>();
        Tabuleiro tabuleiro = jogo.getTabuleiro();

        for (Square s : tabuleiro.getQuadrados()) {
            if (move(coordenadas.getCoordenadaX(), coordenadas.getCoordenadaY(), s.getCoordenadaX(), s.getCoordenadaY(), jogo)) {
                if (s.getPeca() == null) {
                    permittedMoves.add("(" + s.getCoordenadaX() + ", " + s.getCoordenadaY() + ")->0");
                } else {
                    permittedMoves.add("(" + s.getCoordenadaX() + ", " + s.getCoordenadaY() + ")->" + s.getPeca().getValor());
                }
            }
        }
        return permittedMoves;
    }
}
