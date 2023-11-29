package pt.ulusofona.lp2.deisichess;

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

    public int getValor() {
        return valor;
    }

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

    boolean pertenceAequipa(Jogo game){
        for (Square s: game.getTabuleiro().getQuadrados()){
            if (s.isOcupado()){
                if(s.getPeca().getEquipa() == equipa){
                    return true;
                }
            }
        }
        return false;
    }

    abstract boolean move(int x0, int y0, int x1, int y1, Jogo jogo);

    abstract List<Comparable> jogadasPermitidas(Tabuleiro tabuleiro);


    @Override
    public int compareTo(Peca peca) {
        if (peca != null) {
            if (peca.getValor() < valor) {
                return 1;
            } else if (peca.getValor() == valor) {
                if (!peca.getClass().equals(Rainha.class)) {
                    return 0;
                }
            }
            return -1;
        }
        return 2;
    }

}

