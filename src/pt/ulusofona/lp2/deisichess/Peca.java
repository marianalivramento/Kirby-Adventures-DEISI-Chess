package pt.ulusofona.lp2.deisichess;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

abstract class Peca {

    int id;
    int tipo;
    int valor = 0;
    Equipa equipa;
    String alcunha;
    Square coordenadas;
    boolean naoCapturado = true;
    int numeroJogadas = 0;
    int pontos = 0;
    int numeroDeCapturas = 0;
    int numeroDeMovimentosInvalidos = 0;
    int numeroDeMovimentosValidos = 0;

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

    public int getPontos() {
        return pontos;
    }

    public int getNumeroDeCapturas() {
        return numeroDeCapturas;
    }

    public void setNumeroDeCapturas(int numeroDeCapturas) {
        this.numeroDeCapturas = numeroDeCapturas;
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

    void jogadasPermitidas(Jogo jogo, List<Comparable> hints) {
        List<Comparable<Peca>> permittedMoves = new ArrayList<>();
        List<Peca> pecas = new ArrayList<>();
        Tabuleiro tabuleiro = jogo.getTabuleiro();

        for (Square s : tabuleiro.getQuadrados()) {
            if (movesPermitidos(coordenadas.getCoordenadaX(), coordenadas.getCoordenadaY(), s.getCoordenadaX(), s.getCoordenadaY(), jogo)) {
                if (!passaPorPeca(coordenadas.getCoordenadaX(), coordenadas.getCoordenadaY(), s.getCoordenadaX(), s.getCoordenadaY(), jogo)) {

                    //hints.add(s.getPeca());

                }
            }
        }
        /*
        Collections.sort(pecas);
        for (Peca p: pecas){
            permittedMoves.add(p);
            hints.add(p);
        }


         */
        /*
        for (Peca p : pecas) {
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

         */
        // return permittedMoves;
    }

    abstract String nomeDoTipo(Jogo jogo);

    public int getNumeroDeMovimentosInvalidos() {
        return numeroDeMovimentosInvalidos;
    }

    public void setNumeroDeMovimentosInvalidos(int numeroDeMovimentosInvalidos) {
        this.numeroDeMovimentosInvalidos = numeroDeMovimentosInvalidos;
    }

    public int getNumeroDeMovimentosValidos() {
        return numeroDeMovimentosValidos;
    }

    public void setNumeroDeMovimentosValidos(int numeroDeMovimentosValidos) {
        this.numeroDeMovimentosValidos = numeroDeMovimentosValidos;
    }

    public void diminuiNumeroDeCapturas() {
        numeroDeCapturas--;
    }

    public void aumentaNumeroDeCapturas() {
        numeroDeCapturas++;
    }

    public void diminuiPontos(int pontos) {
        this.pontos -= pontos;
    }

    public void aumentaPontos(int pontos) {
        this.pontos += pontos;
    }

    public void aumentaNumeroDeMovimentosInvalidos() {
        numeroDeMovimentosInvalidos++;
    }

    public void aumentaNumeroDeMovimentosValidos() {
        numeroDeMovimentosValidos++;
    }
}




