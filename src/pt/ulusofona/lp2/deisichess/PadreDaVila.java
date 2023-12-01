package pt.ulusofona.lp2.deisichess;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PadreDaVila extends Peca {
    int valor = 3;

    public int getValor() {
        return valor;
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

        if (x0 == x1 && y0 == y1) {
            return false;
        }
        for (int i = 1; i <= 3; i++) {
            if ((x1 == x0 + i && y1 == y0 + i) || (x1 == x0 - i && y1 == y0 - i) || (x1 == x0 + i && y1 == y0 - i) || (x1 == x0 - i && y1 == y0 + i)) {
                if (!passaPorPeca(x0, y0, x1, y1, jogo)) {
                    if (!pertenceAequipa(jogo, x1, y1)) {
                        pecaQueMove.setCoordenadas(quadradoDestino);
                        quadradoDestino.setPeca(pecaQueMove);
                        quadradoDestino.setOcupado(true);
                        quadradoOrigem.resetQuadrado();
                        return true;
                    }
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
        Collections.sort(permittedMoves, (move1, move2) -> {
            int valor1 = Integer.parseInt(move1.toString().split("->")[1]);
            int valor2 = Integer.parseInt(move2.toString().split("->")[1]);
            return Integer.compare(valor2, valor1);
        });
        return permittedMoves;
    }
}
