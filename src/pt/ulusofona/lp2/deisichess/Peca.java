package pt.ulusofona.lp2.deisichess;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

abstract class Peca implements Comparable<Peca> {

    int id;
    int tipo;
    int valor;
    Equipa equipa;
    String alcunha;
    Square coordenadas;
    boolean naoCapturado = true;
    int numeroJogadas = 0;
    int pontos = 0;
    int numeroDeCapturas = 0;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    abstract int getValor();

    public Equipa getEquipa() {
        return equipa;
    }

    public void setEquipa(Equipa equipa) {
        this.equipa = equipa;
    }

    public String getAlcunha() {
        return alcunha;
    }

    public void setAlcunha(String alcunha) {
        this.alcunha = alcunha;
    }

    public Square getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(Square coordenadas) {
        this.coordenadas = coordenadas;
    }

    public boolean getNaoCapturado() {
        return naoCapturado;
    }

    public void setNaoCapturado(boolean naoCapturado) {
        this.naoCapturado = naoCapturado;
    }

    boolean pertenceAequipa(Jogo game, int x, int y) {
        Square s = game.getTabuleiro().retornoQuadrado(x, y);
        if (s.isOcupado()) {
            if (s.getPeca().getEquipa() != null && equipa != null) {
                if (s.getPeca().getEquipa().getPretoOuBranco() == equipa.getPretoOuBranco()) {
                    return true;
                }
            }
        }
        return false;
    }

    boolean passaPorPeca(int x0, int y0, int x1, int y1, Jogo jogo) {
        return false;
    }

    abstract boolean movesPermitidos(int x0, int y0, int x1, int y1, Jogo jogo);

    abstract boolean move(int x0, int y0, int x1, int y1, Jogo jogo);

    List<Comparable> jogadasPermitidas(Jogo jogo, List<Comparable> hints) {
        List<Peca> permittedMoves = new ArrayList<Peca>();
        Tabuleiro tabuleiro = jogo.getTabuleiro();

        for (Square s : tabuleiro.getQuadrados()) {
            if (movesPermitidos(coordenadas.getCoordenadaX(), coordenadas.getCoordenadaY(), s.getCoordenadaX(), s.getCoordenadaY(), jogo)) {
                if (!passaPorPeca(coordenadas.getCoordenadaX(), coordenadas.getCoordenadaY(), s.getCoordenadaX(), s.getCoordenadaY(), jogo)) {
                    if (s.isOcupado()) {
                        permittedMoves.add(s.getPeca());
                    }

                }
            }
        }
        Collections.sort(permittedMoves);
        for (Peca p : permittedMoves) {
            hints.add("(" + p.getCoordenadas().getCoordenadaX() + ", " + p.getCoordenadas().getCoordenadaY() + ")->" + p.getValor());
        }

        for (Square s : tabuleiro.getQuadrados()) {
            if (movesPermitidos(coordenadas.getCoordenadaX(), coordenadas.getCoordenadaY(), s.getCoordenadaX(), s.getCoordenadaY(), jogo)) {
                if (!passaPorPeca(coordenadas.getCoordenadaX(), coordenadas.getCoordenadaY(), s.getCoordenadaX(), s.getCoordenadaY(), jogo)) {
                    if (!s.isOcupado()) {
                        hints.add("(" + s.getCoordenadaX() + ", " + s.getCoordenadaY() + ")->0");
                    }

                }
            }
        }
        return hints;
    }

    @Override
    public int compareTo(Peca peca) {
        if (peca != null) {
            if (peca.getValor() < valor) {
                return 1;
            } else if (peca.getValor() == valor) {
                if (!peca.getClass().equals(Rainha.class) && tipo != 1) {
                    return 0;
                }
                return 0;
            }
            return -1;
        }
        return 2;
    }

}

