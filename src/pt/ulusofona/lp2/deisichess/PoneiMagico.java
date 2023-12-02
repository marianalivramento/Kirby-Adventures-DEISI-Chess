package pt.ulusofona.lp2.deisichess;

import java.util.ArrayList;
import java.util.List;

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

    boolean move(int x0, int y0, int x1, int y1, Jogo jogo) {
        Peca pecaQueMove = jogo.getTabuleiro().retornoQuadrado(x0, y0).getPeca();
        Square quadradoOrigem = jogo.getTabuleiro().retornoQuadrado(x0, y0);
        Square quadradoDestino = jogo.getTabuleiro().retornoQuadrado(x1, y1);

        if ((x1 == x0 + 2) && ((y1 == y0 + 2) || (y1 == y0 - 2))) {
            if (quadradoDestino.isOcupado()) {
                if (!passaPorPeca(x0, y0, x1, y1, jogo)) {
                    if (!pertenceAequipa(jogo, x1, y1)) {
                        pecaQueMove.setCoordenadas(quadradoDestino);
                        quadradoDestino.setPeca(pecaQueMove);
                        quadradoDestino.setOcupado(true);
                        quadradoOrigem.resetQuadrado();
                        equipa.aumentarJogadasValidas();
                        equipa.numeroDoTurno++;
                        return true;
                    }
                }
            } else {
                if (!passaPorPeca(x0, y0, x1, y1, jogo)) {

                    pecaQueMove.setCoordenadas(quadradoDestino);
                    quadradoDestino.setPeca(pecaQueMove);
                    quadradoOrigem.resetQuadrado();
                    equipa.aumentarJogadasValidas();
                    equipa.numeroDoTurno++;
                    return true;

                }
            }

        } else if ((x1 == x0 - 2) && ((y1 == y0 + 2) || (y1 == y0 - 2))) {
            if (quadradoDestino.isOcupado()) {
                if (!passaPorPeca(x0, y0, x1, y1, jogo)) {
                    if (!pertenceAequipa(jogo, x1, y1)) {
                        pecaQueMove.setCoordenadas(quadradoDestino);
                        quadradoDestino.setPeca(pecaQueMove);
                        quadradoDestino.setOcupado(true);
                        quadradoOrigem.resetQuadrado();
                        equipa.aumentarJogadasValidas();
                        equipa.numeroDoTurno++;
                        return true;
                    }
                }
            } else {
                if (!passaPorPeca(x0, y0, x1, y1, jogo)) {

                    pecaQueMove.setCoordenadas(quadradoDestino);
                    quadradoDestino.setPeca(pecaQueMove);
                    quadradoOrigem.resetQuadrado();
                    equipa.aumentarJogadasValidas();
                    equipa.numeroDoTurno++;
                    return true;

                }
            }
        }
        equipa.numeroDoTurno++;
        equipa.aumentarTenativasInvalidas();
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

